package com.gg.patten.decorator2;

public class ADPhoneDecorator extends PhoneDecorator {
    public ADPhoneDecorator(Phone phone) {
        super(phone);
    }

    public void call(String name) {
        System.out.println("ADPhoneDecorator.call() 播放广告");
        super.call(name);
    }

}
