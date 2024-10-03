package com.opencc.LeetCodeTest.Arrays;

import com.opencc.LeetCodeTest.Common;

import java.util.*;

/**
 * 组合总和II
 * <a href="https://leetcode.cn/problems/combination-sum-ii/?envType=problem-list-v2&envId=array&difficulty=MEDIUM">...</a>
 * 输入: candidates = [10,1,2,7,6,1,5], target = 8,
 * 输出:[[1,1,6],[1,2,5],[1,7],[2,6]]
 * 输入: candidates = [2,5,2,1,2], target = 5,
 * 输出:[[1,2,2],[5]]
 * 输入：[1,1,2] target = 2
 * 输出：[[1, 1], [2]]
 * 输入：[1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1]
 * target = 30
 * 输出：
 */
public class LeetCode40 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int[] candidates = Common.parseStrArrToIntArr(in.nextLine());
        int target = in.nextInt();
        System.out.println(new LeetCode40().combinationSum2(candidates, target));
    }

    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        List<List<Integer>> result = new ArrayList<>();
        Set<List<Integer>> uniqueSet = new HashSet<>(); // 使用HashSet来保证唯一性
        Arrays.sort(candidates);
        backtrace(result, new ArrayList<>(), candidates, target, 0, uniqueSet);
        return result;
    }

    private void backtrace(List<List<Integer>> result, List<Integer> current, int[] candidates, int remain, int start, Set<List<Integer>> uniqueSet) {
        if (remain < 0) return;
        else if (remain == 0) {
            if (!uniqueSet.contains(current)) {
                result.add(new ArrayList<>(current));
                uniqueSet.add(new ArrayList<>(current));
            }
        } else {
            for (int i = start; i < candidates.length; i++) {
                // 跳过重复的元素
                if (i > start && candidates[i] == candidates[i - 1]) continue;
                //选择当前的数
                current.add(candidates[i]);
                //递归的构建组合
                backtrace(result, current, candidates, remain - candidates[i], i + 1, uniqueSet); //不允许选择同一位置的数
                //移除最后一个数，进行回溯
                current.remove(current.size() - 1);
            }
        }
    }
}
