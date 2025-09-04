package com.whf.dynamic.rabbitmq.config;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.amqp.core.MessageListener;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.context.annotation.Configuration;

import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicReference;

@Slf4j
@Configuration
public class RabbitmqConfig {


    private static final Map<String, CachingConnectionFactory> connectionFactoryMap = new ConcurrentHashMap<>();

    private static final Map<String, AtomicReference<String>> classReferenceMap = new ConcurrentHashMap<>();

    /**
     * 创建连接并监听
     */
    public void createAndLister(String queueName, MqConfig mqConfig, MessageListener messageListener) {
        try {
            boolean network = RabbitmqFactory.isHostConnectable(mqConfig.getHost(), mqConfig.getPort());
            if (!network) {
                classReferenceMap.remove(queueName);
                log.warn("ThirdMqConfig createAndListener queueName={} host={} port={} network not connect",
                        queueName, mqConfig.getHost(), mqConfig.getPort());
                return;
            }
            AtomicReference<String> classReference = classReferenceMap.get(queueName);
            if (!Objects.isNull(classReference) && StringUtils.isNotEmpty(classReference.get())) {
                log.info("ThirdMqConfig createAndListener queueName={} host={} port={} has binding,ignore it",
                        queueName, mqConfig.getHost(), mqConfig.getPort());
                return;
            }
            log.warn("ThirdMqConfig createAndListener queueName={} host={} port={}",
                    queueName, mqConfig.getHost(), mqConfig.getPort());
            // 创建连接
            CachingConnectionFactory connectionFactory = RabbitmqFactory.createConnectionFactory(queueName,
                    mqConfig.getHost(), mqConfig.getPort(), mqConfig.getUsername(), mqConfig.getPassword());
            connectionFactoryMap.put(queueName, connectionFactory);
            // 绑定队列
            String buildQueueName = RabbitmqFactory.bindBroadCast(connectionFactory, queueName);
            // 开启监听
            RabbitmqFactory.startListener(connectionFactory, messageListener, buildQueueName);
            classReferenceMap.put(queueName, new AtomicReference<>(queueName));
        } catch (Exception e) {
            log.error("ThirdMqConfig createAndListener error={}", e);
        }
    }

    /**
     * 停止监听
     */
    public void stopListener(String queueName) {
        try {
            CachingConnectionFactory connectionFactory = connectionFactoryMap.get(queueName);
            if (Objects.isNull(connectionFactory)) {
                return;
            }
            RabbitmqFactory.stopListener(connectionFactory);
            connectionFactoryMap.remove(queueName);
            classReferenceMap.remove(queueName);
        } catch (Exception e) {
            log.error("ThirdMqConfig stopListener error={}", e);
        }
    }
}
