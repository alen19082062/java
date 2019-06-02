package com.gg.patten.decorator2;

public class Test {
    public static void main(String[] args) {
        Phone phone = new PhoneImpl();
        //如果需要彩铃的手机
        //PhoneDecorator colorPhoneDecorator = new ColorPhoneDecorator(phone);
        //colorPhoneDecorator.call("张三");
        //如果需要广告的手机
        //PhoneDecorator adPhoneDecorator = new ADPhoneDecorator(phone);
        //adPhoneDecorator.call("张三");

        //如果需要既播放彩铃又播放广告的手机
        PhoneDecorator phoneDecorator = new ColorPhoneDecorator(phone);
        phoneDecorator = new ADPhoneDecorator(phoneDecorator);
        phoneDecorator.call("张三");

    }
}
