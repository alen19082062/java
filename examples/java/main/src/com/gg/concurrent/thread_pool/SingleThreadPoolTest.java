package com.gg.concurrent.thread_pool;

import com.gg.util.ThreadUtil;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class SingleThreadPoolTest {
    public static void main(String[] args) {
        // 创建 只有一个线程的线程池
        ExecutorService pool = Executors.newSingleThreadExecutor();
        // 创建实现了Runnable接口对象，Thread对象当然也实现了Runnable接口
        Thread t001 = new NewThread();
        Thread t002 = new NewThread();
        Thread t003 = new NewThread();
        Thread t004 = new NewThread();
        Thread t005 = new NewThread();
        Thread t006 = new NewThread();
        Thread t007 = new NewThread();
        Thread t008 = new NewThread();

        // 将线程放入池中进行执行
        pool.execute(t001);
        pool.execute(t002);
        pool.execute(t003);
        pool.execute(t004);
        pool.execute(t005);
        pool.execute(t006);
        pool.execute(t007);
        pool.execute(t008);

        // 关闭线程池
        pool.shutdown();
    }
}

class NewThread extends Thread {

    @Override
    public void run() {
        // System.out.println(Thread.currentThread().getName()+ " is running. sleep 2s ");
        System.out.println(ThreadUtil.toNameString() + " is running. sleep 2s ");
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName()+ " end ");
    }

}
