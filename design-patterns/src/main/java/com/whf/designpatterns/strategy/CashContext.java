package com.whf.designpatterns.strategy;

/**
 * Created by huang on 17-4-12.
 */
public class CashContext {

    private CashStrategy cashStrategy;

    public CashContext(CashStrategy cashStrategy) {
        this.cashStrategy = cashStrategy;
    }

    public double getResult(double money) {
        return cashStrategy.acceptCash(money);
    }
}