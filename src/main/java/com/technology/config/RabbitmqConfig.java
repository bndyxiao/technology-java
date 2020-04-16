package com.technology.config;

import com.technology.listener.UserOrderListener;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.rabbit.support.CorrelationData;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.amqp.SimpleRabbitListenerContainerFactoryConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

/**
 * @Description:
 * @Author: huangzhb
 * @Date: 2020/4/2 15:04
 * @Version: 1.0
 * @Copyright: (C) Copyright 2020-2034, 蓝海(福建)信息科技有限公司
 */
@Slf4j
//@Configuration
public class RabbitmqConfig {

    @Autowired
    private Environment env;
    @Autowired
    private CachingConnectionFactory connectionFactory;
    @Autowired
    private SimpleRabbitListenerContainerFactoryConfigurer factoryConfigurer;

    /**
     * 单一消费者
     * @return
     */
    @Bean("singleListenerContainer")
    public SimpleRabbitListenerContainerFactory listenerContainer() {

        SimpleRabbitListenerContainerFactory factory = new SimpleRabbitListenerContainerFactory();
        factory.setConnectionFactory(connectionFactory);
        factory.setMessageConverter(new Jackson2JsonMessageConverter());
        factory.setConcurrentConsumers(1);
        factory.setMaxConcurrentConsumers(1);
        factory.setPrefetchCount(1);
        factory.setTxSize(1);
        factory.setAcknowledgeMode(AcknowledgeMode.AUTO);
        return factory;
    }

    /**
     * 多个消费者
     * @return
     */
    @Bean("multiListenerContainer")
    public SimpleRabbitListenerContainerFactory multiListenerContainer() {
        SimpleRabbitListenerContainerFactory factory = new SimpleRabbitListenerContainerFactory();
        factoryConfigurer.configure(factory, connectionFactory);
        factory.setMessageConverter(new Jackson2JsonMessageConverter());
        factory.setAcknowledgeMode(AcknowledgeMode.NONE);
        factory.setConcurrentConsumers(env.getProperty("spring.rabbitmq.listener.concurrency",int.class));
        factory.setMaxConcurrentConsumers(env.getProperty("spring.rabbitmq.listener.max-concurrency",int.class));
        factory.setPrefetchCount(env.getProperty("spring.rabbitmq.listener.prefetch",int.class));
        return factory;
    }

    @Bean
    public RabbitTemplate rabbitTemplate() {

        connectionFactory.setPublisherConfirms(true);
        connectionFactory.setPublisherReturns(true);
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMandatory(true);
        rabbitTemplate.setConfirmCallback(new RabbitTemplate.ConfirmCallback() {
            @Override
            public void confirm(CorrelationData correlationData, boolean ack, String cause) {
                log.info("消息发送成功:correlationData({}),ack({}),cause({})",correlationData,ack,cause);
            }
        });
        rabbitTemplate.setReturnCallback(new RabbitTemplate.ReturnCallback() {
            @Override
            public void returnedMessage(Message message, int replyCode, String replyText, String exchange, String routingKey) {
                log.info("消息丢失:exchange({}),route({}),replyCode({}),replyText({}),message:{}",exchange,routingKey,replyCode,replyText,message);
            }
        });
        return rabbitTemplate;
    }




    // 使用rabbitmq的directexchange+routingkey消息模型实现：用户登录成功记录日志
    /**
     * 实现方案:
     * ① 消息模型:DirectExchange + RoutingKey消息模型
     * ② 消息:用户登录的实体信息，包括用户名，登录时间，来源的IP，所属日志模块等信息
     * ③ 发送接收：在登录的Controller中实现发送，在某个listener中实现接收并监听消费到的消息入数据表；实时发送接收
     *
     * 创建消息模型，包括Queue、Exchange、RoutingKey等的建立
     */
    @Bean("logUserQueue")
    public Queue logUserQueue() {
        return new Queue(env.getProperty("log.user.queue.name"), true);
    }

    @Bean("logUserExchange")
    public DirectExchange logUserExchange() {
        return new DirectExchange(env.getProperty("log.user.exchange.name"), true, false);
    }

    @Bean
    public Binding logUserBinding() {
        return BindingBuilder.bind(logUserQueue()).to(logUserExchange()).with(env.getProperty("log.user.routing.key.name"));
    }

    // 异步发送邮件
    /**
     * 消息模型的创建
     */
    @Bean
    public Queue mailQueue() {
        return new Queue(env.getProperty("mail.queue.name"), true);
    }

    @Bean
    public DirectExchange mailExchange() {
        return new DirectExchange(env.getProperty("mail.exchange.name"), true, false);
    }

    @Bean
    public Binding mailBinding() {
        return BindingBuilder.bind(mailQueue()).to(mailExchange()).with(env.getProperty("mail.routing.key.name"));
    }


