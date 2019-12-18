package com.boot.demo.config.mq;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author sunlichuan
 * Created by sunlichuan on 18-6-6
 */
@Configuration
@EnableConfigurationProperties(RabbitMQProperties.class)
public class RabbitMQConfig {

    @Autowired
    private RabbitMQProperties rabbitMQProperties;

    @Bean
    public MessageConverter messageConverter() {
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    public DirectExchange directExchange() {
        return new DirectExchange(rabbitMQProperties.getUcreditDirectExchangeeName());
    }
    @Bean
    public Queue notifyQueue() {
        // 是否持久化
        boolean durable = true;
        // 仅创建者可以使用的私有队列，断开后自动删除
        boolean exclusive = false;
        // 当所有消费客户端连接断开后，是否自动删除队列
        boolean autoDelete = false;
        return new Queue(rabbitMQProperties.getNotifyQueueName(), durable, exclusive, autoDelete);
    }

    @Bean
    public Binding bindingNotifyQueue() {
        return BindingBuilder.bind(notifyQueue())
                .to(directExchange())
                .with(rabbitMQProperties.getNotifyQueueName());
    }


}
