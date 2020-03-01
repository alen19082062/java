package com.gg.concurrent.juc;

import com.gg.util.ThreadUtil;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;

public class FutureTaskTest2 {
    public static void main(String[] args) {
//        //第一种方式
//        ExecutorService executor = Executors.newCachedThreadPool();
//        Task2 task = new Task2();
//        FutureTask<Integer> futureTask = new FutureTask<Integer>(task);
//        executor.submit(futureTask);
//        executor.shutdown();

        System.out.println(ThreadUtil.toNameString()
                + " executor.submit()，提交 futureTask ...");

        //第二种方式，注意这种方式和第一种方式效果是类似的，
        // 只不过一个使用的是ExecutorService，一个使用的是Thread
        Task task = new Task();
        FutureTask<Integer> futureTask = new FutureTask<Integer>(task);
        Thread thread = new Thread(futureTask);
        thread.start();

        System.out.println(ThreadUtil.toNameString() + " 主线程 sleep 1s ");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e1) {
            e1.printStackTrace();
        }

        System.out.println(ThreadUtil.toNameString()
                + " 主线程等待 task2 运行结果  ");
        try {
            System.out.println(ThreadUtil.toNameString()
                    + " task2 运行结果 : "+futureTask.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        System.out.println("所有任务执行完毕");
    }
}

