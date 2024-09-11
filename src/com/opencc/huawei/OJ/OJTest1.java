package com.opencc.huawei.OJ;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

/**
 * 斗地主之顺子：
 * <a href="https://blog.csdn.net/2301_76848549/article/details/141832089?csdn_share_tail=%7B%22type%22%3A%22blog%22%2C%22rType%22%3A%22article%22%2C%22rId%22%3A%22141832089%22%2C%22source%22%3A%222301_76848549%22%7D">...</a>
 * 3 4 5 6 7 8 9 10 J Q K A 2为顺子
 * 输入实例：2 9 J 2 3 4 K A 7 9 A 5 6
 * 输出： 3 4 5 6 7
 * 注意。J Q K A 2不是顺子
 */
public class OJTest1 {


    public static void main(String[] args) {
        String[] regula = "3,4,5,6,7,8,9,10,J,Q,K,A".split(",");
        Scanner in = new Scanner(System.in);
        List<String> lists = new ArrayList<>();
        String[] inputs = in.nextLine().split(" ");
        Collections.addAll(lists, inputs);
        List<String> result = findStraights(lists, regula);
        for (String s : result) {
            System.out.println(s);
        }
    }

    private static List<String> findStraights(List<String> lists, String[] regula) {
        List<String> result = new ArrayList<>();
        int left = 0; //左窗口位置
        StringBuilder sb = new StringBuilder();
        for (int right = 0; right < regula.length; right++) {
            if (lists.contains(regula[right])) {
                sb.append(regula[right]).append(" ");
            } else {
                if (right - left >= 5) {
                    result.add(sb.toString());
                }
                left = right + 1;
                sb.delete(0, sb.length());
            }
        }
        if (sb.length() >= 10) {
            result.add(sb.toString());
        }
        if (result.isEmpty()) {
            result.add("NO");
        }
        return result;
    }
}
