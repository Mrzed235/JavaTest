package com.opencc.LeetCodeTest;

import java.util.*;

/**
 * 3 无重复字符的最长子串
 * 给定一个字符串 s ，请你找出其中不含有重复字符的 最长
 * 子串的长度。
 * 示例 1:
 * 输入: s = "abcabcbb"
 * 输出: 3
 * 解释: 因为无重复字符的最长子串是 "abc"，所以其长度为 3。
 * 示例 2:
 * 输入: s = "bbbbb"
 * 输出: 1
 * 解释: 因为无重复字符的最长子串是 "b"，所以其长度为 1。
 * 示例 3:
 * 输入: s = "pwwkew"
 * 输出: 3
 * 解释: 因为无重复字符的最长子串是 "wke"，所以其长度为 3。
 * 请注意，你的答案必须是 子串 的长度，"pwke" 是一个子序列，不是子串。
 */
public class LeetCode3 {
    public static void main(String[] args) {
        String s = "abba";
        LeetCode3 lt = new LeetCode3();
        System.out.println(lt.lengthOfLongestSubstring(s));
        System.out.println(lt.lengthOfLongestSubstring2(s));
        System.out.println(lt.lengthOfLongestSubstring3(s));
    }

    public int lengthOfLongestSubstring(String s) {
        // 滑动窗口
        // 用Map来判断是否出现重复字符，并且记录上一次出现重复字符的index
        Map<Character, Integer> map = new HashMap<>();
        int left = 0;
        int result = 0;
        for (int right = 0; right < s.length(); right++) {
            //扩大窗口之前检查字符是否出现过，出现过则重复，将之前出现过的位置取出来，向后偏移一位则为left的新位置。然后将s.charAt(right)替换到map里
            // 赋值新的index
            if (map.containsKey(s.charAt(right)) && left < map.get(s.charAt(right)) + 1) {
                left = map.get(s.charAt(right)) + 1;
            }
            map.put(s.charAt(right), right);
            result = Math.max(result, right - left + 1);
        }
        return result;
    }

    public int lengthOfLongestSubstring3(String s) {
        int left = 0, len = s.length();
        int result = 0;
        int[] symbol = new int[128];
        Arrays.fill(symbol, -1);
        for (int right = 0; right < len; right++) {
            if (symbol[s.charAt(right)] != -1 && symbol[s.charAt(right)] + 1 > left) {
                //判断字符表里是否为-1，不为-1说明之前出现过该字符
                left = symbol[s.charAt(right)] + 1;
            }
            symbol[s.charAt(right)] = right; //记录下标的位置到记录表
            result = Math.max(result, right - left + 1);
        }
        return result;
    }

    public int lengthOfLongestSubstring2(String s) {
        if (s == null || s.isEmpty()) {
            return 0;
        }

        int left = 0;//创建左指针
        int len = s.length();
        int ans = 0;
        int[] occurs = new int[128];
        Arrays.fill(occurs, -1);

        char[] chars = s.toCharArray();//字符串转化为字符数组
        for (int right = 0; right < len; right++) {//单轮循环遍历
            char letter = chars[right];             //从数组依次获取单个字符，第0,1,2,3,......个
            if (occurs[letter] != -1) {             //通过数组下标表示法,来记录字符的情况 occurs[letter]不为-1即就是重复字符位出现
                left = Math.max(left, occurs[letter] + 1);//left左指针右移到发生重复的字符之后
            }
            ans = Math.max(ans, right - left + 1); // 从新开始的字符之后到right下标不重复的字符最大长度为ans
            occurs[letter] = right;               //单轮循环向后遍历时，记录对应letter字符在本字符串中的index位置
        }
        return ans;
    }
}

