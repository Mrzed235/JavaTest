package com.opencc.backtracking;

/**
 * 数独
 *
 */
import java.util.Arrays;

public class SudokuSolverWithPruning {

    // 数独的大小
    private static final int SIZE = 9;
    // 小九宫格的大小
    private static final int SUBGRID_SIZE = 3;

    // 检查在指定列中是否可以放置数字 num
    private static boolean isSafeInRow(int[][] board, int row, int num) {
        for (int col = 0; col < SIZE; col++) {
            if (board[row][col] == num) {
                return false;
            }
        }
        return true;
    }

    // 检查在指定行中是否可以放置数字 num
    private static boolean isSafeInCol(int[][] board, int col, int num) {
        for (int row = 0; row < SIZE; row++) {
            if (board[row][col] == num) {
                return false;
            }
        }
        return true;
    }

    // 检查在指定的小九宫格中是否可以放置数字 num
    private static boolean isSafeInSubgrid(int[][] board, int startRow, int startCol, int num) {
        for (int row = 0; row < SUBGRID_SIZE; row++) {
            for (int col = 0; col < SUBGRID_SIZE; col++) {
                if (board[row + startRow][col + startCol] == num) {
                    return false;
                }
            }
        }
        return true;
    }

    // 检查是否可以在指定位置放置数字 num
    private static boolean isSafe(int[][] board, int row, int col, int num) {
        return isSafeInRow(board, row, num) && isSafeInCol(board, col, num) &&
                isSafeInSubgrid(board, row - row % SUBGRID_SIZE, col - col % SUBGRID_SIZE, num);
    }

    // 解决数独的递归函数
    private static boolean solveSudoku(int[][] board) {
        for (int row = 0; row < SIZE; row++) {
            for (int col = 0; col < SIZE; col++) {
                if (board[row][col] == 0) {
                    for (int num = 1; num <= SIZE; num++) {
                        if (isSafe(board, row, col, num)) {
                            board[row][col] = num;
                            if (solveSudoku(board)) {
                                return true;
                            } else {
                                board[row][col] = 0; // 剪枝：如果当前放置导致后续无法求解，回溯并重置
                            }
                        }
                    }
                    return false;
                }
            }
        }
        return true;
    }

    // 打印数独棋盘
    private static void printBoard(int[][] board) {
        for (int[] row : board) {
            System.out.println(Arrays.toString(row));
        }
    }

    public static void main(String[] args) {
        int[][] board = {
                {5, 3, 0, 0, 7, 0, 0, 0, 0},
                {6, 0, 0, 1, 9, 5, 0, 0, 0},
                {0, 9, 8, 0, 0, 0, 0, 6, 0},
                {8, 0, 0, 0, 6, 0, 0, 0, 3},
                {4, 0, 0, 8, 0, 3, 0, 0, 1},
                {7, 0, 0, 0, 2, 0, 0, 0, 6},
                {0, 6, 0, 0, 0, 0, 2, 8, 0},
                {0, 0, 0, 4, 1, 9, 0, 0, 5},
                {0, 0, 0, 0, 8, 0, 0, 7, 9}
        };

        if (solveSudoku(board)) {
            System.out.println("数独求解成功:");
            printBoard(board);
        } else {
            System.out.println("无法求解数独");
        }
    }
}