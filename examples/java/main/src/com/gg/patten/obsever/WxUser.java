package com.gg.patten.obsever;

public class WxUser implements Observer {
    // 微信用户名
    private String name;
    public WxUser(String name) {
        this.name = name;
    }
    @Override
    public void update(String message) {
        System.out.println(name + " 收到更新消息 :  " + "-" + message);
    }

}
