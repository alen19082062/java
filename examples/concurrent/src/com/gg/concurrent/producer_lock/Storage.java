package com.gg.concurrent.producer_lock;

import java.util.LinkedList;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Condition除了支持上面的功能之外，它更强大的地方在于：能够更加精细的控制多线程的休眠与唤醒。
 * 对于同一个锁，我们可以创建多个Condition，在不同的情况下使用不同的Condition。
 *
 * 例如，假如多线程读/写同一个缓冲区：当向缓冲区中写入数据之后，唤醒"读线程"；当从缓冲区读出数据之后，
 * 唤醒"写线程"；并且当缓冲区满的时候，"写线程"需要等待；当缓冲区为空时，"读线程"需要等待。
 * 如果采用Object类中的wait(), notify(), notifyAll()实现该缓冲区，
 * 当向缓冲区写入数据之后需要唤醒"读线程"时， 不可能通过notify()或notifyAll()明确的指定唤醒"读线程"，
 * 而只能通过notifyAll唤醒所有线程(但是notifyAll无法区分唤醒的线程是读线程，还是写线程)。
 * 但是，通过Condition，就能明确的指定唤醒读线程。
 */
public class Storage {

    // 仓库最大存储量
    private final int MAX_SIZE = 10;
    // 仓库存储的载体
    private LinkedList<Object> list = new LinkedList<Object>();
    // 锁
    private final Lock lock = new ReentrantLock();
    // 仓库满的条件变量
    private final Condition fullCondtion  = lock.newCondition();
    // 仓库空的条件变量
    private final Condition emptyCondtion  = lock.newCondition();

    public void produce()
    {
        // 获得锁
        lock.lock();
        while (list.size() + 1 > MAX_SIZE) {
            System.out.println("【生产者" + Thread.currentThread().getName()   + "】仓库已满");
            try {
                // 由于仓库已满，休息，等待唤醒
                fullCondtion .await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        list.add(new Object());
        System.out.println("【生产者" + Thread.currentThread().getName() + "】生产一个产品，现库存" + list.size());

        // 唤醒其他所有线程、释放锁
        // fullCondtion .signalAll();
        emptyCondtion .signalAll();
        lock.unlock();
    }

    public void consume()
    {
        // 获得锁
        lock.lock();
        while (list.size() == 0) {
            System.out.println("【消费者" + Thread.currentThread().getName()
                    + "】仓库为空");
            try {
                emptyCondtion.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        list.remove();
        System.out.println("【消费者" + Thread.currentThread().getName()
                + "】消费一个产品，现库存" + list.size());

        // 唤醒其他所有线程、释放锁
        // fullCondtion .signalAll();
        emptyCondtion .signalAll();
        lock.unlock();
    }
}

