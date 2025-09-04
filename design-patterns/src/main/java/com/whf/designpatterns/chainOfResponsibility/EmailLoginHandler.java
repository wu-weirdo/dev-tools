package com.whf.designpatterns.chainOfResponsibility;

import lombok.extern.slf4j.Slf4j;

/**
 * 邮箱登录处理器
 */
@Slf4j
public class EmailLoginHandler extends LoginHandler<LoginRequest> {

    /**
     * 私有化构造器
     */
    private EmailLoginHandler() {
    }

    /**
     * 静态内部类实现单例
     */
    private static final class InstanceHolder {
        static final EmailLoginHandler INSTANCE = new EmailLoginHandler();
    }

    /**
     * 获取单例
     *
     * @return
     */
    public static EmailLoginHandler getInstance() {
        return EmailLoginHandler.InstanceHolder.INSTANCE;
    }

    @Override
    protected boolean isLogin(LoginRequest t) {
        if ("123456@qq.com".equals(t.getUserName()) && "123456".equals(t.getPassword())) {
            log.info("邮箱登录成功");
            return true;
        }
        return false;
    }
}
