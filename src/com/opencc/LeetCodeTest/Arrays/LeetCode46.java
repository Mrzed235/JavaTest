package com.opencc.LeetCodeTest.Arrays;

import com.opencc.LeetCodeTest.Common;

import java.util.*;
import java.util.stream.Collectors;

/**
 * 46 全排列
 * <a href="https://leetcode.cn/problems/permutations/description/?envType=problem-list-v2&envId=array&difficulty=MEDIUM">...</a>
 * 示例 1：
 * 输入：nums = [1,2,3]
 * 输出：[[1,2,3],[1,3,2],[2,1,3],[2,3,1],[3,1,2],[3,2,1]]
 * 示例 2：
 * 输入：nums = [0,1]
 * 输出：[[0,1],[1,0]]
 * 示例 3：
 * 输入：nums = [1]
 * 输出：[[1]]
 */
public class LeetCode46 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int[] nums = Common.parseStrArrToIntArr(in.nextLine());
        System.out.println(new LeetCode46().permute(nums));
        System.out.println(new LeetCode46().permute2(nums));
    }

    public List<List<Integer>> permute2(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        List<Integer> list = new ArrayList<>();
        for (int num : nums) {
            list.add(num);
        }
        backtrack(result, new ArrayList<>(), list);
        return result;
    }

    private void backtrack(List<List<Integer>> result, List<Integer> current, List<Integer> nums) {
        if (current.size() == nums.size()) {
            result.add(new ArrayList<>(current));
        } else {
            for (int i = 0; i < nums.size(); i++) {
                if (current.contains(nums.get(i))) {
                    continue; // 跳过已经添加过的元素
                }
                current.add(nums.get(i));
                backtrack(result, current, nums);
                current.remove(current.size() - 1); // 回溯
            }
        }
    }

    public List<List<Integer>> permute(int[] nums) {
        Set<List<Integer>> queue = new HashSet<>();
        List<Integer> list = new ArrayList<>();
        Arrays.sort(nums);
        for (int i = 0; i < nums.length; i++) {
            list.add(nums[i]);
        }
        queue.add(list);
        while (true) {
            //求nums的下一个顺序
            getNextNum(nums);
            List<Integer> mid = new ArrayList<>();
            for (int num : nums) {
                mid.add(num);
            }
            if (queue.contains(mid)) {
                break;
            }
            queue.add(mid);
        }
        return new ArrayList<>(queue);
    }

    private void getNextNum(int[] nums) {
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
