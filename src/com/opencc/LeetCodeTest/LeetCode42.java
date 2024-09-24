package com.opencc.LeetCodeTest;

import java.util.Deque;
import java.util.LinkedList;
import java.util.Scanner;

/**
 * 接雨水：
 * 给定 n 个非负整数表示每个宽度为 1 的柱子的高度图，计算按此排列的柱子，下雨之后能接多少雨水。
 * 示例 1：
 * 输入：height = [0,1,0,2,1,0,1,3,2,1,2,1]
 * 输出：6
 * 解释：上面是由数组 [0,1,0,2,1,0,1,3,2,1,2,1] 表示的高度图，在这种情况下，可以接 6 个单位的雨水（蓝色部分表示雨水）。
 * 示例 2：
 * 输入：height = [4,2,0,3,2,5]
 * 输出：9
 * <a href="https://leetcode.cn/problems/trapping-rain-water/description/?envType=study-plan-v2&envId=top-interview-150">...</a>
 */
public class LeetCode42 {

    /**
     * 从左往后遍历，从右往左遍历构造两个数组，求2个数组每个位置最小值，再算面积
     * @param height
     * @return
     */
    public int trap(int[] height) {
        int len = height.length;
        int[] left = new int[len];
        int lMax = Integer.MIN_VALUE;
        int rMax = Integer.MIN_VALUE;
        int[] right = new int[len];
        for (int i = 0; i < len; i++) {
            lMax = Math.max(lMax, height[i]);
            left[i] = lMax;
        }
        for (int j = len - 1; j >= 0; j--) {
            rMax = Math.max(rMax, height[j]);
            right[j] = rMax;
        }
        int count = 0;
        for (int k = 0; k < len; k++) {
            count += Math.min(left[k], right[k]) - height[k];
        }
        return count;
    }

    /**
     * 维护一个单调栈，单调栈存储的是下标，满足从栈底到栈顶的下标对应的数组 height 中的元素递减。
     * 从左到右遍历数组，遍历到下标 i 时，如果栈内至少有两个元素，记栈顶元素为 top，top 的下面一个元素是 left，
     * 则一定有 height[left]≥height[top]。如果 height[i]>height[top]，则得到一个可以接雨水的区域，
     * 该区域的宽度是 i−left−1，高度是 min(height[left],height[i])−height[top]，
     * 根据宽度和高度即可计算得到该区域能接的雨水量。为了得到 left，需要将 top 出栈。在对 top 计算能接的雨水量之后，left 变成新的 top，
     * 重复上述操作，直到栈变为空，或者栈顶下标对应的 height 中的元素大于或等于 height[i]。
     * 在对下标 i 处计算能接的雨水量之后，将 i 入栈，继续遍历后面的下标，计算能接的雨水量。遍历结束之后即可得到能接的雨水总量。
     * 链接：<a href="https://leetcode.cn/problems/trapping-rain-water/solutions/692342/jie-yu-shui-by-leetcode-solution-tuvc/">...</a>
     *
     * @param height
     * @return
     */
    public int trapStack(int[] height) {
        Deque<Integer> stack = new LinkedList<>();
        int ans = 0; //面积总数

        for (int i = 0; i < height.length; i++) {
            while (!stack.isEmpty() && height[stack.peek()] < height[i]) {
                int top = stack.pop();
                if (stack.isEmpty()) {
                    break;
                }
                int left = stack.peek();
                int curWidth = i - left - 1;
                int curHeight = Math.min(height[left], height[i]);
                ans += curWidth * (curHeight - height[top]);
            }
            stack.push(i);
        }
        return ans;
    }

    /**
     * 对撞指针：
     * 注意到下标 i 处能接的雨水量由 leftMax[i] 和 rightMax[i] 中的最小值决定。由于数组 leftMax 是从左往右计算，数组 rightMax 是从右往左计算，因此可以使用双指针和两个变量代替两个数组。
     * 维护两个指针 left 和 right，以及两个变量 leftMax 和 rightMax，初始时 left=0,right=n−1,leftMax=0,rightMax=0。指针 left 只会向右移动，指针 right 只会向左移动，在移动指针的过程中维护两个变量 leftMax 和 rightMax 的值。
     * 当两个指针没有相遇时，进行如下操作：
     * 使用 height[left] 和 height[right] 的值更新 leftMax 和 rightMax 的值；
     * 如果 height[left]<height[right]，则必有 leftMax<rightMax，下标 left 处能接的雨水量等于 leftMax−height[left]，将下标 left 处能接的雨水量加到能接的雨水总量，然后将 left 加 1（即向右移动一位）；
     * 如果 height[left]≥height[right]，则必有 leftMax≥rightMax，下标 right 处能接的雨水量等于 rightMax−height[right]，将下标 right 处能接的雨水量加到能接的雨水总量，然后将 right 减 1（即向左移动一位）。
     * 当两个指针相遇时，即可得到能接的雨水总量。
     * 下面用一个例子 height=[0,1,0,2,1,0,1,3,2,1,2,1] 来帮助读者理解双指针的做法。
     * @param height
     * @return
     */
    public int trapArr(int[] height) {
        int ans = 0;
        int left = 0, right = height.length - 1;
        int leftMax = 0, rightMax = 0;
        while (left < right) {
            leftMax = Math.max(leftMax, height[left]);
            rightMax = Math.max(rightMax, height[right]);
            if (height[left] < height[right]) {
                ans += leftMax - height[left];
                ++left;
            } else {
                ans += rightMax - height[right];
                --right;
            }
        }
        return ans;
    }


    public static void main(String[] args) {
        LeetCode42 lt = new LeetCode42();
        Scanner in = new Scanner(System.in);
        int[] nums = Common.parseStrArrToIntArr(in.nextLine());
        System.out.println(lt.trap(nums));
        System.out.println(lt.trapStack(nums));
        System.out.println(lt.trapArr(nums));
    }
}
