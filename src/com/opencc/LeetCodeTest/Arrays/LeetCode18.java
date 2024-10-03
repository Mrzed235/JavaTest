package com.opencc.LeetCodeTest.Arrays;

import com.opencc.LeetCodeTest.Common;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

/**
 * 四数之和
 * <a href="https://leetcode.cn/problems/4sum/description/?envType=problem-list-v2&envId=array">...</a>
 * 示例 1：
 * 输入：nums = [1,0,-1,0,-2,2], target = 0
 * 输出：[[-2,-1,1,2],[-2,0,0,2],[-1,0,0,1]]
 * 示例 2：
 * 输入：nums = [2,2,2,2,2], target = 8
 * 输出：[[2,2,2,2]]
 */
public class LeetCode18 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int[] nums = Common.parseStrArrToIntArr(in.nextLine());
        int target = in.nextInt();
        LeetCode18 lt = new LeetCode18();
        System.out.println(lt.fourSum(nums, target));
    }

    public List<List<Integer>> fourSum(int[] nums, int target) {
        List<List<Integer>> res = new ArrayList<>();
        Arrays.sort(nums);
        return null;
    }
}
