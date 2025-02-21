package com.whf.designpatterns.chainOfResponsibility;

import lombok.extern.slf4j.Slf4j;

/**
 * 用户名登录处理器
 */
@Slf4j
public class UserNameLoginHandler extends LoginHandler<LoginRequest> {

    /**
     * 私有化构造器
     */
    private UserNameLoginHandler() {
    }

    /**
     * 静态内部类实现单例
     */
    private static final class InstanceHolder {
        static final UserNameLoginHandler INSTANCE = new UserNameLoginHandler();
    }

    /**
     * 获取单例
     * @return
     */
    public static UserNameLoginHandler getInstance() {
        return UserNameLoginHandler.InstanceHolder.INSTANCE;
    }

    @Override
    protected boolean isLogin(LoginRequest t) {
        if ("admin".equals(t.getUserName()) && "123456".equals(t.getPassword())) {
            log.info("用户名登录成功");
            return true;
        }
        return false;
    }
}
