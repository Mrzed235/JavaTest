package com.opencc.LeetCodeTest;

import java.util.Arrays;
import java.util.Scanner;

/**
 * 加油站
 * 示例 1:
 * 在一条环路上有 n 个加油站，其中第 i 个加油站有汽油 gas[i] 升。
 * 你有一辆油箱容量无限的的汽车，从第 i 个加油站开往第 i+1 个加油站需要消耗汽油 cost[i] 升。你从其中的一个加油站出发，开始时油箱为空。
 * 给定两个整数数组 gas 和 cost ，如果你可以按顺序绕环路行驶一周，则返回出发时加油站的编号，否则返回 -1 。如果存在解，则 保证 它是 唯一 的。
 * 输入: gas = [1,2,3,4,5], cost = [3,4,5,1,2]
 * 输出: 3
 * 解释:
 * 从 3 号加油站(索引为 3 处)出发，可获得 4 升汽油。此时油箱有 = 0 + 4 = 4 升汽油
 * 开往 4 号加油站，此时油箱有 4 - 1 + 5 = 8 升汽油
 * 开往 0 号加油站，此时油箱有 8 - 2 + 1 = 7 升汽油
 * 开往 1 号加油站，此时油箱有 7 - 3 + 2 = 6 升汽油
 * 开往 2 号加油站，此时油箱有 6 - 4 + 3 = 5 升汽油
 * 开往 3 号加油站，你需要消耗 5 升汽油，正好足够你返回到 3 号加油站。
 * 因此，3 可为起始索引。
 * 示例 2:
 * 输入: gas = [2,3,4], cost = [3,4,3]
 * 输出: -1
 * 解释:
 * 你不能从 0 号或 1 号加油站出发，因为没有足够的汽油可以让你行驶到下一个加油站。
 * 我们从 2 号加油站出发，可以获得 4 升汽油。 此时油箱有 = 0 + 4 = 4 升汽油
 * 开往 0 号加油站，此时油箱有 4 - 3 + 2 = 3 升汽油
 * 开往 1 号加油站，此时油箱有 3 - 3 + 3 = 3 升汽油
 * 你无法返回 2 号加油站，因为返程需要消耗 4 升汽油，但是你的油箱只有 3 升汽油。
 * 因此，无论怎样，你都不可能绕环路行驶一周。
 */

public class LeetCode134 {
    /**
     * <a href="https://leetcode.cn/problems/gas-station/solutions/2845138/zhi-jie-lian-ge-shu-zu-xiang-jian-ran-ho-286b/">...</a>
     * @param gas
     * @param cost
     * @return
     */
    public int canCompleteCircuit(int[] gas, int[] cost) {
        int len = gas.length;
        int[] result = new int[len];
        for (int i = 0; i < len; i++) {
            result[i] = gas[i] - cost[i];
        }
        int count = 0;
//        int flag = 0;
        for (int i = 0; i < len; i++) {
            count += result[i];
        }
//        if (count >= 0) {//说明能循环走一次
//            for (int i = 0; i < len; i++) {
//                if (result[i] >= 0 && judgeIf(result, i)) {
//                    flag = i;
//                    break;
//                }
//            }
//            return flag;
//        }

        if (count >= 0) {//说明能循环走一次
            int left = 0, right; //定义左右指针，并计算left和right区间内的合，如果走到某一index时，
            //发现合小于0，则将left右移到一个result[i]不为0的点重新开始
            //例如gas[7,1,0,11,4],cost[5,9,1,2,5]-->result[2,-8,-1,9,-1],求解的结果就是从index=3
            //开始算数组右侧的合，始终都为正数，且right能走到数组尾部，所以我们的结果就是3

            int temp = 0;
            for (right = 0; right < len; right++) {
                if (result[left] >= 0) {
                    temp += result[right];
                    if (temp < 0) {
                        temp = 0;
                        left = right;
                    }
                } else {
                    left = right;
                    temp = result[left];
                }
            }
            return left;
        }
        return -1;
    }

    public boolean judgeIf(int[] nums, int i) {
        int count = 0;
        for (int j = i; j < nums.length; j++) {
            count += nums[j];
            if (count < 0) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        LeetCode134 lt = new LeetCode134();
        Scanner in = new Scanner(System.in);
        int[] gas = Common.parseStrArrToIntArr(in.nextLine());
        int[] cost = Common.parseStrArrToIntArr(in.nextLine());
        System.out.println(lt.canCompleteCircuit(gas, cost));
    }
}
