package com.whf.designpatterns.couponstrategy;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * 优惠券策略上下文
 *
 * @author whf
 * @date 2022/9/6
 */
@Slf4j
@Component
public class CouponStrategyContext implements ApplicationContextAware {

    public static Map<String, CouponStrategy> couponStrategyMap;

    /**
     * 优惠券策略类集合
     */
    public static Object couponHandler(CouponTypeEnum typeEnum) {
        return getCouponStrategy(typeEnum).couponHandler(typeEnum);
    }

    /**
     * 获取适用的策略处理类
     *
     * @param typeEnum
     * @return
     */
    public static CouponStrategy getCouponStrategy(CouponTypeEnum typeEnum) {
        return Optional.ofNullable(couponStrategyMap.get(typeEnum.name()))
                .orElseThrow(() -> new RuntimeException(String.format("not found coupon type strategy , coupon type is %s", typeEnum.name())));
    }

    /**
     * 从容器中加载所有优惠券策略接口的实现类，注册到优惠券策略集合中
     *
     * @param applicationContext 应用程序上下文
     * @throws BeansException
     */
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        Map<String, CouponStrategy> strategyBeans = applicationContext.getBeansOfType(CouponStrategy.class);
        if (CollectionUtils.isEmpty(strategyBeans)) {
            // 无可用的优惠券策略
            return;
        }
        couponStrategyMap = new HashMap<>(strategyBeans.size());
        for (CouponStrategy strategy : strategyBeans.values()) {
            couponStrategyMap.put(strategy.applyCouponType().name(), strategy);
            log.info("register strategy {}",strategy.applyCouponType());
        }
    }
}
