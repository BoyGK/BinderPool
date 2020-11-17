package com.nullpt.lib;

//二进制1的个数
public class MyClass4 {

    public static void main(String[] args) {

        int val = 123456;
        int count = 0;
        while (val != 0) {
            ++count;
            val = val & (val - 1);
        }

        System.out.println(count);

    }

}
