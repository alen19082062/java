package com.gg.patten.decorator2;

 public class PhoneImpl implements Phone {

    @Override
    public void call(String name ) {
        System.out.println("====> " + this.getClass().getCanonicalName() );
        System.out.println("PhoneImpl.call() call " + name + "");
    }
}
