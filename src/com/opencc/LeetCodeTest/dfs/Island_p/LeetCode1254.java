package com.opencc.LeetCodeTest.dfs.Island_p;

import com.opencc.LeetCodeTest.Common;

import java.util.Scanner;

/**
 * 统计封闭岛屿的数目
 * <a href="https://leetcode.cn/problems/number-of-closed-islands/">...</a>
 * 输入：grid = [[1,1,1,1,1,1,1,0],[1,0,0,0,0,1,1,0],[1,0,1,0,1,1,1,0],[1,0,0,0,0,1,0,1],[1,1,1,1,1,1,1,0]]
 * 输出：2
 */
public class LeetCode1254 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int[][] grid = Common.parse(in.nextLine());
        System.out.println(new LeetCode1254().closedIsland(grid));
    }

    public int closedIsland(int[][] grid) {
        int ans = 0;
        int m = grid.length;
        int n = grid[0].length;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 0 && dfs(i, j, grid, m, n)) {
                    ans++;
                }
            }
        }
        return ans;
    }

    private boolean dfs(int x, int y, int[][] grid, int m, int n) {
        if (x < 0 || y < 0 || x >= m || y >= n) { //边界判断,如果找到边界外的话就返回false
            return false;
        }
        if (grid[x][y] != 0) { //判断当前位置是否是陆地，不是陆地的话，就返回true。是陆地的话就继续递归，判断下一个节点，一直到找到水返回true
            return true;
        }
        grid[x][y] = -1;
        boolean up = dfs(x - 1, y, grid, m, n);
        boolean down = dfs(x + 1, y, grid, m, n);
        boolean left = dfs(x, y - 1, grid, m, n);
        boolean right = dfs(x, y + 1, grid, m, n);
        return up & down & left & right;
    }
}
