package com.opencc.LeetCodeTest;

import java.util.Scanner;

/**
 * 跳跃游戏
 * 给你一个非负整数数组 nums ，你最初位于数组的 第一个下标 。数组中的每个元素代表你在该位置可以跳跃的最大长度。
 * 判断你是否能够到达最后一个下标，如果可以，返回 true ；否则，返回 false 。
 * 输入：nums = [2,3,1,1,4]
 * 输出：true
 * 解释：可以先跳 1 步，从下标 0 到达下标 1, 然后再从下标 1 跳 3 步到达最后一个下标
 * 输入：nums = [3,2,1,0,4]
 * 输出：false
 * 解释：无论怎样，总会到达下标为 3 的位置。但该下标的最大跳跃长度是 0 ， 所以永远不可能到达最后一个下标。
 * <a href="https://leetcode.cn/problems/jump-game/description/?envType=study-plan-v2&envId=top-interview-150">...</a>
 */
public class LeetCode55 {
    public static void main(String[] args) {
        LeetCode55 lt = new LeetCode55();
        Scanner in = new Scanner(System.in);
        int[] nums = Common.parseStrArrToIntArr(in.nextLine());
        System.out.println(lt.canJump(nums));
        System.out.println(lt.canJump2(nums));
        System.out.println(lt.canJump3(nums));
    }

    public boolean canJump(int[] nums) {
        int len = nums.length;
        int right = 0;
        for (int i = 0; i < len; i++) {
            if (i <= right) {
                right = Math.max(right, i + nums[i]); //在当前index能跳跃的最大长度。
                if (right >= len - 1) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * 不妨可以想象有一个“右边界”的存在，遍历只是为了拓展右边界的范围，直至可以覆盖到数组的最后一个元素。
     * 终止条件：
     * 遍历完当前右边界内的元素，仍不能使右边界拓展时，退出循环；
     * 当遍历过程中，发现右边界已可以覆盖数组的最后一个元素，退出循环。
     *
     * @param nums
     * @return
     */
    public boolean canJump2(int[] nums) {
        int len = nums.length;
        int right = nums[0];
        for (int i = 0; i <= right; i++) {
            right = Math.max(right, i + nums[i]); //在当前index能跳跃的最大长度。
            if (right >= len - 1) {
                return true;
            }
        }
        return false;
    }

    public boolean canJump3(int[] nums) {
        int len = nums.length;
        int flag = len - 1;
        for (int i = len - 2; i >= 0; i--) {
            if (nums[i] >= flag - i) { // 说明在num[i]开始跳就能到达数组尾部
                flag = i;
            }
        }
        return flag == 0;
    }
}
