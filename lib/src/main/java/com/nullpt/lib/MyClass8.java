package com.nullpt.lib;

public class MyClass8 {

    public static void main(String[] args) {
        String a = "123";
        String b = "123";
        String c = "123";
        String d = c;
        String e = "123456";
        String f = a + "456";
        System.out.println(e == f);
    }

}
