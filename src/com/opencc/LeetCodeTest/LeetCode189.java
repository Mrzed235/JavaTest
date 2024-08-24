package com.opencc.LeetCodeTest;

import java.util.Arrays;
import java.util.Scanner;

/**
 * 转轮数组
 * 给定一个整数数组 nums，将数组中的元素向右轮转 k 个位置，其中 k 是非负数。
 * 输入: nums = [1,2,3,4,5,6,7], k = 3
 * 输出: [5,6,7,1,2,3,4]
 * 向右轮转 1 步: [7,1,2,3,4,5,6]
 * 向右轮转 2 步: [6,7,1,2,3,4,5]
 * 向右轮转 3 步: [5,6,7,1,2,3,4]
 * <p>
 * 输入：nums = [-1,-100,3,99], k = 2
 * 输出：[3,99,-1,-100]
 * 向右轮转 1 步: [99,-1,-100,3]
 * 向右轮转 2 步: [3,99,-1,-100]
 */
public class LeetCode189 {
    public static void main(String[] args) {
        LeetCode189 lt = new LeetCode189();
        Scanner in = new Scanner(System.in);
        int[] nums = Common.parseStrArrToIntArr(in.nextLine());
        int k = in.nextInt();
        lt.rotate(nums, k);
        System.out.println(Arrays.toString(nums));
    }

    public void rotate(int[] nums, int k) {
        k = k % nums.length;
        reserved(nums, 0, nums.length - 1);//先首尾完全翻转
        reserved(nums, 0, k - 1); //翻转前k位数
        reserved(nums, k, nums.length - 1);//翻转数组后n-k位数
    }

    public void reserved(int[] nums, int start, int end) {
        while (start < end) {
            int temp = nums[start];
            nums[start] = nums[end];
            nums[end] = temp;
            start++;
            end--;
        }
    }
}
