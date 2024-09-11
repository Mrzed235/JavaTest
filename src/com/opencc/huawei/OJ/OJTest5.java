package com.opencc.huawei.OJ;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

/**
 * 贪心的商人：
 * <a href="https://www.nowcoder.com/discuss/660868608024670208?sourceSSR=search">...</a>
 * 题目描述
 * 商人经营一家店铺，有number种商品，由于仓库限制每件商品的最大持有数量是item[index]，
 * 每种商品的价格是item-price[item_index][day]，通过对商品的买进和卖出获取利润，请给出商人在days天内能获取的最大的利润。
 * 注：同一件商品可以反复买进和卖出
 * 输入描述
 * <p>
 * 3 第一行输入商品的数量number
 * 3 第二行输入商品售货天数 days
 * 4 5 6 第三行输入仓库限制每件商品的最大持有数量是item[index]
 * 1 2 3 第一件商品每天的价格
 * 4 3 2 第二件商品每天的价格
 * 1 5 3 第三件商品每天的价格
 * 输入：
 * 3
 * 3
 * 4 5 6
 * 1 2 3
 * 4 3 2
 * 1 5 2
 * 输出
 * 32
 */
public class OJTest5 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int number = Integer.parseInt(in.nextLine()); //商品的数量
        int days = Integer.parseInt(in.nextLine()); //售货天数
        int[] item = new int[number]; //每件商品的最大最大持有数量
        String[] inputAmount = in.nextLine().split(" ");
        for (int i = 0; i < number; i++) {
            item[i] = Integer.parseInt(inputAmount[i]);
        }
        int[][] item_price = new int[number][days];
        for (int[] ints : item_price) {
            String[] dayPrice = in.nextLine().split(" ");
            for (int i = 0; i < ints.length; i++) {
                ints[i] = Integer.parseInt(dayPrice[i]);
            }
        }

        System.out.println(maxProfit(number, days, item, item_price));
    }

    public static int maxProfit(int number, int days, int[] item, int[][] itemPrice) {
        int total_profit = 0; //初始化总利润
        for (int i = 0; i < number; i++) {
            //遍历该商品的每一天除了最后一天
            for (int j = 0; j < days - 1; j++) {
                //如果明天的价格高于今天就在今天买入 明天卖出
                if (itemPrice[i][j] < itemPrice[i][j + 1]) {
                    int profit = (itemPrice[i][j + 1] - itemPrice[i][j]) * item[i];
                    total_profit += profit;
                }
            }
        }
        return total_profit;
    }
}
