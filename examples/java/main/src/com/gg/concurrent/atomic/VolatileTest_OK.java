package com.gg.concurrent.atomic;

import com.gg.util.ThreadUtil;

public class VolatileTest_OK {
    // 此处修改
    volatile int result;

    public int getResult() {
        return result;
    }

    // 此处去掉  synchronized
    public void setResult(int result) {
        this.result = result;
    }

    public static void main(String[] args) {
        System.out.println("main()  " + ThreadUtil.toNameString() + " ");

        VolatileTest_OK threadSafeCache = new VolatileTest_OK();

        for (int i = 0; i < 8; i++) {
            new Thread(() -> {
                int x = 0;
                while (threadSafeCache.getResult() < 100) {
                    x++;
                }
                System.out.println(ThreadUtil.toNameString() + " x = " + x);
            }).start();
        }

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        threadSafeCache.setResult(200);
    }
}
