package com.whf.designpatterns.singletonPattern;


/**
 * 单例模式-静态内部类
 * </p>
 * 在静态内部类模式中，单例对象是在静态内部类中被创建的。静态内部类只有在第一次被使用时才会被加载，
 * 因此单例对象也是在第一次使用时被创建的。这样就实现了延迟加载的效果，即在需要时才创建单例对象，
 * 避免了在程序启动时就创建单例对象的开销。
 *
 * @author whf
 * @date 2024/12/17
 */
public class StaticInnerClassStyle {


    /**
     * 私有构造函数(防止外部new新的对象)
     */
    private StaticInnerClassStyle() {
    }

    /**
     * 静态内部类
     */
    private static class SingletonHolder {
        // 静态内部类中的静态变量(单例对象)
        private static final StaticInnerClassStyle INSTANCE = new StaticInnerClassStyle();
    }

    /**
     * 提供一个静态的公有方法，直接返回SingletonInstance.INSTANCE
     *
     * @return instance（单例对象）
     */
    public static StaticInnerClassStyle getInstance() {
        return SingletonHolder.INSTANCE;
    }
}
