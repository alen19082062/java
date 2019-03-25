package com.gg.concurrent.basic;

/**
 * wait(long timeout)会让当前线程处于“等待(阻塞)状态”，
 * 直到其他线程调用此对象的 notify() 方法或 notifyAll() 方法，或者超过指定的时间量，
 * 当前线程被唤醒(进入“就绪状态”)。
 * notifyAll()的用法；它的作用是唤醒在此对象监视器上等待的所有线程。
 */
public class NotifyAllTest {
    private static Object obj = new Object();

    static class ThreadA extends Thread {

        public ThreadA(String name) {
            super(name);
        }

        public void run() {
            synchronized (obj) {
                try {
                    // 打印输出结果
                    System.out.println("ThreadA.run() " + Thread.currentThread().getName() + " wait ...");

                    // 唤醒当前的wait线程
                    obj.wait();

                    // 打印输出结果
                    System.out.println("ThreadA.run() " + Thread.currentThread().getName() + " continue");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void main(String[] args) {
        ThreadA t1 = new ThreadA("t1");
        ThreadA t2 = new ThreadA("t2");
        ThreadA t3 = new ThreadA("t3");

        t1.start();
        t2.start();
        t3.start();

        // 匿名类
        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("annoymous class is running .... ");
            }
        }).start();

        try {
            System.out.println("main()  " + Thread.currentThread().getName() + " sleep(3000)");
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        synchronized (obj) {
            // 主线程等待唤醒。
            System.out.println("main()  " +Thread.currentThread().getName() + " notifyAll()");
            obj.notifyAll();
        }
    }

}
