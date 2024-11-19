package com.whf.message.queue.config;

import org.apache.rocketmq.spring.annotation.RocketMQTransactionListener;
import org.apache.rocketmq.spring.core.RocketMQLocalTransactionListener;
import org.apache.rocketmq.spring.core.RocketMQLocalTransactionState;
import org.springframework.messaging.Message;

/**
 * rocket 事务监听器
 * @author whf
 * @date 2024/11/19
 */
@RocketMQTransactionListener
public class RocketTransactionListener implements RocketMQLocalTransactionListener {

    @Override
    public RocketMQLocalTransactionState executeLocalTransaction(Message msg, Object arg) {
        //执行本地事务
        String user = msg.getPayload().toString();
        return RocketMQLocalTransactionState.COMMIT;
    }

    @Override
    public RocketMQLocalTransactionState checkLocalTransaction(Message msg) {
        //回查本地事务结果
         return RocketMQLocalTransactionState.COMMIT;
    }
}
