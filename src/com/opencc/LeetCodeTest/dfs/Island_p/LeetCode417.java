package com.opencc.LeetCodeTest.dfs.Island_p;

import com.opencc.LeetCodeTest.Common;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * 太平洋大西洋水流问题
 */
public class LeetCode417 {
    static int[][] directions = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int[][] heights = Common.parseToDoubleArray(in.nextLine());
        LeetCode417 lt = new LeetCode417();
        List<List<Integer>> res = lt.pacificAtlantic(heights);
        List<List<Integer>> res2 = lt.pacificAtlantic2(heights);
        System.out.println(res);
        System.out.println(res2);
    }

    public List<List<Integer>> pacificAtlantic(int[][] heights) {
        List<List<Integer>> res = new ArrayList<>();
        int m = heights.length;
        int n = heights[0].length;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                boolean[][] visitedPa = new boolean[m][n];
                boolean[][] visitedAt = new boolean[m][n];
                if (findPaOcean(i, j, heights, visitedPa) && findAtOcean(i, j, heights, visitedAt)) {
                    List<Integer> ls = new ArrayList<>();
                    ls.add(i);
                    ls.add(j);
                    res.add(ls);
                }
            }
        }
        return res;
    }
    //边界条件xy对应的坐标能到x=0,y=0则是太平洋  x=m,y=n则是大西洋

    private boolean findPaOcean(int x, int y, int[][] heights, boolean[][] visited) {
        if (x < 0 || x > heights.length) {
            return false;
        }
        if (x == 0 || y == 0) {
            return true;
        }
        visited[x][y] = true;
        for (int[] dirs : directions) {
            int newX = x + dirs[0];
            int newY = y + dirs[1];
            if (newX >= 0 && newX < heights.length && newY >= 0 && newY < heights[0].length) {
                if (heights[x][y] >= heights[newX][newY] && !visited[newX][newY]) {
                    if (findPaOcean(newX, newY, heights, visited)) return true;
                }
            }
        }
        return false;
    }

    private boolean findAtOcean(int x, int y, int[][] heights, boolean[][] visited) {
        if (x == heights.length - 1 || y == heights[0].length - 1) {
            return true;
        }
        visited[x][y] = true;
        for (int[] dirs : directions) {
            int newX = x + dirs[0];
            int newY = y + dirs[1];
            if (newX >= 0 && newX < heights.length && newY >= 0 && newY < heights[0].length) {
                if (heights[x][y] >= heights[newX][newY] && !visited[newX][newY]) {
                    if (findAtOcean(newX, newY, heights, visited)) return true;
                }
            }
        }
        return false;
    }

    public List<List<Integer>> pacificAtlantic2(int[][] heights) {
        int m = heights.length;
        int n = heights[0].length;
        boolean[][] pacific = new boolean[m][n];
        boolean[][] atlantic = new boolean[m][n];
        for (int i = 0; i < m; i++) { //遍历最左侧列
            dfs(i, 0, pacific, m, n, heights);
        }
        for (int j = 1; j < n; j++) { //遍历最上侧行
            dfs(0, j, pacific, m, n, heights);
        }
        for (int i = 0; i < m; i++) {//从最右侧开始向内遍历
            dfs(i, n - 1, atlantic, m, n, heights);
        }
        for (int j = 0; j < n - 1; j++) { //从最下侧向内遍历
            dfs(m - 1, j, atlantic, m, n, heights);
        }
        List<List<Integer>> res = new ArrayList<>();
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (pacific[i][j] && atlantic[i][j]) {
                    List<Integer> ls = new ArrayList<>();
                    ls.add(i);
                    ls.add(j);
                    res.add(ls);
                }
            }
        }
        return res;
    }

    private void dfs(int x, int y, boolean[][] visited, int m, int n, int[][] heights) {
        if (visited[x][y]) {
            return;
        }
        visited[x][y] = true;
        for (int[] dirs : directions) {
            int newX = x + dirs[0], newY = y + dirs[1];
            if (newX >= 0 && newX < m && newY >= 0 && newY < n && heights[newX][newY] >= heights[x][y]) {
                dfs(newX, newY, visited, m, n, heights);
            }
        }
    }


}
