package com.opencc.huawei.OJ.hard;

import java.util.Scanner;

/**
 * 运输时间
 * <a href="https://www.nowcoder.com/issue/tutorial?zhuanlanId=MgbyJj&uuid=92d567084bff423e878e69d374753211">...</a>
 * 问题描述
 * A先生拥有一支由M辆车组成的车队,他需要将这些车开到N公里外的目的地。由于道路只有一条单行线,车辆无法超车。每辆车的出发时间相隔1小时,即第一辆车在T1时刻出发,
 * 第二辆车在T2时刻出发,以此类推。
 * 不同车辆的行驶速度不同。如果一辆较快的车追上了前方的较慢车辆,它只能以前车的速度继续行驶。A先生想知道,最后一辆车到达目的地需要多长时间?
 * 输入格式
 * 第一行包含两个正整数M和N,分别表示车辆数量和目的地距离(单位:公里),用空格分隔。
 * 接下来M行,每行包含一个正整数 ,表示该车辆的行驶速度(单位:公里/小时)。
 * 输出格式
 * 输出一个实数,表示最后一辆车到达目的地所需的时间(单位:小时),保留一位小数。
 * 样例输入 1
 * 2 11
 * 3
 * 2
 * 样例输出 1
 * 5.5
 */
public class OJTest6 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int M = in.nextInt();
        int N = in.nextInt();
        int[] carSpeed = new int[M];
        for (int i = 0; i < carSpeed.length; i++) {
            carSpeed[i] = in.nextInt();
        }
        System.out.println(translateTime(carSpeed, N));
    }

    private static double translateTime(int[] carSpeed, double n) {
        double[] times = new double[carSpeed.length];
        for (int i = 0; i < times.length; i++) {
            times[i] = i + n / carSpeed[i];
        }
        for (int i = 1; i < times.length; i++) {
            if (times[i] < times[i - 1]) {
                times[i] = times[i - 1];
            }
        }
        return times[times.length - 1] - (times.length - 1);
    }
}
