package com.opencc.huawei.OJ;

import java.util.*;

/**
 * 最小配对和
 * <a href="https://app5938.acapp.acwing.com.cn/contest/1/problem/OD1001">...</a>
 * 输入：
 * 3 1 1 2
 * 3 1 2 3
 * 2
 * 输出：
 * 4
 */
public class OJTest11 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int M = in.nextInt();
        int[] array1 =  new int[M];
        for (int i = 0; i < array1.length; i++) {
            array1[i] = in.nextInt();
        }
        int N = in.nextInt();
        int[] array2 = new int[N];
        for (int i = 0; i < array2.length; i++) {
            array2[i] = in.nextInt();
        }
        int k = in.nextInt();
        System.out.println(solvePair(array1, array2 ,k));
    }

    private static int solvePair(int[] array1, int[] array2, int k) {
        List<Integer> list = new ArrayList<>();
        for (int value : array1) {
            for (int i : array2) {
                list.add(value + i);
            }
        }
        Collections.sort(list);
        int count = 0;
        int sz = list.size();
        for (int i = 0; i < Math.min(k,sz); i++) {
            count+=list.get(i);
        }
        return count;
    }
}
