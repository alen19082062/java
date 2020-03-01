package com.gg.concurrent.atomic;

import com.gg.util.ThreadUtil;

import java.util.concurrent.atomic.AtomicInteger;

// 操作不具备原子性
// 这里运行了20个线程，每个线程对count变量进行1000此自增操作，
// 如果上面这段代码能够正常并发的话，最后的结果应该是20000才对，
// 但实际结果却发现每次运行的结果都不相同，都是一个小于20000的数字。
public class AtomicIntegerTest2 {
    private static final int THREADS_CONUT = 20;
    // public static int count = 0;
    public static AtomicInteger count = new AtomicInteger(0);

    public static void increase() {
        count.incrementAndGet();
        // count++;
    }

    public static void main(String[] args) throws InterruptedException {
        System.out.println(ThreadUtil.toNameString() + " main() running ...");

        Thread[] threads = new Thread[THREADS_CONUT];
        for (int i = 0; i < THREADS_CONUT; i++) {
            threads[i] = new Thread(new Runnable() {
                @Override
                public void run() {
                    System.out.println(ThreadUtil.toNameString() + " begin ...");
                    for (int i = 0; i < 1000; i++) {
                        increase();
                    }
                    // 加了这句，可以返回 20000
                    // System.out.println(ThreadUtil.toNameString() + " end ...");
                }
            });
            threads[i].start();
        }

        // System.out.println("main()  Thread.activeCount() = " + Thread.activeCount());

        // 为什么idea返回2，是因为多了个monitor ctrlbreak线程。
        while (Thread.activeCount() > 2) {
            // System.out.println("main()  Thread.activeCount() = " + Thread.activeCount());
            // Thread.sleep(1000);
            Thread.yield();
        }
        System.out.println("main() sleep ...");
        // Thread.sleep(5000);
        System.out.println(count);
        System.out.println("main() end ...");
    }

}
