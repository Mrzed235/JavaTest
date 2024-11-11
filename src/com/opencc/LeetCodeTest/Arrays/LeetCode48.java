package com.opencc.LeetCodeTest.Arrays;

import com.opencc.LeetCodeTest.Common;

import java.util.Arrays;
import java.util.Scanner;

/**
 * 旋转图像 48
 * <a href="https://leetcode.cn/problems/rotate-image/description/?envType=problem-list-v2&envId=array">...</a>
 * 输入：matrix = [[1,2,3],[4,5,6],[7,8,9]]
 * 输出：[[7,4,1],[8,5,2],[9,6,3]]
 * 输入：matrix = [[5,1,9,11],[2,4,8,10],[13,3,6,7],[15,14,12,16]]
 * 输出：[[15,13,2,5],[14,3,4,1],[12,6,8,9],[16,7,10,11]]
 */
public class LeetCode48 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int[][] matrix = Common.parseToDoubleArray(in.nextLine());
        LeetCode48 lt = new LeetCode48();
        lt.rotate(matrix);
        System.out.println(Arrays.deepToString(matrix));
    }
    public void rotate2(int[][] matrix) {
        int n = matrix.length;

        for (int i = 0; i < n / 2; i++) {
            int[] temRow = matrix[i];
            matrix[i] = matrix[n - 1 - i];
            matrix[n - 1 - i] = temRow;
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < i; j++) {
                int tem = matrix[i][j];
                matrix[i][j] = matrix[j][i];
                matrix[j][i] = tem;
            }
        }

    }

    public void rotate(int[][] matrix) {
        int left = 0, right = matrix[0].length - 1, top = 0, bottom = matrix.length - 1;
        //先通过螺旋数组读取这个二维，然后在螺旋修改
        int[] result = getSpiral(matrix, left, right, top, bottom);
        rotateMatrix(matrix, result, left, right, top, bottom);
    }

    private void rotateMatrix(int[][] matrix, int[] res, int left, int right, int top, int bottom) {
        int x = 0; //定义自增数组下标初始位
        while (true) {
            //从上往下遍历
            for (int i = top; i <= bottom; i++) {
                matrix[i][right] = res[x++];
            }
            if (--right < left) break; //收缩右边界
            //从右往左遍历
            for (int i = right; i >= left; i--) {
                matrix[bottom][i] = res[x++];
            }
            if (--bottom < top) break; //收缩下边界
            //从下往上遍历
            for (int i = bottom; i >= top; i--) {
                matrix[i][left] = res[x++];
            }
            if (++left > right) break;
            //从左往右遍历
            for (int i = left; i <= right; i++) {
                matrix[top][i] = res[x++];
            }
            if (++top > bottom) break; //收缩上边界
        }
    }

    private int[] getSpiral(int[][] matrix, int left, int right, int top, int bottom) {
        int x = 0; //定义自增数组下标初始位
        int[] res = new int[matrix.length * matrix[0].length];
        while (true) {
            //从左往右遍历
            for (int i = left; i <= right; i++) {
                res[x++] = matrix[top][i];
            }
            if (++top > bottom) break; //收缩上边界
            //从上往下遍历
            for (int i = top; i <= bottom; i++) {
                res[x++] = matrix[i][right];
            }
            if (--right < left) break; //收缩右边界
            //从右往左遍历
            for (int i = right; i >= left; i--) {
                res[x++] = matrix[bottom][i];
            }
            if (--bottom < top) break; //收缩下边界
            //从下往上遍历
            for (int i = bottom; i >= top; i--) {
                res[x++] = matrix[i][left];
            }
            if (++left > right) break;
        }
        return res;
    }
}
