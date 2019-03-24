package com.gg.concurrent.test;

import com.gg.util.ThreadUtil;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class VolatileTest {
    public volatile int inc = 0;

    Lock lock = new ReentrantLock();

    /*
    // 非原子操作
    public void increase() {
        inc++;
    }
    */

    /*
    // 增加同步标志，保证函数不重入
    public synchronized void increase() {
        inc++;
    }
    */

    public  void increase() {
        lock.lock();
        try {
            inc++;
        } finally{
            lock.unlock();
        }
    }

    public static void main(String[] args) {
        final VolatileTest test = new VolatileTest();
        for(int i=0;i<10;i++){
            new Thread(){
                public void run() {
                    for(int j=0;j<1000;j++){
                        test.increase();
                    }
                    Thread t = Thread.currentThread();
                    System.out.println(ThreadUtil.toString(t) + "  inc = " + test.inc);
                };
            }.start();
        }

        //保证前面的线程都执行完
        while(Thread.activeCount()>2) {
            int count = Thread.activeCount() ;
            System.out.println("while() thread count = " + count );
            Thread.yield();
            // break ;
        }

        while(true) {
            int count = Thread.activeCount() ;
            System.out.println("while() thread count = " + count );
            if ( count > 2) {
                Thread.yield();
            }
            else {
                System.out.println("while() break " );
                break ;
            }
            // break ;
        }
        System.out.println("main() inc = " + test.inc);
    }
}
