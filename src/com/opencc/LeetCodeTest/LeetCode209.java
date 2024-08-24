package com.opencc.LeetCodeTest;

import java.lang.reflect.Constructor;
import java.util.Arrays;

/**
 * <a href="https://leetcode.cn/problems/minimum-size-subarray-sum/?envType=study-plan-v2&envId=top-interview-150">...</a>
 * 209. 长度最小的子数组
 * 给定一个含有 n 个正整数的数组和一个正整数 target 。找出该数组中满足其总和大于等于 target 的长度最小的 子数组
 * [numsl, numsl+1, ..., numsr-1, numsr] ，并返回其长度。如果不存在符合条件的子数组，返回 0 。
 * 示例 1：
 * 输入：target = 7, nums = [2,3,1,2,4,3]
 * 输出：2
 * 解释：子数组 [4,3] 是该条件下的长度最小的子数组。
 * 示例 2：
 * 输入：target = 4, nums = [1,4,4]
 * 输出：1
 * 示例 3：
 * 输入：target = 11, nums = [1,1,1,1,1,1,1,1]
 * 输出：0
 */
public class LeetCode209 {
    public static void main(String[] args) throws Exception {
        int target = 7;
//        int target2 = 4;
        int[] nums = {2, 3, 1, 2, 4, 3};
//        int[] nums2 = {1, 4, 4};
        Class<?> clz = Class.forName("com.opencc.LeetCodeTest.LeetCode209");
        Constructor<?> constructor = clz.getDeclaredConstructor();
        LeetCode209 lt = (LeetCode209) constructor.newInstance();
        System.out.println(lt.minSubArrayLen(target, nums));
        System.out.println(lt.minSubArrayLen2(target, nums));
    }

    public int minSubArrayLen(int target, int[] nums) {
        //利用滑动窗口的思想去求解，在滑动过程中，进行值的累计，并判断是否>=target，是的话，则缩减左窗口的值并更新reslut，缩减到不符合的情况下
        //扩大右窗口，函数结束的标志位是right滑动到最右边，且符合缩减left后仍然reslut的情况。
        if (target > Arrays.stream(nums).sum()) {
            return 0;
        }
        int left = 0, right = 0; //窗口最右侧为nums.lenght-1
        int count = nums[0]; // 声明一个count记录left到right内的值的记录，如果扩充right的话，count = count + nums[right]
        //缩减left的时候，count = count - nums[left] 去动态的维护一个count，避免了每次都计算对应段内的数组值，影响性能
        int indexMin = nums.length;
        boolean flag = true;
        //right < nums.length - 1
        while (flag) {
            if (right < nums.length - 1) {
                if (count < target) {
                    ++right;
                    count += nums[right];
                } else {
                    if (left < right) {
                        indexMin = Math.min(indexMin, right - left + 1);
                        count -= nums[left];
                        ++left;
                    } else {
                        indexMin = 1;
                        flag = false;
                    }
                }
            } else {
                if (count >= target) {
                    indexMin = Math.min(indexMin, right - left + 1);
                    count -= nums[left];
                    ++left;
                } else flag = false;
            }
        }
        return indexMin;
    }


    public int minSubArrayLen2(int target, int[] nums) {
        int left = 0;
        int count = 0; // 初始化 count 为 0,用来记录中间过程数组的sum
        int result = Integer.MAX_VALUE; // 初始化结果为最大值

        for (int right = 0; right < nums.length; right++) {
            count += nums[right]; // 扩大窗口

            // 当窗口内的元素之和大于等于目标值时，缩小窗口
            while (count >= target) {
                result = Math.min(result, right - left + 1);
                count -= nums[left];
                left++;
            }
        }

        return result == Integer.MAX_VALUE ? 0 : result; // 如果没有找到符合条件的子数组，返回 0
    }
}
//输入：target = 7, nums = [2,3,1,2,4,3]
// 输出：2