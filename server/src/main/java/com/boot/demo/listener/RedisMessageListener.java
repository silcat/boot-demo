package com.boot.demo.listener;

import com.boot.demo.service.ITestService;
import com.boot.demo.service.impl.TestImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.listener.PatternTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.data.redis.listener.adapter.MessageListenerAdapter;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;

@Configuration
public class RedisMessageListener {

    /**
     * redis消息监听器容器
     *
     * @param connectionFactory
     * @return
     */
    @Bean
    RedisMessageListenerContainer container(RedisConnectionFactory connectionFactory, MessageListenerAdapter listenerAdapter) {
        RedisMessageListenerContainer container = new RedisMessageListenerContainer();
        container.setConnectionFactory(connectionFactory);

        // 以下为修改默认的序列化方式，网上大多数消息发布订阅都是String类型,但是实际中数据类型肯定不止String类型
        Jackson2JsonRedisSerializer<Object> jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer<Object>(
                Object.class);
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
        jackson2JsonRedisSerializer.setObjectMapper(objectMapper);

        // 针对每一个消息处理可以设置不同的序列化方式
        listenerAdapter.setSerializer(jackson2JsonRedisSerializer);
        // 点赞主题并绑定消息订阅处理器
        container.addMessageListener(listenerAdapter, new PatternTopic("test.topc"));
        return container;
    }

    /**
     * 指定处理方法
     * @param receiver
     * @return
     */
    @Bean
    MessageListenerAdapter listenerAdapter(ITestService receiver) {
        MessageListenerAdapter messageListenerAdapter = new MessageListenerAdapter(receiver, "getByUid");
        return messageListenerAdapter;
    }



}