package com.gg.patten.strategy;

public class Test {
    public static void main(String[] args) {
        Context c=new Context();
        Strategy sa = new ConcreteStrategyA();
        c.setStrategy(sa);
        c.strategyMethod();
        System.out.println("-----------------");
        Strategy sb=new ConcreteStrategyB();
        c.setStrategy(sb);
        c.strategyMethod();
    }
}
