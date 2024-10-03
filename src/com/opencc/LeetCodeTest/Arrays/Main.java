package com.opencc.LeetCodeTest.Arrays;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        int[] nums = {1, 2, 3};
        List<List<Integer>> result = subsets(nums);
        System.out.println("Subsets are:");
        for (List<Integer> subset : result) {
            System.out.println(subset);
        }
    }

    public static List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        backtrack(result, new ArrayList<>(), nums, 0);
        return result;
    }

    private static void backtrack(List<List<Integer>> result, List<Integer> current, int[] nums, int start) {
        // 添加当前路径到结果中
        result.add(new ArrayList<>(current));

        for (int i = start; i < nums.length; i++) {
            // 将当前数字添加到路径中
            current.add(nums[i]);
            // 以当前数字之后的数字为起点继续构建子集
            backtrack(result, current, nums, i + 1);
            // 移除最后一个数字，回溯
            current.remove(current.size() - 1);
        }
    }
}
