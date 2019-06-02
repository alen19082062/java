package com.gg.concurrent.juc;

import com.gg.util.ThreadUtil;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class CyclicBarrierTest {

    private static int SIZE = 5;
    private static CyclicBarrier cb;

    public static void main(String[] args) {

        System.out.println(ThreadUtil.toNameString() + "  main() start ... " );

        // 参数parties指让多少个线程或者任务等待至barrier状态；
        // 参数barrierAction为当这些线程都达到barrier状态时会执行的内容。
        cb = new CyclicBarrier(SIZE, new Runnable() {
            public void run() {
                System.out.println(ThreadUtil.toNameString() + " 等待 5s ") ;
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(ThreadUtil.toNameString() + " 到达 barrier 状态，CyclicBarrier's parties is: " + cb.getParties());
            }
        });

        // 新建5个任务
        for (int i = 0; i < SIZE; i++)
            new InnerThread().start();
    }

    static class InnerThread extends Thread {
        public void run() {
            try {
                System.out.println(ThreadUtil.toNameString() + " wait for CyclicBarrier.");

                // 将cb的参与者数量加1
                cb.await();

                // cb的参与者数量等于5时，才继续往后执行
                System.out.println(ThreadUtil.toNameString() + " continued.");

            } catch (BrokenBarrierException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

