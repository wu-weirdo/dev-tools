package com.whf.message.queue.listener;

import com.whf.message.queue.model.User;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RocketMQMessageListener(topic = "topic", consumerGroup = "rocketmq-consumer-group")
public class RocketConsumer implements RocketMQListener<User> {
    @Override
    public void onMessage(User message) {
      log.info("receive message: {}", message);
    }
}
