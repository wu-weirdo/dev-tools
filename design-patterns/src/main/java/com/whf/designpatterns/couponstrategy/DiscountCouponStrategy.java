package com.whf.designpatterns.couponstrategy;

import org.springframework.stereotype.Service;

/**
 * 折扣券策略
 *
 * @author whf
 * @date 2022/09/06
 */
@Service
public class DiscountCouponStrategy implements CouponStrategy {

    /**
     * 适用的优惠券类型
     *
     * @return
     */
    @Override
    public CouponTypeEnum applyCouponType() {
        return CouponTypeEnum.DISCOUNT;
    }

    /**
     * 优惠券策略算法执行入口
     *
     * @param typeEnum
     * @return
     */
    @Override
    public Object couponHandler(CouponTypeEnum typeEnum) {
        System.out.println("执行折扣券策略");
        return null;
    }
}