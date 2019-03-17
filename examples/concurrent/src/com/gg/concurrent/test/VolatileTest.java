package com.gg.concurrent.test;

public class VolatileTest {
    public volatile int inc = 0;

    public void increase() {
        inc++;
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
                    System.out.println("当前线程名字：" + t.getName() + " 当前线程的优先级别为："+ t.getPriority() + " ID:" + t.getId() + ", inc = " + test.inc);
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
