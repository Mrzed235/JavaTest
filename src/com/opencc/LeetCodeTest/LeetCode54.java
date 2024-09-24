package com.opencc.LeetCodeTest;

import java.util.ArrayList;
import java.util.List;

/**
 * 螺旋矩阵
 * <a href="https://leetcode.cn/problems/spiral-matrix/description/">...</a>
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
