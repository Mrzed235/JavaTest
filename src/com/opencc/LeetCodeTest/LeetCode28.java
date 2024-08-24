package com.opencc.LeetCodeTest;

/**
 * 找出字符串中第一个匹配项的下标
 * 示例 1：
 * <p>
 * 输入：haystack = "sadbutsad", needle = "sad"
 * 输出：0
 * 解释："sad" 在下标 0 和 6 处匹配。
 * 第一个匹配项的下标是 0 ，所以返回 0 。
 * 示例 2：
 * <p>
 * 输入：haystack = "leetcode", needle = "leeto"
 * 输出：-1
 * 解释："leeto" 没有在 "leetcode" 中出现，所以返回 -1 。
 */
public class LeetCode28 {
    public int strStr(String haystack, String needle) {
        if (!haystack.contains(needle)) {
            return -1;
        }
        int len = haystack.length(), reg = needle.length();
        int index = 0;
        for (int i = 0; i < len; i++) {
            int flag = 0;
            while (flag < reg && i < len && needle.charAt(flag) == haystack.charAt(i)) {
                flag++;
                i++;
            }
            if (flag == reg) {
                index = i - reg;
                break;
            } else {
                i = i - flag;
            }
        }
        return index;
    }

    public static void main(String[] args) {
        LeetCode28 lt = new LeetCode28();
        System.out.println(lt.strStr("bbbbababbbaabbba", "abb"));
    }
}
