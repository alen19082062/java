package com.gg.concurrent.thread_pool;

import com.gg.util.ThreadUtil;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThreadPoolTest {
    public static void main(String[] args) {
        // 创建一个可重用固定线程数的线程池
        ExecutorService pool = Executors.newFixedThreadPool(2);
        // 创建实现了Runnable接口对象，Thread对象当然也实现了Runnable接口
        Thread t001 = new MyThread("t001");
        Thread t002 = new MyThread("t002");
        Thread t003 = new MyThread("t003");
        Thread t004 = new MyThread("t004");
        Thread t005 = new MyThread("t005");
        Thread t006 = new MyThread("t006");
        Thread t007 = new MyThread("t007");
        Thread t008 = new MyThread("t008");

        // 将线程放入池中进行执行
        pool.execute(t001);
        pool.execute(t002);
        pool.execute(t003);
        pool.execute(t004);
        pool.execute(t005);
        pool.execute(t006);
        pool.execute(t007);
        pool.execute(t008);

        // 关闭线程池
        pool.shutdown();
    }
}

class MyThread extends Thread {

    private String name = "" ;

    public MyThread(String name){
        this.name = name ;
        this.setName(name);
    }

    @Override
    public void run() {

        this.setName(this.name);
        // System.out.println(Thread.currentThread().getName()+ " is running. sleep 2s ");
        System.out.println("name : " + name);
        System.out.println(ThreadUtil.toNameString() + " is running. sleep 2s ");

        System.out.println("run start-----------------" + System.currentTimeMillis());
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("run end-----------------" + System.currentTimeMillis());
        System.out.println(Thread.currentThread().getName()+ " end ");
    }

}
