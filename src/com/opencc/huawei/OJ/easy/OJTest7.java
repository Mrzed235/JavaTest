package com.opencc.huawei.OJ.easy;

import java.util.Arrays;
import java.util.Scanner;

/**
 * 日志采集系统
 * 题目描述
 * 日志采集是运维系统的的核心组件。日志是按行生成，每行记做一条，由采集系统分批上报。如果上报太频繁，会对服务端造成压力；如果上报太晚，会降低用户的体验；如果一次上报的条数太多，会导致超时失败。为此，项目组设计了如下的上报策略：
 * 每成功上报一条日志，奖励1分
 * 每条日志每延迟上报1秒，扣1分
 * 积累日志达到100条，必须立即上报
 * 给出日志序列，根据该规则，计算首次上报能获得的最多积分数。
 * 输入描述
 * 按时序产生的日志条数 T1,T2...Tn, 其中 1≤n≤1000,0≤Ti≤100
 * 输出描述
 * 首次上报最多能获得的积分数
 * 示例1
 * 输入
 * 1 98 1
 * 输出
 * 98
 * 说明：采集系统第2个时刻上报，可获得最大积分(98+1)-1=98
 * 示例2
 * 输入
 * 50 60 1
 * 输出
 * 50
 * 说明
 * 如果第1个时刻上报，获得积分50。
 * 如果第2个时刻上报，最多上报100条，前50条延迟上报1s，每条扣除1分，共获得积分为 100-50=50。
 * 输入：3 7 40 10 60
 * 输出：37
 */
public class OJTest7 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String[] inputs = in.nextLine().split(" ");
        int[] arrs = new int[inputs.length];
        for (int i = 0; i < arrs.length; i++) {
            arrs[i] = Integer.parseInt(inputs[i]);
        }
        System.out.println(countScores(arrs));
    }

    private static int countScores(int[] arrs) {
        //维护三个数组
        int[] positive_scores = new int[arrs.length];
        positive_scores[0] = arrs[0];
        int[] penalty_scores = new int[arrs.length];
        int[] final_scores = new int[arrs.length];
        for (int i = 1; i < positive_scores.length; i++) {
            positive_scores[i] = positive_scores[i - 1] + arrs[i];
            if (positive_scores[i] >= 100) {
                positive_scores[i] = 100;
            }
        }

        for (int i = penalty_scores.length - 1; i >= 0; i--) {
            for (int j = 0; j < i; j++) {
                penalty_scores[i] += (i - j) * arrs[j];
            }
        }
        for (int i = 0; i < final_scores.length; i++) {
            final_scores[i] = positive_scores[i] - penalty_scores[i];
        }
        return Arrays.stream(final_scores).max().getAsInt();
    }
}
