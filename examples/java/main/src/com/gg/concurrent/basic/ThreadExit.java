package com.gg.concurrent.basic;

public class ThreadExit {

    /**
     * 如何确保main()方法所在的线程是Java 程序最后结束的线程？
     *
     * 我们可以使用Thread类的join()方法来确保所有程序创建的线程在main()方法退出前结束。
     * 使主线程等待子线程执行完成后再执行，换句话说就是将线程的并行执行变为串行执行。
     * @param args
     * @throws InterruptedException
     */
    public static void main(String[] args) throws InterruptedException {
        Thread.sleep(300);

        // 构造函数的原型
        // public Thread(Runnable target, String name)
        //
        Thread thread1 = new Thread(() -> {
            // System.out.println(Thread.currentThread().getName());
            Thread t = Thread.currentThread();
            System.out.println("当前线程名字：" + t.getName() + " 当前线程的优先级别为："+ t.getPriority() + " ID:" + t.getId() );
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "thread1");

        Thread thread2 = new Thread(() -> {
            Thread t = Thread.currentThread();
            System.out.println("当前线程名字：" + t.getName() + " 当前线程的优先级别为："+ t.getPriority() + " ID:" + t.getId() );
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "thread2");

        Thread thread3 = new Thread(() -> {
            Thread t = Thread.currentThread();
            System.out.println("当前线程名字：" + t.getName() + " 当前线程的优先级别为："+ t.getPriority() + " ID:" + t.getId() );
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "thread3");

        // 最少的代码拉起一个线程 {} 是代码内容，也可以写入一个函数
        new Thread(() -> {
            System.out.println("最少的代码拉起一个线程，Thread : t4 ");
            Thread t = Thread.currentThread();
            System.out.println("当前线程名字：" + t.getName() + " 当前线程的优先级别为："+ t.getPriority() + " ID:" + t.getId() );
        },"t4" ).start();

        thread2.start();
        //thread2.join();
        thread1.start();
        //thread1.yield();
        // Thread.currentThread().yield();
        // thread1.join();
        thread3.start();
        thread3.join();
        System.out.println("main:" + Thread.currentThread().getName());


    }

}
