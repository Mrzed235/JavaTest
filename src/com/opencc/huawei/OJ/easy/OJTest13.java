package com.opencc.huawei.OJ.easy;

import java.util.Arrays;
import java.util.Scanner;

/**
 * 转盘寿司
 * <a href="https://www.nowcoder.com/discuss/622116704721686528?sourceSSR=search">...</a>
 * 寿司店周年庆，正在举办优惠活动回馈新老用户。
 * 寿司转盘上总共有 n 盘寿司， prices[i] 是第 i 盘寿司的价格。
 * 如果客户选择了第 i 盘寿司， 寿司店免费赠送客户距离第 i 盘寿司最近的下一盘寿司 j ，前提是 prices[j] < prices[i]，如果没有满足条件的 i ，则不赠送寿司。
 * 每个价格的寿司都可无限供应。
 * 输入的每一个数字代表寿司的价格，每盘寿司的价格之间使用空格分隔
 * 输入：
 * 3 15 6 14
 * 输出享受优惠后的一组数据，每个值表示客户端选择第 i 盘寿司实际得到的寿司的总价格，使用空格进行分隔
 * 输出：3 21 9 17
 */
public class OJTest13 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int[] prices = Arrays.stream(in.nextLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int[] sushi = solveSushi(prices);
        for (int i : sushi) {
            System.out.printf("%d ", i);
        }
    }

    private static int[] solveSushi(int[] prices) {
        int len = prices.length;
        int[] pricesDis = new int[len * 2];
        for (int i = 0; i < pricesDis.length; i++) {
            pricesDis[i] = prices[i % len]; //给新数组赋值，模拟转盘
        }
        for (int i = 0; i < len; i++) {
            for (int j = i + 1; j < i + len; j++) {
                if (pricesDis[j] < pricesDis[i]) {
                    prices[i] = pricesDis[j] + pricesDis[i];
                    break;
                }
            }
        }
        return prices;
    }
}
