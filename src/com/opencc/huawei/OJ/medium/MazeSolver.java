package com.opencc.huawei.OJ.medium;

import java.util.ArrayList;
import java.util.List;

/**
 * 经典走迷宫问题；
 */
public class MazeSolver {
    static int[][] directions = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}}; //定义上下左右

    private static List<int[]> solveMaze(int[][] maze, int[] start, int[] end) {
        //定义一个boolean的二维数组来记录路径是否走过了，走过了标记为false
        int m = maze.length;
        int n = maze[0].length;
        boolean[][] visited = new boolean[m][n];
        List<int[]> path = new ArrayList<>();
        if (dfs(maze, start, end, visited, path)) {
            return path;
        }
        return null;
    }

    public static boolean dfs(int[][] maze, int[] curr, int[] end, boolean[][] visited, List<int[]> path) {
        int x = curr[0];
        int y = curr[1];
        if (x == end[0] && y == end[1]) { //说明已经走到了目的地
            path.add(curr);
            return true;
        }
        visited[x][y] = true; //标记当前xy为true
        path.add(curr);
        for (int[] dir : directions) {
            int newX = x + dir[0];
            int newY = y + dir[1];
            if (newX >= 0 && newX < maze.length && newY >= 0 && newY < maze[0].length && !visited[newX][newY] && maze[newX][newY] == 0) {
                //判断即将要走的xy的坐标是否在边界内，且是否已经走过，并且是否等于0，等于0说明能走，1是墙
                if (dfs(maze, new int[]{newX, newY}, end, visited, path)) {
                    return true;
                }
            }
        }
        //走到这说明以当前xy的路径都尝试了，且都失败了，所以要退出一步骤
        path.remove(path.size() - 1);
        return false;
    }

    public static void main(String[] args) {
        int[][] maze = {{0, 1, 0, 0}, {0, 0, 1, 0}, {0, 0, 0, 0}, {1, 0, 1, 0}};
        int[] start = {0, 0};
        int[] end = {3, 3};
        List<int[]> path = solveMaze(maze, start, end);
        if (path != null) {
            for (int[] p : path) {
                System.out.println("(" + p[0] + ", " + p[1] + ")");
            }
        } else {
            System.out.println("没有找到路径");
        }
    }


}
