package com.opencc.huawei.OJ.medium;


import java.util.Scanner;

/**
 * 最大报酬
 * <a href="https://www.nowcoder.com/issue/tutorial?zhuanlanId=MgbyJj&uuid=ed82cb5db31749f6ae9435bed7a8c576">...</a>
 */
public class OJTest7 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int timeCount = in.nextInt();
        int jobNums = in.nextInt();
        in.nextLine();
        int[] costTimes = new int[jobNums];
        int[] rewards = new int[jobNums];
        for (int i = 0; i < jobNums; i++) {
            String[] inputs = in.nextLine().split(" ");
            costTimes[i] = Integer.parseInt(inputs[0]);
            rewards[i] = Integer.parseInt(inputs[1]);
        }
        calculateReward(jobNums, timeCount, costTimes, rewards);
        calculateReward2(jobNums, timeCount, costTimes, rewards);
    }

    private static void calculateReward(int jobNums, int timeCount, int[] costTimes, int[] rewards) {
        //实际上是一个动态规划的问题，类似01背包问题。 n项工作，选择做哪项或者不做
        //定义一个dp[jobNums+1][timeCount+1], 数组的行代表的是工作时长，从0开始，列代表的是工作的数量，dp[i][j]的值是获得的报酬；
        int[][] dp = new int[jobNums + 1][timeCount + 1];
        for (int i = 1; i < jobNums + 1; i++) {
            for (int j = 1; j < timeCount + 1; j++) {
                dp[i][j] = dp[i - 1][j]; // 不选择当前工作的情况
                if (j >= costTimes[i - 1]) {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i - 1][j - costTimes[i - 1]] + rewards[i - 1]);
                }
            }
        }
        System.out.println(dp[jobNums][timeCount]);
    }

    private static void calculateReward2(int jobNums, int timeCount, int[] costTimes, int[] rewards) {
        //一维dp解法
        int[] dp = new int[timeCount + 1];
        for (int i = 0; i < jobNums; i++) {
            for (int j = timeCount; j >= costTimes[i]; j--) {
                dp[j] = Math.max(dp[j], dp[j - costTimes[i]] + rewards[i]);
            }
        }
        System.out.println(dp[timeCount]);
    }
}
