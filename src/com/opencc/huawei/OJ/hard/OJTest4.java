package com.opencc.huawei.OJ.hard;

import java.util.Scanner;
import java.util.regex.Pattern;

/**
 * 最长方连续方波信号(200分)
 * <a href="https://app5938.acapp.acwing.com.cn/contest/11/problem/OD1006">...</a>
 *Description
 * 输入一串方波信号，求取最长的完全连续交替方波信号，并将其输出。如果有相同长度的交替方波信号，输出任一即可。方波信号高位用 1 标识，低位用 0 标识。
 * 说明：
 * 1. 一个完整的信号一定以 0 开始然后以 0 结尾，即 010 是一个完整信号，但 101，1010，0101 不是。
 * 2. 输入的一串方波信号是由一个或多个完整信号组成。
 * 3. 两个相邻信号之间可能有 0 个或多个低位，如 0110010，011000010。
 * 4. 同一个信号中可以有连续的高位，如 01110101011110001010，前 14 位是一个具有连续高位的信号。
 * 5. 完全连续交替方波是指 10 交替，如 01010 是完全连续交替方波，0110 不是。
 * 示意图：
 * 连续方波信号：   0   1   1   1   0   1   0   1   0   0   0   1   0   1   0   0   0
 *                 |___|¯¯¯¯¯¯¯|___|¯|___|¯|___|_______|¯|___|¯|___|_______|
 * 二进制表示：    0 1 1 1 0 1 0 1 0 0 0 1 0 1 0 0 0
 * Input
 * 输入一行，为信号字符串（长度≥3且≤1024）。
 * 注：输入总是合法的，不用考虑异常情况。
 * Output
 * 输出一行，为最长的完全连续交替方波信号串。
 * 若不存在完全连续交替方波信号串，输出 -1。
 * 输入：
 * 00101010101100001010010
 * 输出：
 * 01010
 * 提示：
 * 样例	解释说明
 * 样例1	输入信号串中有三个信号：
 * 0 010101010110（第一个信号段）
 * 00 01010（第二个信号段）
 * 010（第三个信号段）第一个信号虽然有交替的方波信号段，但出现了 11 部分的连续高位，不算完全连续交替方波。在剩下的连续方波信号串中，01010 最长。
 */
public class OJTest4 {
    public static void main(String[] args) {
        Scanner in =  new Scanner(System.in);
        String single = in.nextLine();
        System.out.println(findResult(single));
        System.out.println(findResult2(single));
    }

    private static String findResult(String single) {
        // 正则表达式模式，匹配完全连续交替的方波信号
        Pattern pattern = Pattern.compile("^(01)+0$");
        int maxLength = 0;
        String longestWare = "-1";
        StringBuilder currentWare =  new StringBuilder();
        for (char c : single.toCharArray()) {
            if (c == '0') {
                if (currentWare.length()>0 && currentWare.charAt(currentWare.length() - 1) == '0') {
                    if (pattern.matcher(currentWare).matches() && currentWare.length() > maxLength) {
                        maxLength = currentWare.length();
                        longestWare = currentWare.toString();
                    }
//                    currentWare = new StringBuilder();
//                    currentWare.setLength(0);  这三行都是把StringBuilder置为空
                    currentWare.delete(0,currentWare.length());
                }
            }
            currentWare.append(c);
        }
        //检查最后一个波形
        if (pattern.matcher(currentWare).matches() && currentWare.length() > maxLength) {
            longestWare = currentWare.toString();
        }
        return longestWare;
    }
    private static String findResult2(String single) {
        char[] singles = single.toCharArray();
        StringBuilder sb = new StringBuilder();
        int maxLength = 0;
        String result = "-1";
        Pattern pattern = Pattern.compile("^(01)+0$"); //^：表示匹配字符串的开头。(01)+：表示匹配一个或多个连续的01子串。0$：表示匹配以0结尾的字符串。
        for (int i = 0; i < singles.length; i++) {
            if (singles[i] == '0') {
                if (sb.length() > 0 && sb.charAt(sb.length() - 1) == '0') { //说明出现标志位00，之前加入的sb的字符串可以构成一个信号串
                    if (pattern.matcher(sb).matches() && sb.length() > maxLength) {
                        result = sb.toString();
                        maxLength = sb.length();
                    }
                    sb.setLength(0);
                }
            }
            sb.append(singles[i]);
        }
        //尾部的波形结尾是10，不存在下一个0来让他进入上述的匹配判断里，所以得在这里判断一次。
        if (pattern.matcher(sb).matches() && sb.length() > maxLength) {
            result = sb.toString();
            maxLength = sb.length();
        }

        return result;
    }
}
