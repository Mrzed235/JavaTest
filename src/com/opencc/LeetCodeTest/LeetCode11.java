package com.opencc.LeetCodeTest;

/**
 * <a href="https://leetcode.cn/problems/container-with-most-water/?envType=study-plan-v2&envId=top-interview-150">...</a>
 * 11 盛最多水的容器
 * 给定一个长度为 n 的整数数组 height 。有 n 条垂线，第 i 条线的两个端点是 (i, 0) 和 (i, height[i]) 。
 * 找出其中的两条线，使得它们与 x 轴共同构成的容器可以容纳最多的水。
 * 返回容器可以储存的最大水量。
 * 说明：你不能倾斜容器。
 * 输入：[1,8,6,2,5,4,8,3,7]
 * 输出：49
 * 输入：[2,3,4,5,18,17,6]
 * 输出：
 * 解释：图中垂直线代表输入数组 [1,8,6,2,5,4,8,3,7]。在此情况下，容器能够容纳水（表示为蓝色部分）的最大值为 49。
 */
public class LeetCode11 {
    public static void main(String[] args) {
        int[] height = {1, 8, 6, 2, 5, 4, 8, 3, 7};
        int[] height2 = {2,3,4,5,18,17,6};
        System.out.println(maxArea(height));
        System.out.println(maxArea(height2));
    }

    public static int maxArea(int[] height) {
        int left = 0, right = height.length - 1; //定义双指针，分别指向数组头尾。
        int maxLight = 0; //maxLight的计算公式为。 Math.min(height[left],height[right])*(right - left)
        //todo: left从前往后， right从后往前，比较两个里边的最小值，求出当前的接水量，如果height[left]<height[right], left++,else right --。
        // 当 left和right碰撞后，求和结束。
        while (left != right) {
            int area = Math.min(height[left], height[right]) * (right - left);
            maxLight = Math.max(area, maxLight);
            if (height[left] < height[right]) {
                ++left;
            } else --right;
        }
        return maxLight;
    }
}
