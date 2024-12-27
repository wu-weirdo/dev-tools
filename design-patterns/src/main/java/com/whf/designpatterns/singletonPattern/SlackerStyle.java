package com.whf.designpatterns.singletonPattern;

/**
 * 单例模式-懒汉式 懒汉式+锁
 *
 * @author whf
 * @date 2024/12/17
 */
public class SlackerStyle {

    private static SlackerStyle instance;

    /**
     * 私有化构造方法(防止外部new新的对象)
     */
    private SlackerStyle() {
    }

    /**
     * 提供一个静态的公有方法，当使用到该方法时，才去创建instance
     * 即懒汉式
     *
     * @return instance（单例对象）
     */
    public static SlackerStyle getInstance() {
        if (instance == null) {
            instance = new SlackerStyle();
        }
        return instance;
    }

    /**
     * 懒汉式 + 锁 线程安全 效率降低
     *
     * @return instance（单例对象）
     */
    public static synchronized SlackerStyle getInstance2() {
        if (instance == null) {
            instance = new SlackerStyle();
        }
        return instance;
    }
}
