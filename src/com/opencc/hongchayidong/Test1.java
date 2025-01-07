package com.opencc.hongchayidong;


public class Test1 {
    public boolean isAdditiveSequence(String num) {
        int n = num.length();
        // i表示暴力枚举第一个数字
        for (int i = 0; i < n - 2; i++) {
            // 剪枝操作，如果num字符串的第一个字符为0的话，则表示序列中的第一个数字只能是0，如果在下面搜索过程中没有找到
            // 以0作为第一个数字的累加数的话，直接返回false即可
            if (num.charAt(0) == '0' && i > 0) {
                return false;
            }
            // j表示枚举第二个数字
            for (int j = i + 1; j < n - 1; j++) {
                // 如果第二个字符为0的话，那么第二个数字只能是0，但是这里需要注意的是跟找第一个数不同，我们不能直接
                // 返回false，因为我们改变第一个数字后，还有可能找到累加数的情况，所以直接退出当前第二层循环即可
                if (num.charAt(i + 1) == '0' && j > i + 1) {
                    break;
                }
                // 下面就是以Long类型来接收第一个数和第二个数了
                Long first = Long.parseLong(num.substring(0, i + 1));
                Long second = Long.parseLong(num.substring(i + 1, j + 1));
                if (dfs(first, second, num, j + 1)) {
                    return true;
                }
            }
        }
        return false;
    }

    boolean dfs(Long fisrtNum, Long secondNum, String num, int thirdNumIndex) {
        if (thirdNumIndex == num.length()) {
            return true;
        }
        for (int i = thirdNumIndex; i < num.length(); i++) {
            // 同样第三个数字只能为0，在第三个数字为0的情况下还没找到正确结果的情况下直接返回false即可
            if (num.charAt(thirdNumIndex) == '0' && i > thirdNumIndex) {
                return false;
            }
            Long thirdNum = Long.parseLong(num.substring(thirdNumIndex, i + 1));
            // 这里就是把第二个数再看作第一个数，第三个数看作第二个数，继续往后寻找第三个数
            if (thirdNum > fisrtNum + secondNum) {
                return false;
            } else if (thirdNum == fisrtNum + secondNum) {
                if (dfs(secondNum, thirdNum, num, i + 1)) {
                    return true;
                }
            }
        }
        return false;
    }

    public static void main(String[] args) {
        Test1 t1 = new Test1();
        System.out.println(t1.isAdditiveSequence("12358")); // true
        System.out.println(t1.isAdditiveSequence("19101929")); // true
        System.out.println(t1.isAdditiveSequence("191011")); // false
    }
}