    // 并发量配置与消息确认机制
    /**
     * 对于消息模型中的listener而言，默认情况下是单消费实例的配置，即一个listener对应一个消费者,
     * 这种配置对于上面所讲的异步记录用户操作日志，异步发送邮件等并发量不高的场景下是适用的。但是对于
     * 秒杀系统、商城抢单等场景下可能会显得很吃力
     * 我们知道，秒杀系统跟商城抢单均有一个共同的明显特征，即在某个时刻会有成百上千万的请求到达我们的
     * 接口，即瞬间这股巨大的流量将涌入我们的系统，我们可以采用下面一图来大致提现这一现象
     *
     *
     * 当到了“开始秒杀”、“开始抢单”的时刻，此时系统可能会出现这样的几种现象：
     *
     * 应用系统配置承载不了这股瞬间流量，导致系统直接挂掉，即传说中的“宕机”现象；
     * 接口逻辑没有考虑并发情况，数据库读写锁发生冲突，导致最终处理结果跟理论上的结果数据不一致（如商品存库量只有 100，但是高并发情况下，实际表记录的抢到的用户记录数据量却远远大于 100）；
     * 应用占据服务器的资源直接飙高，如 CPU、内存、宽带等瞬间直接飙升，导致同库同表甚至可能同 host 的其他服务或者系统出现卡顿或者挂掉的现象；
     * 于是乎，我们需要寻找解决方案！对于目前来讲，网上均有诸多比较不错的解决方案，在此我顺便提一下我们的应用系统采用的常用解决方案，包括：
     *
     * 我们会将处理抢单的整体业务逻辑独立、服务化并做集群部署；
     * 我们会将那股巨大的流量拒在系统的上层，即将其转移至 MQ 而不直接涌入我们的接口，从而减少数据库读写锁冲突的发生以及由于接口逻辑的复杂出现线程堵塞而导致应用占据服务器资源飙升；
     * 我们会将抢单业务所在系统的其他同数据源甚至同表的业务拆分独立出去服务化，并基于某种 RPC 协议走 HTTP 通信进行数据交互、服务通信等等；
     * 采用分布式锁解决同一时间同个手机号、同一时间同个 IP 刷单的现象；
     *
     * 下面，我们用 RabbitMQ 来实战上述的第二点！即我们会在“请求” -> "处理抢单业务的接口" 中间架一层消息中间件做“缓冲”、“缓压”处理
     * 并发量配置与消息确认机制
     *
     * 正如上面所讲的，对于抢单、秒杀等高并发系统而言，如果我们需要用 RabbitMQ 在 “请求” - “接口” 之间充当限流缓压的角色，那便需要我们对 RabbitMQ 提出更高的要求，即支持高并发的配置，在这里我们需要明确一点，“并发消费者”的配置其实是针对 listener 而言，当配置成功后，我们可以在 MQ 的后端控制台应用看到 consumers 的数量
     * 其中，这个 listener 在这里有 10 个 consumer 实例的配置，每个 consumer 可以预监听消费拉取的消息数量为 5 个（如果同一时间处理不完，会将其缓存在 mq 的客户端等待处理！）
     *
     * 另外，对于某些消息而言，我们有时候需要严格的知道消息是否已经被 consumer 监听消费处理了，即我们有一种消息确认机制来保证我们的消息是否已经真正的被消费处理。在 RabbitMQ 中，消息确认处理机制有三种：Auto - 自动、Manual - 手动、None - 无需确认，而确认机制需要 listener 实现 ChannelAwareMessageListener 接口，并重写其中的确认消费逻辑。在这里我们将用 “手动确认” 的机制来实战用户商城抢单场景。
     *
     */
    //  RabbitMQConfig 中配置确认消费机制以及并发量的配置
    // 商城抢单实战
    @Bean("userOrderQueue")
    public Queue userOrderQueue() {
        return new Queue(env.getProperty("user.order.queue.name"), true);
    }

    @Bean("userOrderExchange")
    public TopicExchange userOrderExchange() {
        return new TopicExchange(env.getProperty("user.order.exchange.name"), true, false);
    }

    @Bean
    public Binding userOrderBinding() {
        return BindingBuilder.bind(userOrderQueue()).to(userOrderExchange()).with(env.getProperty("user.order.routing.key.name"));
    }

    @Autowired
    private UserOrderListener userOrderListener;

    @Bean
    public SimpleMessageListenerContainer listenerContainerUserOrder(@Qualifier("userOrderQueue")Queue userOrderQueue) {
        SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
        container.setConnectionFactory(connectionFactory);
        container.setMessageConverter(new Jackson2JsonMessageConverter());

        // 并发配置
        container.setConcurrentConsumers(env.getProperty("spring.rabbitmq.listener.simple.concurrency", Integer.class));
        container.setMaxConcurrentConsumers(env.getProperty("spring.rabbitmq.listener.simple.max-concurrency", Integer.class));
        container.setPrefetchCount(env.getProperty("spring.rabbitmq.listener.simple.prefetch", Integer.class));

        // 消息确认机制
        container.setQueues(userOrderQueue);
        container.setMessageListener(userOrderListener);
        // 手动确认
        container.setAcknowledgeMode(AcknowledgeMode.MANUAL);

        return container;
    }
}
