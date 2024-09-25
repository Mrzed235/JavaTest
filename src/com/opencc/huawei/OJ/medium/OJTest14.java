package com.opencc.huawei.OJ.medium;

import java.util.Scanner;

/**
 * 全排列
 * <a href="https://app5938.acapp.acwing.com.cn/contest/1/problem/OD1119">...</a>
 * LYA 最近在研究一个有趣的字母游戏。她有一个只包含大写英文字母的字符串S，现在她想知道通过重新排列S中的字母，可以得到多少种不同的排列。
 * LYA需要你的帮助来解决这个问题。
 * 输入：输入为一行，包含一个长度不超过 10 的字符串S
 * 输出：输出一个整数，表示字符串S的所有不同排列的数量（包括S本身）
 * <p>
 * 输入： ABA 输出： 3
 * 输入： ABCDEFGHHA 输出： 907200
 * 字符串 "ABA" 的所有不同排列为：ABA、AAB、BAA，共 3 种。
 */
public class OJTest14 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String s = in.nextLine();
        byte[] c = new byte[26];
        for (char c1 : s.toCharArray()) {
            c[c1 - 'A']++;
        }
        int max = solveFactorial(s.length());
        for (byte b : c) {
            if (b > 1) {
                max/=solveFactorial(b);
            }
        }
        System.out.println(max);
    }

    private static int solveFactorial(int length) {
        if (length == 1) {
            return 1;
        }
        return length * solveFactorial(length - 1);
    }
}
