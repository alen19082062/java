package com.gg.concurrent.thread_pool;

import com.gg.util.ThreadUtil;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class CustomThreadPoolExecutor  {
    public volatile int inc = 1 ;
    public volatile int inc2 = 1 ;
    Lock lock = new ReentrantLock();

    private static final int CPU_COUNT = 15 ;
    private static final int COPE_POOL_SIZE = CPU_COUNT+1;
    private static final int MAXIMUM_POOL_SIZE =CPU_COUNT*2+1;
    private static final int KEEP_ALIVE =1;

    private static final ThreadFactory mThreadFactory = new ThreadFactory() {
        private final AtomicInteger mInteger = new AtomicInteger(1);
        @Override
        public Thread newThread(Runnable r) {
            return new Thread(r,"AsyncTask #"+mInteger.getAndIncrement());
        }
    };
    // 创建一个可重用固定线程数的线程池
    private static final BlockingDeque<Runnable> mWorkQueue = new LinkedBlockingDeque<>(128);
    public static ExecutorService pool ;

    public void init() {
        pool = new ThreadPoolExecutor(
                COPE_POOL_SIZE,
                MAXIMUM_POOL_SIZE,
                KEEP_ALIVE,
                TimeUnit.SECONDS,
                mWorkQueue,
                mThreadFactory,
                new CustomRejectedExecutionHandler() );
    }

    private class CustomRejectedExecutionHandler implements RejectedExecutionHandler {
        @Override
        public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
            // 记录异常
            // 报警处理等
            System.out.println("rejectedExecution() error.............");
        }
    }

    public ExecutorService getCustomThreadPoolExecutor() {
        return this.pool;
    }

    public  void increase() {
        lock.lock();
        try {
            inc++;
            System.out.println("increase() inc = " + inc);
        } finally{
            lock.unlock();
        }
    }

    public synchronized void increase2() {
        inc2++;
        System.out.println("increase2() inc2 = " + inc2);
    }

    public static void main(String[] args) {

        CustomThreadPoolExecutor exec = new CustomThreadPoolExecutor();
        // 1.初始化
        exec.init();
        ExecutorService pool = exec.getCustomThreadPoolExecutor();
        for(int i=1; i<100; i++) {
            System.out.println("提交第" + i + "个任务!");
            pool.execute(new Runnable() {
                @Override
                public void run() {
                    try {
                        System.out.println(ThreadUtil.toNameString() + " Thread running=====  ");
                        exec.increase();
                        exec.increase2();
                        System.out.println("Thread sleep 1s ...");
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println(ThreadUtil.toNameString() + " Thread exit ");
                }
            });
        }

        // 2.销毁----此处不能销毁,因为任务没有提交执行完,如果销毁线程池,
        // 任务也就无法执行了
        // exec.destory();
        System.out.println("main() " + ThreadUtil.toNameString() + " exit , inc = " + exec.inc);

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
