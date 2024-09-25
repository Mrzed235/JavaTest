package com.opencc.huawei.OJ.easy;

import java.util.*;
import java.util.Scanner;

/**
 * 流浪地球
 * 题目描述
 * 流浪地球计划在赤道上均匀部署了N个转向发动机，按位置顺序编号为
 * 0~N。
 * 1) 初始状态下所有的发动机都是未启动状态;
 * 2) 发动机启动的方式分为”手动启动"和”关联启动"两种方式;
 * 3) 如果在时刻1一个发动机被启动，下一个时刻2与之相邻的两个发动机就会被”关联启动”;
 * 4) 如果准备启动某个发动机时，它已经被启动了，则什么都不用做;
 * 5)发动机0与发动机N-1是相邻的;
 * 地球联合政府准备挑选某些发动机在某些时刻进行“手动启动”。当然最终所有的发动机都会被启动。
 * 哪些发动机最晚被启动呢?
 * 输入描述：
 * 第一行两个数字N和E，中间有空格
 * N代表部署发动机的总个数，E代表计划手动启动的发动机总个数
 * 1<N<=1000,1<=E<=1000,E<=N
 * 接下来共E行，每行都是两个数字T和P，中间有空格
 * T代表发动机的手动启动时刻，P代表此发动机的位置编号。
 * 0<=T<=N.0<=P<N
 * 输出描述：
 * 第一行一个数字N，以回车结束
 * N代表最后被启动的发动机个数
 * 第二行N个数字，中间有空格，以回车结束
 * 每个数字代表发动机的位置编号，从小到大排序
 * <p>
 * 示例1
 输入：
 8 2
 0 2
 0 6
 输出：
 2
 0 4
 * 说明： 8个发动机;
 * 时刻0启动(2,6);
 * 时刻1启动(1,3.5,7)(其中1,3被2关联启动，5，7被6关联启动);
 * 时刻2启动(0,4)(其中0被1,7关联启动，4被3,5关联启动);
 * 至此所有发动机都被启动，最后被启动的有2个，分别是0和4。
 输入：
 8 2
 0 0
 1 7
 输出：
 1
 4
 */
public class OJTest4 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // 读取发动机总数 N 和手动启动发动机数 E
        int N = scanner.nextInt();
        int E = scanner.nextInt();

        // 用于存储手动启动信息，每个元素包含启动时刻和发动机位置
        List<int[]> manualStarts = new ArrayList<>();
        for (int i = 0; i < E; i++) {
            int time = scanner.nextInt();
            int position = scanner.nextInt();
            manualStarts.add(new int[]{time, position});
        }

        //初始化记录时刻的数组：
        int[] start_times = new int[N];
        Arrays.fill(start_times, Integer.MAX_VALUE);

        //处理手动启动的输入：
        for (int[] start : manualStarts) {
            start_times[start[1]] = start[0];
        }

        //模拟关联启动：
        for (int t = 0; t < N; t++) {
            for (int i = 0; i < N; i++) {
                if (start_times[i] == t) {
                    // 更新相邻发动机的时间
                    start_times[(i + N - 1) % N] = Math.min(start_times[(i + N - 1) % N], t + 1);
                    start_times[(i + N + 1) % N] = Math.min(start_times[(i + N + 1) % N], t + 1);
                }
            }
        }

        // 找出最后启动的时间
        int lastStart = Arrays.stream(start_times).max().getAsInt();
        int count = 0;
        // 统计并输出最后启动的发动机
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < N; i++) {
            if (start_times[i] == lastStart) {
                if (sb.length() != 0) {
                    sb.append(' ');
                }
                sb.append(i);
                count++;
            }
        }


        System.out.println(count);
        System.out.println(sb);
    }
}