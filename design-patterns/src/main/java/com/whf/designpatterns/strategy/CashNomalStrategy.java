package com.whf.designpatterns.strategy;

/**
 * 正常价格
 */
public class CashNomalStrategy implements CashStrategy {

    @Override
    public double acceptCash(double money) {
        return money;
    }
}