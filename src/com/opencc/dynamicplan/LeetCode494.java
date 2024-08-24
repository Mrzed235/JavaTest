package com.opencc.dynamicplan;

import com.opencc.LeetCodeTest.Common;

import java.util.Arrays;
import java.util.Scanner;

/**
 * 目标和
 * 给你一个非负整数数组 nums 和一个整数 target 。
 * 向数组中的每个整数前添加 '+' 或 '-' ，然后串联起所有整数，可以构造一个 表达式 ：
 * 例如，nums = [2, 1] ，可以在 2 之前添加 '+' ，在 1 之前添加 '-' ，然后串联起来得到表达式 "+2-1" 。
 * 返回可以通过上述方法构造的、运算结果等于 target 的不同 表达式 的数目。
 * 示例 1：
 * <p>
 * 输入：nums = [1,1,1,1,1], target = 3
 * 输出：5
 * 解释：一共有 5 种方法让最终目标和为 3 。
 * -1 + 1 + 1 + 1 + 1 = 3
 * +1 - 1 + 1 + 1 + 1 = 3
 * +1 + 1 - 1 + 1 + 1 = 3
 * +1 + 1 + 1 - 1 + 1 = 3
 * +1 + 1 + 1 + 1 - 1 = 3
 * <p>
 * 示例 2：
 * <p>
 * 输入：nums = [1], target = 1
 * 输出：1
 * [1,2,3,2,1]
 * 3
 */
public class LeetCode494 {
    /**
     * <a href="https://leetcode.cn/problems/target-sum/solutions/816361/mu-biao-he-by-leetcode-solution-o0cp/">...</a>
     * 解法：记数组的元素和为 sum，添加 - 号的元素之和为 neg，则其余添加 + 的元素之和为 sum−neg，则 sum-neg-neg=target，neg =( sum - target)/2
     * 如果输入的sum-target不为非负偶数的话，则结果直接返回 0。
     * 若上式成立，问题转化成在数组 nums 中选取若干元素，使得这些元素之和等于 neg，计算选取元素的方案数。我们可以使用动态规划的方法求解。
     * 定义二维数组 dp，其中 dp[i][j] 表示在数组 nums 的前 i 个数中选取元素，使得这些元素之和等于 j 的方案数。假设数组 nums 的长度为 n，则最终答案为 dp[n][neg]。
     * 当没有任何元素可以选取时，元素和只能是 0，对应的方案数是 1，因此动态规划的边界条件是：
     * dp[0][j]= 1, j=0; dp[0][j]= 0, j>1;
     * 当 1≤i≤n 时，对于数组 nums 中的第 i 个元素 num（i 的计数从 1 开始），遍历 0≤j≤neg，计算 dp[i][j] 的值：
     * 如果 j<num，则不能选 num，此时有 dp[i][j]=dp[i−1][j]；
     * 如果 j≥num，则如果不选 num，方案数是 dp[i−1][j]，如果选 num，方案数是 dp[i−1][j−num]，此时有 dp[i][j]=dp[i−1][j]+dp[i−1][j−num]。
     * 作者：力扣官方题解
     * 链接：<a href="https://leetcode.cn/problems/target-sum/solutions/816361/mu-biao-he-by-leetcode-solution-o0cp/">...</a>
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     *
     * @param nums
     * @param target
     * @return
     */
    public static int findTargetSumWays(int[] nums, int target) {
        int sum = 0;
        for (int num : nums) {
            sum += num;
        }
        int diff = sum - target;
        if (diff < 0 || diff % 2 != 0) {
            return 0;
        }
        int n = nums.length, neg = diff / 2;
        System.out.println("sum == " + sum);
        System.out.println("target == " + target);
        System.out.println("diff == " + diff);
        int[][] dp = new int[n + 1][neg + 1];
        dp[0][0] = 1;
        for (int i = 1; i <= n; i++) {
            int num = nums[i - 1];
            for (int j = 0; j <= neg; j++) {
                dp[i][j] = dp[i - 1][j];
                if (j >= num) {
                    dp[i][j] = dp[i - 1][j] + dp[i - 1][j - num];
                }
            }
        }
        for (int i = 0; i < dp.length; i++) {
            System.out.println(Arrays.toString(dp[i]));
        }
        return dp[n][neg];
    }

    public static int findTargetSumWays2(int[] nums, int target) {
        int totalSum = Arrays.stream(nums).sum();

        // 检查是否有解
        if (Math.abs(target) > totalSum || (totalSum + target) % 2 != 0) {
            return 0;
        }

        // 动态规划数组的大小
        int dpSize = totalSum + 1;
        int[][] dp = new int[nums.length + 1][2 * dpSize];
        dp[0][dpSize] = 1;  // 初始状态只有空集，所以只有一种方式达到0

        // 更新动态规划数组
        for (int i = 1; i <= nums.length; i++) {
            for (int j = -totalSum; j <= totalSum; j++) {
                int index = j + dpSize;  // 调整索引以处理负数
                if (j - nums[i - 1] >= -totalSum) {  // 加法的情况
                    dp[i][index] += dp[i - 1][index - nums[i - 1]];
                }
                if (j + nums[i - 1] <= totalSum) {  // 减法的情况
                    dp[i][index] += dp[i - 1][index + nums[i - 1]];
                }
            }
        }

        for (int i = 0; i < dp.length; i++) {
            System.out.println(Arrays.toString(dp[i]));
        }

        // 返回结果
        return dp[nums.length][target + dpSize];
    }

    public static int findTargetSumWays3(int[] nums, int target) {
        int totalSum = Arrays.stream(nums).sum();

        // 检查是否有解
        if (Math.abs(target) > totalSum || (totalSum + target) % 2 != 0) {
            return 0;
        }

        // 新的目标和 S
        int S = (totalSum + target) / 2;

        // 动态规划数组的大小
        int dpSize = S + 1;
        int[] dp = new int[dpSize];
        dp[0] = 1;  // 初始状态只有空集，所以只有一种方式达到0

        // 更新动态规划数组
        for (int num : nums) {
            for (int j = S; j >= num; j--) {
                dp[j] += dp[j - num];
            }
        }

        // 返回结果
        return dp[S];
    }

    public static void main(String[] args) {
        int[] nums = {1, 2, 3, 2, 1};
        int target = 1;
        System.out.println(findTargetSumWays(nums, target));
        System.out.println(findTargetSumWays2(nums, target));
        System.out.println(findTargetSumWays3(nums, target));
    }
}
