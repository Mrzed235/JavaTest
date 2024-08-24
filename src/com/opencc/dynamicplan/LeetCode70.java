package com.opencc.dynamicplan;

import java.util.Scanner;

/**
 * <a href="https://leetcode.cn/problems/climbing-stairs/description/">...</a>
 * 假设你正在爬楼梯。需要 n 阶你才能到达楼顶。
 * 每次你可以爬 1 或 2 个台阶。你有多少种不同的方法可以爬到楼顶呢？
 * 示例 1：
 * 输入：n = 2
 * 输出：2
 * 解释：有两种方法可以爬到楼顶。
 * 1. 1 阶 + 1 阶
 * 2. 2 阶
 * 示例 2：
 * 输入：n = 3
 * 输出：3
 * 解释：有三种方法可以爬到楼顶。
 * 1. 1 阶 + 1 阶 + 1 阶
 * 2. 1 阶 + 2 阶
 * 3. 2 阶 + 1 阶
 */
public class LeetCode70 {
    public int climbStairs(int n) {
        if (n == 1) {
            return 1;
        }
        int[] dp = new int[n + 1];
        dp[1] = 1;
        dp[2] = 2;

        for (int i = 3; i < dp.length; i++) {
            dp[i] = dp[i - 2] + dp[i - 1];
        }
        return dp[n];
    }
    public int climbStairs2(int n) {
        double d = Math.sqrt(5);
        return (int)Math.round(Math.pow((1 + d) / 2, n + 1) / d);
    }

    public static void main(String[] args) {
        LeetCode70 lt = new LeetCode70();
        Scanner in = new Scanner(System.in);
        lt.climbStairs(in.nextInt());
    }
}
