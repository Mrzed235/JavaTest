package com.opencc.huawei.OJ;

import java.util.Arrays;
import java.util.Scanner;

/**
 * 分糖果
 * 题目描述
 * 小明从糖果盒中随意抓一把糖果，每次小明会取出一半的糖果分给同学们。
 * 当糖果不能平均分配时，小明可以选择从糖果盒中（假设盒中糖果足够）取出一个糖果或放回一个糖果。
 * 小明最少需要多少次（取出、放回和平均分配均记一次），能将手中糖果分至只剩一颗。
 * 输入描述
 * 抓取的糖果数（<10000000000）：15
 * 输出描述
 * 最少分至一颗糖果的次数：5
 * 示例1
 * 输入
 * 15
 * 输出
 * 5
 * 说明
 * 15+1=16;16/2=8;8/2=4;4/2=2;2/2=1;
 */
public class OJTest8 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int candies = Integer.parseInt(in.nextLine());
        System.out.println(minSteps(candies));
        System.out.println(minStepsHelper(candies));
    }

    //dp解法:
    public static int minSteps(int candies) {
        int[] dp = new int[candies + 1]; //定义dp数组的大小为candies+1
        Arrays.fill(dp, Integer.MAX_VALUE); //填充数组为最大值
        if (candies <= 1) {                 //判断极端情况，为1 的时候返回0
            return 0;
        }
        dp[0] = 0;  //给dp数组的前两位赋值
        dp[1] = 0;
        for (int i = 2; i <= candies; i++) {
            if (i % 2 == 0) { //如果为偶数的时候，分糖果的时候的等式如下
                dp[i] = Math.min(dp[i], dp[i / 2] + 1);
            } else {  //非偶数的糖果分的方式为 Math.min方法里的dp等式，这里加了两次1的原因是 放入或取出糖果都记为一次
                dp[i] = Math.min(dp[i], Math.min(dp[(i + 1) / 2], dp[(i - 1) / 2]) + 1) + 1;
            }
        }
        //System.out.println(Arrays.toString(dp)); //输出dp数组
        return dp[candies];
    }

    //递归解法：
    private static int minStepsHelper(long candies) {
        //递归出口条件
        if (candies <= 1) {
            return 0;
        }
        long halfCandies = candies / 2;
        if (candies % 2 == 0) { //判断是否是偶数
            return minStepsHelper(halfCandies) + 1;
        } else {
            return Math.min(minStepsHelper(candies + 1), minStepsHelper(candies - 1)) + 1;
        }
    }
}
