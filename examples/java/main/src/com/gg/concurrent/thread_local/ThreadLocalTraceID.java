package com.gg.concurrent.thread_local;

import com.gg.util.ThreadUtil;

/**
 * 设置 TraceID 的例子
 */
public class ThreadLocalTraceID {

    private static final ThreadLocal<String> mThreadLocal
            = new ThreadLocal<String>();

    public static void setTrackerID(String id) {
        mThreadLocal.set(id);
    }
    public static String getTrackerID() {
        return mThreadLocal.get();
    }

    public static void clearValue(){
        mThreadLocal.remove();
    }

    public static void main(String[] args) throws InterruptedException {
        //线程1的ThreadLocalMap中存着key为threadLocalA，value为A1；
        // key为threadLocalB，value为B1
        new Thread(){
            @Override
            public void run(){
                setTrackerID("A1111");
                System.out.println(ThreadUtil.toNameString() + "thread1:" + getTrackerID());
                clearValue();

                setTrackerID("B1111");
                System.out.println(ThreadUtil.toNameString() + "thread1:" + getTrackerID());
                clearValue();
            }
        }.start();

        Thread.sleep(2000);

        //线程2的ThreadLocalMap中存着key为threadLocalA，value为A2；
        // key为threadLocalB，value为B2
        new Thread(){
            @Override
            public void run(){
                setTrackerID("A222222");
                System.out.println(ThreadUtil.toNameString() + " thread2:" + getTrackerID());
                clearValue();

                setTrackerID("B222222");
                System.out.println(ThreadUtil.toNameString() + " thread2:" + getTrackerID());
                clearValue();
            }
        }.start();
    }

}
