package com.opencc.LeetCodeTest.Arrays;

import com.opencc.LeetCodeTest.Common;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * 16. 最接近的三数之和
 * <a href="https://leetcode.cn/problems/3sum-closest/description/?envType=problem-list-v2&envId=array">...</a>
 * 示例 1：
 * 输入：nums = [-1,2,1,-4], target = 1
 * 输出：2
 * 解释：与 target 最接近的和是 2 (-1 + 2 + 1 = 2)。
 * 示例 2：
 * 输入：nums = [0,0,0], target = 1
 * 输出：0
 * 解释：与 target 最接近的和是 0（0 + 0 + 0 = 0）。
 */
public class LeetCode16 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int[] nums = Common.parseStrArrToIntArr(in.nextLine());
        int target = in.nextInt();
        LeetCode16 lt = new LeetCode16();
        System.out.println(lt.threeSumClosest(nums, target));
        System.out.println(lt.threeSumClosest2(nums, target));
    }

    public int threeSumClosest(int[] nums, int target) {
        //先对数组进行排序
        Arrays.sort(nums);
        int m = nums.length;
        Map<Integer, Integer> map = new HashMap<>();
        int nearly = Integer.MAX_VALUE;
        for (int first = 0; first < m; first++) {
            int needle = target - nums[first]; //需要second和third凑成的最近的目标
            for (int second = first + 1; second < m; second++) { //从第二个开始搜索
                for (int third = second + 1; third < m; third++) {
                    if (nearly > Math.abs(needle - (nums[third] + nums[second]))) {
                        nearly = Math.abs(needle - (nums[third] + nums[second]));
                        map.put(nearly, nums[third] + nums[second] + nums[first]);
                    }
                }
            }
        }
        return map.get(nearly);
    }

    public int threeSumClosest2(int[] nums, int target) {
        Arrays.sort(nums);
        int m = nums.length;
        int best = Integer.MAX_VALUE;
        //枚举a
        for (int i = 0; i < m; i++) {
            if (i > 0 && nums[i] == nums[i - 1]) continue;//保证和上一次枚举的元素不相等
            //使用双指针来求b和c
            int j = i + 1, k = m - 1;
            while (j < k) { //当jk对撞结束本轮循环，记录最接近的值
                int sum = nums[i] + nums[j] + nums[k];
                if (sum == target) {
                    return sum;
                }
                if (Math.abs(sum - target) < Math.abs(best - target)) {
                    best = sum;
                }
                if (sum > target) {
                    //如果和大于target，移动c的指针
                    int k0 = k - 1;
                    //移动到下一个不相等的元素
                    while (j < k0 && nums[k0] == nums[k]) k0--;
                    k = k0;
                } else {
                    //如果和小于target，移动b的指针
                    int j0 = j + 1;
                    //移动到下一个不想等的元素
                    while (k > j0 && nums[j0] == nums[j]) j0++;
                    j = j0;
                }
            }

        }
        return best;
    }
}
