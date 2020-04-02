package com.technology.listener;

import com.rabbitmq.client.Channel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.core.ChannelAwareMessageListener;
import org.springframework.stereotype.Component;

/**
 * @Description:
 * @Author: huangzhb
 * @Date: 2020/4/2 16:45
 * @Version: 1.0
 * @Copyright: (C) Copyright 2020-2034, 蓝海(福建)信息科技有限公司
 */
@Component("userOrderListener")
@Slf4j
public class UserOrderListener implements ChannelAwareMessageListener {
    @Override
    public void onMessage(Message message, Channel channel) throws Exception {


        long tag = message.getMessageProperties().getDeliveryTag();
        try { // 监听消费处理逻辑
            byte[] body = message.getBody();
            String mobile = new String(body, "UTF-8");
            log.info("监听到抢单手机号:{}", mobile);
            channel.basicAck(tag, true); // 确认消费
        } catch (Exception e) {
            log.error("用户商城抢单发生异常:",e.fillInStackTrace());
            channel.basicReject(tag, false); // 确认消费
        }
    }
}
