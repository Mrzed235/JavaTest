package com.opencc.huawei.OJ.easy;

import java.util.Scanner;

/**
 * 光伏场地建设规则
 * 祖国西北部有一片大片荒地，其中零星的分布着一些湖泊，保护区，矿区；整体上常年光照良好，但是也有一些地区光照不太好。
 * 某电力公司希望在这里建设多个光伏电站，生产清洁能源。对每平方公里的土地进行了发电评估，其中不能建设的区域发电量为0kw，
 * 可以发电的区域根据光照，地形等给出了每平方公里年发电量x千瓦。我们希望能够找到其中集中的矩形区域建设电站，能够获得良好的收益。
 * 输入描述
 * 第一行输入为调研的地区长，宽，以及准备建设的电站【长宽相等，为正方形】的边长，最低要求的发电量
 * 之后每行为调研区域每平方公里的发电量
 * 例如，输入为：  2行5列的二维数组。电站的范围为2的一个正方形，最低要求的发电量为6
 * 2 5 2 6
 * 1 3 4 5 8
 * 2 3 6 7 1
 * 输出：4  有四块区域
 * 输入：
 * 2 5 1 6
 * 1 3 4 5 8
 * 2 3 6 7 1
 * 输出：3  有三块区域满足
 * 输入：
 * 2 5 1 0
 * 1 3 4 5 8
 * 2 3 6 7 1
 * 输出： 10
 */
public class OJTest3 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String[] inputs = in.nextLine().split(" ");
        int row = Integer.parseInt(inputs[0]);
        int column = Integer.parseInt(inputs[1]);
        int area = Integer.parseInt(inputs[2]);
        int minCapacity = Integer.parseInt(inputs[3]);
        int[][] powers = new int[row][column];
        for (int i = 0; i < powers.length; i++) {
            String[] ps = in.nextLine().split(" ");
            for (int j = 0; j < powers[i].length; j++) {
                powers[i][j] = Integer.parseInt(ps[j]);
            }
        }
        System.out.println(findAreaCorrect(powers, area, minCapacity));
    }

    private static int findAreaCorrect(int[][] powers, int area, int minCapacity) {
        int count = 0;
        for (int i = 0; i < powers.length; i++) {
            for (int j = 0; j < powers[i].length; j++) {
                if (judgeCorrect(powers, i, j, area, minCapacity)) {
                    count++;
                }
            }
        }
        return count;
    }

    private static boolean judgeCorrect(int[][] powers, int i, int j, int area, int minCapacity) {
        //todo 判断正方体内是否超出边界，未超出的话，计算发电量，超出返回false；
        int row = powers.length;
        int column = powers[i].length;
        int capacity = 0;
        if (i + area  > row) {
            return false;
        } else if (j + area  > column) {
            return false;
        } else {
            for (int m = 0; m < area; m++) {
                for (int n = 0; n < area; n++) {
                    capacity += powers[i + m][j + n];
                }
            }
            return capacity >= minCapacity;
        }
    }
}
