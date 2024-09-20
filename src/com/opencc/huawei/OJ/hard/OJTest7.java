package com.opencc.huawei.OJ.hard;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * 伐木工
 * <a href="https://www.nowcoder.com/issue/tutorial?zhuanlanId=MgbyJj&uuid=86b51b0101c942c39fef334b48a2d007">...</a>
 * 题目描述
 * K小姐是一位伐木工人，她有一根长度为X米的原木。为了获得最大的收益，她需要将原木切割成若干段，每一段的长度都必须是正整数。
 * 木材交易价格为每根木头长度的乘积，也可以不切割，拿整根原木进行交易，其交易价格等于原木长度。
 * 现在，请你帮助K小姐计算，要想获得最大收益，并且切割次数尽量少，她应该如何切割这根原木？
 * 输入格式
 * 输入仅一行，包含一个正整数X,表示原木的长度。
 * 输出格式
 * 输出若干个正整数，表示切割后每一段木材的长度。你需要按照长度从小到大的顺序输出，相邻两个数之间用一个空格隔开。
 * 如果有多种切割方案都能获得最大收益，你可以输出任意一种。
 * 样例输入
 * 10
 * 样例输出
 * 3 3 4
 * 数据范围
 */
public class OJTest7 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int X = in.nextInt();
        List<Integer> list = lumberWood(X);
        for (int i = 0; i < list.size(); i++) {
            System.out.printf("%d ",list.get(i));
        }
    }

    private static List<Integer> lumberWood(int x) {
        List<Integer> list = new ArrayList<>();
        if (x <= 4) {
            list.add(x);
            return list;
        }
        int a = x / 3, b = x % 3;
        if (b == 0) {
            for (int i = 0; i < a; i++) {
                list.add(3);
            }
            return list;
        }
        if (b == 1) {
            for (int i = 0; i < a - 1; i++) {
                list.add(3);
            }
            list.add(4);
            return list;
        }
        list.add(2);
        for (int i = 0; i < a; i++) {
            list.add(3);
        }
        return list;
    }
}