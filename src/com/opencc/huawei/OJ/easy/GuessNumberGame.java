package com.opencc.huawei.OJ.easy;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * 等同于OJTest9
 */
class GuessInfo {
    String guess;
    int a;
    int b;

    public GuessInfo(String guess, int a, int b) {
        this.guess = guess;
        this.a = a;
        this.b = b;
    }
}

public class GuessNumberGame {


    public static boolean check(String answer, GuessInfo info) {
        int aCount = 0;
        int bCount = 0;
        boolean[] usedAnswer = new boolean[4];
        boolean[] usedGuess = new boolean[4];

        //统计数字正确且位置正确的数的个数
        for (int i = 0; i < 4; i++) {
            if (answer.charAt(i) == info.guess.charAt(i)) {
                aCount++;
                usedAnswer[i] = true;
                usedGuess[i] = true;
            }
        }

        // 统计 B 的数量
        for (int i = 0; i < 4; i++) {
            if (!usedGuess[i]) {
                for (int j = 0; j < 4; j++) {
                    if (!usedAnswer[j] && info.guess.charAt(i) == answer.charAt(j)) {
                        bCount++;
                        usedAnswer[j] = true;
                        break;
                    }
                }
            }
        }

        return aCount == info.a && bCount == info.b;
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = Integer.parseInt(in.nextLine());
        List<GuessInfo> guessInfos = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            String[] stirs = in.nextLine().split(" ");
            String[] gusses = stirs[1].replace("A", " ").replace("B", " ").split(" ");
            int a = Integer.parseInt(gusses[0]);
            int b = Integer.parseInt(gusses[1]);
            guessInfos.add(new GuessInfo(stirs[0], a, b));
        }

        int count = 0;
        String answer = "";

        // 遍历所有可能的四位数
        for (int i = 0; i < 10000; i++) {
            String temp = String.format("%04d", i);
            boolean valid = true;

            // 检查当前数字是否满足所有猜测条件
            for (GuessInfo info : guessInfos) {
                if (!check(temp, info)) {
                    valid = false;
                    break;
                }
            }

            if (valid) {
                count++;
                answer = temp;
                if (count > 1) {
                    break;
                }
            }
        }

        if (count == 1) {
            System.out.println(answer);
        } else {
            System.out.println("NA");
        }
    }
}