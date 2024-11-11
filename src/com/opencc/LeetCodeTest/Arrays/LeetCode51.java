package com.opencc.LeetCodeTest.Arrays;

import java.util.*;

/**
 * N皇后
 * <a href="https://leetcode.cn/problems/n-queens/description/?envType=problem-list-v2&envId=array">...</a>
 */
public class LeetCode51 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        LeetCode51 lt = new LeetCode51();
        List<List<String>> res = lt.solveNQueens(n);
//        System.out.println(res);
        for (List<String> re : res) {
            System.out.println(re);
        }
    }

    public List<List<String>> solveNQueens(int n) {
        char[] board = new char[n];
        Arrays.fill(board, '.');
        List<List<String>> result = new ArrayList<>();
        int[] res = new int[n];
        solveNQueens(0, n, board, result, res);
        return result;
    }

    private void solveNQueens(int row, int n, char[] board, List<List<String>> result, int[] res) {
        if (row == n) {
            List<String> coll = new ArrayList<>();
            for (int re : res) {
                StringBuilder sb = new StringBuilder();
                for (int i = 0; i < board.length; i++) {
                    if (i == re) {
                        sb.append('Q');
                        continue;
                    }
                    sb.append(board[i]);
                }
                coll.add(sb.toString());
                sb.setLength(0);
            }
            result.add(coll);
            return;
        }
        for (int col = 0; col < n; col++) {
            //* 逐行往下考察每一行。同列，result[i] == column
            //* 同对角线，row - i == Math.abs(result[i] - column)
            // */
            boolean flag = true;
            res[row] = col;
            for (int i = row - 1; i >= 0; i--) { //检查当前row的前几层每一层的是否在同一列 同一对角线
                if (res[i] == col || row - i == Math.abs(res[i] - col)) {
                    // res[i] == col表示和当前层在同一个列
                    // row - i ==  Math.abs(res[i] - col) 表示和当前层的Q在一个对角线上
                    flag = false;
                    break;
                }
            }
            if (flag) {
                solveNQueens(row + 1, n, board, result, res);
            }
        }
    }
}
