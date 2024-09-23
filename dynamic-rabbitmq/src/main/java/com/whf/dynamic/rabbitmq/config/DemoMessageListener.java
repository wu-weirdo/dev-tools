package com.whf.dynamic.rabbitmq.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageListener;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;

@Slf4j
@Component
public class DemoMessageListener implements MessageListener {


    @Override
    public void onMessage(Message message) {
        //获取消息内容
        String content = new String(message.getBody(), StandardCharsets.UTF_8);
        //处理消息
    }
}
