package com.opencc.LeetCodeTest.Arrays;

import com.opencc.LeetCodeTest.Common;

import java.util.Arrays;
import java.util.Scanner;

/**
 * 下一个排列
 * <a href="https://leetcode.cn/problems/next-permutation/description/?envType=problem-list-v2&envId=array&difficulty=MEDIUM">...</a>
 * 示例 1：
 * 输入：nums = [1,2,3]
 * 输出：[1,3,2]
 * 示例 2：
 * 输入：nums = [3,2,1]
 * 输出：[1,2,3]
 * 示例 3：
 * 输入：nums = [1,1,5]
 * 输出：[1,5,1]
 */
public class LeetCode31 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int[] nums = Common.parseStrArrToIntArr(in.nextLine());
        new LeetCode31().nextPermutation(nums);
        System.out.println(Arrays.toString(nums));
    }

    public void nextPermutation(int[] nums) {
        //必须原地修改的意思是不能申请额外的空间
        // 1 1 5 2 3 ---> 1 1 5 3 2 ---> 1 2 1 3 5
        //观察上述数组根据字典序排序的话，数组分为两部分，顺序递增和递减。
        //从最右侧往前遍历 找第一个较小的数
        int i = nums.length - 2;
        while (i >= 0 && nums[i] >= nums[i + 1]) i--; //找到i的下标
        if (i >= 0) {//说明没有到达本组数据序列的最大值，还可以从后续序列中选取较大的值来替换nums[i]
            //将nums[i]和从index=i+1->nums.length开始的最后侧数据交换
            int j = nums.length - 1;
            while (j > i && nums[j] <= nums[i]) j--;
            swap(nums, i, j);
        }
        reserve(nums, i + 1); //任何情况都会走，比如 2 1 5 3 1，上述找到i=1，交换后得到2 3 5 1 1 ，其中5 1 1 必定是顺序递减的，所以反转它即可
    }

    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    private void reserve(int[] nums, int start) {
        int left = start, right = nums.length - 1;
        while (left < right) {
            swap(nums, left, right);
            left++;
            right--;
        }
    }
}
