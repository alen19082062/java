package com.gg.concurrent.test;

public class ThreadJoin {

    public static void main(String[] args) throws InterruptedException {
        Thread.sleep(300);

        Thread thread1 = new Thread(() -> {
            // System.out.println(Thread.currentThread().getName());
            Thread t = Thread.currentThread();
            System.out.println("当前线程名字：" + t.getName() + " 当前线程的优先级别为："+ t.getPriority() + " ID:" + t.getId() );
        }, "thread1");

        Thread thread2 = new Thread(() -> {
            Thread t = Thread.currentThread();
            System.out.println("当前线程名字：" + t.getName() + " 当前线程的优先级别为："+ t.getPriority() + " ID:" + t.getId() );

        }, "thread2");

        Thread thread3 = new Thread(() -> {
            Thread t = Thread.currentThread();
            System.out.println("当前线程名字：" + t.getName() + " 当前线程的优先级别为："+ t.getPriority() + " ID:" + t.getId() );

        }, "thread3");

        thread2.start();
        //thread2.join();
        thread1.start();
        Thread.currentThread().yield();
        //thread1.join();
        thread3.start();
        thread3.join();
        System.out.println("main:" + Thread.currentThread().getName());


    }

}
