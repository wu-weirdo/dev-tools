package com.whf.message.queue.provider;

import com.whf.message.queue.config.RabbitMQConfig;
import com.whf.message.queue.model.User;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * rabbit mq 消息提供者
 *
 * @author whf
 * @date 2024/11/15
 */
@Component
public class RabbitProvider {

    @Resource
    private RabbitTemplate rabbitTemplate;

    /**
     * 发送直连消息
     */
    public void sendDirectMessage(User user) {
        rabbitTemplate.convertAndSend(RabbitMQConfig.DIRECT_EXCHANGE_NAME, RabbitMQConfig.DIRECT_ROUTING_KEY, user);
    }

    /**
     * 发送主题消息
     */
    public void sendTopicMessage(User user) {
        rabbitTemplate.convertAndSend(RabbitMQConfig.TOPIC_EXCHANGE_NAME, RabbitMQConfig.TOPIC_ROUTING_KEY, user);
    }

    /**
     * 发送广播消息
     */
    public void sendFanoutMessage(User user) {
        rabbitTemplate.convertAndSend(RabbitMQConfig.FANOUT_EXCHANGE_NAME, "", user);
    }

    /**
     * 发送延迟消息
     */
    public void sendDelayMessage(User user) {
        rabbitTemplate.convertAndSend(RabbitMQConfig.DELAY_EXCHANGE_NAME, RabbitMQConfig.DELAY_ROUTING_KEY, user);
    }
}
