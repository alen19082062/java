package com.gg.concurrent.producer_semaphore;

public class Main {

    public static void main(String[] args) {
        Storage storage = new Storage();
        Thread p1 = new Thread(new Producer(storage,"producer_1"));
        Thread p2 = new Thread(new Producer(storage,"producer_2"));
        Thread p3 = new Thread(new Producer(storage,"producer_3"));

        Thread c1 = new Thread(new Consumer(storage,"consumer_1"));
        Thread c2 = new Thread(new Consumer(storage,"consumer_1"));
        Thread c3 = new Thread(new Consumer(storage,"consumer_1"));

        p1.start();
        p2.start();
        p3.start();
        c1.start();
        c2.start();
        c3.start();
    }
}
