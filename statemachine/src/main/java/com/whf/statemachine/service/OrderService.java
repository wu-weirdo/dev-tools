package com.whf.statemachine.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.whf.statemachine.entity.Order;

/**
 * @author hfwu
 * @description 针对表【order(订单表)】的数据库操作Service
 * @createDate 2024-01-18 14:24:32
 */
public interface OrderService extends IService<Order> {

    /**
     * 创建订单
     *
     * @param order
     */
    Order create(Order order);

    /**
     * 支付订单
     *
     * @param id
     */
    Order pay(Long id);

    /**
     * 发货
     *
     * @param id
     */
    Order deliver(Long id);

    /**
     * 收货
     *
     * @param id
     */
    Order receive(Long id);
}
