package com.opencc.LeetCodeTest;

import java.util.Scanner;

/**
 * 验证回文串
 * 如果在将所有大写字符转换为小写字符、并移除所有非字母数字字符之后，短语正着读和反着读都一样。则可以认为该短语是一个 回文串 。
 * 字母和数字都属于字母数字字符。
 * 给你一个字符串 s，如果它是 回文串 ，返回 true ；否则，返回 false 。
 * 示例 1：
 * 输入: s = "A man, a plan, a canal: Panama"
 * 输出：true
 * 解释："amanaplanacanalpanama" 是回文串。
 * 示例 2：
 * 输入：s = "race a car"
 * 输出：false
 * 解释："raceacar" 不是回文串。
 * 示例 3：
 * 输入：s = " "
 * 输出：true
 * 解释：在移除非字母数字字符之后，s 是一个空字符串 "" 。
 * 由于空字符串正着反着读都一样，所以是回文串。
 */
public class LeetCode125 {
    public boolean isPalindrome(String s) {
        s = s.replaceAll("[^0-9a-zA-Z]", "").toLowerCase();
        int left = 0, right = s.length() - 1;
        while (left <= right && s.charAt(left) == s.charAt(right)) {
            left++;
            right--;
        }
        return left == (s.length() + 1) / 2;
    }

    public boolean isPalindrome2(String s) {
        StringBuilder sb = new StringBuilder();
        s = s.replaceAll("[^0-9a-zA-Z]", "").toLowerCase();
        sb.append(s);
        reverse(sb, 0, sb.length() - 1);
        return s.contentEquals(sb);
    }

    public void reverse(StringBuilder sb, int left, int right) {
        while (left < right) {
            char tmp = sb.charAt(left);
            sb.setCharAt(left++, sb.charAt(right));
            sb.setCharAt(right--, tmp);
        }
    }


    public static void main(String[] args) {
        LeetCode125 lt = new LeetCode125();
        Scanner in = new Scanner(System.in);
        String s = in.nextLine();
        System.out.println(lt.isPalindrome(s));
        System.out.println(lt.isPalindrome2(s));
    }
}
