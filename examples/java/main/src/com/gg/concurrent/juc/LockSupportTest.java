package com.gg.concurrent.juc;

import java.util.concurrent.locks.LockSupport;

public class LockSupportTest {

    static class ThreadA extends Thread{

        public ThreadA(String name) {
            super(name);
        }

        public void run() {

            System.out.println(Thread.currentThread().getName()+"  sleep 2s");
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            System.out.println(Thread.currentThread().getName()+" wakup main thread ");
            // 唤醒“主线程”
            LockSupport.unpark(mainThread);
        }
    }

    private static Thread mainThread;

    public static void main(String[] args) {

        ThreadA t1 = new ThreadA("t1");
        // 获取主线程
        mainThread = Thread.currentThread();

        System.out.println(Thread.currentThread().getName()+" start t1 thread ");
        t1.start();

        System.out.println(Thread.currentThread().getName()+" will be blocked ...");
        // 主线程阻塞
        LockSupport.park(mainThread);

        System.out.println(Thread.currentThread().getName()+" end ");
    }



}
