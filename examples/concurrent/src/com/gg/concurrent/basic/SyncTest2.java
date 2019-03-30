package com.gg.concurrent.basic;

import com.gg.util.ThreadUtil;

/**
 * 这里其实2个synchronized方法里面的Thread.sheep其实要不要是无所谓的，估计是就为混淆增加难度。
 * 3步骤执行的时候其实很快子线程也准备好了，但是由于synchronized的存在，并且是作用同一对象，
 * 所以子线程就只有必须等待了。
 * 由于main方法里面执行顺序是顺序执行的，所以必须是步骤3执行完成之后才可以到4步骤，
 * 而由于3步骤执行完成，子线程就可以执行m1了。
 */
public class SyncTest2  implements Runnable {

    int b = 100;

    synchronized void m1() throws InterruptedException {
        b = 1000;
        Thread t = Thread.currentThread();
        System.out.println(ThreadUtil.toString(t) +  " m1() begin sleep 5s");
        Thread.sleep(5000); //6
        System.out.println(ThreadUtil.toString(t) +  " m1() end sleep ");
        System.out.println(ThreadUtil.toString(t) +  "m1() b=" + b);
    }

    synchronized void m2() throws InterruptedException {
        Thread t = Thread.currentThread();
        System.out.println(ThreadUtil.toString(t) +  " m2() begin sleep 2s");
        Thread.sleep(2000); //5
        b = 2000;
        System.out.println(ThreadUtil.toString(t) + " m2() end sleep ");
        System.out.println(ThreadUtil.toString(t) + " m2() b=" + b);
    }

    // main() 主线程有同步块，抢到了一定要执行完成
    public static void main(String[] args) throws InterruptedException {
        Thread t_now = Thread.currentThread();
        System.out.println(ThreadUtil.toString(t_now) +  " main() ");

        SyncTest2 tt = new SyncTest2();
        Thread t = new Thread(tt);  // 1
        t.start(); // 2 ，执行 m1()
        tt.m2(); // 3
        System.out.println("main thread b=" + tt.b); // 4
    }

    @Override
    public void run() {
        try {
            m1();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
