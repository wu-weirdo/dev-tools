package com.whf.designpatterns.chainOfResponsibility;

public class LoginService {

    public static void main(String[] args) {
        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setUserName("1234567");
        loginRequest.setPassword("123456");
        // 获取单例用户名登录处理器
        LoginHandler<LoginRequest> loginHandler = UserNameLoginHandler.getInstance();
        // 获取单例手机登录处理器
        PhoneLoginHandler phoneLoginHandler = PhoneLoginHandler.getInstance();
        // 获取单例邮箱登录处理器
        EmailLoginHandler emailLoginHandler = EmailLoginHandler.getInstance();
        // 设置责任链
        loginHandler.setNext(phoneLoginHandler);
        phoneLoginHandler.setNext(emailLoginHandler);
        // 开始处理
        boolean handle = loginHandler.handle(loginRequest);
        System.out.println("登录结果：" + handle);
    }
}
