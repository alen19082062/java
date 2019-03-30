package com.gg.concurrent.juc;
import com.gg.util.ThreadUtil;
import java.util.concurrent.*;

public class FutureTest {
    public static void main(String[] args) {
        ExecutorService executor = Executors.newCachedThreadPool();
        Task task = new Task();
        Future<Integer> result = executor.submit(task);
        executor.shutdown();

        System.out.println(ThreadUtil.toNameString() + " begin sleep ...");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e1) {
            e1.printStackTrace();
        }
        System.out.println(ThreadUtil.toNameString() + " running ...");

        try {
            System.out.println(ThreadUtil.toNameString() + " task运行结果 : "+result.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        System.out.println(ThreadUtil.toNameString() + " 所有任务执行完毕");
    }
}
class Task implements Callable<Integer> {
    @Override
    public Integer call() throws Exception {
        System.out.println(ThreadUtil.toNameString() + " 子线程在进行计算");
        Thread.sleep(3000);
        int sum = 0;
        for(int i=0;i<100;i++)
            sum += i;
        return sum;
    }
}
