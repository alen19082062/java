package com.gg.patten.proxy;

public class Proxy implements Subject {

    public void WrapMac(){
        System.out.println("Proxy.WarpMac() ...");
        System.out.println("Proxy.WarpMac() 包装好 ");
    }

    @Override
    public void buyMac() {
        //引用并创建真实对象实例，即”我“
        RealSubject realSubject = new RealSubject();
        //调用真实对象的方法，进行代理购买Mac
        realSubject.buyMac();
        //代理对象额外做的操作
        this.WrapMac();
    }

}
