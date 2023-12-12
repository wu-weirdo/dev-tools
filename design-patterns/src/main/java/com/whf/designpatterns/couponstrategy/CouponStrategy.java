package com.whf.designpatterns.couponstrategy;

/**
 * 优惠券策略
 *
 * @author whf
 * @date 2022/09/06
 */
public interface CouponStrategy {
 
    /**
     * 适用的优惠券类型
     *
     * @return
     */
    CouponTypeEnum applyCouponType();
 
    /**
     * 优惠券策略算法执行入口
     *
     * @param typeEnum
     * @return
     */
    Object couponHandler(CouponTypeEnum typeEnum);
}