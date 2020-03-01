package com.gg.concurrent.juc;

import com.gg.util.ThreadUtil;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class CyclicBarrierTest {

    private static int SIZE = 5;
    private static CyclicBarrier cb;

    public static void main(String[] args) {

        System.out.println(ThreadUtil.toNameString() + "  main() start ... " );
        System.out.println(ThreadUtil.toNameString() + "  main() CyclicBarrier size : " + SIZE );

        // 参数parties指让多少个线程或者任务等待至barrier状态；这里是 5 个
        // 参数barrierAction为当这些线程都达到barrier状态时会执行的内容。
        cb = new CyclicBarrier(SIZE, new Runnable() {
            public void run() {
                System.out.println(ThreadUtil.toNameString() + " 到达 barrier 状态，执行 barrierAction ");
                System.out.println(ThreadUtil.toNameString() + " CyclicBarrier's parties is: " + cb.getParties());
                System.out.println(ThreadUtil.toNameString() + " barrierAction work for 5s ") ;
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(ThreadUtil.toNameString() + " barrierAction work end ... ") ;
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
                System.out.println(ThreadUtil.toNameString() + " before cb.await() cb = " + cb.getNumberWaiting() );

                // 将cb的参与者数量加1
                cb.await();
                System.out.println(ThreadUtil.toNameString() + " after cb.await()  cb = " + cb.getNumberWaiting() );

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

