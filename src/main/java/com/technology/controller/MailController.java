package com.technology.controller;

import com.alibaba.fastjson.JSONObject;
import com.technology.mapper.UserMapper;
import com.technology.pojo.User;
import com.technology.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description:
 * @Author: huangzhb
 * @Date: 2020/4/2 16:19
 * @Version: 1.0
 * @Copyright: (C) Copyright 2020-2034, 蓝海(福建)信息科技有限公司
 */
@RestController
@Slf4j
public class MailController {

    /*@Autowired
    private RabbitTemplate rabbitTemplate;
    @Autowired
    private Environment env;*/

    @Autowired
    private UserService userService;

    @GetMapping("/send")
    public JSONObject sendMail() {

       /* rabbitTemplate.setExchange(env.getProperty("mail.exchange.name"));
        rabbitTemplate.setRoutingKey(env.getProperty("mail.routing.key.name"));
        rabbitTemplate.convertAndSend("发送给张三的邮件");
        log.info("邮件发送完毕");*/
        User user = userService.findUserById(1);

        user =  userService.slaveById(2);

        user = userService.defaultById(1);

        return new JSONObject();
    }

}
