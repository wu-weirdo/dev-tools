package com.whf.designpatterns.strategy;

/**
 * 返利200返100等
 */
public class CashReturnStrategy implements CashStrategy {
    private double moneyCondition = 0.0d;

    private double moneyReturn = 0.0d;

    public CashReturnStrategy(double moneyCondition, double moneyReturn) {
        this.moneyCondition = moneyCondition;
        this.moneyReturn = moneyReturn;
    }

    @Override
    public double acceptCash(double money) {
        if (money >= moneyCondition) {
            money = money - (int) (money / moneyCondition) * moneyReturn;
        }
        return money;
    }
}