package com.gg.util;

public class ThreadUtil {
    public static void prt(Thread t){
        System.out.println("当前线程：" + t.getName() + " / " + t.getId() + " 优先级别为："+ t.getPriority() );
    }

    public static String toString(Thread t){
        String str = "[" + t.getName() + " / " + t.getId() + "  poirity ："+ t.getPriority() + " ]" ;
        return str ;
    }
}
