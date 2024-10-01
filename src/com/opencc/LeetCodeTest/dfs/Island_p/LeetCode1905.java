package com.opencc.LeetCodeTest.dfs.Island_p;

import com.opencc.LeetCodeTest.Common;

import java.util.Arrays;
import java.util.Scanner;

/**
 * 统计子岛屿
 * <a href="https://leetcode.cn/problems/count-sub-islands/description/">...</a>
 * 输入：
 * [[1,1,1,0,0],[0,1,1,1,1],[0,0,0,0,0],[1,0,0,0,0],[1,1,0,1,1]]
 * [[1,1,1,0,0],[0,0,1,1,1],[0,1,0,0,0],[1,0,1,1,0],[0,1,0,1,0]]
 * 输出：3
 * 输入：
 * [[1,0,1,0,1],[1,1,1,1,1],[0,0,0,0,0],[1,1,1,1,1],[1,0,1,0,1]]
 * [[0,0,0,0,0],[1,1,1,1,1],[0,1,0,1,0],[0,1,0,1,0],[1,0,0,0,1]]
 * 输出：2
 */
public class LeetCode1905 {
    static int[][] directions = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}}; //上下左右

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int[][] grid1 = Common.parse(in.nextLine());
        int[][] grid2 = Common.parse(in.nextLine());
        int[][] grid2Back = new int[grid2.length][grid2[0].length];
        for (int i = 0; i < grid2Back.length; i++) {
            grid2Back[i] = Arrays.copyOf(grid2[i], grid2[i].length);
        }
        System.out.println(new LeetCode1905().countSubIslands(grid1, grid2));
        System.out.println(new LeetCode1905().countSubIslands2(grid1, grid2Back));
    }

    public int countSubIslands(int[][] grid1, int[][] grid2) {
        int m = grid1.length;
        int n = grid1[0].length;
        int gridNums = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                boolean[] flag = {true}; //定义boolean类型数组主要是为了引用传递，能把修改后的值带出来，定义成boolean flag = true就不行
                if (grid2[i][j] == 1) {
                    dfs(i, j, grid1, grid2, m, n, flag);
                    gridNums++;
                    if (!flag[0]) {
                        gridNums--;
                    }
                }
            }
        }
        return gridNums;
    }

    private static void dfs(int x, int y, int[][] grid1, int[][] grid2, int m, int n, boolean[] flag) {
        if (x < 0 || x == m || y < 0 || y == n || grid2[x][y] != 1) {
            return;
        }
        if ((grid1[x][y] & grid2[x][y]) != 1) { //grid2的格子是否被1包含
            flag[0] = false;
        }
        grid2[x][y] = 2;
        for (int[] dirs : directions) {
            int newX = x + dirs[0];
            int newY = y + dirs[1];
            dfs(newX, newY, grid1, grid2, m, n, flag);
        }
        //把四个方向都走一边就可以向上层返回操作结束的标识就是true
    }

    public int countSubIslands2(int[][] grid1, int[][] grid2) {
        int m = grid2.length;
        int n = grid2[0].length;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid2[i][j] == 1) {
                    grid2[i][j] += grid1[i][j];
                }
            }
        }
        int res = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid2[i][j] == 2 && findAll(i, j, grid2)) {
                    res++;
                }
            }
        }
        return res;
    }

    private boolean findAll(int x, int y, int[][] grid2) {
        if (x < 0 || x == grid2.length || y < 0 || y == grid2[0].length) { //当找到四个边界不满足的时候返回true
            return true;
        }
        if (grid2[x][y] != 2) {
            return grid2[x][y] == 0; //即找到连接的岛屿等于1 的时候就返回false，等于0 是正常的
        }
        grid2[x][y] = 0; //置为0是为了防止重复遍历，也可以用一个标志位的数组去判断
        boolean down = findAll(x - 1, y, grid2);
        boolean up = findAll(x + 1, y, grid2);
        boolean right = findAll(x, y - 1, grid2);
        boolean left = findAll(x, y + 1, grid2);
        return down & up & right & left;
    }
}
