package com.gg.patten.adapter;

public class Adapter extends Adaptee implements Target {
    @Override
    public void request() {
        //...一些操作...
        System.out.println("Adapter.request() before call super.adapteeRequest().... ");

        super.adapteeRequest();

        //...一些操作...
        System.out.println("Adapter.request() after call super.adapteeRequest() .... ");
    }
}
