package com.opencc.LeetCodeTest;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

/**
 * <a href="https://leetcode.cn/problems/longest-common-prefix/?envType=study-plan-v2&envId=top-interview-150">...</a>
 * 最长公共前缀:
 * 输入：strs = ["flower","flow","flight"]
 * 输出："fl"
 * 输入：strs = ["dog","racecar","car"]
 * 输出：""
 */
public class LeetCode14 {
    public String longestCommonPrefix(String[] strs) {
        if (strs == null || strs.length == 0) {
            return "";
        }
        String prefix = strs[0];
        int count = strs.length;
        for (int i = 1; i < count; i++) {
            prefix = longestCommonPrefix(prefix, strs[i]);
            if (prefix.length() == 0) {
                break;
            }
        }
        return prefix;
    }

    public String longestCommonPrefix(String str1, String str2) {
        int length = Math.min(str1.length(), str2.length());
        int index = 0;
        while (index < length && str1.charAt(index) == str2.charAt(index)) {
            index++;
        }
        return str1.substring(0, index);
    }

    public static void main(String[] args) {
        LeetCode14 lt = new LeetCode14();
        Scanner in = new Scanner(System.in);
        String[] strs = in.nextLine().replace("[", "").replace("]", "").split(",");
        System.out.println(lt.longestCommonPrefix(strs));
    }
}
