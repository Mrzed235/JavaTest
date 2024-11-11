package com.opencc.LeetCodeTest.Arrays;

import java.util.ArrayList;
import java.util.List;

/**
 * 螺旋矩阵
 * <a href="https://leetcode.cn/problems/spiral-matrix/description/">...</a>
 * 问题描述
 * LYA小姐最近在家无聊时发明了一种填数游戏。给定一个矩阵的行数M和需要填入的数字个数N,从矩阵的左上角开始,按顺时针螺旋的方式依次填入从L开始的连续正整数。
 * 矩阵需要满足以下要求:
 * 1:每行数字的个数相同。
 * 2:列数尽可能少。
 * 3:优先填充矩阵的外圈。
 * 4：如果数字不够填充完整个矩阵,则使用单个星号 * 占位。
 * 输入格式
 * 输入为一行,包含两个用空格分隔的正整数N和M,分别表示需要填入的数字个数和矩阵的行数。
 * 输出格式
 * 输出为一个矩阵,每行元素之间用单个空格分隔,最后一行无多余空格。
 * 样例输入 1
 * 9 4
 * 样例输出 1
 * 1 2 3
 * * * 4
 * 9 * 5
 * 8 7 6
 * 样例输入 2
 * 3 5
 * 样例输出 2
 * 1
 * 2
 * 3
 * *
 * *
 */
public class LeetCode54 {
    public List<Integer> spiralOrder(int[][] matrix) {
        if (matrix.length == 0) {
            return null;
        }
        int l = 0, r = matrix[0].length - 1, t = 0, b = matrix.length - 1;
        List<Integer> rs = new ArrayList<>();
        while (true) {
            //从左到右遍历
            for (int i = l; i <= r; i++) {
                rs.add(matrix[t][i]);
            }
            if (++t > b) break; //上边界收缩
            //从上往下遍历
            for (int i = t; i <= b; i++) {
                rs.add(matrix[i][r]);
            }
            if (--r < l) break; //收缩右边界
            //从右往左遍历
            for (int i = r; i >= l; i--) {
                rs.add(matrix[b][i]);
            }
            if (--b < t) break; //收缩下边界
            //从下往上遍历
            for (int i = b; i >= t; i--) {
                rs.add(matrix[i][l]);
            }
            if (++l > r) break;//收缩左边界
        }
        return rs;
    }

    public static void main(String[] args) {
        int[][] matrix = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
//        int[][] matrix = {{2, 5, 8}, {4, 0, -1}};
        List<Integer> rs = new LeetCode54().spiralOrder(matrix);
        System.out.println(rs);
    }
}
