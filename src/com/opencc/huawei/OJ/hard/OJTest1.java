package com.opencc.huawei.OJ.hard;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * <a href="https://www.nowcoder.com/discuss/659459464759672832?sourceSSR=search">...</a>
 * 空栈压数
 * 问题描述
 * 输入格式
 * 一行字符串，包含使用单个空格隔开的正整数，如 "5 6 7 8"，左边的数字先入栈。
 * 输出格式
 * 最终栈中存留的元素值，元素值使用单个空格隔开，如 "8 7 6 5"，从左至右依次为栈顶至栈底的数字。
 * 样例输入
 * 10 20 50 80 1 1
 * 样例输出
 * 2 160
 * 样例解释
 * 解释：向栈压入 80 时，10+20+50=80，数据合并后入栈 160，压入两个 1 时，合并为 2，最终栈顶至栈底的数字为 2 和 160。
 * 样例输入
 * 5 10 20 50 85 1
 * 样例输出
 * 1 170
 */
public class OJTest1 {
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        String[] inputs = bf.readLine().split(" ");
        int[] nums = new int[inputs.length];
        for (int i = 0; i < nums.length; i++) {
            nums[i] = Integer.parseInt(inputs[i]);
        }
        StackArray stack = new StackArray(nums.length);
        List<Integer> list = stackCaculate(stack, nums);
        System.out.println(list);
    }

    private static List<Integer> stackCaculate(StackArray stack, int[] nums) {
        int optionNums = 1;
        int count = nums[0];
        stack.push(nums[0]);
        for (int i = 1; i < nums.length; i++) {
            //入栈前先判断是否和栈顶的数字相同，相同的话，先出栈*2再加入
            if (stack.peek() == nums[i]) {
                stack.pop();
                stack.push(2 * nums[i]);
                count+=nums[i];
                continue;
            } else if (count == nums[i]) { //判断当前栈内的数字和是否和nums[i]相等，相等的话 全部出栈然后nums[i]*2 push进去
                for (int op = 0; op < optionNums; op++) {
                    stack.pop();
                }
                stack.push(2 * nums[i]);
                optionNums = 1;
                count+=nums[i];
                continue;

            }
            count += nums[i];
            stack.push(nums[i]);
            optionNums++;
        }
        List<Integer> list = new ArrayList<>();
        while (!stack.isEmpty()) {
            list.add(stack.pop());
        }
        return list;
    }
}

class StackArray {

    private int[] stack;
    private int top;

    // 构造函数，初始化栈的大小
    public StackArray(int size) {
        stack = new int[size];
        top = -1;
    }

    // 入栈操作
    public void push(int value) {
        if (top < stack.length - 1) {
            top++;
            stack[top] = value;
        } else {
            System.out.println("Stack is full.");
        }
    }

    // 出栈操作
    public int pop() {
        if (top >= 0) {
            int value = stack[top];
            stack[top] = 0;
            top--;
            return value;
        } else {
            System.out.println("Stack is empty.");
            return -1;
        }
    }

    // 获取栈顶元素
    public int peek() {
        if (top >= 0) {
            return stack[top];
        } else {
            System.out.println("Stack is empty.");
            return -1;
        }
    }

    // 判断栈是否为空
    public boolean isEmpty() {
        return top == -1;
    }

    // 判断栈是否已满
    public boolean isFull() {
        return top == stack.length - 1;
    }
}
