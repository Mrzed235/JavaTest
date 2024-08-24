package com.opencc.compratorTest;

import java.util.Arrays;

/**
 * 用递归实现翻转数组
 */
public class ReverseArray {
    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 4, 5};
        System.out.println(Arrays.toString(reverseArray(arr, 0, arr.length - 1)));
        readArray(arr, 0, arr.length - 1);
    }

    public static int[] reverseArray(int[] array, int start, int end) {
        //todo  设置终止条件
        if (start >= end) {
            return array;
        }
        int temp = array[start];
        array[start] = array[end];
        array[end] = temp;
        return reverseArray(array, start + 1, end - 1);
    }

    public static int[] readArray(int[] array, int start, int end) {
        if (start > end) {
            return array;
        }
        System.out.println(array[start]);
        return readArray(array, start + 1, end);
    }
}