package com.opencc.huawei.OJ.easy;

import java.util.Scanner;

/**
 * 字符串序列判断
 * <a href="https://app5938.acapp.acwing.com.cn/contest/1/problem/OD1004">...</a>
 * 输入两个字符串S和L，都只包含英文小写字母。判定S是否是L的有效子序列。
 * 判定规则：S中的每个字符在L中都能找到（可以不连续），且S在L中字符的前后顺序与S中顺序要保持一致。例如，S="ace" 是 L="abcde" 的一个子序列，有效字符是a、c、e,
 * 而S="aec"不是有效子序列。
 * 输入：
 * 第一行输入字符串S，第二行输入字符串L，都只包含英文小写字母。
 * 输出：
 * 输出S串最后一个有效字符在L中的位置（首位从 0 开始计算），无有效字符返回 -1。
 * 输入：
 * ace
 * abcde
 * 输出：4
 * 输入：
 * fgh
 * abcde
 * 输出：-1
 */
public class OJTest10 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String S = in.nextLine();
        String L = in.nextLine();
        System.out.println(solveStr(S, L));
    }

    private static int solveStr(String S, String L) {
        //判断S是否是L的子序列，判断S的每一位是否顺序的出现在L中
        int sIndex = 0, lIndex = 0;
        while (sIndex < S.length() && lIndex < L.length()) {
            if (S.charAt(sIndex) == L.charAt(lIndex)) {
                sIndex++;
            }
            lIndex++;
        }
        return sIndex == S.length() ? lIndex - 1 : -1;
    }
}
