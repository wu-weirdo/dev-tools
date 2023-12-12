package com.whf.designpatterns.strategy;

public class Main {
        public static void main(String[] args) {
        /**
         * Java 8前策略模式使用
         */
        CashStrategy cashStrategy = new CashReturnStrategy(500, 200);
        CashContext cashContext = new CashContext(cashStrategy);
        double price = cashContext.getResult(400.6);
        System.out.println("Java 8 以前策略模式计算金额 price=" + price);

        /**
         * Java 8 重构策略模式
         */
        CashContext cashContext1 = new CashContext(money -> money * 200);
        System.out.println("Java 8 以后简便写法计算金额 price=" + cashContext1.getResult(400.6));
      }
    }