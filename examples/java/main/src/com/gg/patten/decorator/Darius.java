package com.gg.patten.decorator;

//ConcreteComponent 具体英雄 德莱厄斯 我大诺手
public class Darius implements Hero {
    private String name;

    public Darius(String name) {
        this.name = name;
    }

    @Override
    public void learnSkills() {
        System.out.println(name + "学习了以上技能！");
    }
}
