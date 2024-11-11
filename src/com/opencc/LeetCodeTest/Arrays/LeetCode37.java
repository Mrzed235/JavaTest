package com.opencc.LeetCodeTest.Arrays;

import com.opencc.LeetCodeTest.Common;

import java.util.Arrays;
import java.util.Scanner;

/**
 * 解数独
 * <a href="https://leetcode.cn/problems/sudoku-solver/?envType=problem-list-v2&envId=array">...</a>
 * 输入：board = [["5","3",".",".","7",".",".",".","."],["6",".",".","1","9","5",".",".","."],[".","9","8",".",".",".",".","6","."],["8",".",".",".","6",".",".",".","3"],["4",".",".","8",".","3",".",".","1"],["7",".",".",".","2",".",".",".","6"],[".","6",".",".",".",".","2","8","."],[".",".",".","4","1","9",".",".","5"],[".",".",".",".","8",".",".","7","9"]]
 * 输出：[["5","3","4","6","7","8","9","1","2"],["6","7","2","1","9","5","3","4","8"],["1","9","8","3","4","2","5","6","7"],["8","5","9","7","6","1","4","2","3"],["4","2","6","8","5","3","7","9","1"],["7","1","3","9","2","4","8","5","6"],["9","6","1","5","3","7","2","8","4"],["2","8","7","4","1","9","6","3","5"],["3","4","5","2","8","6","1","7","9"]]
 */
public class LeetCode37 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        char[][] board = Common.parseSuDoKu(in.nextLine());
        LeetCode37 lt = new LeetCode37();
        lt.solveSudoku(board);
        System.out.println(Arrays.deepToString(board));

    }

    public void solveSudoku(char[][] board) {
        dfs(board, 0, 0);
    }

    private boolean dfs(char[][] board, int row, int col) {
        if (col == 9) {
            col = 0;
            row++;
        }
        if (row == 9) {
            return true;
        }
        if (board[row][col] != '.') {
            return dfs(board, row, col + 1);
        }
        for (char c = '1'; c <= '9'; c++) {
            if (isValid(board, row, col, c)) { //检查是否在同一行同一列，同一9宫格内存在相同数
                board[row][col] = c; //检查c符合条件，将c赋值给当前的坐标，然后走下一个判断；
                if (dfs(board, row, col + 1)) {
                    return true;
                }
                board[row][col] = '.';
            }
        }
        return false;
    }

    private boolean isValid(char[][] board, int row, int col, char c) {
        for (int i = 0; i < 9; i++) {
            if (board[row][i] == c || board[i][col] == c || board[(row / 3) * 3 + i / 3][(col / 3) * 3 + i % 3] == c) {
                return false;
            }
        }
        return true;
    }
}
