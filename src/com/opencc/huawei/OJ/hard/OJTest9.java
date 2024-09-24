package com.opencc.huawei.OJ.hard;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * 特殊加密算法
 * <a href="https://www.nowcoder.com/issue/tutorial?zhuanlanId=MgbyJj&uuid=38e604354e004aa1aada41c4cb70cccf">...</a>
 * 输入：
 * 2
 * 0 3
 * 3
 * 0 0 2
 * 1 3 4
 * 6 6 4
 * 输出 0 1 1 1
 * 输入：
 * 2
 * 0 5
 * 3
 * 0 0 2
 * 1 3 4
 * 6 6 4
 * 输出：error
 */
public class OJTest9 {
    static int[][] directions = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};//移动的四个方向，上下左右
    static int[] data;
    static int[][] passW;
    static int M;
    static int N;

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        N = in.nextInt();
        data = new int[N];
        for (int i = 0; i < data.length; i++) {
            data[i] = in.nextInt();
        }
        M = in.nextInt();
        passW = new int[M][M];
        for (int[] ps : passW) {
            for (int i = 0; i < ps.length; i++) {
                ps[i] = in.nextInt();
            }
        }
        solvePass();
    }

    private static void solvePass() {
        //先找到data数组的头部明文在密文中的下标位置并记录。
        for (int i = 0; i < M; i++) {
            for (int j = 0; j < M; j++) {
                if (data[0] == passW[i][j]) {//找到明文密码的开始坐标
                    boolean[][] visited = new boolean[M][M]; //定义boolean数组来标记走过的路线
                    List<Integer> res = new ArrayList<>();
                    if (dfs(i, j, 0, visited, res)) {
                        for (int k = 0; k < res.size(); k++) {
                            System.out.print(res.get(k));
                            if (k < res.size() - 1) {
                                System.out.print(" ");
                            }
                        }
                        return;
                    }
                }
            }
        }
        System.out.println("error");
    }

    private static boolean dfs(int x, int y, int index, boolean[][] visited, List<Integer> res) {
        visited[x][y] = true;
        res.add(x);
        res.add(y);
        //判断递归出口，
        if (index == N - 1) {
            return true;
        }
        for (int[] dir : directions) {
            int newX = x + dir[0];
            int newY = y + dir[1];
            if (newX >= 0 && newX < M && newY >= 0 && newY < M && !visited[newX][newY] && passW[newX][newY] == data[index + 1]) {
                if (dfs(newX, newY, index + 1, visited, res)) {
                    return true;
                }
            }
        }
        visited[x][y] = false; //走到这说明四个方向都走不通，需要回退
        res.remove(res.size() - 1);
        res.remove(res.size() - 1);
        return false;
    }
}
