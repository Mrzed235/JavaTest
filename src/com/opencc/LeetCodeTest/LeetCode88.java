package com.opencc.LeetCodeTest;

import java.util.Arrays;
import java.util.Scanner;

/**
 * 合并两个有序数组
 *
 * 给你两个按 非递减顺序 排列的整数数组 nums1 和 nums2，另有两个整数 m 和 n ，分别表示 nums1 和 nums2 中的元素数目。
 * 请你 合并 nums2 到 nums1 中，使合并后的数组同样按 非递减顺序 排列。
 * 注意：最终，合并后数组不应由函数返回，而是存储在数组 nums1 中。为了应对这种情况，nums1 的初始长度为 m + n，
 * 其中前 m 个元素表示应合并的元素，后 n 个元素为 0 ，应忽略。nums2 的长度为 n 。
 * <p>
 * 输入：nums1 = [1,2,3,0,0,0], m = 3, nums2 = [2,5,6], n = 3
 * [1,2,3,0,0,0]
 * 3
 * [2,5,6]
 * 3
 * 输出：[1,2,2,3,5,6]
 * 解释：需要合并 [1,2,3] 和 [2,5,6] 。
 * 合并结果是 [1,2,2,3,5,6] ，其中斜体加粗标注的为 nums1 中的元素。
 */
public class LeetCode88 {
    public static void main(String[] args) {
        LeetCode88 lt = new LeetCode88();
        Scanner in = new Scanner(System.in);
        String[] strs = new String[4];
        for (int i = 0; i < 4; i++) {
            strs[i] = in.nextLine();
        }
        int[] nums1 = Common.parseStrArrToIntArr(strs[0]);
        int[] nums2 = Common.parseStrArrToIntArr(strs[2]);
        int m = Integer.parseInt(strs[1]);
        int n = Integer.parseInt(strs[3]);
        lt.merge(nums1, m, nums2, n);
    }


    public void merge(int[] nums1, int m, int[] nums2, int n) {
        int p1 = 0, p2 = 0;//定义两个标志位遍历两个数组
        int[] sorted = new int[m + n];//定义新的数组长度为m+n
        int cur; //用来获取两个数组中的当前头部最小数
        while (p1 < m || p2 < n) {
            if (p1 == m) {//极端情况，num1数组里的数字都比num2小，遍历完num1后p1为m，num2再开始遍历
                cur = nums2[p2++];
            } else if (p2 == n) {//极端情况，num2数组里的数字都比num1小，遍历完num2后p2为n，num1再开始遍历
                cur = nums1[p1++];
            } else if (nums1[p1] < nums2[p2]) {
                cur = nums1[p1++];
            } else {
                cur = nums2[p2++];
            }
            sorted[p1 + p2 - 1] = cur;
        }
        for (int i = 0; i != m + n; ++i) {
            nums1[i] = sorted[i];
        }
        System.out.println(Arrays.toString(nums1));
    }
}
