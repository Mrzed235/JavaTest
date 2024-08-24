package com.opencc.dynamicplan;

import java.util.Scanner;

public class LeetCode509 {
    public int fib(int n) {
        if (n == 0) {
            return 0;
        }
        if (n == 1) {
            return 1;
        }
        int[] dp = new int[n + 1];
        dp[0] = 0;
        dp[1] = 1;

        for (int i = 2; i < dp.length; i++) {
            dp[i] = dp[i - 2] + dp[i - 1];
        }
        return dp[n];
    }

    public static void main(String[] args) {
        LeetCode509 lt = new LeetCode509();
        Scanner in = new Scanner(System.in);
        lt.fib(in.nextInt());
    }
}
