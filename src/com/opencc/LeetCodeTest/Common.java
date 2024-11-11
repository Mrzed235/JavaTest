package com.opencc.LeetCodeTest;

import java.util.*;

public class Common {
    /**
     * 将形如[1,2,3,0,0,0]的字符串转化为int数组输出
     *
     * @param s
     * @return
     */
    public static int[] parseStrArrToIntArr(String s) {
        String[] strings = s.replace("[", "").replace("]", "").split(",");
        int[] nums = new int[strings.length];
        for (int i = 0; i < strings.length; i++) {
            nums[i] = Integer.parseInt(strings[i]);
        }
        return nums;
    }

    /**
     * 返回数组的前缀和数组，前缀和主要适用的场景是原始数组不会被修改的情况下，频繁查询某个区间的累加和。
     * prefix[i] 就代表着 nums[0..i-1] 所有元素的累加和，如果我们想求区间 nums[i..j] 的累加和，
     * 只要计算 prefix[j+1] - prefix[i] 即可，而不需要遍历整个区间求和。
     *
     * @param nums
     * @return prefix
     */
    public static int[] preFixCount(int[] nums) {
        int[] prefix = new int[nums.length + 1];
        prefix[0] = 0; //前缀和数组首位值为0
        for (int i = 1; i < prefix.length; i++) {
            prefix[i] = nums[i - 1] + prefix[i - 1];
        }
        return prefix;
    }

    /**
     * 差分数组的主要适用场景是频繁对原始数组的某个区间的元素进行增减。
     * 这样构造差分数组 diff，就可以快速进行区间增减的操作，如果你想对区间 nums[i..j] 的元素全部加 3，
     * 那么只需要让 diff[i] += 3，然后再让 diff[j+1] -= 3 即可：原理很简单，回想 diff 数组反推 nums 数组的过程，diff[i] += 3
     * 意味着给 nums[i..] 所有的元素都加了 3，然后 diff[j+1] -= 3 又意味着对于 nums[j+1..] 所有元素再减 3，那综合起来，
     * 是不是就是对 nums[i..j] 中的所有元素都加 3 了？
     * <a href="https://www.cnblogs.com/labuladong/p/13975759.html">...</a>
     *
     * @param nums
     * @return diff
     */
    public static int[] diffArr(int[] nums) {
        int[] diff = new int[nums.length];
        diff[0] = nums[0];
        for (int i = 1; i < nums.length; i++) {
            diff[i] = nums[i] - nums[i - 1];
        }
        return diff;
    }

    /**
     * 优先队列构造滑动窗口求窗口内最大值。
     *
     * @param nums
     * @param k
     * @return res
     */
    public static int[] maxSlidingWindow(int[] nums, int k) {
        // 优先级队列：先将当前元素的下标加入队列（队列按照值从大到小排序下标），在记录最大值之前需要先判断当前队列头是否还在窗口范围内
        if (nums == null || nums.length < 1 || k < 1) {
            throw new IllegalArgumentException();
        }
        int length = nums.length;
        int[] res = new int[length - k + 1];
        //优先级队列（大根堆），存储的是元素下标，按照元素大小从大到小排列
        PriorityQueue<Integer> pq = new PriorityQueue<>((o1, o2) -> nums[o2] - nums[o1]);
        for (int i = 0; i < length; i++) {
            pq.offer(i);

            // 窗口的左边界
            int left = i - k + 1;
            // 如果边界内不存在队列最大值
            while (!pq.isEmpty() && pq.peek() < left) {
                pq.poll();
            }
            // 如果窗口形成
            if (left >= 0) {
                res[left] = nums[pq.peek()];
            }

        }
        return res;
    }

