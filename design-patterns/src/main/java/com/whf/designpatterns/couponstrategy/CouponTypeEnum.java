package com.whf.designpatterns.couponstrategy;

/**
 * 优惠券枚举类型
 *
 * @author whf
 * @date 2022/09/06
 */
public enum CouponTypeEnum {

    /**
     * 折扣
     */
    DISCOUNT("折扣券"),

    /**
     * 退税
     */
    REBATE("抵扣券"),

    /**
     * 完整减少
     */
    FULL_REDUCE("满减券");

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    private String name;

    CouponTypeEnum(String name) {
        this.name = name;
    }
}