package com.boot.demo.config.mq;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;


@Getter
@Setter
@NoArgsConstructor
@ConfigurationProperties
public class RabbitMQProperties {
    @Value("${spring.rabbitmq.queue.notify}")
    private String notifyQueueName;
    @Value("${spring.rabbitmq.queue.async}")
    private String asyncQueueName;
    @Value("${spring.rabbitmq.exchange.direct.ucredit}")
    private String ucreditDirectExchangeeName;

}