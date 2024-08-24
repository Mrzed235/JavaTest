package com.opencc.backtracking;

import java.util.LinkedList;
import java.util.List;

/**
 * 给定一个数组，输出他的全排列
 */
public class findNumsSort {
    //全排列，即所有路径集合
    List<List<Integer>> allPath = new LinkedList<>();

    public List<List<Integer>> permute(int[] nums) {
        //定义的allPath用来记录最终结果
        List<Integer> path =  new LinkedList<>();
        backTrace(nums, path);
        return allPath;
    }

    public void backTrace(int[] nums, List<Integer> path) {
        //todo  递归中止条件
        //当path需要记录的条数达到标准则返回函数并记录结果。
        if (nums.length == path.size()) {
            allPath.add(new LinkedList<>(path));
            return;
        }

        //for循环遍历nums来构造path的值
        for (int i = 0; i < nums.length; i++) {
            if (path.contains(nums[i])) {
                continue; //减少不必要的遍历
            }
            path.add(nums[i]); //记录满足条件的值
            backTrace(nums, path); //递归，进入下一层决策
            path.remove(path.size() - 1);//移除链表中的最后一个元素，来取消决策
        }
    }

    public static void main(String[] args) {
        int[] nums = {1, 2, 3};
        findNumsSort lt = new findNumsSort();
        System.out.println(lt.permute(nums));
    }
}
