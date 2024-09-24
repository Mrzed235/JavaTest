package com.opencc.huawei.OJ.medium;

import java.util.Arrays;
import java.util.Scanner;

/**
 * 螺旋矩阵填数
 * <a href="https://www.nowcoder.com/issue/tutorial?zhuanlanId=MgbyJj&uuid=ccc7e66a5e88442bbcc0bedc2490adba">...</a>
 */
public class OJTest12 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt(); //数字个数
        int m = in.nextInt(); //矩阵行数
        int column = 0;
        if (n % m != 0) {
            column = n / m + 1;
        } else column = n / m;
        String[][] matrix = new String[m][column];
        for (String[] row : matrix) {
            Arrays.fill(row, "*");
        }
        solveMatrix(matrix, n);
        for (String[] row : matrix) {
            System.out.println(String.join(" ", row));
        }
    }

    private static void solveMatrix(String[][] matrix, int n) {
        //定义四个边界
        int l = 0, r = matrix[0].length - 1, t = 0, b = matrix.length - 1;
        int x = 1;
        while (true) {
            //从左往右遍历，赋值
            for (int i = l; i <= r; i++) {
                if (x <= n) {
                    matrix[t][i] = Integer.toString(x++);
                }
            }
            if (++t > b) break;//收缩上边界
            //从上往下遍历，赋值
            for (int i = t; i <= b; i++) {
                if (x <= n) {
                    matrix[i][r] = Integer.toString(x++);
                }
            }
            if (--r < l) break; //收缩右边界
            //从右往左遍历，赋值
            for (int i = r; i >= l; i--) {
                if (x <= n) {
                    matrix[b][i] = Integer.toString(x++);
                }
            }
            if (--b < t) break;//收缩下边界
            //从下往上遍历，赋值
            for (int i = b; i >= t; i--) {
                if (x <= n) {
                    matrix[i][l] = Integer.toString(x++);
                }
            }
            if (++l > r) break;//收缩左边界
        }
    }
}
