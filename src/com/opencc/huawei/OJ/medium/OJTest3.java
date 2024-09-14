package com.opencc.huawei.OJ.medium;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * 增强的strstr
 * 问题描述
 * C 语言中有一个库函数 char *strstr(const char *haystack, const char *needle)，用于在字符串 haystack 中查找第一次出现字符串 needle 的位置。现在需要实现一个 strstr 的增强函数，支持使用带可选段的字符串进行模糊查询。
 * 可选段使用 "[]" 标识，表示该位置可以是可选段中的任意一个字符。例如，"a[bc]" 可以匹配 "ab" 或 "ac"。
 * 输入格式
 * 输入包含两个字符串，分别是源字符串和目标字符串，以空格分隔。
 * 输出格式
 * 输出一个整数，表示匹配子字符串在源字符串中的起始位置（从 0 开始计数）。如果没有匹配，则输出 -1。
 * 样例输入
 * abcd bc[cd]
 * 样例输出
 * 1
 * 样例解释
 * 目标字符串 "bc[cd]" 可以匹配 "bcc" 或 "bcd"。在源字符串 "abcd" 中，"bcd" 子字符串的起始位置是 1。
 * abcdefd de[fd]
 * 输出：3
 * abcdefd dc[fd]
 * 输出：-1
 */
public class OJTest3 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String[] inputs = in.nextLine().split(" ");
        String origin = inputs[0];
        String needle = inputs[1];
        System.out.println(strsrt(origin, needle));
    }

    private static int strsrt(String origin, String needle) {
        String[] needles = needle.split("\\[");
        String must = needles[0];
        List<Character> list = new ArrayList<>();
        String fuzzy = needles[1].replace("]", "");
        for (int i = 0; i < fuzzy.length(); i++) {
            list.add(fuzzy.charAt(i));
        }

        for (int i = 0; i <= origin.length() - must.length(); i++) {
            boolean found = true;
            for (int j = 0; j < must.length(); j++) {
                if (origin.charAt(i + j) != must.charAt(j)) {
                    found = false;
                    break;
                }
            }
            //判断是否fuzzy字符串存在后续的charAt
            if (found) {
                if (list.contains(origin.charAt(i +must.length()))) {
                    return i;
                }
            }
        }
        return -1;
    }
}
