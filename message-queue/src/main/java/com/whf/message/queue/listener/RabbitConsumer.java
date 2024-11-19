package com.whf.message.queue.listener;

import com.rabbitmq.client.Channel;
import com.whf.message.queue.config.RabbitMQConfig;
import com.whf.message.queue.model.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.io.IOException;


/**
 * rabbit mq 消费者
 *
 * @author whf
 * @date 2024/11/15
 */
@Slf4j
@Component
public class RabbitConsumer {

    /**
     * basicAck：这个是手动确认消息已经成功消费，该方法有两个参数：
     * 第一个参数表示消息的 id；
     * 第二个参数 multiple 如果为 false，表示仅确认当前消息消费成功，如果为 true，则表示当前消息之前所有未被当前消费者确认的消息都消费成功。
     * <p>
     * basicNack：这个是告诉 RabbitMQ 当前消息未被成功消费，该方法有三个参数：
     * 第一个参数表示消息的 id；
     * 第二个参数 multiple 如果为 false，表示仅拒绝当前消息的消费，如果为 true，则表示拒绝当前消息之前所有未被当前消费者确认的消息；
     * 第三个参数 requeue 含义和前面所说的一样，被拒绝的消息是否重新入队。
     */

    @RabbitListener(queues = {RabbitMQConfig.DIRECT_QUEUE_NAME,
            RabbitMQConfig.FANOUT_QUEUE_NAME,
            RabbitMQConfig.FANOUT2_QUEUE_NAME,
            RabbitMQConfig.TOPIC_QUEUE_NAME})
    public void receiveDirectMessage(User user, Message message, Channel channel) throws IOException {
        // 消息的唯一标识符
        long deliveryTag = message.getMessageProperties().getDeliveryTag();
        try {
            // 消息处理逻辑
            log.info("received exchange: {}", message.getMessageProperties().getReceivedExchange());
            log.info("received queue: {}", message.getMessageProperties().getConsumerQueue());
            log.info("received message: {}", user);
            // 消息处理成功，确认消息
            channel.basicAck(deliveryTag, false);
        } catch (Exception e) {
            // 消息处理失败，拒绝消息，并重新放入队列
            channel.basicNack(deliveryTag, false, true);
        }
    }
}
