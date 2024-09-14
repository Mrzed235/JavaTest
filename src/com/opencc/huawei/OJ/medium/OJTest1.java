package com.opencc.huawei.OJ.medium;

import java.util.Arrays;
import java.util.Scanner;

/**
 * 爱吃蟠桃的孙悟空
 * <a href="https://www.nowcoder.com/discuss/663688287038554112?sourceSSR=search">...</a>
 * 题目描述
 * 孙悟空爱吃蟠桃，有一天趁着蟠桃园守卫不在来偷吃。已知蟠桃园有 N 棵桃树，每颗树上都有桃子，守卫将在 H 小时后回来。
 * 孙悟空可以决定他吃蟠桃的速度K（个/小时），每个小时选一颗桃树，并从树上吃掉 K 个，如果树上的桃子少于 K 个，则全部吃掉，并且这一小时剩余的时间里不再吃桃。
 * 孙悟空喜欢慢慢吃，但又想在守卫回来前吃完桃子。
 * 请返回孙悟空可以在 H 小时内吃掉所有桃子的最小速度 K（K为整数）。如果以任何速度都吃不完所有桃子，则返回0。
 * 输入描述
 * 第一行输入为 N 个数字，N 表示桃树的数量，这 N 个数字表示每颗桃树上蟠桃的数量。
 * 第二行输入为一个数字，表示守卫离开的时间 H。
 * 其中数字通过空格分割，N、H为正整数，每颗树上都有蟠桃，且 0 < N < 10000，0 < H < 10000。
 * 输出描述
 * 吃掉所有蟠桃的最小速度 K，无解或输入异常时输出 0。
 * 输入样例1：
 * 2 3 4 5 4
 * 5
 * 输出：5
 * 输入样例2：
 * 2 3 4 5
 * 3
 * 输出：0
 * 输入样例3：
 * 30 11 23 4 20
 * 6
 * 输出：23
 */
public class OJTest1 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int[] peaches = Arrays.stream(in.nextLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int hours = in.nextInt();
        System.out.println(min_eating_speed(peaches, hours));
    }

    private static int min_eating_speed(int[] peaches, int hours) {
        //计算吃完所有桃子的最小速度
        if (peaches.length > hours) {
            return 0;
        }
        int left = 1, right = Arrays.stream(peaches).max().getAsInt();
        while (left < right) {
            int mid = (left + right) / 2;
            if (can_eat_all(mid, peaches, hours)) {
                //如果在mid时间能吃完的话，说明还能把吃桃的速度放慢，即把right左移置为mid
                right = mid;
            } else left = mid + 1; //吃不完的话，说明要把吃桃的速度放快点。即把left右移置为mid
        }
        return left;
    }

    private static boolean can_eat_all(int mid, int[] peaches, int hours) {
        //检查是否能在给定的时间用mid的速度吃完桃子
        int time = 0;
        for (int i = 0; i < peaches.length; i++) {
            if (peaches[i] % mid > 0) {
                time++;
            }
            time += peaches[i] / mid;
        }
        return time <= hours;
    }
}
