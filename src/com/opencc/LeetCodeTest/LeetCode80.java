package com.opencc.LeetCodeTest;

import java.util.Scanner;

/**
 * 删除有序数组中的重复项 II
 * 给你一个有序数组 nums ，请你 原地 删除重复出现的元素，使得出现次数超过两次的元素只出现两次 ，返回删除后数组的新长度。
 * 不要使用额外的数组空间，你必须在 原地 修改输入数组 并在使用 O(1) 额外空间的条件下完成。
 * 输入：nums = [1,1,1,2,2,3]
 * 输出：5, nums = [1,1,2,2,3]
 * 输入：nums = [0,0,1,1,1,1,2,3,3]
 * 输出：7, nums = [0,0,1,1,2,3,3]
 */
public class LeetCode80 {

    public static void main(String[] args) {
        LeetCode80 lt = new LeetCode80();
        Scanner in = new Scanner(System.in);
        int[] nums = Common.parseStrArrToIntArr(in.nextLine());
        System.out.println(lt.removeDuplicates(nums));
    }

    /**
     * 纯纯三指针啊。flag用来标志位赋值，left和right用来过滤重复数字
     *
     * @param nums
     * @return
     */
    public int removeDuplicates(int[] nums) {
        int left = 0, flag = 0, right;
        for (right = 0; right < nums.length; right++) {
            if (nums[left] != nums[right]) {
                if (right - left >= 2) {
                    nums[flag++] = nums[left++];
                    nums[flag++] = nums[left];
                    left = right;
                } else {
                    nums[flag] = nums[left];
                    flag++;
                    left = right;
                }
            }
        }
        if (right - left >= 2) {
            nums[flag++] = nums[left++];
            nums[flag++] = nums[left];
        }else {
            nums[flag++] = nums[left];
        }
        return flag;
    }
}
