package com.opencc.huawei.OJ.medium;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * 机器人活动区域
 * <a href="https://www.nowcoder.com/issue/tutorial?zhuanlanId=MgbyJj&uuid=d38d9d9edbfa452fb5421902b0b7dd85">...</a>
 * 问题描述
 * 现有一个机器人，可放置于M*N的网格中任意位置，每个网格包含一个非负整数编号，当相邻网格的数字编号差值的绝对值小于等于 1 时，机器人可以在网格间移动。
 * 问题： 求机器人可活动的最大范围对应的网格点数目。
 * 说明： 网格左上角坐标为(0,0),右下角坐标为(m-1,n-1)，机器人只能在相邻网格间上下左右移动。
 * 输入格式
 * 第 1 行输入为M和N,M表示网格的行数,N表示网格的列数。之后M行表示网格数值，每行N个数值（数值大小用K表示），数值间用单个空格分隔，行首行尾无多余空格。
 * 输出格式
 * 输出 1 行，包含 1 个数字，表示最大活动区域的网格点数目，行首行尾无多余空格。
 * 输入：
 * 4 4
 * 1 2 5 2
 * 2 4 4 5
 * 3 5 7 1
 * 4 6 2 4
 * 输出：6
 */
public class OJTest11 {

    static int[][] directions = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};//移动的四个方向，上下左右

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int M = in.nextInt();
        int N = in.nextInt();
        int[][] maze = new int[M][N];
        for (int i = 0; i < maze.length; i++) {
            for (int j = 0; j < maze[0].length; j++) {
                maze[i][j] = in.nextInt();
            }
        }
        List<Integer> result = new ArrayList<>();
        for (int i = 0; i < maze.length; i++) {
            for (int j = 0; j < maze[0].length; j++) {
                int rs = solveMachine(maze, i, j);
                result.add(rs);
            }
        }
        System.out.println(result.stream().max(Integer::compareTo).orElse(null));
    }

    private static int solveMachine(int[][] maze, int i, int j) {
        //定义一个boolean的二维数组来记录路径是否走过了，走过了标记为true
        int M = maze.length;
        int N = maze[0].length;
        int count =0;
        boolean[][] visited = new boolean[M][N];
        if (!dfs(maze, i, j, visited)) {
            for (boolean[] bs : visited) {
                for (boolean b : bs) {
                    if (b) {
                        count++;
                    }
                }
            }
        }
        return count;
    }

    public static boolean dfs(int[][] maze, int i, int j, boolean[][] visited) {
        visited[i][j] = true; //进来函数先标记当前点
        //递归中止条件，发现上下左右都不能走就是中止条件
        for (int[] dir : directions) {
            int newX = i + dir[0];
            int newY = j + dir[1];
            if (newX >= 0 && newX < maze.length && newY >= 0 && newY < maze[0].length && !visited[newX][newY]) {
                if (Math.abs(maze[i][j] - maze[newX][newY]) <= 1) {
                    dfs(maze, newX, newY, visited);
                }
            }
        }
        //走到这说明四个方向都尝试了走不通，直接返回上层即可
        return false;
    }
}
