package com.gg.patten.proxy_dyn2;


import java.lang.reflect.Proxy;

public class Test {
    public static void main(String[] args) {
        ProxyHandler proxyHandler = new ProxyHandler();
        proxyHandler.setRealSubject(new RealSubject());
        Subject subject =
                (Subject)Proxy.newProxyInstance(RealSubject.class.getClassLoader(),
                        RealSubject.class.getInterfaces(), proxyHandler);
        subject.buyMac();

    }
}
