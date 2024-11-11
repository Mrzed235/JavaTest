package com.opencc.LeetCodeTest.putaocheng;

import java.util.Scanner;

/**
 * 示例：
 * 以下字符串是合法的十进制数字：
 * 1.23E+10
 * 以下字符串是合法的十六进制数字：
 * ABC
 * A.BC
 * 以下字符串既是合法的十进制数字，也是合法的十六进制数字：
 * 0
 * 123
 * 1.23
 * -123
 * 以下字符串是不合法的数字：
 * 000
 * 123abcdefg
 * .123
 * 0.1.2
 */
public class NumberValidator {
    private final static String digits = "0123456789";
    private final static String hexDigits = "abcdefABCDEF";

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String input = in.nextLine();
        System.out.println(ValidIfDecimalOrHexadecimal(input));
    }

    private static String ValidIfDecimalOrHexadecimal(String input) {
        if (input == null || input.isEmpty()) {
            return ("\"" + input + "\" 是不合法的数字.");
        }
        // 处理正负号
        if (input.charAt(0) == '-' || input.charAt(0) == '+') {
            input = input.substring(1);
        }

        boolean isDecimal = isDecimal(input);
        boolean isHexadecimal = isHexadecimal(input);
        if (isDecimal && isHexadecimal) {
            return ("\"" + input + "\" 既是合法的十进制数字，也是合法的十六进制数字.");
        } else if (isDecimal) {
            return ("\"" + input + "\" 是合法的十进制数字.");
        } else if (isHexadecimal) {
            return ("\"" + input + "\" 是合法的十六进制数字.");
        } else {
            return ("\"" + input + "\" 是不合法的数字.");
        }
    }

    public static boolean isDecimal(String str) {
        int index = 0;
        int length = str.length();

        //如果以.开头或者0开头的数字类似00，01 为不合法字符串
        if (str.charAt(index) == '.' || (str.charAt(index) == '0' && length > 1 && digits.indexOf(str.charAt(index + 1)) != -1)) {
            return false;
        }

        boolean decimalPointSeen = false;
        boolean exponentSeen = false;

        for (; index < length; index++) {
            char c = str.charAt(index);

            if (c == '.') {
                if (decimalPointSeen) {
                    return false;
                }
                decimalPointSeen = true;
            } else if (c == 'e' || c == 'E') {
                exponentSeen = true;

                // 检查指数部分的正负号
                if (index + 1 < length && (str.charAt(index + 1) == '-' || str.charAt(index + 1) == '+')) {
                    index++;
                }

                // 检查指数部分是否全为数字
                for (int expIndex = index + 1; expIndex < length; expIndex++) {
                    if (digits.indexOf(str.charAt(expIndex)) == -1) {
                        return false;
                    }
                }

                break;
            } else if (digits.indexOf(c) == -1) {
                return false;
            }
        }

        // 处理科学记数法中指数部分缺失的情况
        if (exponentSeen && index + 1 == length) {
            return false;
        }

        return true;
    }

    private static boolean isHexadecimal(String str) {
        int index = 0;
        //如果以.开头或者0开头的数字类似00，01 为不合法字符串
        if (str.charAt(index) == '.' || (str.charAt(index) == '0' && str.length() > 1 && digits.indexOf(str.charAt(index + 1)) != -1)) {
            return false;
        }

        boolean decimalPointSeen = false;

        for (; index < str.length(); index++) {
            char c = str.charAt(index);

            if (c == '.') {
                if (decimalPointSeen) {
                    return false;
                }
                decimalPointSeen = true;
            } else if (digits.indexOf(c) == -1 && hexDigits.indexOf(c) == -1) {
                return false;
            }
        }
        return true;
    }

}
