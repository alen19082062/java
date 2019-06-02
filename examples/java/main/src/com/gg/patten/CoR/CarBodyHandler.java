package com.gg.patten.CoR;

public class CarBodyHandler extends AbstractCarHandler {
    @Override
    public void carHandler() {
        System.out.println("处理车的 Body ！");

        //下传
        this.doChain();
    }
}
