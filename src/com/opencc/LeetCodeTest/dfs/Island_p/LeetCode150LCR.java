package com.opencc.LeetCodeTest.dfs.Island_p;

import com.opencc.LeetCodeTest.Common;

import java.util.Scanner;

/**
 * 岛屿的最大面积
 * <a href="https://leetcode.cn/problems/ZL6zAn/description/">...</a>
 * 输入: grid = [[0,0,1,0,0,0,0,1,0,0,0,0,0],[0,0,0,0,0,0,0,1,1,1,0,0,0],[0,1,1,0,1,0,0,0,0,0,0,0,0],[0,1,0,0,1,1,0,0,1,0,1,0,0],[0,1,0,0,1,1,0,0,1,1,1,0,0],[0,0,0,0,0,0,0,0,0,0,1,0,0],[0,0,0,0,0,0,0,1,1,1,0,0,0],[0,0,0,0,0,0,0,1,1,0,0,0,0]]
 * 输出: 6
 */
public class LeetCode150LCR {
    static int[][] directions = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}}; //上下左右


    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String input = in.nextLine();
        int[][] grid = Common.parseToDoubleArray(input);
        System.out.println(maxAreaOfIsland(grid));
    }

    private static int maxAreaOfIsland(int[][] grid) {
        int ans = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                ans = Math.max(ans, dfs(grid, i, j));
            }
        }
        return ans;
    }

    public static int dfs(int[][] grid, int x, int y) {
        if (x < 0 || x == grid.length || y < 0 || y == grid[0].length || grid[x][y] != 1) {
            return 0;
        }
        grid[x][y] = 2; //将走过的岛屿置为2
        int ans = 1;
        for (int[] dirs : directions) {
            int newX = x + dirs[0];
            int newY = y + dirs[1];
            ans += dfs(grid, newX, newY);
        }
        return ans;
    }

}
