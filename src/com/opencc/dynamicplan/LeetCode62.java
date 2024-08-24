package com.opencc.dynamicplan;

import java.util.Scanner;

/**
 * 不同路径
 * <a href="https://leetcode.cn/problems/unique-paths/description/">...</a>
 * 一个机器人位于一个 m x n 网格的左上角 （起始点在下图中标记为 “Start” ）。
 * 机器人每次只能向下或者向右移动一步。机器人试图达到网格的右下角（在下图中标记为 “Finish” ）。
 * 问总共有多少条不同的路径：
 * <p>
 * 输入：m = 3, n = 7
 * 输出：28
 * <p>
 * 输入：m = 3, n = 2
 * 输出：3
 */
public class LeetCode62 {
    public int uniquePaths(int m, int n) {
        //创建DP二维数组m行n列
        int[][] dp = new int[m][n];
        for (int i = 0; i < m; i++) {
            dp[i][0] = 1;
        }
        for (int i = 0; i < n; i++) {
            dp[0][i] = 1;
        }
        //因为左上角是起点，到起点的方式默认为1中，并且机器人只能向右或者向下，则dp[0][n]0行所有列只有一种方法到达，
        //同理dp[m][0] 0列所有行也只有向下这一种方式到达。推断状态转移方程为dp[i][j] = dp[i][j-1] + dp[i-1][j]的方式到达。
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                dp[i][j] = dp[i][j - 1] + dp[i - 1][j];
            }
        }
        return dp[m - 1][n - 1];
    }

    public static void main(String[] args) {
        LeetCode62 lt = new LeetCode62();
        Scanner in = new Scanner(System.in);
        int m = in.nextInt();
        int n = in.nextInt();
        System.out.println(lt.uniquePaths(m, n));
    }
}
