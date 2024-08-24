package com.opencc.LeetCodeTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.function.Predicate;

/**
 * 移除元素
 *
 * 给你一个数组 nums 和一个值 val，你需要 原地 移除所有数值等于 val 的元素。元素的顺序可能发生改变。然后返回 nums 中与 val 不同的元素的数量。
 * 假设 nums 中不等于 val 的元素数量为 k，要通过此题，您需要执行以下操作：
 * 更改 nums 数组，使 nums 的前 k 个元素包含不等于 val 的元素。nums 的其余元素和 nums 的大小并不重要。
 * 返回 k。
 * <p>
 * 输入：nums = [3,2,2,3], val = 3
 * 输出：2, nums = [2,2,_,_]
 * 解释：你的函数函数应该返回 k = 2, 并且 nums 中的前两个元素均为 2。
 * 你在返回的 k 个元素之外留下了什么并不重要（因此它们并不计入评测）
 */
public class LeetCode27 {
    public static void main(String[] args) {
        LeetCode27 lt = new LeetCode27();
        Scanner in = new Scanner(System.in);
        int[] nums = Common.parseStrArrToIntArr(in.nextLine());
        int val = in.nextInt();
//        System.out.println(lt.removeElement2(nums, val));
        System.out.println(lt.removeElement(nums, val));
    }

    public int removeElement(int[] nums, int val) {
        int left = 0;
        for (int right = 0; right < nums.length; right++) {
            if (nums[right] != val) {
                nums[left] = nums[right];
                left++;
            }
        }
        return left;
    }

    public int removeElement2(int[] nums, int val) {
        List<Integer> items = new ArrayList<>();
        for (int i = 0; i < nums.length; i++) {
            items.add(nums[i]);
        }
        while (items.contains(val)) {
//            items.removeIf(Predicate.isEqual(val));
            items.removeIf(e -> e == val);
        }
        for (int i = 0; i < items.size(); i++) {
            nums[i] = items.get(i);
        }
        return items.size();
    }
}