    /**
     * 单调队列的：LinkedList实现双端队列
     *
     * @param nums
     * @param k
     * @return
     */
    public static int[] maxSlidingWindowDeque(int[] nums, int k) {
        // 单调队列：队列中下标所对应的值在 nums 数组中严格单调递减
        if (nums == null || nums.length < 1 || k < 1) {
            throw new IllegalArgumentException();
        }

        int length = nums.length;
        int[] res = new int[length - k + 1];
        // 单调队列存储下标值，且下标对应的数值在 nums 中严格单调递减
        Deque<Integer> queue = new LinkedList<>();
        for (int i = 0; i < length; i++) {
            int left = i - k + 1;

            // 如果当前下标对应的值大于队尾下标对应的值，则队尾出队
            while (!queue.isEmpty() && nums[i] > nums[queue.peekLast()]) {
                queue.pollLast();
            }

            queue.offerLast(i);
            // 在记录最大值之前先判断最大值是否还在窗口内
            if (queue.peekFirst() < left) {
                queue.pollFirst();
            }

            // 如果窗口形成
            if (left >= 0) {
                res[left] = nums[queue.peekFirst()];
            }
        }
        return res;
    }

    /**
     * 数组实现单调队列，queue数组用来保存构成单调递减的数组下标，利用tail--重新赋值来实现从队尾出队重新进队的功能。
     *
     * @param nums
     * @param k
     * @return
     */
    public static int[] maxSlidingWindowArr(int[] nums, int k) {
        // 单调队列：队列中下标所对应的值在 nums 数组中严格单调递减
        if (nums == null || nums.length < 1 || k < 1) {
            throw new IllegalArgumentException();
        }
        if (k == 1) return nums;
        int length = nums.length;
        int[] res = new int[length - k + 1];
        // 单调队列存储下标值，且下标对应的数值在 nums 中严格单调递减
        int[] queue = new int[length];
        // head 为队列头指针，tail 为队列尾指针
        int head = 0, tail = -1;

        for (int i = 0; i < length; i++) {
            // 窗口的左边界
            int left = i - k + 1;
            // 如果当前下标对应的值大于队尾对应的值，则队尾出队
            while (head <= tail && nums[i] > nums[queue[tail]]) {
                tail--;
            }
            queue[++tail] = i;

            // 如果队列头不在窗口内
            if (queue[head] < left) {
                head++;
            }

            // 如果窗口形成，则记录最大值
            if (left >= 0) {
                res[left] = nums[queue[head]];
            }

        }
        return res;
    }

    /**
     * 将输入类型为[[1,1],[2,3]] 转化为二维数组
     *
     * @param input
     * @return
     */
    public static int[][] parseToDoubleArray(String input) {
        String[] rows = input.substring(2, input.length() - 2).split("],\\[");
        int[][] result = new int[rows.length][];
        for (int i = 0; i < rows.length; i++) {
            String[] values = rows[i].split(",");
            int[] row = new int[values.length];
            for (int j = 0; j < values.length; j++) {
                row[j] = Integer.parseInt(values[j]);
            }
            result[i] = row;
        }
        return result;
    }

    /**
     * 将输入["eat", "tea", "tan", "ate", "nat", "bat"]转为String数组
     * @param input
     * @return String[]
     */
    public static String[] parseToStringArray(String input) {
        return input.replace("[", "").replace("]", "").replace("\"", "").split(",");
    }

    /**
     * 将输入：[["5","3",".",".","7",".",".",".","."],["6",".",".","1","9","5",".",".","."]]转为数独棋盘
     * @param s
     * @return char[][]
     */
    public static char[][] parseSuDoKu(String s) {
        String[] strs = s.substring(2, s.length() - 2).replace("\"", "").replace(",", "").split("]\\[");
        char[][] res = new char[strs.length][strs[0].length()];
        for (int i = 0; i < strs.length; i++) {
            res[i] = strs[i].toCharArray();
        }
        return res;
    }
    public static void main(String[] args) {
//        Scanner in = new Scanner(System.in);
//        int[] nums = parseStrArrToIntArr(in.nextLine()); //  [3,5,2,-2,4,1]
//        System.out.println(Arrays.toString(preFixCount(nums)));
//        //求nums[i] 到 nums[j] 之间的和
//        int[] prefix = preFixCount(nums);
//        int i = 2, j = 5;
//        System.out.println(prefix[j + 1] - prefix[i]);
        int[] res = maxSlidingWindowArr(new int[]{1, 3, -3, -1, -2, -3, 6, 7}, 3);
        System.out.println(Arrays.toString(res));

    }


}
