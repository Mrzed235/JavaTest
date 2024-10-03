package com.opencc.LeetCodeTest.Arrays;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

/**
 * 三数之和
 * <a href="https://leetcode.cn/problems/3sum/description/?envType=problem-list-v2&envId=array">...</a>
 * 输入：nums = [-1,0,1,2,-1,-4]
 * 输出：[[-1,-1,2],[-1,0,1]]
 */
public class LeetCode15 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int[] nums = Arrays.stream(in.nextLine().replace("[", "").replace("]", "").split(",")).mapToInt(Integer::parseInt).toArray();
        LeetCode15 lt = new LeetCode15();
        System.out.println(lt.threeSum(nums));
    }

    public List<List<Integer>> threeSum(int[] nums) {
        int n = nums.length;
        List<List<Integer>> res = new ArrayList<>();
        Arrays.sort(nums);
        for (int first = 0; first < n; first++) {
            //需要和上一次枚举的数不相同
            if (first > 0 && nums[first] == nums[first - 1]) {
                continue;
            }
            //c对应的指针初始指向数组的最右侧
            int third = n - 1;
            int target = -nums[first];
            //枚举b
            for (int second = first + 1; second < n; second++) {
                if (second > first + 1 && nums[second] == nums[second - 1]) {
                    continue;
                }
                //需要保证b的指针在c的指针的左侧
                while (second < third && nums[second] + nums[third] > target) {
                    --third;
                }

                //如果指针重合，随着b后续的增加
                //就不会有满足a+b+c=0并且b<c的c了，可以退出循环
                if (second == third) {
                    break;
                }

                if (nums[second] + nums[third] == target) {
                    res.add(Arrays.asList(nums[first], nums[second], nums[third]));
                }
            }
        }
        return res;
    }
}
