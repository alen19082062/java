package com.gg.patten.CoR;

public abstract class AbstractCarHandler {
    AbstractCarHandler carHandler = null;

    public abstract void carHandler();

    public AbstractCarHandler setNextCarHandler(AbstractCarHandler nextCarHandler){
        this.carHandler = nextCarHandler;
        return this.carHandler;
    }

    /**职责下传*/
    protected void doChain(){
        if(this.carHandler != null){
            this.carHandler.carHandler();
        }
    }

}
