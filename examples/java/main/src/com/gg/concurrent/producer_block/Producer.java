package com.gg.concurrent.producer_block;

public class Producer implements Runnable{
    private Storage storage;

    public Producer(){}

    public Producer(Storage storage , String name){
        this.storage = storage;
    }

    @Override
    public void run(){
        while(true){
            try{
                // 随机休眠
                int sleep_num = (int) (Math.random() * 1000);
                Thread.sleep(sleep_num);
                storage.produce();
            }catch (InterruptedException e){
                e.printStackTrace();
            }
        }
    }
}

