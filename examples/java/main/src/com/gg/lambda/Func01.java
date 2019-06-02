package com.gg.lambda;

import java.util.Arrays;

public class Func01 {

    public static void main(String[] args) {
        System.out.println("test case 1 --> ");
        String separator = ",";

        final int i = 1 ;
        Arrays.asList("a","b","c").forEach((String e) -> {
            System.out.print(e + separator);
            System.out.println(e);
        });

        //
        System.out.println("test case 2 --> ");
        Arrays.asList( "e", "f", "g" ).forEach(
                ( String str ) -> System.out.println( str + " ," ) );
    }
}
