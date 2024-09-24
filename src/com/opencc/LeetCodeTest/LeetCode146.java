package com.opencc.LeetCodeTest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

/**
 * 螺旋遍历二维数组
 * <a href="https://leetcode.cn/problems/shun-shi-zhen-da-yin-ju-zhen-lcof/description/">...</a>
 * 螺旋遍历：从左上角开始，按照 向右、向下、向左、向上 的顺序 依次 提取元素，然后再进入内部一层重复相同的步骤，直到提取完所有元素。
 * 示例 1：
 * 输入：array = [[1,2,3],[8,9,4],[7,6,5]]
 * 输出：[1,2,3,4,5,6,7,8,9]
 * 示例 2：
 * 输入：array  = [[1,2,3,4],[12,13,14,5],[11,16,15,6],[10,9,8,7]]
 * 输出：[1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16]
 */
public class LeetCode146 {
    public static void main(String[] args) {
        int[][] array = {{1, 2, 3}, {8, 9, 4}, {7, 6, 5}};
        int[] result = spiralArray(array);
        System.out.println(Arrays.toString(result));
    }

    public static int[] spiralArray(int[][] array) {
        if (array.length == 0) {
            return new int[0];
        }
        int l = 0, r = array[0].length - 1, t = 0, b = array.length - 1, x = 0;//定义四个边界
        int[] res = new int[(r + 1) * (b + 1)];
        while (true) {
            for (int i = l; i <= r; i++) {
                res[x++] = array[t][i]; //从左到右
            }
            if (++t > b) break;    //收缩上边界，所以要++t
            for (int i = t; i <= b; i++) {
                res[x++] = array[i][r]; //从上到下，最右侧边界
            }
            if (l > --r) break; //收缩右边界，所以要--r
            for (int i = r; i >= l; i--) {
                res[x++] = array[b][i];//从右往左
            }
            if (t > --b) break; //收缩下边界，所以要--b
            for (int i = b; i >= t; i--) {
                res[x++] = array[i][l]; //从下往上，最左侧边界
            }
            if (++l > r) break; //收缩左边界，所以要++l
        }
        return res;
    }
}
