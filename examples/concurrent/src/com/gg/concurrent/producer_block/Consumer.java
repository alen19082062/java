package com.gg.concurrent.producer_block;

public class Consumer implements Runnable{
    private Storage storage;

    public Consumer(){}

    public Consumer(Storage storage , String name){
        this.storage = storage;
    }

    @Override
    public void run(){
        while(true){
            try{
                // 随机休眠
                int sleep_num = (int) (Math.random() * 1000);
                Thread.sleep(sleep_num);
                storage.consume();
            }catch (InterruptedException e){
                e.printStackTrace();
            }
        }
    }
}
