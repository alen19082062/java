package com.gg.concurrent.basic;

/**
 * 2个线程，轮流打印
 */
public class InTurnTwoThread {

    static Object obj = new Object();
    static volatile int count = 1 ;
    static boolean bRun = true ;

    static class ThreadPrt extends Thread {
        public void run(){
            while (bRun) {
                synchronized (obj) {
                    obj.notify();

                    String name = Thread.currentThread().getName() ;
                    System.out.println(name + " : " + count);
                    count++;

                    if ( count <= 20 ) {
                        try {
                            obj.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    else {
                        bRun = false  ;
                    }
                }
            }
        }
    }


    public static void main(String[] args) {
        ThreadPrt odd = new ThreadPrt() ;
        odd.setName("线程1");
        odd.start();

        ThreadPrt even = new ThreadPrt() ;
        even.setName("线程2");
        even.start();

    }
}
