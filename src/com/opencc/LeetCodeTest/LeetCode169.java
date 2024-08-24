package com.opencc.LeetCodeTest;

import java.util.Arrays;
import java.util.Scanner;

/**
 * 多数元素
 * 给定一个大小为 n 的数组 nums ，返回其中的多数元素。多数元素是指在数组中出现次数 大于 ⌊ n/2 ⌋ 的元素。
 * 你可以假设数组是非空的，并且给定的数组总是存在多数元素。
 * 输入：nums = [3,2,3]
 * 输出：3
 * 输入：nums = [2,2,1,1,1,2,2]
 * 输出：2
 */
public class LeetCode169 {
    public static void main(String[] args) {
        LeetCode169 lt = new LeetCode169();
        Scanner in = new Scanner(System.in);
        int[] nums = Common.parseStrArrToIntArr(in.nextLine());
        System.out.println(lt.majorityElement(nums));
    }

    public int majorityElement(int[] nums) {
        Arrays.sort(nums);
        return nums[nums.length / 2];
    }
}
