package com.gg.patten.CoR;

public class CarTailHandler extends AbstractCarHandler {
    @Override
    public void carHandler() {
        System.out.println("处理车的 Tail ！");

        //下传
        this.doChain();
    }
}
