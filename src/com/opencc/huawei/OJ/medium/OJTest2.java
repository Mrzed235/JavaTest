package com.opencc.huawei.OJ.medium;

import java.util.Arrays;
import java.util.Scanner;

/**
 * 出租车计费：
 * 问题描述
 * K小姐打了一辆出租车去上班。出于职业敏感,她注意到这辆出租车的计费表有点问题,总是偏大。出租车司机解释说他不喜欢数字4,所以改装了计费表,任何数字位置遇到数字4就直接跳过,其余功能都正常。比如:
 * 1.23 再多1元就变为1.25
 * 2.39 再多1元变为2.50
 * 3.99 再多1元变为5.00
 * K小姐识破了司机的伎俩,准备利用自己的学识打败司机的阴谋。
 * 给出计费表的表面读数,返回实际产生的费用。
 * 输入格式
 * 只有一行,数字N,表示里程表的读数(1≤N≤88888888)
 * 输出格式
 * 一个数字,表示实际产生的费用。以回车结束。
 * 样例输入
 * 5
 * 样例输出
 * 4
 * 样例解释
 * 5 表示计费表的表面读数,4表示实际产生的费用其实只有4元。
 * 样例输入
 * 17
 * 样例输出
 * 15
 * 样例输入
 * 100
 * 样例输出
 * 81
 */
public class OJTest2 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int[] nums = Arrays.stream(in.nextLine().split("")).mapToInt(Integer::parseInt).toArray();
        System.out.println(calculate_price(nums));
    }

    private static int calculate_price(int[] nums) {
        int actual = 0;
        for (int num : nums) {
            actual = actual * 9 + num;
            if (num > 4) {
                actual -= 1;
            }
        }
        return actual;
    }
}
