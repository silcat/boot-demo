package com.boot.demo.listener;

import com.boot.demo.model.mq.MQNotify;
import com.boot.demo.config.common.TraceIdUtil;
import com.boot.demo.utils.JsonUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;



@Slf4j
@Component
public class RabbitMQListener {

    /**
     * 自动跳转流程控制（内部流程节点逻辑入口）
     * @param mqNotify
     */
//    @RabbitListener(queues = "${spring.rabbitmq.queue.notify}" )
    public void notifyLoanflow(MQNotify mqNotify) {
        TraceIdUtil.setTraceId(mqNotify.getTraceId());
        log.info("接收流程MQ通知: {}", JsonUtils.toJson(mqNotify));
        TraceIdUtil.remove();
    }

}
