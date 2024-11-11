package com.opencc.LeetCodeTest.dfs.Island_p;

import com.opencc.LeetCodeTest.Common;

import java.util.*;

/**
 * 最大人工岛 困难
 * <a href="https://leetcode.cn/problems/making-a-large-island/description/">...</a>
 */
public class LeetCode827 {
    static int[][] directions = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int[][] grid = Common.parseToDoubleArray(in.nextLine());
        LeetCode827 lt = new LeetCode827();
        System.out.println(lt.largestIsland(grid));
        System.out.println(lt.largestIsland2(grid));
    }

    public int largestIsland(int[][] grid) { //暴力破解
        //遍历整个grid找到为0的所有位置坐标，从所有的0尝试开始，找出最大面积
        int m = grid.length, n = grid[0].length;
        int maxArea = 0;
        int count = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                boolean[][] visited = new boolean[m][n];
                if (grid[i][j] == 0) {
                    grid[i][j] = 1;
                    maxArea = Math.max(maxArea, dfs(i, j, grid, visited, m, n));
                    grid[i][j] = 0;
                    count++;
                }
            }
        }
        return count > 0 ? maxArea : m * n;
    }

    private static int dfs(int x, int y, int[][] grid, boolean[][] visited, int m, int n) {
        if (x < 0 || x >= m || y < 0 || y >= n || visited[x][y] || grid[x][y] != 1) {
            return 0;
        }
        int ans = 1;
        visited[x][y] = true;
        for (int[] dirs : directions) {
            int newX = x + dirs[0];
            int newY = y + dirs[1];
            ans += dfs(newX, newY, grid, visited, m, n);
        }
        return ans;
    }

    public int largestIsland2(int[][] grid) {
        Map<Integer, Integer> mapArea = new HashMap<>();
        //先对岛屿进行遍历，每个岛屿进行一次记录保存到map里
        int m = grid.length, n = grid[0].length;
        int flag = 2;
        for (int i = 0; i < m; i++) {  //第一遍搜索是为了对所有的面积进行标记和存储
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 1) {
                    mapArea.put(flag, caArea(i, j, grid, flag, m, n));
                    flag++;
                }
            }
        }
        int maxArea = 0;
        //对岛屿进行遍历，找到所有0位置关联的最大面积。
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 0) {
                    maxArea = Math.max(maxArea, findArea(i, j, grid, mapArea));
                }
            }
        }
        return maxArea;
    }

    private int findArea(int x, int y, int[][] grid, Map<Integer, Integer> mapArea) {
        int area = 1;
        Set<Integer> set = new HashSet<>();
        set.add(findAllGridNums(x - 1, y, grid));
        set.add(findAllGridNums(x + 1, y, grid));
        set.add(findAllGridNums(x, y - 1, grid));
        set.add(findAllGridNums(x, y + 1, grid));
        for (Integer i : set) {
            area += mapArea.getOrDefault(i, 0);
        }
        return area;
    }

    private int findAllGridNums(int x, int y, int[][] grid) {
        if (x < 0 || x >= grid.length || y < 0 || y >= grid[0].length) {
            return 0;
        }
        return grid[x][y];
    }

    private int caArea(int x, int y, int[][] grid, int flag, int m, int n) {
        int ans = 1;
        if (x < 0 || x >= m || y < 0 || y >= n || grid[x][y] != 1) {
            return 0;
        }
        grid[x][y] = flag;
        for (int[] dirs : directions) {
            int newX = x + dirs[0];
            int newY = y + dirs[1];
            ans += caArea(newX, newY, grid, flag, m, n);
        }
        return ans;
    }
}
