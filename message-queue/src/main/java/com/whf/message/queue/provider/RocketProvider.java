package com.whf.message.queue.provider;

import com.whf.message.queue.config.RocketMQConfig;
import com.whf.message.queue.model.User;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.producer.SendCallback;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Slf4j
@Component
public class RocketProvider {

    @Resource
    private RocketMQTemplate rocketMQTemplate;

    /**
     * 同步消息
     */
    public void sendMessage(User user) {
        SendResult sendResult = rocketMQTemplate.syncSend(RocketMQConfig.TOPIC + ":" + RocketMQConfig.SYNC_TAG, user);
        log.info("send sync message result: {}", sendResult);
    }

    /**
     * 异步消息
     */
    public void sendAsyncMessage(User user) {
        rocketMQTemplate.asyncSend(RocketMQConfig.TOPIC + ":" + RocketMQConfig.ASYNC_TAG, user, new SendCallback() {
            @Override
            public void onSuccess(SendResult sendResult) {
                log.info("send async message result: {}", sendResult);
            }

            @Override
            public void onException(Throwable e) {
                log.error("send async message error: {}", e);
            }
        });
    }

    /**
     * 单向消息
     */
    public void sendOneWayMessage(User user) {
        rocketMQTemplate.sendOneWay(RocketMQConfig.TOPIC + ":" + RocketMQConfig.ONEWAY_TAG, user);
    }

    /**
     * 顺序消息
     */
    public void sendOrderMessage(User user) {
        SendResult sendResult = rocketMQTemplate.syncSendOrderly(RocketMQConfig.TOPIC + ":" + RocketMQConfig.ORDER_TAG, user, String.valueOf(user.getId()));
        log.info("send order message result: {}", sendResult);
    }

    /**
     * 延时消息
     */

    public void sendDelayMessage(User user) {
        Message<User> message = MessageBuilder.withPayload(user).build();
        SendResult sendResult = rocketMQTemplate.syncSend(RocketMQConfig.TOPIC + ":" + RocketMQConfig.DELAY_TAG, message, 10000, 2);
        log.info("send delay message result: {}", sendResult);
    }

    /**
     * 事务消息
     */
    public void sendTransactionMessage(User user) {
        Message<User> message = MessageBuilder.withPayload(user).build();
        rocketMQTemplate.sendMessageInTransaction(RocketMQConfig.TRANSACTION_TOPIC, message, null);
    }
}
