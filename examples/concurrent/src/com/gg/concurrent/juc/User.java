package com.gg.concurrent.juc;

import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class User {
    private String name;            //用户名
    private MyCount myCount;        //所要操作的账户
    private ReadWriteLock myLock;   //执行操作所需的锁对象

    User(String name, MyCount myCount) {
        this.name = name;
        this.myCount = myCount;
        this.myLock = new ReentrantReadWriteLock();
    }

    public void getCash() {
        new Thread() {
            public void run() {
                System.out.println(Thread.currentThread().getName() +" will getCash() get readLock().lock() ");
                myLock.readLock().lock();
                try {
                    System.out.println(Thread.currentThread().getName() +"  get writeLock().lock(), getCash start");
                    myCount.getCash();
                    Thread.sleep(3);
                    System.out.println(Thread.currentThread().getName() +" getCash end");
                }
                catch (InterruptedException e) {
                    e.printStackTrace();;
                }
                finally {
                    System.out.println(Thread.currentThread().getName() +" getCash() end, readLock().unlock() ");
                    myLock.readLock().unlock();
                }
            }
        }.start();
    }

    public void setCash(final int cash) {
        new Thread() {
            public void run() {
                System.out.println(Thread.currentThread().getName() +" will setCash() get writeLock().lock() ");
                myLock.writeLock().lock();
                try {
                    System.out.println(Thread.currentThread().getName() +" get writeLock().lock(), set cash start");
                    myCount.setCash(cash);
                    Thread.sleep(3);
                    System.out.println(Thread.currentThread().getName() +" set cash ");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    System.out.println(Thread.currentThread().getName() +" setCash() end, writeLock().unlock() ");
                    myLock.writeLock().unlock();
                }
            }
        }.start();
    }
}
