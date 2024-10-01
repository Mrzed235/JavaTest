package com.opencc.LeetCodeTest.dfs.Island_p;

import com.opencc.LeetCodeTest.Common;

import java.util.Scanner;

/**
 * 使陆地分离的最小天数
 * <a href="https://leetcode.cn/problems/minimum-number-of-days-to-disconnect-island/description/">...</a>
 */
public class LeetCode1568 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int[][] grid = Common.parse(in.nextLine());
        LeetCode1568 lt = new LeetCode1568();
        System.out.println(lt.minDays(grid));
    }

    public int minDays(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                
            }
        }
    }
}
