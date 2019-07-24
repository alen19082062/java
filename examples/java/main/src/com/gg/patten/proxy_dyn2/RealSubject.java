package com.gg.patten.proxy_dyn2;

public class RealSubject implements Subject {
    @Override
    public void buyMac() {
        System.out.println("RealSubject.buyMac() 买一台Mac ");
    }
}
