package com.nullpt.lib;

import java.util.Arrays;

//合并两个数组
public class MyClass6 {
    public static void main(String[] args) {

        int[] a = {3, 4, 5, 6, 0, 0, 0, 0, 0};
        int[] b = {1, 2, 3, 4};
        merge(a, 4, b, 4);
        System.out.println(Arrays.toString(a));
    }

    public static void merge(int[] nums1, int m, int[] nums2, int n) {
        int i = m - 1, j = n - 1, k = m + n - 1;
        while (j >= 0) {
            nums1[k--] = i >= 0 && nums1[i] > nums2[j] ? nums1[i--] : nums2[j--];
        }
    }
}
