package com.whf.designpatterns.singletonPattern;

/**
 * 单例模式-枚举
 *<p>
 * 由于枚举类型的特性，INSTANCE 会被自动初始化为单例对象的一个实例，并且保证在整个应用程序的生命周期中只有一个实例
 * @author whf
 * @date 2024/12/17
 */
public enum EnumerateSingletons {

    /**
     * 枚举单例
     */
    INSTANCE;

    /**
     * 枚举单例方法
     */
    public void doSomething() {
        System.out.println("do something");
    }
}
