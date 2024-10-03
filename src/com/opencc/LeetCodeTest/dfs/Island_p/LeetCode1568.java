package com.opencc.LeetCodeTest.dfs.Island_p;

import com.opencc.LeetCodeTest.Common;

import java.util.Scanner;

/**
 * 使陆地分离的最小天数  没搞懂
 * <a href="https://leetcode.cn/problems/minimum-number-of-days-to-disconnect-island/description/">...</a>
 * 输入：[[1,1,1,1],[1,1,1,1],[1,1,1,1]] 输出：2
 * 输入：[[0,1,1,0],[0,1,1,0],[0,0,0,0]] 输出：2
 * 输入：[[1,1]] 输出：2
 */
public class LeetCode1568 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int[][] grid = Common.parse(in.nextLine());
        LeetCode1568 lt = new LeetCode1568();
        System.out.println(lt.minDays(grid));
    }

    int[] dx = {0, 1, 0, -1};
    int[] dy = {1, 0, -1, 0};

    public int minDays(int[][] grid) {
        int n = grid.length, m = grid[0].length;
        // 岛屿数量不为 1，陆地已经分离
        if (count(grid, n, m) != 1) {
            return 0;
        }
        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < m; ++j) {
                if (grid[i][j] != 0) {
                    grid[i][j] = 0;
                    if (count(grid, n, m) != 1) {
                        // 更改一个陆地单元为水单元后陆地分离
                        return 1;
                    }
                    grid[i][j] = 1;
                }
            }
        }
        return 2;
    }

    public int count(int[][] grid, int n, int m) {
        int cnt = 0;
        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < m; ++j) {
                if (grid[i][j] == 1) {
                    cnt++;
                    dfs(i, j, grid, n, m);
                }
            }
        }
        // 还原
        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < m; ++j) {
                if (grid[i][j] == 2) {
                    grid[i][j] = 1;
                }
            }
        }
        return cnt;
    }

    public void dfs(int x, int y, int[][] grid, int n, int m) {
        grid[x][y] = 2;
        for (int i = 0; i < 4; ++i) {
            int tx = dx[i] + x;
            int ty = dy[i] + y;
            if (tx < 0 || tx >= n || ty < 0 || ty >= m || grid[tx][ty] != 1) {
                continue;
            }
            dfs(tx, ty, grid, n, m);
        }
    }
}
