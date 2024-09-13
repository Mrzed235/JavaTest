package com.opencc.huawei.OJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
/**
 * 猜数字
 * 题目描述
 * 一个人设定一组四码的数字作为谜底，另一方猜。每猜一个数，出数者就要根据这个数字给出提示，提示以XAYB形式呈现，直到猜中位置。其中X表示位置正确的数的个数(数字正确且位置正确)，而Y表示数字正确而位置不对的数的个数。例如，当谜底为8123，而猜谜者猜1052时，出题者必须提示0A2B。例如，当谜底为5637，而猜谜者才4931时，出题者必须提示1A0B。当前已知N组猜谜者猜的数字与提示，如果答案确定，请输出答案，不确定则输出NA。
 * 输入描述
 * 第一行输入一个正整数，0<N<100。
 * 接下来N行，每一行包含一个猜测的数字与提示结果。
 * 输出描述
 * 输出最后的答案，答案不确定则输出NA。
 * 示例1
 * 输入
 * 6
 * 4815 1A1B
 * 5716 0A1B
 * 7842 0A1B
 * 4901 0A0B
 * 8585 3A0B
 * 8555 2A1B
 * 输出
 * 3585
 */
public class OJTest9 {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(reader.readLine());
        String[] guesses = new String[n];
        int[][] hints = new int[n][2];
        for (int i = 0; i < n; i++) {
            String[] line = reader.readLine().split(" ");
            guesses[i] = line[0];
            String[] strs =  line[1].replace("A"," ").replace("B", " ").split(" ");
            hints[i][0]= Integer.parseInt(strs[0]);
            hints[i][1]= Integer.parseInt(strs[1]);
        }
        int validCount = 0;
        String answer = "";

        // 遍历所有可能的四位数
        for (int num = 0; num < 10000; num++) {
            String temp = String.format("%04d", num);
            boolean isValid = true;


            // 检查当前数字是否符合所有猜测的提示
            for (int i = 0; i < n; i++) {
                if (!check(temp, guesses[i], hints[i][0], hints[i][1])) {
                    isValid = false;
                    break;
                }
            }

            if (isValid) {
                validCount++;
                answer = temp;
                if (validCount > 1) {
                    break;
                }
            }
        }

        if (validCount == 1) {
            System.out.println(answer);
        } else {
            System.out.println("NA");
        }

    }

    // 检查当前猜测与谜底是否匹配
    public static boolean check(String target, String guess, int a, int b) {
        int correctPosition = 0;
        int correctNumberWrongPosition = 0;
        boolean[] usedTarget = new boolean[4];
        boolean[] usedGuess = new boolean[4];

        // 统计位置和数字都正确的数量
        for (int i = 0; i < 4; i++) {
            if (target.charAt(i) == guess.charAt(i)) {
                correctPosition++;
                usedTarget[i] = true;
                usedGuess[i] = true;
            }
        }

        // 统计数字正确但位置不正确的数量
        for (int i = 0; i < 4; i++) {
            if (!usedGuess[i]) {
                for (int j = 0; j < 4; j++) {
                    if (!usedTarget[j] && guess.charAt(i) == target.charAt(j)) {
                        correctNumberWrongPosition++;
                        usedTarget[j] = true;
                        break;
                    }
                }
            }
        }

        return correctPosition == a && correctNumberWrongPosition == b;
    }
}
