package com.gg.concurrent.thread_local;

import com.gg.util.ThreadUtil;

// 1、ThreadLocal是用来维护本线程的变量的，并不能解决共享变量的并发问题
// 2、ThreadLocal是各线程将值存入该线程的map中，以ThreadLocal自身作为key，
// 需要用时获得的是该线程之前存入的值。
public class ThreadLocalTest {

    private static final ThreadLocal<String> threadLocalA = new ThreadLocal<>();
    private static final ThreadLocal<String> threadLocalB = new ThreadLocal<>();

    private static final ThreadLocal<String> mThreadLocal
            = new ThreadLocal<String>();

    public static void setTrackerID(String id) {
        mThreadLocal.set(id);
    }
    public static String getTrackerID() {
        return mThreadLocal.get();
    }
    /**
     * 在调用的线程的map中存入key为ThreadLocal本身，value为在该线程设置的值
     * @param value
     */
    public static void setValueA(String value){
        threadLocalA.set(value);
    }

    public static String getValueA(){
        return threadLocalA.get();
    }

    public static void clearValueA(){
        threadLocalA.remove();
    }

    public static void setValueB(String value){
        threadLocalB.set(value);
    }

    public static String getValueB(){
        return threadLocalB.get();
    }

    public static void clearValueB(){
        threadLocalB.remove();
    }

    public static void main(String[] args) throws InterruptedException {
        //线程1的ThreadLocalMap中存着key为threadLocalA，value为A1；
        // key为threadLocalB，value为B1
        new Thread(){
            @Override
            public void run(){
                setValueA("A1111");
                System.out.println(ThreadUtil.toNameString() + "thread1:" + getValueA());
                clearValueA();

                setValueB("B1111");
                System.out.println(ThreadUtil.toNameString() + "thread1:" + getValueB());
                clearValueB();
            }
        }.start();

        Thread.sleep(2000);

        //线程2的ThreadLocalMap中存着key为threadLocalA，value为A2；
        // key为threadLocalB，value为B2
        new Thread(){
            @Override
            public void run(){
                setValueA("A2");
                System.out.println(ThreadUtil.toNameString() + " thread2:" + getValueA());
                clearValueA();

                setValueB("B2");
                System.out.println(ThreadUtil.toNameString() + " thread2:" + getValueB());
                clearValueB();
            }
        }.start();
    }

}
