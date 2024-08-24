package com.opencc.dynamicplan;

public class PackageTest {

    public static int packageValue(int[] weight, int[] value, int capacity) {
        //01背包问题如下，构造dp方程
        int n = weight.length;
        int[][] dp = new int[n + 1][capacity + 1];
        //表示背包容量为0时无法放入任何物品，价值也为0 ,列为容积。则dp[i][0] = 0
        //表示没有物品时背包的价值为0，则dp[0][i] = 0
        for (int i = 1; i < n + 1; i++) {
            for (int j = 1; j < capacity + 1; j++) {
                if (j < weight[i - 1]) { //如果当前的背包装不下这个东西，则继承上一次能装下的价值.
                    dp[i][j] = dp[i - 1][j];
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i - 1][j - weight[i - 1]] + value[i - 1]);//如果能装下，算出装下它剩余的空间能装的东西的价值和当前东西的价值，并且和上一次能装下的东西价值做比较
                } //其中dp[i - 1][j - weight[i - 1]]指的是，当你放了当前物品i时，剩余重量能取到的最大的价值由上一次计算得到，即dp[i - 1]行获取。
            }
        }
        return dp[n][capacity];
    }


    public static void main(String[] args) {
        int[] weights = {2, 3, 4}; // 物品的重量
        int[] values = {3, 4, 5};  // 物品的价值
        int capacity = 5;          // 背包的最大容量
        System.out.println(packageValue(weights, values, capacity));

        int n = weights.length;
        int[][] dp = new int[n + 1][capacity + 1];

        // 填充dp数组
        for (int i = 1; i <= n; i++) {
            for (int j = 0; j <= capacity; j++) {
                if (weights[i - 1] > j) {
                    // 如果当前物品的重量大于背包剩余容量，则不选该物品
                    dp[i][j] = dp[i - 1][j];
                } else {
                    // 否则可以选择该物品
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i - 1][j - weights[i - 1]] + values[i - 1]);
                }
            }
        }

        // 输出最终结果
        System.out.println("最大价值: " + dp[n][capacity]);

        // 打印dp表
        for (int i = 0; i <= n; i++) {
            for (int j = 0; j <= capacity; j++) {
                System.out.printf("%3d ", dp[i][j]);
            }
            System.out.println();
        }
    }
}
