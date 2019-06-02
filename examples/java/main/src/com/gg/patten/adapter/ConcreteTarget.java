package com.gg.patten.adapter;

// 直接实现 Target 是不行的
public class ConcreteTarget implements Target {
    @Override
    public void request() {
        System.out.println("ConcreteTarget.request() 目标方法");
    }
}
