package com.opencc.huawei.OJ.easy;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * 单词加密
 * <a href="https://app5938.acapp.acwing.com.cn/contest/1/problem/OD1124">...</a>
 * 输入：Hello world
 * 输出：H*ll* w*rld
 */
public class OJTest15 {
    public static void main(String[] args) {
        char[] ch = {'a', 'e', 'i', 'o', 'u', 'A', 'E', 'I', 'O', 'U'};
        List<Character> list = new ArrayList<>();
        for (char c : ch) {
            list.add(c);
        }
        Scanner in = new Scanner(System.in);
        String[] strs = in.nextLine().split(" ");
        List<String> res = new ArrayList<>();
        for (String str : strs) {
            res.add(solveSalt(str, list));
        }
        System.out.println(String.join(" ", res));
    }

    private static String solveSalt(String str, List<Character> list) {
        StringBuilder sb = new StringBuilder();
        char[] array = str.toCharArray();
        if (str.chars().mapToObj(c -> (char) c).noneMatch(list::contains)) {
            char temp = array[0];
            array[0] = array[array.length - 1];
            array[array.length - 1] = temp;
        }
        for (char c : array) {
            if (list.contains(c)) {
                sb.append('*');
            } else sb.append(c);
        }
        return sb.toString();
    }
}
