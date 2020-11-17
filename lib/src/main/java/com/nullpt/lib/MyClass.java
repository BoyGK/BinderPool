package com.nullpt.lib;

import java.util.ArrayList;
import java.util.Arrays;

import kotlinx.coroutines.GlobalScope;

//字符串全排列
public class MyClass {


    public static void main(String[] args) {
        System.out.println(Arrays.toString(list("abc").toArray()));
    }

    private static ArrayList<String> list(String str) {
        ArrayList<String> res = new ArrayList<>();
        xyz(0, str, res);
        return res;
    }

    private static void xyz(int pos, String str, ArrayList<String> res) {
        if (pos + 1 == str.length()) {
            System.out.println("last->" + str);
            res.add(str);
            return;
        }
        for (int i = pos; i < str.length(); i++) {
            str = swap(str, pos, i);
            System.out.println("A->" + str);
            xyz(pos + 1, str, res);
            str = swap(str, pos, i);
            System.out.println("B->" + str);
        }
    }

    private static String swap(String s, int i, int j) {
        String temp = s.charAt(i) + "";
        StringBuilder builder = new StringBuilder(s);
        builder.replace(i, i + 1, s.charAt(j) + "");
        builder.replace(j, j + 1, temp);
        return builder.toString();
    }

}
