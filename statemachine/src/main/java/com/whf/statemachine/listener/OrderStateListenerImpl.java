package com.whf.statemachine.listener;

import com.whf.statemachine.annotation.StateResult;
import com.whf.statemachine.constants.OrderConstants;
import com.whf.statemachine.entity.Order;
import com.whf.statemachine.enums.OrderStatus;
import com.whf.statemachine.enums.OrderStatusChangeEvent;
import com.whf.statemachine.mapper.OrderMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.Message;
import org.springframework.statemachine.annotation.OnTransition;
import org.springframework.statemachine.annotation.WithStateMachine;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * 监听订单状态的变化
 */
@Component("orderStateListener")
@WithStateMachine(name = "orderStateMachine")
@Slf4j
public class OrderStateListenerImpl {
    @Resource
    private OrderMapper orderMapper;

    @OnTransition(source = "WAIT_PAYMENT", target = "WAIT_DELIVER")
    @Transactional(rollbackFor = Exception.class)
    @StateResult(key = OrderConstants.payTransition)
    public void payTransition(Message<OrderStatusChangeEvent> message) {
        Order order = (Order) message.getHeaders().get("order");
        log.info("支付，状态机反馈信息：{}", message.getHeaders().toString());
        //更新订单
        order.setStatus(OrderStatus.WAIT_DELIVER.getKey());
        orderMapper.updateById(order);
        //TODO 其他业务
    }
    @OnTransition(source = "WAIT_DELIVER", target = "WAIT_RECEIVE")
    @StateResult(key = OrderConstants.deliverTransition)
    public void deliverTransition(Message<OrderStatusChangeEvent> message) {
        Order order = (Order) message.getHeaders().get("order");
        log.info("发货，状态机反馈信息：{}", message.getHeaders().toString());
        //更新订单
        order.setStatus(OrderStatus.WAIT_RECEIVE.getKey());
        orderMapper.updateById(order);
        //TODO 其他业务
    }
    @OnTransition(source = "WAIT_RECEIVE", target = "FINISH")
    @StateResult(key = OrderConstants.receiveTransition)
    public void receiveTransition(Message<OrderStatusChangeEvent> message) {
        Order order = (Order) message.getHeaders().get("order");
        log.info("确认收货，状态机反馈信息：{}", message.getHeaders().toString());
        //更新订单
        order.setStatus(OrderStatus.FINISH.getKey());
        orderMapper.updateById(order);
        //TODO 其他业务
    }
}