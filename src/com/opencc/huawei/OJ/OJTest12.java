package com.opencc.huawei.OJ;

import java.util.Scanner;

/**
 * 异国人的幸运数字
 * <a href="https://app5938.acapp.acwing.com.cn/contest/1/problem/OD1003">...</a>
 * 进制转换：
 * 10 2 4
 * 2
 */
public class OJTest12 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int k = in.nextInt();
        int n = in.nextInt();
        int m = in.nextInt();
        //n是幸运数字
        System.out.println(findLuckyNum(k, n, m));
    }

    private static int findLuckyNum(int k, int n, int m) {
        if (n == m) {
            return 0;
        }
        StringBuilder sb = new StringBuilder();
        while (k > 0) {
            sb.append(k % m);
            k = k / m;
        }
        sb.reverse();
        int count = 0;
        String result = sb.toString();
        for (int i = 0; i < result.length(); i++) {
            if (Integer.parseInt(result.substring(i, i + 1)) == n) {
                count++;
            }
        }
        return count;
    }

    private static int findLuckyNum2(int k, int n, int m) {
        int count = 0;
        while (k > 0) {
            if (k % m == n) {
                count++;
            }
            k /= m;
        }
        return count;
    }
}
