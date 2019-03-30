package com.gg.concurrent.basic;

public class User {
    public User(){
        System.out.println("user 构造方法被调用");
    }
    private String name = "张三";
    private int age = 18;
    private static String id="USER_ID";

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", age=" + age +' ' +
                ", id=" + id +'\'' +
                '}';
    }
}
