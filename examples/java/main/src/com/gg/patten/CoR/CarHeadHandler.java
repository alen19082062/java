package com.gg.patten.CoR;

public class CarHeadHandler extends AbstractCarHandler {
    @Override
    public void carHandler() {
        System.out.println("处理车的head！");

        //下传
        this.doChain();
    }
}
