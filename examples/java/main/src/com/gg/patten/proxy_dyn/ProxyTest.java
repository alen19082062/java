package com.gg.patten.proxy_dyn;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Proxy;

public class ProxyTest {
    public static void main(String[] args)  {

        System.out.println("JDK 动态代理调用 ...");
        IHello  iHello2 = (IHello) Proxy.newProxyInstance(IHello.class.getClassLoader(), // 加载接口的类加载器
                new Class[]{IHello.class}, // 一组接口
                new JDKInvocationHandlerImpl(new HelloImpl())); // 自定义的InvocationHandler
        iHello2.sayHello();
    }
}
