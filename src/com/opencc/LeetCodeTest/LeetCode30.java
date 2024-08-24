package com.opencc.LeetCodeTest;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * <a href="https://leetcode.cn/problems/substring-with-concatenation-of-all-words/?envType=study-plan-v2&envId=top-interview-150">...</a>
 * 给定一个字符串 s 和一个字符串数组 words。 words 中所有字符串 长度相同。
 * s 中的 串联子串 是指一个包含  words 中所有字符串以任意顺序排列连接起来的子串。
 * 例如，如果 words = ["ab","cd","ef"]， 那么 "abcdef"， "abefcd"，"cdabef"， "cdefab"，"efabcd"， 和 "efcdab" 都是串联子串。
 * "acdbef" 不是串联子串，因为他不是任何 words 排列的连接。返回所有串联子串在 s 中的开始索引。你可以以 任意顺序 返回答案。
 * <p>
 * 示例 1：
 * 输入：s = "barfoothefoobarman", words = ["foo","bar"]
 * 输出：[0,9]
 * 解释：因为 words.length == 2 同时 words[i].length == 3，连接的子字符串的长度必须为 6。
 * 子串 "barfoo" 开始位置是 0。它是 words 中以 ["bar","foo"] 顺序排列的连接。
 * 子串 "foobar" 开始位置是 9。它是 words 中以 ["foo","bar"] 顺序排列的连接。
 * 输出顺序无关紧要。返回 [9,0] 也是可以的。
 * 示例 3：
 * 输入：s = "barfoofoobarthefoobarman", words = ["bar","foo","the"]
 * 输出：[6,9,12]
 * 解释：因为 words.length == 3 并且 words[i].length == 3，所以串联子串的长度必须为 9。
 * 子串 "foobarthe" 开始位置是 6。它是 words 中以 ["foo","bar","the"] 顺序排列的连接。
 * 子串 "barthefoo" 开始位置是 9。它是 words 中以 ["bar","the","foo"] 顺序排列的连接。
 * 子串 "thefoobar" 开始位置是 12。它是 words 中以 ["the","foo","bar"] 顺序排列的连接。
 */
public class LeetCode30 {
    public static void main(String[] args) {
        String[] words = {"foo", "bar", "the"};
        String[] words3 = {"foo", "bar"};
        String[] words2 = {"word", "good", "best", "word"};
        String s = "barfoofoobarthefoobarman";
        String s2 = "wordgoodgoodgoodbestword";
        String s3 = "barfoothefoobarman";
        System.out.println(findSubstring(s, words));
        System.out.println(findSubstring(s2, words2));
        System.out.println(findSubstring(s3, words3));
    }

    public List<Integer> findSubstring2(String s, String[] words) {
        int n = s.length(), m = words.length, w = words[0].length();
        Map<String, Integer> map = new HashMap<>();
        for (String word : words) map.put(word, map.getOrDefault(word, 0) + 1);
        List<Integer> ans = new ArrayList<>();
        out:for (int i = 0; i + m * w <= n; i++) {
            Map<String, Integer> cur = new HashMap<>();
            String sub = s.substring(i, i + m * w);
            for (int j = 0; j < sub.length(); j += w) {
                String item = sub.substring(j, j + w);
                if (!map.containsKey(item)) continue out;
                cur.put(item, cur.getOrDefault(item, 0) + 1);
            }
            if (cur.equals(map)) ans.add(i);
        }
        return ans;
    }


    public static List<Integer> findSubstring(String s, String[] words) {
        // 输入参数的word数组是可以随意组合成一个字符串，找到在s中出现的index位置。可以有多个
        // 将word存入hashmap
        Map<Integer, String> map = new HashMap<>();
        for (int i = 0; i < words.length; i++) {
            map.put(i, words[i]);
        }
        int right = 0;
        int offset = Arrays.stream(words).mapToInt(String::length).sum(); //固定窗口的大小为left - left+offset
        List<Integer> rs = new ArrayList<>();

        for (int left = 0; left <= s.length() - offset; left++) { //right < s.length()
            right = left + offset;
            String s1 = s.substring(left, right);
            if (equalWithMap(map, s1)) {
                rs.add(left);
            }
        }

        return rs;
    }

    public static boolean equalWithMap(Map<Integer, String> map, String s) {
        Map<Integer, String> map1 = new HashMap<>();
        for (int j = 0, i = 0; j < s.length(); j++) {
            if (map.containsValue(s.substring(i, j + 1))) {
                map1.put(i, s.substring(i, j + 1));
                i = j + 1;
            }
        }
        String collect1 = map1.values().stream().sorted(new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return o1.compareTo(o2);
            }
        }).collect(Collectors.toList()).toString();
        String collect2 = map.values().stream().sorted(new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return o1.compareTo(o2);
            }
        }).collect(Collectors.toList()).toString();
        if (collect1.equals(collect2)) {
            return true;
        }
        return false;
    }
}
