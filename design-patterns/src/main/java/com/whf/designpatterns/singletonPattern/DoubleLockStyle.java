package com.whf.designpatterns.singletonPattern;

/**
 * 单例模式-双重校验锁
 * @author whf
 * @date 2024/12/17
 */
public class DoubleLockStyle {

    /**
     * volatile关键字，使得instance变量在多个线程间可见，禁止指令重排序优化
     * volatile是一个轻量级的同步机制，即轻量锁
     */
    private static volatile DoubleLockStyle instance;

    /**
     * 私有化构造方法(防止外部new新的对象)
     */
    private DoubleLockStyle(){}

    /**
     * 提供一个静态的公有方法，加入双重检查代码，解决线程安全问题，同时解决懒加载问题
     * 即双重检查锁模式
     *
     * @return instance（单例对象）
     */
    public static DoubleLockStyle getInstance() {
        if (instance == null) {
            // 同步代码块，线程安全的创建实例
            synchronized (DoubleLockStyle.class) {
                // 双重检查，解决多线程并发问题
                if (instance == null) {
                    instance = new DoubleLockStyle();
                }
            }
        }
        return instance;
    }
}
