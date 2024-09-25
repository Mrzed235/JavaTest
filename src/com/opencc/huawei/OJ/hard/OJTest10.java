package com.opencc.huawei.OJ.hard;

import java.util.Arrays;
import java.util.Scanner;

/**
 * 计算疫情扩散时间
 * <a href="https://app5938.acapp.acwing.com.cn/contest/11/problem/OD1015">...</a>
 * 1 0 1
 * 0 0 0
 * 1 0 1
 * 输入：1,0,1,0,0,0,1,0,1
 * 输出：2
 * 输入：0,0,0,0
 * 输出：-1
 */
public class OJTest10 {
    static int[][] directions = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}}; //定义上下左右

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int[] nums = Arrays.stream(in.nextLine().split(",")).mapToInt(Integer::parseInt).toArray();
        int m = (int) Math.sqrt(nums.length);
        int[][] matrix = new int[m][m];
        int index = 0;
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                matrix[i][j] = nums[index++];
            }
        }
        System.out.println(findDay(matrix));
    }

    private static int findDay(int[][] matrix) {
        int day = 1;
        boolean[][] visited = new boolean[matrix.length][matrix[0].length];
        boolean flag = true;
        while (flag) {
            for (int i = 0; i < matrix.length; i++) {
                for (int j = 0; j < matrix[i].length; j++) {
                    if (matrix[i][j] == day) { //当前坐标被感染了
                        bfs(i, j, matrix, day, visited);
                    }
                }
            }
            day++;
            flag = checkMatrix(matrix);
        }
        return findMaxDayInMatrix(matrix);
    }

    private static int findMaxDayInMatrix(int[][] matrix) {
        int maxNum = 0;
        for (int[] m : matrix) {
            for (int n : m) {
                maxNum = Math.max(n, maxNum);
            }
        }
        return maxNum - 1;
    }

    private static boolean checkMatrix(int[][] matrix) {
        int count = 0;
        for (int[] m : matrix) {
            for (int n : m) {
                if (n == 0) {
                    count++;
                }
            }
        }
        return count > 0 && count < matrix.length * matrix[0].length;
    }

    private static void bfs(int i, int j, int[][] matrix, int day, boolean[][] visited) {
        visited[i][j] = true;
        for (int[] dir : directions) {
            int newX = i + dir[0];
            int newY = j + dir[1];
            if (newX >= 0 && newX < matrix.length && newY >= 0 && newY < matrix[0].length && matrix[newX][newY] == 0) {
                matrix[newX][newY] = day + 1;
            }
        }
    }
}
