package com.whf.designpatterns.singletonPattern;

/**
 * 单例模式-饿汉式
 * @author whf
 * @date 2024/12/17
 */
public class HungryManStyle {

    /**
     * 静态变量，在类加载的时候就创建对象(不存在线程安全问题)
     */
    private static final HungryManStyle instance = new HungryManStyle();

    /**
     * 私有化构造方法(防止外部new新的对象)
     */
    private HungryManStyle() {}

    /**
     * 提供一个静态方法，返回单例对象
     */
    public static HungryManStyle getInstance() {
        return instance;
    }
}
