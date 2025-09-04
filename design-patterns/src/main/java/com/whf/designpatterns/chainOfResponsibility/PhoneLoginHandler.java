package com.whf.designpatterns.chainOfResponsibility;

import lombok.extern.slf4j.Slf4j;

/**
 * 手机号登录处理器 单例模式(静态内部类)
 */
@Slf4j
public class PhoneLoginHandler extends LoginHandler<LoginRequest> {

    /**
     * 私有化构造器
     */
    private PhoneLoginHandler() {
    }

    /**
     * 静态内部类实现单例
     */
    private static final class InstanceHolder {
        static final PhoneLoginHandler INSTANCE = new PhoneLoginHandler();
    }

    /**
     * 获取单例
     *
     * @return
     */
    public static PhoneLoginHandler getInstance() {
        return InstanceHolder.INSTANCE;
    }

    @Override
    protected boolean isLogin(LoginRequest t) {
        if ("123456".equals(t.getUserName()) && "123456".equals(t.getPassword())) {
            log.info("手机号登录成功");
            return true;
        }
        return false;
    }
}
