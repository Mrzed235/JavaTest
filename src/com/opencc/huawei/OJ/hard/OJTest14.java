package com.opencc.huawei.OJ.hard;

import java.util.Scanner;

/**
 * 通过软盘拷贝软件
 * <a href="https://app5938.acapp.acwing.com.cn/contest/11/problem/OD1017">...</a>
 * 输入：
 * 3
 * 737270
 * 737272
 * 737288
 * 输出：1474542
 * 输入：6
 * 400000
 * 200000
 * 200000
 * 200000
 * 400000
 * 400000
 * 输出：1400000
 */
public class OJTest14 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int N = in.nextInt();
        int[] data = new int[N];
        for (int i = 0; i < data.length; i++) {
            data[i] = in.nextInt();
        }
        int cap = 1474560 / 512; //块的数量 2880
        int[] dp = new int[cap + 1];
        for (int i = 0; i < data.length; i++) {
            int block = (data[i] + 511) / 512; //737270-->1440
            for (int j = cap; j >= block; j--) {
                dp[j] = Math.max(dp[j], dp[j - block] + data[i]);
            }
        }
        System.out.println(dp[cap]);
    }
}
