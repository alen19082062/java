package com.gg.jvm;

import java.net.URL;

public class ClassLoaderTest {

    public static void main(String[] args) {
        ClassLoader c  = ClassLoaderTest.class.getClassLoader();  //获取Test类的类加载器
        System.out.println(c);
        c  = Thread.currentThread().getContextClassLoader();
        System.out.println(c);
        System.out.println("class path :");
        String extDirs = System.getProperty("java.ext.dirs");
        for (String path : extDirs.split(";")) {
            System.out.println(path);
        }

        ClassLoader c1 = c.getParent();  //获取c这个类加载器的父类加载器
        System.out.println(c1);

        // Bootstrap Loader是用C++语言写的，依java的观点来看，
        // 逻辑上并不存在Bootstrap Loader的类实体，
        // 所以在java程序代码里试图打印出其内容时，我们就会看到输出为null。
        ClassLoader c2 = c1.getParent();//获取c1这个类加载器的父类加载器
        System.out.println(c2);

        System.out.println("BootstrapClassLoader 's load url ") ;
        URL[] urLs = sun.misc.Launcher.getBootstrapClassPath().getURLs();
        for (URL url : urLs) {
            System.out.println(url.toExternalForm());
        }

        System.out.print("com.gg.jvm.ClassLoaderTest 's classloader : ");
        // System.out.println("sun.security.ec.SunEC 's classloader  :");
        // ClassLoader classLoader = sun.security.ec.SunEC.class.getClassLoader();
        ClassLoader classLoader = com.gg.jvm.ClassLoaderTest.class.getClassLoader();
        System.out.println(classLoader);
        System.out.println("parent : " +  classLoader.getParent());

    }
}
