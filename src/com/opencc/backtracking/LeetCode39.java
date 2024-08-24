package com.opencc.backtracking;

import com.opencc.LeetCodeTest.LeetCode3;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * <a href="https://leetcode.cn/problems/combination-sum/description/">...</a>
 * 39 组合总和
 * 给你一个 无重复元素 的整数数组 candidates 和一个目标整数 target ，找出 candidates 中可以使数字和为目标数 target 的 所有 不同组合 ，并以列表形式返回。你可以按 任意顺序 返回这些组合。
 * candidates 中的 同一个 数字可以 无限制重复被选取 。如果至少一个数字的被选数量不同，则两种组合是不同的。
 * 对于给定的输入，保证和为 target 的不同组合数少于 150 个。
 * <p>
 * 输入：candidates = [2,3,6,7], target = 7
 * 输出：[[2,2,3],[7]]
 * 解释：
 * 2 和 3 可以形成一组候选，2 + 2 + 3 = 7 。注意 2 可以使用多次。
 * 7 也是一个候选， 7 = 7 。
 * 仅有这两种组合。
 */
public class LeetCode39 {
    List<List<Integer>> result = new LinkedList<>();
    List<Integer> path = new LinkedList<>();

    public static void main(String[] args) {
        int[] candidates = {2, 3, 6, 7};
        int target = 7;
        //定义的allPath用来记录最终结果
        LeetCode39 lt = new LeetCode39();
        System.out.println(lt.combinationSum(candidates, target));
    }

    public List<List<Integer>> combinationSum(int[] candidates, int target) {
//        List<Integer> path = new LinkedList<>();
        findCombinationSum(candidates, target, 0, 0);
        return result;
    }

    public void findCombinationSum(int[] candidates, int target, int sum, int index) {
        // 递归中止条件
        if (sum == target) {
            result.add(new LinkedList<>(path));
            return;
        }
        if (sum > target) return;

        for (int i = index; i < candidates.length; i++) {
            path.add(candidates[i]);
            sum += candidates[i];
            findCombinationSum(candidates, target, sum, i);
            path.remove(path.size() - 1);
            sum -= candidates[i];
        }
    }
}
