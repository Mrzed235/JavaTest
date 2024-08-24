package com.opencc.LeetCodeTest;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Scanner;

/**
 * 除自身以外数组的乘积
 * 给你一个整数数组 nums，返回 数组 answer ，其中 answer[i] 等于 nums 中除 nums[i] 之外其余各元素的乘积 。
 * 题目数据 保证 数组 nums之中任意元素的全部前缀元素和后缀的乘积都在  32 位 整数范围内。
 * 请 不要使用除法，且在 O(n) 时间复杂度内完成此题。
 * 示例 1:
 * 输入: nums = [1,2,3,4]
 * 输出: [24,12,8,6]
 * 示例 2:
 * 输入: nums = [-1,1,0,-3,3]
 * 输出: [0,0,9,0,0]
 */
public class LeetCode238 {
    /**
     * 左右乘积列表
     * 我们不必将所有数字的乘积除以给定索引处的数字得到相应的答案，而是利用索引左侧所有数字的乘积和右侧所有数字的乘积（即前缀与后缀）相乘得到答案。
     * 对于给定索引 i，我们将使用它左边所有数字的乘积乘以右边所有数字的乘积。下面让我们更加具体的描述这个算法。
     * 链接：<a href="https://leetcode.cn/problems/product-of-array-except-self/solutions/272369/chu-zi-shen-yi-wai-shu-zu-de-cheng-ji-by-leetcode-/">...</a>
     * 时间复杂度：O(N)，其中 N 指的是数组 nums 的大小。预处理 L 和 R 数组以及最后的遍历计算都是 O(N) 的时间复杂度。
     * 空间复杂度：O(N)，其中 N 指的是数组 nums 的大小。使用了 L 和 R 数组去构造答案，L 和 R 数组的长度为数组 nums 的大小。
     *
     * @param nums
     * @return result
     */
    public int[] productExceptSelf(int[] nums) {
        int len = nums.length;
        int[] left = new int[len];
        int[] right = new int[len];
        int[] result = new int[len];
        // left[i] 为索引 i 左侧所有元素的乘积
        left[0] = 1;
        for (int i = 1; i < len; i++) {
            left[i] = nums[i - 1] * left[i - 1];
        }
        // right[i] 为索引 i 右侧所有元素的乘积
        right[len - 1] = 1;
        for (int i = len - 2; i >= 0; i--) {
            right[i] = nums[i + 1] * right[i + 1];
        }
        //result[i] =  left[i] * right[i]
        for (int i = 0; i < len; i++) {
            result[i] = left[i] * right[i];
        }
        return result;
    }

    /**
     * 由于输出数组不算在空间复杂度内，那么我们可以将 L 或 R 数组用输出数组来计算。先把输出数组当作 L 数组来计算，然后再动态构造 R 数组得到结果。
     * 1：初始化 answer 数组，对于给定索引 i，answer[i] 代表的是 i 左侧所有数字的乘积。
     * 2：构造方式与之前相同，只是我们试图节省空间，先把 answer 作为方法一的 L 数组。
     * 3：这种方法的唯一变化就是我们没有构造 R 数组。而是用一个遍历来跟踪右边元素的乘积。并更新数组 answer[i]=answer[i]∗R。
     * 然后 R 更新为 R=R∗nums[i]，其中变量 R 表示的就是索引右侧数字的乘积。
     * @param nums
     * @return
     */
    public int[] productExceptSelf2(int[] nums) {
        int len = nums.length;
        int[] result = new int[len];
        result[0] = 1;
        for (int i = 1; i < len; i++) {
            result[i] = nums[i - 1] * result[i - 1];
        }
        int R = 1;
        for (int i = len - 1; i >= 0; i--) {
            result[i] = result[i] * R;
            R = R * nums[i];
        }
        return result;
    }

    public int[] productExceptSelf3(int[] nums) {
        int len = nums.length;
        int[] result = new int[len];
        result[len - 1] = 1;
        for (int i = len - 2; i >= 0; i--) {
            result[i] = result[i + 1] * nums[i + 1];
        }
        int L = 1;
        for (int i = 0; i < len; i++) {
            result[i] = L * result[i];
            L = L * nums[i];
        }
        return result;
    }

    public static void main(String[] args) {
        LeetCode238 lt = new LeetCode238();
        Scanner in = new Scanner(System.in);
        int[] nums = Common.parseStrArrToIntArr(in.nextLine());
        System.out.println(Arrays.toString(lt.productExceptSelf(nums)));
        System.out.println(Arrays.toString(lt.productExceptSelf2(nums)));
        System.out.println(Arrays.toString(lt.productExceptSelf3(nums)));
    }
}
