package com.gg.patten.decorator2;

public class ColorPhoneDecorator extends PhoneDecorator {
    private Phone phone;

    public ColorPhoneDecorator(Phone phone) {
        super(phone);
    }

    @Override
    public void call(String name ) {
        System.out.println("ColorPhoneDecorator.call()  播放彩铃");
        super.call(name);
    }
}
