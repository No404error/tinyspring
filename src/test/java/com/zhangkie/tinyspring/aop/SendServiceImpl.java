package com.zhangkie.tinyspring.aop;

public class SendServiceImpl implements SendService{
    @Override
    public void send() {
        System.out.println("send to do!");
    }
}
