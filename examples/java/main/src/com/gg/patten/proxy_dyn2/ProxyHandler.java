package com.gg.patten.proxy_dyn2;


import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

// 这里不严谨， ProxyHandler 应该实现 Subject
public class ProxyHandler implements InvocationHandler {

    private RealSubject realSubject = null;

    public void setRealSubject(RealSubject realSubject) {
        this.realSubject = realSubject;
    }

    public void beforeBuy(){
        System.out.println("ProxyHandler.beforeBuy() ...");
        System.out.println("ProxyHandler.beforeBuy() 包装好  PC ");
    }

    public void afterBuy(){
        System.out.println("ProxyHandler.afterBuy() ...");
        System.out.println("ProxyHandler.afterBuy() 收钱  ");
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        beforeBuy();
        Object result = method.invoke(realSubject, args);
        afterBuy();
        return result;
    }

}
