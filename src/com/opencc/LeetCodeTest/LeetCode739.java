package com.opencc.LeetCodeTest;

import java.util.*;

/**
 * 每日温度
 * 给定一个整数数组 temperatures ，表示每天的温度，返回一个数组 answer ，其中 answer[i] 是指对于第 i 天，下一个更高温度出现在几天后。如果气温在这之后都不会升高，请在该位置用 0 来代替。
 * 示例 1:
 * 输入: temperatures = [73,74,75,71,69,72,76,73]
 * 输出: [1,1,4,2,1,1,0,0]
 * 示例 2:

 * 输入: temperatures = [30,40,50,60]
 * 输出: [1,1,1,0]
 * 示例 3:

 * 输入: temperatures = [30,60,90]
 * 输出: [1,1,0]
 */
public class LeetCode739 {
    public int[] dailyTemperatures(int[] temperatures) {
        Deque<Integer> stack =  new LinkedList<>(); //创建栈
        int len = temperatures.length;
        int[] res = new int[len];
        for (int i = 0; i < len; i++) {
            while (!stack.isEmpty() && temperatures[i] > temperatures[stack.peek()]) {
                int prevIndex = stack.pop();
                res[prevIndex] = i - prevIndex;
            }
            stack.push(i);
        }
        return res;
    }

    public static void main(String[] args) {
        LeetCode739 lt = new LeetCode739();
        Scanner in = new Scanner(System.in);
        int[] nums = Common.parseStrArrToIntArr(in.nextLine());
        System.out.println(Arrays.toString(lt.dailyTemperatures(nums)));
    }
}
