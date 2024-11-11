package com.opencc.LeetCodeTest.dfs.Island_p;

import com.opencc.LeetCodeTest.Common;

import java.util.Scanner;

/**
 * 岛屿面积
 * <a href="https://leetcode.cn/problems/number-of-islands/description/">...</a>
 * 输入：[["1","1","1","1","0"],["1","1","0","1","0"],["1","1","0","0","0"],["0","0","0","0","0"]]
 * 输出：1
 * 输入：[["1","1","0","0","0"],["1","1","0","0","0"],["0","0","1","0","0"],["0","0","0","1","1"]]
 * 输出：3
 */
public class LeetCode200 {
    static int[][] directions = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}}; //上下左右

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String input = in.nextLine();
        input = input.replace("\"", "");
        int[][] grid = Common.parseToDoubleArray(input);
        System.out.println(findGridNums(grid));
    }

    private static int findGridNums(int[][] grid) {
        int gridNum = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                if (dfs(i, j, grid)) {
                    gridNum++;
                }
            }
        }
        return gridNum;
    }

    private static boolean dfs(int x, int y, int[][] grid) {
        if (x < 0 || x == grid.length || y < 0 || y == grid[0].length || grid[x][y] != 1) {
            return false;
        }
        grid[x][y] = 2; //将走过的格子变为2
        for (int[] dirs : directions) {
            int newX = x + dirs[0];
            int newY = y + dirs[1];
            dfs(newX, newY, grid);
        }
        return true;
    }
}
