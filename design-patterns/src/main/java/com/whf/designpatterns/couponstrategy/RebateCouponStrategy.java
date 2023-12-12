package com.whf.designpatterns.couponstrategy;

import org.springframework.stereotype.Service;

/**
 * 抵扣券计算策略
 *
 * @author whf
 * @date 2022/09/06
 */
@Service
public class RebateCouponStrategy implements CouponStrategy {
 
    /**
     * 适用的优惠券类型
     *
     * @return
     */
    @Override
    public CouponTypeEnum applyCouponType() {
        return CouponTypeEnum.REBATE;
    }
 
    /**
     * 优惠券策略算法执行入口
     *
     * @param typeEnum
     * @return
     */
    @Override
    public Object couponHandler(CouponTypeEnum typeEnum) {
        System.out.println("执行抵扣券策略");
        return null;
    }
}