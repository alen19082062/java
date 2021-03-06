package com.gg.concurrent.basic;

/**
 * 3个线程，轮流打印
 */
public class InTurnThreeThread {

    static Object obj = new Object();
    static volatile int count = 1 ;
    static boolean bRun = true ;
    final static int MAX_COUNT = 20 ;

    static class ThreadPrt extends Thread {

        // 线程序号
        int seq = 1 ;
        public void setSeq(int seq){
            this.seq = seq ;
        }

        public void run(){
            while (bRun) {
                synchronized (obj) {
                    // 随机休眠
                    int sleep_num = (int) (Math.random() * 1000);
                    try {
                        Thread.sleep(sleep_num);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    int reminder = count % 3 ;
                    if ( reminder == seq ) {
                        String name = Thread.currentThread().getName();
                        System.out.println(name + ",sleep " + sleep_num +  " , count = "  + count);
                        count++;
                    }

                    obj.notifyAll();

                    // 到达最大数，退出
                    if ( count > MAX_COUNT ){
                        bRun = false;
                        break ;
                    }
                    try {
                        obj.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }


    public static void main(String[] args) {
        ThreadPrt t1 = new ThreadPrt() ;
        t1.setName("线程1");
        t1.setSeq(1);
        t1.start();

        ThreadPrt t2 = new ThreadPrt() ;
        t2.setName("线程2");
        t2.setSeq(2);
        t2.start();

        ThreadPrt t3 = new ThreadPrt() ;
        t3.setName("线程3");
        t3.setSeq(0);
        t3.start();


//        while ( count < 100 ){
//            System.out.println( "waitting ....");
//            try {
//                Thread.sleep(1000);
//                synchronized (obj) {
//                    // 主线程等待唤醒。
//                    System.out.println("main()  " +Thread.currentThread().getName() + " notifyAll()");
//                    obj.notify();
//                }
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        }
    }
}
