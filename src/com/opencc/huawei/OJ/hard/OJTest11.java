package com.opencc.huawei.OJ.hard;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

/**
 * 跳马 200分
 * <a href="https://www.nowcoder.com/issue/tutorial?zhuanlanId=MgbyJj&uuid=e3a6d2623b8848e6924648488cbacc6e">...</a>
 */
public class OJTest11 {
    static int[][] directions = {{1, 2}, {1, -2}, {-1, 2}, {-1, -2}, {2, 1}, {2, -1}, {-2, 1}, {-2, -1}};

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int m = in.nextInt();
        int n = in.nextInt();
        char[][] board = new char[m][n];
        for (char[] bs : board) {
            for (int i = 0; i < bs.length; i++) {
                bs[i] = in.next().charAt(0);
            }
        }
        System.out.println(findResult(m, n, board));
    }

    private static int findResult(int m, int n, char[][] board) {
        boolean[][] visited = new boolean[m][n];
        int[][] step = new int[m][n];
        for (boolean[] vi : visited) {
            Arrays.fill(vi, true);
        }
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (board[i][j] != '.') {
                    int index = board[i][j] - '0';
                    bfs(i, j, index, m, n, step, visited);
                }
            }
        }
        int minS = Integer.MAX_VALUE;
        boolean flag = false;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (visited[i][j]) {
                    flag = true;
                    minS = Math.min(minS, step[i][j]);
                }
            }
        }
        return flag ? minS : -1;
    }

    private static void bfs(int x, int y, int index, int m, int n, int[][] step, boolean[][] visited) {
        Queue<int[]> queue = new LinkedList<>();
        boolean[][] end = new boolean[m][n];
        queue.offer(new int[]{x, y, 0});//将起始点加入队列
        end[x][y] = true;
        while (!queue.isEmpty() && index > 0) {//BFS主循环
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                int[] cur = queue.poll();
                for (int[] dir : directions) {
                    int newX = cur[0] + dir[0];
                    int newY = cur[1] + dir[1];
                    if (newX >= 0 && newX < m && newY >= 0 && newY < n && !end[newX][newY]) {//检查新位置是否访问过
                        queue.offer(new int[]{newX, newY, cur[2] + 1});
                        step[newX][newY] += cur[2] + 1;
                        end[newX][newY] = true;
                    }
                }
            }
            index--; //减少剩余步数
        }
        for (int i = 0; i < m; i++) { //更新可达性
            for (int j = 0; j < n; j++) {
                visited[i][j] &= end[i][j];
            }
        }
    }
}
