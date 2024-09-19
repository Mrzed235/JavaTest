package com.opencc.huawei.OJ.medium;

import java.util.*;

/**
 * 猜字谜
 * <a href="https://www.nowcoder.com/discuss/663334910345699328?sourceSSR=search">...</a>
 * 示例1
 * 输入
 * conection
 * connection,today
 * 输出
 * connection
 * 示例2
 * 输入
 * bdni,wooood
 * bind,wrong,wood
 * 输出
 * bind,wood
 */
public class OJTest6 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String[] guessWords = in.nextLine().split(",");
        String[] answerWords = in.nextLine().split(",");
        List<String> guesses = distinctAndSort(guessWords);
        List<String> answers = distinctAndSort(answerWords);
        List<String> result = new ArrayList<>();
        for (String guess : guesses) {
            for (int i = 0; i < answers.size(); i++) {
                if (guess.equals(answers.get(i))) {
                    result.add(answerWords[i]);
                }
            }
        }
        if (result.isEmpty()) {
            System.out.println("not found");
        } else {
            System.out.println(String.join(",", result));
        }
    }

    public static List<String> distinctAndSort(String[] strs) {
        List<String> list = new ArrayList<>();
        for (String str : strs) {
            //转化为char数组
            char[] chars = str.toCharArray();
            //创建一个HashSet来去重并且重排序
            HashSet<Character> set = new HashSet<>();
            for (char c : chars) {
                set.add(c);
            }
            Object[] array = set.toArray();
            chars = new char[array.length];
            for (int i = 0; i < array.length; i++) {
                chars[i] = ((Character) array[i]);
            }

            // 将字符数组转换为字符串
            StringBuilder sb = new StringBuilder();
            for (char c : chars) {
                sb.append(c);
            }
            list.add(sb.toString());
        }
        return list;
    }
}
