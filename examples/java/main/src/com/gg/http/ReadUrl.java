package com.gg.http;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.Charset;

public class ReadUrl {

    /**
     *  打印 URLConnection 头部信息
     *  URL和URLConnection这两个类最大的不同在于:
     *  URLConnection提供了对HTTP首部的访问；
     *  URLConnection可以配置发送给服务器的请求参数；
     *  URLConnection除了读取服务器数据外，还可以向服务器写入数据；
     */
    public static void printConnection(URLConnection connection){
        System.out.println("Content-Type: " + connection.getContentType());
        System.out.println("Content-Length: " + connection.getContentLength());
        System.out.println("Content-LengthLong: " + connection.getContentLengthLong());
        System.out.println("Content-encoding: " + connection.getContentEncoding());
        System.out.println("Date: " + connection.getDate());
        System.out.println("Expires: " + connection.getExpiration());
        System.out.println("Last-modified: " + connection.getLastModified());
    }

    public static void printHeader(URLConnection connection) {
        for (int i = 1; ; i++) {
            String header = connection.getHeaderField(i);
            if (header == null) {
                break;
            }
            System.out.println(connection.getHeaderFieldKey(i) + ": " + header);
        }
    }

    // 直接输出 char 类型
    public static void get(URLConnection connection) {
        try {
            ReadUrl.printHeader(connection);

            InputStream in = connection.getInputStream();
            //将InputStream串链到一个Reader
            Reader reader = new InputStreamReader(in);
            int c;
            while ((c = reader.read())!= -1) {
                System.out.print((char)c);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void getString(URLConnection connection) {
        try {
            InputStream inputStream=connection.getInputStream();
            byte[] data=new byte[1024];
            StringBuffer sb = new StringBuffer();
            int length = 0;
            while ((length = inputStream.read(data)) != -1){
                String s = new String(data, Charset.forName("utf-8"));
                sb.append(s);
            }
           String message = sb.toString();
            System.out.println(message);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {

        try {
            URL url = new URL("http://www.baidu.com/");
            URLConnection connection = url.openConnection();

            System.out.println("print HTTP Header =====>>  ");
            ReadUrl.printConnection(connection);
            System.out.println("print HTTP Header Field ======>> ");
            ReadUrl.printHeader(connection);

            // ReadUrl.get(connection);
            ReadUrl.getString(connection);

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
