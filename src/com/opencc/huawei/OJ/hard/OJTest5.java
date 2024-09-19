package com.opencc.huawei.OJ.hard;

import java.util.Scanner;

/**
 * 贪吃的猴子
 * <a href="https://www.nowcoder.com/issue/tutorial?zhuanlanId=MgbyJj&uuid=52d6b40b2db7498ba7ec03ccb3a47275">...</a>
 * 问题描述
 * LYA是一位热爱水果的小猴子。最近,她来到一个果园,发现里面种植了许多串香蕉,这些香蕉串排成一行。每串香蕉上有若干根香蕉,数量不等。
 * LYA打算采摘这些香蕉,但有一个规定:每次她只能从行的开头或末尾采摘一串香蕉,并且总共只能采摘  次。
 * LYA想知道,在这个限制下,她最多能采摘到多少根香蕉?
 * 输入格式
 * 第一行包含一个正整数n,表示香蕉串的数量。
 * 第二行包含n个正整数a1,...an,其中ai表示第i串香蕉上的香蕉根数。
 * 第三行包含一个正整数N,表示LYA能够采摘的次数。
 * 输出格式
 * 输出一个正整数,表示LYA最多能采摘到的香蕉根数。
 * 样例输入 1
 * 7
 * 1 2 2 7 3 6 1
 * 3
 * 样例输出 1
 * 10
 * 样例输入 2
 * 3
 * 1 2 3
 * 3
 * 样例输出 2
 * 6
 * 样例输入 3
 * 4
 * 4 2 2 3
 * 2
 * 样例输出 3
 * 7
 */
public class OJTest5 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int[] a = new int[n];
        for (int i = 0; i < a.length; i++) {
            a[i] = in.nextInt();
        }
        int N = in.nextInt();
        System.out.println(bananaCounts(a, N));
        System.out.println(bananaCounts2(a, N));
    }

    private static int bananaCounts(int[] a, int n) {
        int maxCount = 0;
        for (int i = 0; i <= n; i++) {
            int count = 0;
            int flag = 0;
            for (int j = a.length - n + i; j < a.length + i; ) {
                if (j >= a.length) {
                    j = j % a.length;
                }
                count += a[j];
                j++;
                flag++;
                if (flag == n) {
                    break;
                }
            }
            maxCount = Math.max(maxCount, count);
        }
        return maxCount;
    }

    private static int bananaCounts2(int[] a, int n) {
        int maxScore = 0;
        for (int i = 0; i < n; i++) {
            maxScore += a[i];
        }

        int currentScore = maxScore;
        int left = n - 1, right = a.length - 1;
        while (left >= 0) {
            maxScore -= a[left--];
            maxScore += a[right--];
            currentScore = Math.max(currentScore, maxScore);
        }
        return currentScore;
    }
}
