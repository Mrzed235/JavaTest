package com.opencc.LeetCodeTest;

import java.util.ArrayList;
import java.util.List;

/**
 * Z 字形变换
 * 将一个给定字符串 s 根据给定的行数 numRows ，以从上往下、从左到右进行 Z 字形排列。
 * 比如输入字符串为 "PAYPALISHIRING" 行数为 3 时，排列如下：
 * P   A   H   N
 * A P L S I I G
 * Y   I   R
 * 之后，你的输出需要从左往右逐行读取，产生出一个新的字符串，比如："PAHNAPLSIIGYIR"。
 * <p>
 * 示例 1：
 * 输入：s = "PAYPALISHIRING", numRows = 3
 * 输出："PAHNAPLSIIGYIR"
 * 示例 2：
 * 输入：s = "PAYPALISHIRING", numRows = 4
 * 输出："PINALSIGYAHRPI"
 * 解释：
 * P     I    N
 * A   L S  I G
 * Y A   H R
 * P     I
 * 示例 3：
 * 输入：s = "A", numRows = 1
 * 输出："A"
 */
public class LeetCode6 {
    /**
     * * 按顺序遍历字符串 s ：
     * * res[i] += c： 把每个字符 c 填入对应行 s
     * * i += flag： 更新当前字符 c 对应的行索引；
     * * flag = - flag： 在达到 Z 字形转折点时，执行反向。
     * * 链接：<a href="https://leetcode.cn/problems/zigzag-conversion/solutions/">...</a>
     *
     * @param s
     * @param numRows
     * @return
     */
    public String convert(String s, int numRows) {
        if (numRows < 2) {
            return s;
        }
        List<StringBuilder> rows = new ArrayList<>();
        for (int i = 0; i < numRows; i++) {
            rows.add(new StringBuilder());
        }

        int i = 0;
        int flag = -1;
        for (char c : s.toCharArray()) {
            rows.get(i).append(c);
            if (i == 0 || i == numRows - 1) {
                flag = -flag;
            }
            i += flag;
        }
        StringBuilder res = new StringBuilder();
        for (StringBuilder row : rows) {
            res.append(row);
        }
        return res.toString();
    }

    /**
     * 二维矩阵解法
     * Z 字形变换的周期 t=r+r−2=2r−2,每个周期会占用矩阵上的 1+r−2=r−1 列.因此我们有(n + t - 1)/t个周期，乘上每个周期的列数，
     * 得到矩阵的列数c=(n + t - 1) / t * (r - 1);
     * 创建一个 r 行 c 列的矩阵，然后遍历字符串 s 并按 Z 字形填写，若当前字符下标 i 满足 i%t<r−1，则向下移动，否则向右上移动。
     * 链接：<a href="https://leetcode.cn/problems/zigzag-conversion/solutions/1298127/z-zi-xing-bian-huan-by-leetcode-solution-4n3u/">...</a>
     *
     * @param s
     * @param numRows
     * @return
     */
    public String convert2(String s, int numRows) {
        int n = s.length(), r = numRows;
        if (r == 1 || r >= n) {
            return s;
        }
        int t = r * 2 - 2; //一个周期用掉的字母个数
        int c = (n + t - 1) / t * (r - 1);//(n + t - 1)防止字母剩余的尾巴不计入，所以加上t-1再除。r-1是每个周期占用矩阵上列数
        char[][] mat = new char[r][c]; //构造一个r行c列的二维数组。
        for (int i = 0, x = 0, y = 0; i < n; i++) {
            mat[x][y] = s.charAt(i);
            if (i % t < r - 1) {
                ++x;
            } else {
                --x;
                ++y;
            }
        }
        StringBuffer ans = new StringBuffer();
        for (char[] row : mat) {
            for (char ch : row) {
                if (ch != 0) {
                    ans.append(ch);
                }
            }
        }
        return ans.toString();
    }

    public String convert3(String s, int numRows) {
        int n = s.length(), r = numRows;
        if (r == 1 || r >= n) {
            return s;
        }
        int t = r * 2 - 2; //一个周期用掉的字母个数
        StringBuilder[] sb = new StringBuilder[r];
        for (int i = 0; i < sb.length; i++) {
            sb[i] = new StringBuilder();
        }
        for (int i = 0, x = 0; i < n; i++) {
            sb[x].append(s.charAt(i));
            if (i % t < r - 1) {
                ++x;
            }else {
                --x;
            }
        }
        StringBuilder builder = new StringBuilder();
        for (StringBuilder stringBuilder : sb) {
            builder.append(stringBuilder);
        }
        return builder.toString();
    }

    public static void main(String[] args) {
        LeetCode6 lt = new LeetCode6();
        System.out.println(lt.convert("PAYPALISHIRING", 3));
        System.out.println(lt.convert2("PAYPALISHIRING", 3));
        System.out.println(lt.convert3("PAYPALISHIRING", 3));
        System.out.println(lt.convert("PAYPALISHIRING", 4));
        System.out.println(lt.convert2("PAYPALISHIRING", 4));
        System.out.println(lt.convert3("PAYPALISHIRING", 4));
    }
}
