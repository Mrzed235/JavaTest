package com.opencc.LeetCodeTest.dfs.Island_p;

import com.opencc.LeetCodeTest.Common;

import java.util.Scanner;

/**
 * 岛屿的周长
 * <a href="https://leetcode.cn/problems/island-perimeter/">...</a>
 * 输入：grid = [[0,1,0,0],[1,1,1,0],[0,1,0,0],[1,1,0,0]]
 * 输出：16
 * 输入：grid = [[1]]
 * 输出：4
 * 输入：grid = [[1,0]]
 * 输出：4
 */
public class LeetCode463 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int[][] grid = Common.parseToDoubleArray(in.nextLine());
        System.out.println(islandPerimeter(grid));
    }

    public static int islandPerimeter(int[][] grid) {
        int area = 0;
        int m = grid.length, n = grid[0].length;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 1) {
                    area += bfs(i, j, grid);
                }
            }
        }
        return area;
    }

    private static int bfs(int x, int y, int[][] grid) {
        int count = 0;
        boolean up = findLength(x - 1, y, grid); //判断四个方向的周长是否能用，不连接岛屿的话周长是可以+1的
        boolean down = findLength(x + 1, y, grid);
        boolean left = findLength(x, y + 1, grid);
        boolean right = findLength(x, y - 1, grid);
        if (up) {
            count++;
        }
        if (down) {
            count++;
        }
        if (left) {
            count++;
        }
        if (right) {
            count++;
        }
        return count;
    }

    private static boolean findLength(int x, int y, int[][] grid) {
        return x < 0 || x >= grid.length || y < 0 || y >= grid[0].length || grid[x][y] == 0;
        //判断当前xy坐标是否是边界或者0，返回true，是岛屿返回false代表不计入周长
    }
}
