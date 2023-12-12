package com.whf.designpatterns.strategy;

/**
     * 抽象算法类
     */
    public interface CashStrategy {
    
        double acceptCash(double money);
    }