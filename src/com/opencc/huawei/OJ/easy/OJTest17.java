package com.opencc.huawei.OJ.easy;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Scanner;

/**
 * 最大括号深度(100分)
 * <a href="https://app5938.acapp.acwing.com.cn/contest/1/problem/OD1120">...</a>
 * 输入：([]{()}) 输出：3
 * 输入：[] 输出： 1
 * 输入：(] 输出：0
 * 输入：([)] 输出：0
 * 输入：)( 输出：0
 */
public class OJTest17 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String s = in.nextLine();
        System.out.println(findSymbolDepth(s));
    }

    private static int findSymbolDepth(String s) {
        Deque<Character> openStack = new ArrayDeque<>();
        Deque<Character> closeStack = new ArrayDeque<>();
        int depth = 0;
        for (char c : s.toCharArray()) {
            if (c == '(' || c == '[' || c == '{') {
                openStack.push(c);
            } else {
                if (!openStack.isEmpty()) {
                    if (openStack.peek() == '(' && c == ')' || openStack.peek() == '[' && c == ']' || openStack.peek() == '{' && c == '}') {
                        openStack.pop();
                        continue;
                    }
                }
                closeStack.push(c);
            }
            depth = Math.max(depth, openStack.size());
        }
        return openStack.isEmpty() && closeStack.isEmpty() ? depth : 0;
    }
}
