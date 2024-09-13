package com.opencc.huawei.OJ.hard;

import java.util.Scanner;

/**
 * 查找充电设备：
 * <a href="https://www.nowcoder.com/issue/tutorial?zhuanlanId=MgbyJj&uuid=6ca016d7534546d780d14f3e349dc7b3">...</a>
 * 问题描述
 * 某个充电站可提供N个充电设备，每个充电设备均有对应的输出功率。任意个充电设备组合的输出功率总和，均构成功率集合P的1个元素。功率集合P的最优元素，表示最接近充电站最大输出功率Pmax的元素。
 * 输入格式
 * 输入为 3 行：
 * 第 1 行为充电设备个数 。
 * 第 2 行为每个充电设备的输出功率。
 * 第 3 行为充电站最大输出功率 。
 * 输出格式
 * 输出功率集合  的最优元素。
 * <p>
 * 样例输入 1
 * 4
 * 50 20 20 60
 * 90
 * 样例输出 1
 * 90
 * 样例解释 1
 * 当充电设备输出功率 50、20、20 组合时，其输出功率总和为 90，最接近充电站最大充电输出功率，因此最优元素为 90。
 * <p>
 * 样例输入 2
 * 2
 * 50 40
 * 30
 * 样例输出 2
 * 0
 * 样例解释 2
 * 所有充电设备的输出功率组合，均大于充电站最大充电输出功率 30，此时最优元素值为 0。
 */
public class OJTest3 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = Integer.parseInt(in.nextLine());
        String[] nums = in.nextLine().split(" ");
        int[] powers = new int[n];
        for (int i = 0; i < powers.length; i++) {
            powers[i] = Integer.parseInt(nums[i]);
        }
        int pMax = Integer.parseInt(in.nextLine());
        System.out.println(solve(powers, pMax, n));
    }

    private static int solve(int[] powers, int pMax, int n) {
        // 初始化 dp 数组，dp[i] 表示是否可以达到功率 i
        boolean[] dp = new boolean[pMax + 1];
        dp[0] = true; // 总是可以达到 0 功率

        // 遍历每个充电设备的功率
        for (int power : powers) {
            // 从大到小遍历，避免重复计算
            for (int i = pMax; i >= power; i--) {
                if (dp[i - power]) {
                    dp[i] = true;
                }
            }
        }

        // 从 pMax 开始向下查找第一个为 True 的索引
        for (int i = pMax; i >= 0; i--) {
            if (dp[i]) {
                return i;
            }
        }

        return 0; // 如果没有找到合适的组合，返回 0
    }
}
