package com.nullpt.lib;

import static java.lang.Math.min;

//换钱最少货币数
public class MyClass7 {

    public static void main(String[] args) {
        int aim = 20;
        Integer[] coins = {2, 3, 5};
        getCount(coins, aim);
    }

    private static void getCount(Integer[] arr, int aim) {
        int[] dp = new int[aim + 1];
        for (int i = 1; i < dp.length; i++) {
            dp[i] = Integer.MAX_VALUE;
        }
        for (Integer integer : arr) {
            for (int j = 0; j <= aim - integer; j++) {
                if (dp[j] != Integer.MAX_VALUE) {
                    dp[j + integer] = min(dp[j + integer], dp[j] + 1);
                }
            }
        }
        if (dp[aim] == Integer.MAX_VALUE) {
            System.out.println(-1);
        } else {
            System.out.println(dp[aim]);
        }
    }
}
