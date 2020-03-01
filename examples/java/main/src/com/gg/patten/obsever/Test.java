package com.gg.patten.obsever;

public class Test {
    public static void main(String[] args) {
        ConcreteSubject mSubscriptionSubject=new ConcreteSubject();
        //创建微信用户
        WxUser user1=new WxUser("杨影枫");
        WxUser user2=new WxUser("月眉儿");
        WxUser user3=new WxUser("紫轩");
        //订阅公众号
        mSubscriptionSubject.attach(user1);
        mSubscriptionSubject.attach(user2);
        mSubscriptionSubject.attach(user3);
        //公众号更新发出消息给订阅的微信用户
        System.out.println("Send notify : 刘望舒的专栏更新了 ...");
        mSubscriptionSubject.notify("刘望舒的专栏更新了");

    }
}
