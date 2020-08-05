package com.shanjupay.merchant;

import java.util.Arrays;

public class Test {

    @org.junit.Test
    public void getLastIndexOf(){
        String str= "0126456.java";
        String suf = str.substring(str.lastIndexOf('.'),8);
        System.out.println(suf);
        Integer a = 128;
        Integer b = 128;
        System.out.println(a == b);
    }

    public static int testTryCatch(){
        try {
            int  i =  1 / 0;
            System.out.println(1111);
            return 1;
        } catch (Exception e){
            System.out.println(2111);
            return  2;
        }
        finally {
            System.out.println(311);
            return 3;
        }
    }

    public static int f(int value) {
        try {
            return value * value;
        } finally {
//            if (value == 2) {
//                return 0;
//            }
            value = value + 1;
        }
    }

    @org.junit.Test
    public void testTry(){
        int i = testTryCatch();
        System.out.println("return: " + i);
        System.out.println(f(2));
    }

    final int a = 3;
    @org.junit.Test
    public void testFinal(){
        final  int b = 4;
        final int[] arr = {1,2,3,5};
        arr[3] = 50;
        System.out.println(Arrays.toString(arr));
    }

}
