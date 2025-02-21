package com.whf.designpatterns.chainOfResponsibility;

import lombok.Setter;

/**
 * 责任链模式 登录处理抽象类
 * @param <T>
 */
@Setter
public abstract class LoginHandler<T> {

    protected LoginHandler<T> next;

    public boolean handle(T t) {
        // 判断是否登录
        boolean login = isLogin(t);
        // 登录失败，并且有下一个处理者，则继续处理
        if (!login && next != null) {
            return next.handle(t);
        }
        return login;
    }

    protected abstract boolean isLogin(T t);
}
