package com.gg.patten.decorator;

public class Test {
    public static void main(String[] args) {
        //选择英雄
        Hero hero = new Darius("德莱厄斯");

        Skills skills = new Skills(hero);
        Skills q = new Skills_Q(skills,"旋转");
        Skills w = new Skills_W(q,"拉起");

        //学习技能
        w.learnSkills();

    }
}
