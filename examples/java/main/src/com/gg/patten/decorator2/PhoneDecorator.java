package com.gg.patten.decorator2;

public class PhoneDecorator implements Phone {
    private Phone phone;

    public PhoneDecorator(Phone phone){
        this.phone = phone;
    }

    @Override
    public void call(String name ) {
        // System.out.println("====> " + this.getClass().getCanonicalName() );
        phone.call(name);
    }
}
