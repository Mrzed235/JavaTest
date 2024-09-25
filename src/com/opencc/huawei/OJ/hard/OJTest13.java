package com.opencc.huawei.OJ.hard;

import java.util.Scanner;

/**
 * 部分组队编程
 * <a href="https://www.nowcoder.com/issue/tutorial?zhuanlanId=MgbyJj&uuid=b9d8ea498513445ab75c20cb02d40e15">...</a>
 */
public class OJTest13 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int[] level = new int[n];
        for (int i = 0; i < n; i++) {
            level[i] = in.nextInt();
        }

        long res = countGroups(level);
        res += countGroups(reverse(level));
        System.out.println(res);
    }

    private static long countGroups(int[] level) {
        int n = level.length;
        long res = 0;
        int[] left = new int[n];
        int[] right = new int[n];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < i; j++) {
                if (level[j] < level[i]) {
                    left[i]++;
                }
            }
            for (int j = i + 1; j < n; j++) {
                if (level[j] > level[i]) {
                    right[i]++;
                }
            }
        }

        for (int i = 0; i < n; i++) {
            res += (long) left[i] * right[i];
        }

        return res;
    }

    private static int[] reverse(int[] level) {
        int n = level.length;
        int[] res = new int[n];
        for (int i = 0; i < n; i++) {
            res[i] = level[n - i - 1];
        }
        return res;
    }
}

