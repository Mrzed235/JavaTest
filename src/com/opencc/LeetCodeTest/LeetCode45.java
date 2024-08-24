package com.opencc.LeetCodeTest;

import java.util.Scanner;

/**
 * 跳跃游戏II
 * 给定一个长度为 n 的 0 索引整数数组 nums。初始位置为 nums[0]。
 * 每个元素 nums[i] 表示从索引 i 向前跳转的最大长度。换句话说，如果你在 nums[i] 处，你可以跳转到任意 nums[i + j] 处:
 * 0 <= j <= nums[i]
 * i + j < n
 * 返回到达 nums[n - 1] 的最小跳跃次数。生成的测试用例可以到达 nums[n - 1]。
 * <p>
 * 输入: nums = [2,3,1,1,4]
 * 输出: 2
 * 解释: 跳到最后一个位置的最小跳跃数是 2。
 * 从下标为 0 跳到下标为 1 的位置，跳 1 步，然后跳 3 步到达数组的最后一个位置。
 * <p>
 * 输入: nums = [2,3,0,1,4]
 * 输出: 2
 * <a href="https://leetcode.cn/problems/jump-game-ii/description/?envType=study-plan-v2&envId=top-interview-150">...</a>
 */
public class LeetCode45 {
    public int jump(int[] nums) {
        int p = nums.length - 1;
        int step = 0;
        while (p > 0) {
            for (int i = 0; i < p; i++) {
                if (i + nums[i] >= p) {
                    p = i;
                    step++;
                    break;
                }
            }
        }
        return step;
    }

    /**
     * 反向查找出发位置：
     * 我们的目标是到达数组的最后一个位置，因此我们可以考虑最后一步跳跃前所在的位置，该位置通过跳跃能够到达最后一个位置。
     * 如果有多个位置通过跳跃都能够到达最后一个位置，那么我们应该如何进行选择呢？直观上来看，我们可以「贪心」地选择距离最后一个位置最远的那个位置，也就是对应下标最小的那个位置。因此，我们可以从左到右遍历数组，选择第一个满足要求的位置。
     * 找到最后一步跳跃前所在的位置之后，我们继续贪心地寻找倒数第二步跳跃前所在的位置，以此类推，直到找到数组的开始位置。
     * 作者：力扣官方题解
     * 链接：<a href="https://leetcode.cn/problems/jump-game-ii/solutions/230241/tiao-yue-you-xi-ii-by-leetcode-solution/">...</a>
     * 从后往前遍历找出距离终点最远的点，例如[2,3,1,1,4]  3时，相当于for从前往后找当i+nums[i] >= 4时，此时，3是离
     * 终点最远的点，此时step++，将终点flag置为3的位置index = 1，重新找离3最远的点，当找到数组头的时候就结束了，此时flag = 0；
     *
     * @param nums
     * @return step
     */
    public int jump2(int[] nums) {
        int flag = nums.length - 1;
        int step = 0; //记录步数
        while (flag > 0) {
            for (int i = 0; i < flag; i++) {
                if (i + nums[i] >= flag) {
                    flag = i;
                    step++;
                    break;
                }
            }
        }
        return step;
    }

    /**
     * 正向查找可到达的最大位置:
     * 方法一虽然直观，但是时间复杂度比较高，有没有办法降低时间复杂度呢？
     * 如果我们「贪心」地进行正向查找，每次找到可到达的最远位置，就可以在线性时间内得到最少的跳跃次数。
     * 例如，对于数组 [2,3,1,2,4,2,3]，初始位置是下标 0，从下标 0 出发，最远可到达下标 2。下标 0 可到达的位置中，下标 1 的值是 3，
     * 从下标 1 出发可以达到更远的位置，因此第一步到达下标 1。从下标 1 出发，最远可到达下标 4。下标 1 可到达的位置中，下标 4 的值是 4 ，
     * 从下标 4 出发可以达到更远的位置，因此第二步到达下标 4。
     * 在具体的实现中，我们维护当前能够到达的最大下标位置，记为边界。我们从左到右遍历数组，到达边界时，更新边界并将跳跃次数增加 1。
     * 在遍历数组时，我们不访问最后一个元素，这是因为在访问最后一个元素之前，我们的边界一定大于等于最后一个位置，否则就无法跳到最后一个位置了。如果访问最后一个元素，在边界正好为最后一个位置的情况下，我们会增加一次「不必要的跳跃次数」，因此我们不必访问最后一个元素。
     * 作者：力扣官方题解
     * 链接：<a href="https://leetcode.cn/problems/jump-game-ii/solutions/230241/tiao-yue-you-xi-ii-by-leetcode-solution/">...</a>
     *
     * @param nums
     * @return step
     */
    public int jump3(int[] nums) {
        int maxPosition = 0; //记录遍历到index能到达的最大位置。
        int end = 0; //
        int step = 0;// 记录步数，到达边界则步数++
        for (int i = 0; i < nums.length - 1; i++) {
            maxPosition = Math.max(maxPosition, i + nums[i]);
            if (i == end) { //在具体的实现中，我们维护当前能够到达的最大下标位置，记为边界。我们从左到右遍历数组，到达边界时，更新边界并将跳跃次数增加 1。
                end = maxPosition;
                step++;
            }
        }
        return step;
    }

    public static void main(String[] args) {
        LeetCode45 lt = new LeetCode45();
        Scanner in = new Scanner(System.in);
        int[] nums = Common.parseStrArrToIntArr(in.nextLine());
        System.out.println(lt.jump(nums));
        System.out.println(lt.jump2(nums));
        System.out.println(lt.jump3(nums));
    }
}
