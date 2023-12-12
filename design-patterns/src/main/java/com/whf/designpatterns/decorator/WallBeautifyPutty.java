package com.whf.designpatterns.decorator;

/**
 * 墙面装修装饰器角色实现（刮腻子）
 * @author: 栈长
 * @from: 公众号Java技术栈
 */
public class WallBeautifyPutty extends WallBeautifyDecorator {

    public WallBeautifyPutty(WallBeautify wallBeautify) {
        super(wallBeautify);
    }

    @Override
    public void decoration() {
        System.out.println("开始刮腻子");
    }

}