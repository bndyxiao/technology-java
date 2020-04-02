package com.technology.controller;

import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.technology.common.jwt.UserLoginToken;
import com.technology.exception.CommonException;
import com.technology.exception.ExceptionEnum;
import com.technology.pojo.User;
import com.technology.service.UserService;
import com.technology.util.jwt.JwtUtil;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageBuilder;
import org.springframework.amqp.core.MessageDeliveryMode;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.AbstractJavaTypeMapper;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * @author: huangzhb
 * @Date: 2019年01月16日 11:03:40
 * @Description:
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private RabbitTemplate rabbitTemplate;
    @Autowired
    private Environment env;


    @GetMapping("/login")
    public ResponseEntity<JSONObject> login() {

        /*User user = userService.findUserByUsername(u.getUsername());

        if (user == null) {
            throw new CommonException(ExceptionEnum.BAD_REQUEST);
        } else {

            if (!user.getPassword().equals(u.getPassword())) {
                throw new CommonException(ExceptionEnum.BAD_REQUEST);
            } else {
                String token = JwtUtil.getToken(user);
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("token", token);
                return ResponseEntity.ok(jsonObject);
            }

        }*/

        rabbitTemplate.setMessageConverter(new Jackson2JsonMessageConverter());
        rabbitTemplate.setExchange(env.getProperty("log.user.exchange.name"));
        rabbitTemplate.setRoutingKey(env.getProperty("log.user.routing.key.name"));

        JSONObject userLog = new JSONObject();
        userLog.put("id", 1);
        userLog.put("name", "zhagnsan");
        try {
            Message message = MessageBuilder.withBody(objectMapper.writeValueAsBytes(userLog)).setDeliveryMode(MessageDeliveryMode.PERSISTENT).build();
            message.getMessageProperties().setHeader(AbstractJavaTypeMapper.DEFAULT_CONTENT_CLASSID_FIELD_NAME, MessageProperties.CONTENT_TYPE_JSON);
            rabbitTemplate.convertAndSend(message);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        return ResponseEntity.ok(new JSONObject());
    }


    @UserLoginToken
    @GetMapping("getMessage")
    public String getMessage() {
        return "您已通过验证";
    }

}
