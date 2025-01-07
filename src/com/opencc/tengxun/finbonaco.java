package com.opencc.tengxun;

import java.math.BigDecimal;
import java.math.BigInteger;

public class finbonaco {

    public static BigInteger[] dp = new BigInteger[100 + 1];

    public static void fibonacoo(int n) {
        dp[0] = BigInteger.valueOf(1);
        dp[1] = BigInteger.valueOf(1);
        for (int i = 2; i < n; i++) {
            dp[i] = dp[i - 1].add(dp[i - 2]);
        }
    }

    public static void main(String[] args) {
        int n = 100;
        fibonacoo(n);
        for (int i = 0; i < n; i++) {
            System.out.println(dp[i]);
        }
    }
}
