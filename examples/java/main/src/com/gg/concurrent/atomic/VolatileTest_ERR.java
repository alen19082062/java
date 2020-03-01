package com.gg.concurrent.atomic;

import com.gg.util.ThreadUtil;

// 如果 result 不是 volatile , 操作不可见
public class VolatileTest_ERR {
    int result;

    public int getResult() {
        return result;
    }

    public synchronized void setResult(int result) {
        this.result = result;
    }

    public static void main(String[] args) {
        System.out.println("main()  " + ThreadUtil.toNameString() + " ");

        VolatileTest_ERR threadSafeCache = new VolatileTest_ERR();

        for (int i = 0; i < 8; i++) {
            new Thread(() -> {
                int x = 0;
                while (threadSafeCache.getResult() < 100) {
                    x++;

                    // 加上这句，可以工作，其实这句是主动调度一下
//                    try {
//                        Thread.sleep(0);
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }
                }
                System.out.println(ThreadUtil.toNameString() + " x = " + x);
            }).start();
        }

        System.out.println("main()  sleep 1s ");

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        threadSafeCache.setResult(200);
    }
}
