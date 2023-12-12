package com.whf.designpatterns.strategy;

/**
 * 打折
 */
public class CashRebateStrategy implements CashStrategy {
    private double moneyRebate = 1d;// 打折额度

    public CashRebateStrategy(double moneyRebate) {
        this.moneyRebate = moneyRebate;
    }

    @Override
    public double acceptCash(double money) {
        return money * moneyRebate;
    }
}