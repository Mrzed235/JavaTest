package com.opencc.LeetCodeTest.Arrays;

import com.opencc.LeetCodeTest.Common;

import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

/**
 * 有效的数独
 * <a href="https://leetcode.cn/problems/valid-sudoku/description/?envType=problem-list-v2&envId=array">...</a>
 * 请你判断一个 9 x 9 的数独是否有效。只需要 根据以下规则 ，验证已经填入的数字是否有效即可。
 * 数字 1-9 在每一行只能出现一次。
 * 数字 1-9 在每一列只能出现一次。
 * 数字 1-9 在每一个以粗实线分隔的 3x3 宫内只能出现一次。（请参考示例图）
 * 输入：[["5","3",".",".","7",".",".",".","."],["6",".",".","1","9","5",".",".","."],[".","9","8",".",".",".",".","6","."],["8",".",".",".","6",".",".",".","3"],["4",".",".","8",".","3",".",".","1"],["7",".",".",".","2",".",".",".","6"],[".","6",".",".",".",".","2","8","."],[".",".",".","4","1","9",".",".","5"],[".",".",".",".","8",".",".","7","9"]]
 * 输出：true
 * 输入：[["8","3",".",".","7",".",".",".","."],["6",".",".","1","9","5",".",".","."],[".","9","8",".",".",".",".","6","."],["8",".",".",".","6",".",".",".","3"],["4",".",".","8",".","3",".",".","1"],["7",".",".",".","2",".",".",".","6"],[".","6",".",".",".",".","2","8","."],[".",".",".","4","1","9",".",".","5"],[".",".",".",".","8",".",".","7","9"]]
 * 输出： false
 */
public class LeetCode36 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        char[][] board = Common.parseSuDoKu(in.nextLine());
        System.out.println(new LeetCode36().isValidSudoku(board));
        System.out.println(new LeetCode36().isValidSudoku2(board));
    }

    public boolean isValidSudoku2(char[][] board) {
        Set<Integer>[] rows = new HashSet[9];
        Set<Integer>[] cols = new HashSet[9];
        Set<Integer>[] boxes = new HashSet[9];

        for (int i = 0; i < 9; i++) {
            rows[i] = new HashSet<>();
            cols[i] = new HashSet<>();
            boxes[i] = new HashSet<>();
        }

        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (board[i][j] != '.') {
                    int num = board[i][j] - '1';

                    if (!rows[i].add(num) || !cols[j].add(num) || !boxes[(i / 3) * 3 + j / 3].add(num)) {
                        return false;
                    }
                }
            }
        }

        return true;
    }

    public boolean isValidSudoku(char[][] board) {
        //记录某行，某位数字是否已经被放置，被放置的话置为true
        //记录某列，某位数字是否已经被放置，被放置的话置为true
        //记录九宫格，某位数字是否已经被放置，被放置的话置为true
        int m = board.length;
        int n = board[0].length;
        boolean[][] row = new boolean[m][n];
        boolean[][] col = new boolean[m][n];
        boolean[][] block = new boolean[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (board[i][j] != '.') {
                    int num = board[i][j] - '1'; //因为数独范围是1-9所以这里不减0  减1
                    int blockIndex = i / 3 * 3 + j / 3; //一个九宫格内的数组下标重定义，将一个区块内的数字算出来的数字一样
                    System.out.println(blockIndex);
                    if (row[i][num] || col[j][num] || block[blockIndex][num]) {
                        return false;
                    } else {
                        row[i][num] = true;
                        col[j][num] = true;
                        block[blockIndex][num] = true;
                    }
                }
            }
        }
        return true;
    }
}
