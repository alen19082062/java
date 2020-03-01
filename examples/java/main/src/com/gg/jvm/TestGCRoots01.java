package com.gg.jvm;

/**
 * -Xms1024m -Xmx1024m -Xmn512m -XX:+PrintGCDetails
 */
public class TestGCRoots01 {
    private int _10MB = 10 * 1024 * 1024;
    private byte[] memory = new byte[10 * _10MB];

    public static void main(String[] args) {
        System.out.println("main() begin ");
        method01();
        System.out.println("main() .... ");
        System.gc();
        System.out.println("第二次GC完成");
    }

    public static void method01() {
        System.out.println("method01() begin ");
        TestGCRoots01 t = new TestGCRoots01();
        System.gc();
        System.out.println("第一次GC完成");
        System.out.println("method01() end ");
    }
}

