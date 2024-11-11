package com.opencc.LeetCodeTest.Arrays;

import com.opencc.LeetCodeTest.Common;

import java.util.*;

/**
 * 字母异位词分组
 * <a href="https://leetcode.cn/problems/group-anagrams/description/?envType=problem-list-v2&envId=array">...</a>
 * 给你一个字符串数组，请你将 字母异位词 组合在一起。可以按任意顺序返回结果列表。
 * 字母异位词 是由重新排列源单词的所有字母得到的一个新单词。
 * 示例 1:
 * 输入: strs = ["eat", "tea", "tan", "ate", "nat", "bat"]
 * 输出: [["bat"],["nat","tan"],["ate","eat","tea"]]
 * 示例 2:
 * 输入: strs = [""]
 * 输出: [[""]]
 * 示例 3:
 * 输入: strs = ["a"]
 * 输出: [["a"]]
 */
public class LeetCode49 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String[] strs = Common.parseToStringArray(in.nextLine());
        LeetCode49 lt = new LeetCode49();
        System.out.println(lt.groupAnagrams(strs));
        System.out.println(lt.groupAnagrams2(strs));
        System.out.println(lt.groupAnagrams3(strs));
    }

    public List<List<String>> groupAnagrams3(String[] strs) {
        Map<String, List<String>> map = new HashMap<>();
        for (String str : strs) {
            int[] count = new int[26];
            int length = str.length();
            for (int i = 0; i < length; i++) {
                count[str.charAt(i) - 'a']++;
            }
            //将每个出现次数大于0的字母和出现次数按照顺序拼接成字符串 ，组成建
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < 26; i++) {
                if (count[i] != 0) {
                    sb.append((char) ('a' + i));
                    sb.append(count[i]);
                }
            }
            String key = sb.toString();
            List<String> list = map.getOrDefault(key, new ArrayList<String>());
            list.add(str);
            map.put(key, list);
        }
        return new ArrayList<>(map.values());
    }

    public List<List<String>> groupAnagrams2(String[] strs) {
        Map<String, List<String>> map = new HashMap<>();
        for (String str : strs) {
            char[] arrays = str.toCharArray();
            Arrays.sort(arrays);
            String key = new String(arrays);
            List<String> list = map.getOrDefault(key, new ArrayList<>());
            list.add(str);
            map.put(key, list);
        }
        return new ArrayList<>(map.values());
    }

    public List<List<String>> groupAnagrams(String[] strs) {
        int n = strs.length;
        boolean[] visited = new boolean[n];
        List<List<String>> res = new ArrayList<>();
        judgeArr(visited, strs, res);
        return res;
    }

    private void judgeArr(boolean[] visited, String[] strs, List<List<String>> res) {
        for (int i = 0; i < strs.length; i++) {
            if (visited[i]) {
                continue;
            }
            List<String> list = new ArrayList<>();
            String s1 = strs[i];
            list.add(s1);
            visited[i] = true;
            char[] array1 = s1.toCharArray();
            Arrays.sort(array1);
            String oldS = new String(array1);
            for (int j = i + 1; j < strs.length; j++) {
                if (!visited[j] && judgeIfEquals(oldS, strs[j])) {
                    list.add(strs[j]);
                    visited[j] = true;
                }
            }
            list.sort(String::compareTo);
            res.add(list);
        }
        res.sort(Comparator.comparing(o -> o.get(0)));
    }

    public boolean judgeIfEquals(String s1, String s2) {
        char[] array2 = s2.toCharArray();
        Arrays.sort(array2);
        String newS = new String(array2);
        return s1.equals(newS);
    }
}
