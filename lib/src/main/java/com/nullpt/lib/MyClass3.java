package com.nullpt.lib;

//第K大的数/第K小的数
public class MyClass3 {

    public static void main(String[] args) {
        //第k大
        System.out.println(findk(new int[]{1, 2, 5, 6, 9, 8, 4, 6, 5, 1}, 0, 9, 3));
    }

    private static int partn(int[] arr, int left, int right) {
        int p = arr[left];
        while (left < right) {
            while (left < right && arr[right] <= p) {
                right--;
            }
            arr[left] = arr[right];
            while (left < right && arr[left] >= p) {
                left++;
            }
            arr[right] = arr[left];


        }
        arr[left] = p;
        return left;
    }

    private static int findk(int[] arr, int left, int right, int k) {
        if (left <= right) {
            int p = partn(arr, left, right);
            if (p == k - 1) {
                return arr[p];
            } else if (p < k - 1) {
                return findk(arr, p + 1, right, k);
            } else {
                return findk(arr, left, p - 1, k);
            }
        }
        return -1;
    }

}
