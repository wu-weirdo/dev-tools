package com.whf.designpatterns;

import com.whf.designpatterns.couponstrategy.CouponStrategyContext;
import com.whf.designpatterns.couponstrategy.CouponTypeEnum;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class DesignPatternsApplicationTests {

    @Test
    void contextLoads() {
    }

    /**
     * 策略模式
     */
    @Test
    void couponStrategy() {
        CouponStrategyContext.couponHandler(CouponTypeEnum.DISCOUNT);
    }
}
