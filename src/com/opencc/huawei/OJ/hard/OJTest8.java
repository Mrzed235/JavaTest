package com.opencc.huawei.OJ.hard;

import java.util.*;

/**
 * 环形字符串最长子串
 * <a href="https://www.nowcoder.com/issue/tutorial?zhuanlanId=MgbyJj&uuid=7574dbf048894fd2a5ad3f3b7558d347">...</a>
 */
public class OJTest8 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String s = in.nextLine();
//        System.out.println(longestSubstring(s)); //错解 待修改
        System.out.println(longestSubstring2(s));
//        System.out.println(longestSubstring3(s)); //错解 待修改
    }

    private static int longestSubstring2(String s) {
        int n = s.length();
        String t = s + s; // 将字符串复制一遍,形成环形字符串
        int ans = 0;
        Deque<Integer>[] q = new ArrayDeque[8]; // 初始化 8 个双端队列,用于存储不同状态码下的子串起始位置,因为状态码有8种
        for (int i = 0; i < 8; i++) {
            q[i] = new ArrayDeque<>();
        }
        int x = 0; // 初始状态码为 0
        q[0].offerLast(-1); // 将 -1 加入到初始队列中,表示子串的起始位置可以从 0 开始

        for (int i = 0; i < 2 * n; i++) {
            if (t.charAt(i) == 'l') {
                x ^= 1; // 更新状态码中 'I' 的位
            } else if (t.charAt(i) == 'o') {
                x ^= 2; // 更新状态码中 'o' 的位
            } else if (t.charAt(i) == 'x') {
                x ^= 4; // 更新状态码中 'x' 的位
            }

            q[x].offerLast(i); // 将当前位置加入到对应的队列中

            // 从队列头部删除长度超过 n 的子串的起始位置
            while (q[x].peekLast() - q[x].peekFirst() > n) {
                q[x].pollFirst();
            }

            ans = Math.max(ans, q[x].peekLast() - q[x].peekFirst()); // 更新最长子串的长度
        }

        return ans;
    }

//    private static int longestSubstring(String s) {
//        Map<Character, List<Integer>> maps = new HashMap<>();
//        maps.put('l', new ArrayList<>());
//        maps.put('o', new ArrayList<>());
//        maps.put('x', new ArrayList<>());
//        String ss = s + s;//赋值数组模拟环形字符串
//        int Left = 0, Right = 0;
//        int maxLen = 0;
//        while (Right - Left + 1 <= s.length() && Right < ss.length()) {
//            char ch = ss.charAt(Right);
//            if (ch == 'l' || ch == 'o' || ch == 'x') {
//                List<Integer> list = maps.get(ch);
//                Collections.sort(list);
//                if (list.size() < 2) {
//                    list.add(Right);
//                } else {
//                    Left = list.get(0) + 1;
//                    list.remove(0);
//                    mapsCheck(Left, maps, ch);
//                    list.add(Right);
//                }
//            }
//            if (maps.get('l').size() % 2 == 0 && maps.get('o').size() % 2 == 0 && maps.get('x').size() % 2 == 0) {
//                maxLen = Math.max(maxLen, Right - Left + 1);
//                System.out.println(ss.substring(Left, Right + 1) + "   len = "+ (Right - Left + 1));
//            }
//            Right++;
//        }
//        return maxLen;
//    }

//    private static void mapsCheck(int left, Map<Character, List<Integer>> maps, char ch) {
//        for (Character c : maps.keySet()) {
//            if (c == ch) {
//                continue;
//            }
//            List<Integer> list = maps.get(c);
//            for (int i = 0; i < list.size(); ) {
//                if (list.get(i) < left) {
//                    list.remove(i);
//                } else i++;
//            }
//        }
//    }

//    public static int longestSubstring3(String s) {
//        int n = s.length();
//        String t = s + s;
//        int ans = 0;
//        Map<String, Integer> stateMap = new HashMap<>(); // 使用哈希表存储状态和对应的起始位置
//        stateMap.put("000", -1); // 初始状态为 "000"，表示 'l'、'o'、'x' 都出现 0 次
//
//        for (int i = 0; i < 2 * n; i++) {
//            char c = t.charAt(i);
//            String state = updateState(stateMap, c); // 更新状态
//
//            if (stateMap.containsKey(state)) {
//                ans = Math.max(ans, i - stateMap.get(state)); // 更新最长子串长度
//            } else {
//                stateMap.put(state, i); // 记录新的状态和起始位置
//            }
//        }
//
//        return ans;
//    }

//    public static String updateState(Map<String, Integer> stateMap, char c) {
//        String state = stateMap.keySet().iterator().next();
//        StringBuilder sb = new StringBuilder(state);
//
//        if (c == 'l') {
//            sb.setCharAt(0, sb.charAt(0) == '0'? '1' : '0');
//        } else if (c == 'o') {
//            sb.setCharAt(1, sb.charAt(1) == '0'? '1' : '0');
//        } else if (c == 'x') {
//            sb.setCharAt(2, sb.charAt(2) == '0'? '1' : '0');
//        }
//
//        return sb.toString();
//    }
}
