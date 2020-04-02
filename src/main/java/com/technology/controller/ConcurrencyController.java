package com.technology.controller;

import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageBuilder;
import org.springframework.amqp.core.MessageDeliveryMode;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.UnsupportedEncodingException;

/**
 * @Description:
 * @Author: huangzhb
 * @Date: 2020/4/2 16:59
 * @Version: 1.0
 * @Copyright: (C) Copyright 2020-2034, 蓝海(福建)信息科技有限公司
 */
@RestController
@Slf4j
public class ConcurrencyController {

    @Autowired
    private RabbitTemplate rabbitTemplate;
    @Autowired
    private Environment env;

    @RequestMapping("/robbing")
    public JSONObject robbingThread() {
        rabbitTemplate.setExchange(env.getProperty("user.order.exchange.name"));
        rabbitTemplate.setRoutingKey(env.getProperty("user.order.routing.key.name"));
        try {
            Message message = MessageBuilder.withBody("1".getBytes("UTF-8")).setDeliveryMode(MessageDeliveryMode.PERSISTENT).build();
            rabbitTemplate.send(message);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return new JSONObject();
    }
}
