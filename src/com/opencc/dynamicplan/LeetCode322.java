package com.opencc.dynamicplan;

import com.opencc.LeetCodeTest.Common;

import java.util.Arrays;
import java.util.Map;
import java.util.Scanner;

/**
 * 零钱兑换
 * 给你一个整数数组 coins ，表示不同面额的硬币；以及一个整数 amount ，表示总金额。
 * 计算并返回可以凑成总金额所需的 最少的硬币个数 。如果没有任何一种硬币组合能组成总金额，返回 -1 。
 * 你可以认为每种硬币的数量是无限的。
 * 示例 1：
 * 输入：coins = [1, 2, 5], amount = 11
 * [1, 2, 5]
 * 11
 * 输出：3
 * 解释：11 = 5 + 5 + 1
 * 示例 2：
 * 输入：coins = [2], amount = 3
 * 输出：-1
 * <a href="https://leetcode.cn/problems/coin-change/">...</a>
 */
public class LeetCode322 {
    /**
     * 以示例1来举例：
     * 假如要凑成的硬币是11，则能凑成11元硬币需要的最少硬币个数为，凑成10元硬币个数+1或者凑成9元硬币个数+1或者6元硬币个数+1；
     * 可得状态转移方程为 dp[amount] = min(dp[amount - coins[0]],dp[amount - coins[1]],...,dp[amount - coins[n - 1]]) + 1的个数一样
     * dp[0] = 0; dp[1] = min(dp[1 - dp[0]]) + 1 并且当coins[j] > dp[i]时凑不成硬币。
     *
     * @param coins
     * @param amount
     * @return
     */
    public int coinChange(int[] coins, int amount) {
        int[] dp = new int[amount + 1];
        int max = amount + 1;
        Arrays.fill(dp, max);
        dp[0] = 0;
        for (int i = 1; i < amount + 1; i++) {
            for (int j = 0; j < coins.length; j++) {
                if (coins[j] <= i) {
                    dp[i] = Math.min(dp[i], dp[i - coins[j]] + 1);
                }
            }
        }
        // 打印dp表
        System.out.println(Arrays.toString(dp));
        return dp[amount] > amount ? -1 : dp[amount];
    }

    public int coinChange2(int[] coins, int amount) {
        int n = coins.length;
        int[][] dp = new int[n + 1][amount + 1];

        // 初始化dp数组的第一列
        for (int i = 0; i <= n; i++) {
            dp[i][0] = 0;
        }

        // 初始化dp数组的第一行
        for (int j = 1; j <= amount; j++) {
            dp[0][j] = Integer.MAX_VALUE - 1;
        }

        // 填充dp数组
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= amount; j++) {
                if (coins[i - 1] > j) {
                    // 如果当前硬币的面额大于目标金额，则不选该硬币
                    dp[i][j] = dp[i - 1][j];
                } else {
                    // 否则可以选择该硬币
                    dp[i][j] = Math.min(dp[i - 1][j], dp[i][j - coins[i - 1]] + 1);
                }
            }
        }

        for (int i = 0; i < n; i++) {
            System.out.println(Arrays.toString(dp[i]));
        }
        return dp[n][amount] == Integer.MAX_VALUE - 1 ? -1 : dp[n][amount];
    }

    public int coinChange3(int[] coins, int amount) {
        //coins为硬币数组。 amount为要凑成的金额
        //定义dp数组为dp[amount]
        int[] dp = new int[amount + 1];
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[0] = 0;
        for (int i = 1; i < dp.length; i++) {
            for (int j = 0; j < coins.length; j++) {
                if (coins[j] <= i) { //如果当前的硬币小于等于要凑成的金额，那么选择他。
                    dp[i] = Math.min(dp[i], dp[i - coins[j]] + 1);
                }
            }
        }
        // 打印dp表
        System.out.println(Arrays.toString(dp));
        return dp[amount] > amount ? -1 : dp[amount];
    }

    public static void main(String[] args) {
        LeetCode322 lt = new LeetCode322();
        Scanner in = new Scanner(System.in);
        int[] coins = Common.parseStrArrToIntArr(in.nextLine());
        int amount = in.nextInt();
        System.out.println(lt.coinChange(coins, amount));
        System.out.println(lt.coinChange2(coins, amount));
        System.out.println(lt.coinChange3(coins, amount));
    }
}
