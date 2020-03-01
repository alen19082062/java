package com.gg.concurrent.juc;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class CopyOnWriteArrayListTest2 {

    public static void main(String[] args) throws InterruptedException {
        List<String> list = new ArrayList<String>();
        list.add("aaa");
        list.add("bbb");
        list.add("ccc");

        final CopyOnWriteArrayList<String> cowList = new CopyOnWriteArrayList<String>(list);

        Thread t = new Thread(new Runnable() {
            int count = 1;
            @Override
            public void run() {
                while (true) {
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    cowList.add("new_item_" +  count++  );
                }
            }
        });
        t.setDaemon(true);
        t.start();

        Thread.currentThread().sleep(1000);
        System.out.println("cowList's hashcode : " + cowList.hashCode());
        for (String s : cowList) {
            System.out.println(s);
        }
    }
}
