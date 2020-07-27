package com.shanjupay.merchant;

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

}
