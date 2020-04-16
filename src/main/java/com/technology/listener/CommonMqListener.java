package com.technology.listener;

import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

/**
 * @Description:
 * @Author: huangzhb
 * @Date: 2020/4/2 15:59
 * @Version: 1.0
 * @Copyright: (C) Copyright 2020-2034, 蓝海(福建)信息科技有限公司
 */
//@Component
@Slf4j
public class CommonMqListener {

    @Autowired
    private ObjectMapper objectMapper;

    @RabbitListener(queues = "${log.user.queue.name}", containerFactory = "singleListenerContainer")
    public void consumeUserLogQueue(@Payload byte[] message) {

        try {
            JSONObject userLog = objectMapper.readValue(message, JSONObject.class);
            log.info("消费用户日志:{}", userLog);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @RabbitListener(queues = "${mail.queue.name}", containerFactory = "singleListenerContainer")
    public void consumeMailQueue(Message message) {

        try {
            log.info("监听消费邮件发送:" + new String(message.getBody(), "UTF-8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

    }
}
