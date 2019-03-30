package com.gg.concurrent.juc;

import com.gg.util.ThreadUtil;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThreadPoolTest {
    public static void main(String[] args) {
        // 创建一个可重用固定线程数的线程池
        ExecutorService pool = Executors.newFixedThreadPool(1);
        // 创建实现了Runnable接口对象，Thread对象当然也实现了Runnable接口
        Thread t001 = new MyThread();
        Thread t002 = new MyThread();
        Thread t003 = new MyThread();
        Thread t004 = new MyThread();
        Thread t005 = new MyThread();
        Thread t006 = new MyThread();
        Thread t007 = new MyThread();
        // 将线程放入池中进行执行
        pool.execute(t001);
        pool.execute(t002);
        pool.execute(t003);
        pool.execute(t004);
        pool.execute(t005);
        pool.execute(t006);
        pool.execute(t007);
        // 关闭线程池
        pool.shutdown();
    }
}

class MyThread extends Thread {

    @Override
    public void run() {
        // System.out.println(Thread.currentThread().getName()+ " is running. sleep 2s ");
        System.out.println(ThreadUtil.toNameString() + " is running. sleep 2s ");
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName()+ " end ");
    }

}
