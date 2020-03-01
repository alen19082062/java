package com.gg.patten.proxy;

public class RealSubject implements Subject {
    @Override
    public void buyMac() {
        System.out.println("RealSubject.buyMac() 买一台Mac ");
    }
}
