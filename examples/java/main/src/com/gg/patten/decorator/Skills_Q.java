package com.gg.patten.decorator;

//ConreteDecorator 技能：Q
public class Skills_Q extends Skills{
    private String skillName;

    public Skills_Q(Hero hero, String skillName) {
        super(hero);
        this.skillName = skillName;
    }

    @Override
    public void learnSkills() {
        System.out.println("学习了技能 Q:" +skillName);
        super.learnSkills();
    }
}
