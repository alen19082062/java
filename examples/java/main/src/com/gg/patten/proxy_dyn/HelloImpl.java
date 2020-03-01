package com.gg.patten.proxy_dyn;

public class HelloImpl implements IHello {
    @Override
    public void sayHello() {
        System.out.println("HelloImpl.sayHello() Hello world!");
    }
}
