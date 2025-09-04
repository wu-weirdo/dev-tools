package com.whf.designpatterns.decorator;

/**
 * 墙面装修装饰器角色实现（挂壁画）
 *
 * @author: 栈长
 * @from: 公众号Java技术栈
 */
public class WallBeautifyHang extends WallBeautifyDecorator {

    public WallBeautifyHang(WallBeautify wallBeautify) {
        super(wallBeautify);
    }

    @Override
    public void decoration() {
        System.out.println("开始挂壁画");
    }

}