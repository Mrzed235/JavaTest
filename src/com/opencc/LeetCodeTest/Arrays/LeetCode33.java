package com.opencc.LeetCodeTest.Arrays;

import com.opencc.LeetCodeTest.Common;

import java.util.Scanner;

/**
 * 33 搜索旋转排序数组
 * <a href="https://leetcode.cn/problems/search-in-rotated-sorted-array/description/?envType=problem-list-v2&envId=array&difficulty=MEDIUM">...</a>
 * 示例 1：
 * 输入：nums = [4,5,6,7,0,1,2], target = 0
 * 输出：4
 * 示例 2：
 * 输入：nums = [4,5,6,7,0,1,2], target = 3
 * 输出：-1
 * 示例 3：
 * 输入：nums = [1], target = 0
 * 输出：-1
 */
public class LeetCode33 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int[] nums = Common.parseStrArrToIntArr(in.nextLine());
        int target = in.nextInt();
        LeetCode33 lt = new LeetCode33();
        System.out.println(lt.search(nums, target));
    }

    public int search(int[] nums, int target) {
        int n = nums.length;
        if (n == 0) {
            return -1;
        }
        if (n == 1) {
            return nums[0] == target ? 0 : -1;
        }
        int l = 0, r = n - 1;
        while (l <= r) {
            int mid = (l + r) / 2;
            if (nums[mid] == target) {
                return mid;
            }
            if (nums[0] <= nums[mid]) {
                if (nums[0] <= target && target < nums[mid]) {
                    r = mid - 1;
                } else {
                    l = mid + 1;
                }
            } else {
                if (nums[mid] < target && target <= nums[n - 1]) {
                    l = mid + 1;
                } else {
                    r = mid - 1;
                }
            }
        }
        return -1;
    }
}
