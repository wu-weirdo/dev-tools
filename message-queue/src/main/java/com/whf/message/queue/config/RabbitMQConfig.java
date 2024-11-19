package com.whf.message.queue.config;

import org.springframework.amqp.core.*;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @title: rabbitmq配置
 * @author: whf
 * @description:
 */
@Configuration
public class RabbitMQConfig {

    // 直连交换机
    public static final String DIRECT_EXCHANGE_NAME = "exchange.direct";

    public static final String DIRECT_QUEUE_NAME = "queue.direct";

    public static final String DIRECT_ROUTING_KEY = "routing.direct";

    // 主题交换机
    public static final String TOPIC_EXCHANGE_NAME = "exchange.topic";

    public static final String TOPIC_QUEUE_NAME = "queue.topic";

    public static final String TOPIC_ROUTING_KEY = "routing.topic";

    // 广播交换机
    public static final String FANOUT_EXCHANGE_NAME = "exchange.fanout";

    public static final String FANOUT_QUEUE_NAME = "queue.fanout";

    public static final String FANOUT2_QUEUE_NAME = "queue.fanout2";

    // 延时交换机
    public static final String DELAY_EXCHANGE_NAME = "exchange.delay";

    public static final String DELAY_QUEUE_NAME = "queue.delay";

    public static final String DELAY_ROUTING_KEY = "routing.delay";

    public static final int DELAY_TIME = 10000;


    @Bean
    public MessageConverter messageConverter() {
        //在容器中导入Json的消息转换器
        return new Jackson2JsonMessageConverter();
    }

    //==================================直连队列配置=======================================

    /**
     * 创建队列
     */
    @Bean
    public Queue directQueue() {
        /**
         String name,  队列名字
         boolean durable,  是否持久化
         boolean exclusive,  是否排他
         boolean autoDelete, 是否自动删除
         Map<String, Object> arguments 属性
         */
        return new Queue(DIRECT_QUEUE_NAME, true, false, false);
    }

    /**
     * 创建交换机
     */
    @Bean
    public DirectExchange directExchange() {
        /**
         String name,  交换机名字
         boolean durable,  是否持久化
         boolean autoDelete, 是否自动删除
         Map<String, Object> arguments 属性
         */
        return new DirectExchange(DIRECT_EXCHANGE_NAME, true, false);
    }

    /**
     * 绑定队列到交换机
     */
    @Bean
    public Binding bindDirectQueue() {
        /**
         * String destination, 目的地（队列名或者交换机名字）
         * DestinationType destinationType, 目的地类型（Queue、Exhcange）
         * String exchange, 交换机名称
         * String routingKey, 路由键
         * Map<String, Object> arguments 属性
         */
        return new Binding(DIRECT_QUEUE_NAME, Binding.DestinationType.QUEUE, DIRECT_EXCHANGE_NAME, DIRECT_ROUTING_KEY, null);
    }

    //==================================主题队列配置=======================================
    @Bean
    public Queue topicQueue() {
        return new Queue(TOPIC_QUEUE_NAME);
    }

    @Bean
    public TopicExchange topicExchange() {
        return new TopicExchange(TOPIC_EXCHANGE_NAME, true, false);
    }

    @Bean
    public Binding bindTopicQueue() {
        return BindingBuilder.bind(topicQueue()).to(topicExchange()).with(TOPIC_ROUTING_KEY);
    }

    //=================================广播队列配置=======================================
    // 广播模式会对绑定的所有队列发送消息 同一个队列里的消息仅被消费一次
    @Bean
    public Queue fanoutQueue() {
        return new Queue(FANOUT_QUEUE_NAME, true, false, false);
    }

    @Bean
    public Queue fanoutQueue2() {
        return new Queue(FANOUT2_QUEUE_NAME, true, false, false);
    }

    @Bean
    public FanoutExchange fanoutExchange() {
        return new FanoutExchange(FANOUT_EXCHANGE_NAME, true, false);
    }

    @Bean
    public Binding bindFanoutQueue() {
        return BindingBuilder.bind(fanoutQueue()).to(fanoutExchange());
    }

    @Bean
    public Binding bindFanoutQueue2() {
        return BindingBuilder.bind(fanoutQueue2()).to(fanoutExchange());
    }

    //==================================延时队列==========================================

    @Bean
    public DirectExchange delayExchange() {
        return new DirectExchange(DELAY_EXCHANGE_NAME, true, false);
    }

    @Bean
    public Queue delayQueue() {
        /**
         * String name,  队列名字
         * boolean durable,  是否持久化
         * boolean exclusive,  是否排他
         * boolean autoDelete, 是否自动删除
         * Map<String, Object> arguments 属性
         */
        /*HashMap<String, Object> arguments = new HashMap<>();
        //死信交换机
        arguments.put("x-dead-letter-exchange", DELAY_EXCHANGE_NAME);
        //死信路由键
        arguments.put("x-dead-letter-routing-key", DIRECT_ROUTING_KEY);
        //过期时间
        arguments.put("x-message-ttl", 60000); // 消息过期时间 1分钟
        return new Queue(DELAY_QUEUE_NAME, true, false, false, arguments);*/

        return QueueBuilder.durable(DELAY_QUEUE_NAME)
                .deadLetterExchange(DIRECT_EXCHANGE_NAME)
                .deadLetterRoutingKey(DIRECT_ROUTING_KEY)
                .ttl(DELAY_TIME)
                .build();
    }

    @Bean
    public Binding bindDelayQueue() {
        return BindingBuilder.bind(delayQueue()).to(delayExchange()).with(DELAY_ROUTING_KEY);
    }
}
