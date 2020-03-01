package com.gg.concurrent.juc;

import com.gg.util.ThreadUtil;

import java.util.concurrent.CountDownLatch;

public class CountDownLatchTest {
    // 初始化,需要 6 次 count down
    static CountDownLatch latch = new CountDownLatch(6);
    static {
        System.out.println("Init ...需要 6 次 count down ， latch  :  " + latch.toString());
    }

    //初始化线程(只有一步，有4个)
    private static class InitThread implements Runnable{

        @Override
        public void run() {
            Thread t = Thread.currentThread() ;
            // ThreadUtil.prt(t);
            System.out.println( "InitThread : " + ThreadUtil.toString(t) + " do something , then latch.countDown() .....");
            // System.out.println("当前线程名字：" + t.getName() + " 当前线程的优先级别为："+ t.getPriority() + " ID:" + t.getId() + ",ready init work......");

            //初始化线程完成工作了，countDown方法只扣减一次；
            latch.countDown();
            System.out.println("InitThread : " + latch.toString());

            for(int i =0;i<2;i++) {
                System.out.println( "InitThread : " + ThreadUtil.toString(t) + " ...... continue do its work");
                // System.out.println("当前线程名字：" + t.getName() + " 当前线程的优先级别为："+ t.getPriority() + " ID:" + t.getId() + ", ...... continue do its work");
                // System.out.println("Thread_"+ t.getId() + " ........continue do its work");
            }
        }
    }

    //业务线程，需要所有的初始化完成后，再运行
    private static class BusiThread implements Runnable{

        @Override
        public void run() {
            try {
                System.out.println("BusiThread() latch.await() ... 进入等待状态 ... "  );
                latch.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("BusiThread() : " + latch.toString());

            Thread t = Thread.currentThread() ;
            System.out.println("BusiThread() Now，" + ThreadUtil.toString(t)  +"  do business .... ");
            for(int i =0;i<3;i++) {
                System.out.println("BusiThread()  do do do +++++  ");
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {

        //单独的初始化线程,初始化分为2步，需要扣减两次
        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("main_thread begin step 1st.......");

                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                Thread t = Thread.currentThread() ;
                System.out.println("main_thread : " + ThreadUtil.toString(t) +" ready init work step 1st......");

                latch.countDown();//每完成一步初始化工作，扣减一次
                System.out.println("main_thread : " +latch.toString());

                System.out.println("main_thread begin step 2nd.......");
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(ThreadUtil.toString(t) + " ready init work step 2nd......");

                latch.countDown();//每完成一步初始化工作，扣减一次
                System.out.println("main_thread : " +latch.toString());
            }
        }).start();

        System.out.println("main() start BusiThread .......");
        new Thread(new BusiThread()).start();

        System.out.println("main() start InitThread .......");
        for(int i=0;i<=3;i++){
            System.out.println("main() start InitThread ....." + (i+1) );
            Thread thread = new Thread(new InitThread());
            thread.start();
        }

        latch.await();
        System.out.println("Main do ites work........");
    }
}

