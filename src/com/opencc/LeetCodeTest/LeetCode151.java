package com.opencc.LeetCodeTest;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * "the sky is blue"
 * "  hello world  "
 * "a good   example"
 */
public class LeetCode151 {
    public String reverseWords(String s) {
        // 除去开头和末尾的空白字符
        s = s.trim();
        // 正则匹配连续的空白字符作为分隔符分割
        List<String> wordList = Arrays.asList(s.split("\\s+"));
        Collections.reverse(wordList);
        return String.join(" ", wordList);
    }

    /**
     * <a href="https://leetcode.cn/problems/reverse-words-in-a-string/solutions/194450/fan-zhuan-zi-fu-chuan-li-de-dan-ci-by-leetcode-sol/">...</a>
     *
     * @param s
     * @return
     */
    public String reverseWords2(String s) {
        StringBuilder sb = trimSpaces(s);

        // 翻转字符串
        reverse(sb, 0, sb.length() - 1);

        // 翻转每个单词
        reverseEachWord(sb);

        return sb.toString();
    }

    private StringBuilder trimSpaces(String s) {
        int left = 0, right = s.length() - 1;
        //去掉字符串开头的字符
        while (left <= right && s.charAt(left) == ' ') {
            ++left;
        }
        //去掉字符串末尾的字符
        while (left <= right && s.charAt(right) == ' ') {
            --right;
        }
        //将字符串间多余的空格去掉
        StringBuilder builder = new StringBuilder();
        while (left <= right) {
            char c = s.charAt(left);

            if (c != ' ') {
                builder.append(c);
            } else if (builder.charAt(builder.length() - 1) != ' ') {
                builder.append(c);
            }
            ++left;
        }
        return builder;
    }

    public void reverse(StringBuilder sb, int left, int right) {
        while (left < right) {
            char tmp = sb.charAt(left);
            sb.setCharAt(left++, sb.charAt(right));
            sb.setCharAt(right--, tmp);
        }
    }

    public void reverseEachWord(StringBuilder sb) {
        int n = sb.length();
        int start = 0, end = 0;
        while (start < n) {
            // 循环至单词的末尾
            while (end < n && sb.charAt(end) != ' ') {
                ++end;
            }
            // 翻转单词
            reverse(sb, start, end - 1);
            // 更新start，去找下一个单词
            start = end + 1;
            ++end;
        }
    }


    public static void main(String[] args) {

    }
}
