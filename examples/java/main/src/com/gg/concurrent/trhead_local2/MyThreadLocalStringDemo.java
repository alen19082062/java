package com.gg.concurrent.trhead_local2;

import com.gg.util.ThreadUtil;

import java.util.concurrent.CountDownLatch;

/**
 * ThreadLocal 这个类提供线程局部变量，这些变量与其他正常的变量的不同之处在于，
 * 每一个访问该变量的线程在其内部都有一个独立的初始化的变量副本；ThreadLocal
 * 实例变量通常采用private static在类中修饰。
 *
 * 只要 ThreadLocal 的变量能被访问，并且线程存活，那每个线程都会持有
 * ThreadLocal 变量的副本。当一个线程结束时，它所持有的所有 ThreadLocal
 * 相对的实例副本都可被回收。
 */
public class MyThreadLocalStringDemo {
    private static ThreadLocal<String> threadLocal = new ThreadLocal<>();

    private String getString() {
        return threadLocal.get();
    }

    private void setString(String string) {
        threadLocal.set(string);
    }

    public static void main(String[] args) {
        int threads = 9;
        MyThreadLocalStringDemo demo = new MyThreadLocalStringDemo();
        CountDownLatch countDownLatch = new CountDownLatch(threads);
        for (int i = 0; i < threads; i++) {
            Thread thread = new Thread(() -> {
                demo.setString(Thread.currentThread().getName());
                System.out.println( demo.getString());
                System.out.println(ThreadUtil.toNameString() + " : " + demo.getString());
                countDownLatch.countDown();
            }, "thread - " + i);
            thread.start();
        }
    }

}
