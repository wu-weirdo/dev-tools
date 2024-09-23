package com.whf.dynamic.rabbitmq.config;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;

import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 动态链接多个rabbitmq
 */
public class RabbitmqFactory {

    // 链接工厂
    private static final Map<String, CachingConnectionFactory> connectionFactoryMap = new ConcurrentHashMap<>();

    // 监听器容器工厂
    private static final Map<ConnectionFactory, SimpleRabbitListenerContainerFactory> listenerContainerFactoryMap = new ConcurrentHashMap<>();

    // 监听器容器
    private static final Map<SimpleRabbitListenerContainerFactory, SimpleMessageListenerContainer> listernerContainerMap = new ConcurrentHashMap<>();

    /**
     * 获取链接工厂
     */
    private static CachingConnectionFactory getConnectionFactory(String host, Integer port, String username, String password) {
        CachingConnectionFactory connectionFactory = new CachingConnectionFactory();
        connectionFactory.setHost(host);
        connectionFactory.setPort(port);
        connectionFactory.setUsername(username);
        connectionFactory.setPassword(password);
        connectionFactory.setVirtualHost("/");
        return connectionFactory;
    }

    /**
     * 创建链接工厂
     */
    public static CachingConnectionFactory createConnectionFactory(String queueName,String host, Integer port, String username, String password) {
        //创建链接工厂唯一key
        String key = queueName.concat("_").concat(host).concat("_").concat(String.valueOf(port)).concat("_").concat(username).concat("_").concat(password);
        //判断该链接工厂是否已经创建 不存在则创建并缓存
        CachingConnectionFactory factory = connectionFactoryMap.get(key);
        if (factory == null) {
            factory = getConnectionFactory(host, port, username, password);
            connectionFactoryMap.put(key, factory);
        }
        return factory;
    }


    /**
     * 创建监听器容器工厂
     */
    private static SimpleRabbitListenerContainerFactory getListenerContainerFactory(ConnectionFactory connectionFactory) {
        SimpleRabbitListenerContainerFactory containerFactory = listenerContainerFactoryMap.get(connectionFactory);
        if (containerFactory != null) {
            containerFactory = new SimpleRabbitListenerContainerFactory();
            containerFactory.setConnectionFactory(connectionFactory);
            containerFactory.setDefaultRequeueRejected(false);
            containerFactory.setAcknowledgeMode(AcknowledgeMode.AUTO);
            listenerContainerFactoryMap.put(connectionFactory, containerFactory);
        }
        return containerFactory;
    }

    /**
     * 开启监听
     */
    public static void startListener(ConnectionFactory connectionFactory, MessageListener messageListener, String... queueNames) {
        //获取监听器容器工厂
        SimpleRabbitListenerContainerFactory containerFactory = getListenerContainerFactory(connectionFactory);
        //创建监听器容器
        SimpleMessageListenerContainer listenerContainer = containerFactory.createListenerContainer();
        //缓存监听器容器
        listernerContainerMap.put(containerFactory, listenerContainer);
        listenerContainer.setQueueNames(queueNames);
        listenerContainer.setMessageListener(messageListener);
        listenerContainer.start();
    }

    /**
     * 关闭监听
     */
    public static void stopListener(ConnectionFactory connectionFactory) {
        SimpleRabbitListenerContainerFactory containerFactory = getListenerContainerFactory(connectionFactory);
        SimpleMessageListenerContainer listenerContainer = listernerContainerMap.get(containerFactory);
        if (listenerContainer != null) {
            listenerContainer.stop();
        }
    }

    public static Boolean isHostConnectable(String host, Integer port) {
        Socket socket = new Socket();
        try {
            socket.connect(new InetSocketAddress(host, port));
            return true;
        } catch (Exception e) {
            return false;
        } finally {
            try {
                socket.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 创建广播队列
     * @param connectionFactory
     * @param className
     * @return
     */
    public static String bindBroadCast(CachingConnectionFactory connectionFactory, String className) {
        String queueName = className + "_"; //获取服务器ip地址拼接
        RabbitAdmin rabbitAdmin = new RabbitAdmin(connectionFactory);
        rabbitAdmin.setAutoStartup(true);
        //创建队列
        Queue queue = new Queue(queueName, true, false, true);
        rabbitAdmin.declareQueue(queue);
        //创建交换机
        Exchange exchange = ExchangeBuilder.topicExchange(queueName).durable(true).autoDelete().build();
        rabbitAdmin.declareExchange(exchange);
        //绑定队列到交换机
        rabbitAdmin.declareBinding(BindingBuilder.bind(queue).to(exchange).with("").noargs());
        return queueName;
    }
}
