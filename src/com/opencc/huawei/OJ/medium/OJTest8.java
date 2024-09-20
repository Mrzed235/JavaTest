package com.opencc.huawei.OJ.medium;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * 数大雁
 * <a href="https://www.nowcoder.com/issue/tutorial?zhuanlanId=MgbyJj&uuid=ce3c0c6addf24a7598ff3e208839c49d">...</a>
 * 输入：quackquack
 * 输出：1
 * 输入：qaauucqcaa
 * 输出：-1
 * 输入：qququaauqccauqkkcauqqkcauuqkcaaukccakkck
 * 输出：5
 * 输入：quacqkuquacqkacuqkackuack
 * 输出：3
 */
public class OJTest8 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String s = in.nextLine();
        //定义数组记录
        System.out.println(countDucks(s));
//        System.out.println(countDucks2(s));
    }

    public static int countDucks(String s) {
        String t = "quack";  // 标准叫声
        int m = t.length();  // 标准叫声的长度
        List<Integer> v = new ArrayList<>();  // 用于存储每只大雁的当前状态

        for (char ch : s.toCharArray()) {
            int idx = t.indexOf(ch);  // 查找当前字符在标准叫声中的位置
            if (idx == -1) continue;  // 如果不在标准叫声中，跳过

            boolean flg = true;  // 标记是否需要新增大雁
            for (int i = 0; i < v.size(); i++) {
                if (v.get(i) % m == idx) {
                    v.set(i, v.get(i) + 1);  // 更新大雁状态
                    flg = false;
                    break;
                }
            }

            if (flg && idx == 0) {
                v.add(1);  // 新增大雁，状态设为1
             }
        }

        // 统计完成至少一次完整叫声的大雁数量
        int cnt = (int) v.stream().filter(i -> i >= m).count();

        return cnt > 0 ? cnt : -1;
    }

}
