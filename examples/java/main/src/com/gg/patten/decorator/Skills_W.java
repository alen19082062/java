package com.gg.patten.decorator;

//ConreteDecorator 技能：Q
public class Skills_W extends Skills{
    private String skillName;

    public Skills_W(Hero hero, String skillName) {
        super(hero);
        this.skillName = skillName;
    }

    @Override
    public void learnSkills() {
        System.out.println("学习了技能 W :" +skillName);
        super.learnSkills();
    }
}
