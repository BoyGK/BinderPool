package com.nullpt.lib;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

//最小的k个数(优先队列实现)
public class MyClass5 {

    public static void main(String[] args) {
        System.out.println(findsmK(new int[]{1, 2, 3, 5, 6, 8, 9}, 4));

    }

    private static List<Integer> findsmK(int[] arr, int k) {
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(k, new Comparator<Integer>() {
            @Override
            public int compare(Integer integer, Integer t1) {
                return t1.compareTo(integer);
            }
        });
        for (int i = 0; i < arr.length; i++) {
            if (maxHeap.size() != k) {
                maxHeap.offer(arr[i]);
            } else if (maxHeap.peek() > arr[i]) {
                maxHeap.poll();
                maxHeap.offer(arr[i]);
            }
        }
        return Arrays.asList(maxHeap.toArray(new Integer[0]));
    }

}
