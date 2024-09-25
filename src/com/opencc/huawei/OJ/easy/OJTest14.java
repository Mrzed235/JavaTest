package com.opencc.huawei.OJ.easy;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * 最左侧冗余覆盖子串
 * <a href="https://app5938.acapp.acwing.com.cn/contest/11/problem/OD1013">...</a>
 */
public class OJTest14 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String s1 = in.nextLine();
        String s2 = in.nextLine();
        int k = in.nextInt();
        System.out.println(solveIndex(s1, s2, k));
    }

    private static int solveIndex(String s1, String s2, int k) {
        int n = s1.length();
        int m = s2.length();
        int windowSize = n + k;
        if (windowSize > m) {
            return -1;
        }
        Map<Character, Integer> s1Map = new HashMap<>();
        Map<Character, Integer> s2Map = new HashMap<>();
        //统计s1字符串中字符的出现次数
        for (char c : s1.toCharArray()) {
            s1Map.put(c, s1Map.getOrDefault(c, 0) + 1);
        }
        //初始化窗口
        for (int i = 0; i < windowSize; i++) {
            char c = s2.charAt(i);
            s2Map.put(c, s2Map.getOrDefault(c, 0) + 1);
        }
        //检查第一个窗口
        if (checkRedundancy(s1Map, s2Map)) {
            return 0;
        }
        //滑动窗口
        for (int i = 1; i <= m - windowSize; i++) {
            char removeChar = s2.charAt(i - 1);
            char addChar = s2.charAt(i + windowSize - 1);
            s2Map.put(removeChar, s2Map.get(removeChar) - 1);
            if (s2Map.get(removeChar) == 0) {
                s2Map.remove(removeChar);
            }
            s2Map.put(addChar, s2Map.getOrDefault(addChar, 0) + 1);
            if (checkRedundancy(s1Map, s2Map)) {
                return i;
            }
        }

        return -1;
    }

    public static boolean checkRedundancy(Map<Character, Integer> s1Count, Map<Character, Integer> windowCount) {
        for (char c : s1Count.keySet()) {
            if (windowCount.getOrDefault(c, 0) < s1Count.get(c)) {
                return false;
            }
        }
        return true;
    }
}
