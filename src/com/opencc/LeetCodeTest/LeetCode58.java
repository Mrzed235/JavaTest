package com.opencc.LeetCodeTest;

/**
 * <a href="https://leetcode.cn/problems/length-of-last-word/?envType=study-plan-v2&envId=top-interview-150">...</a>
 */
public class LeetCode58 {
    public int lengthOfLastWord(String s) {
        int index = s.length() - 1;
        while (s.charAt(index) == ' ') {
            index--;
        }

        int wordLen = 0;
        while (index >= 0 && s.charAt(index) != ' ') {
            wordLen++;
            index--;
        }
        return wordLen;
    }

    public static void main(String[] args) {
        LeetCode58 lt = new LeetCode58();
        System.out.println(lt.lengthOfLastWord("Hello World"));
        System.out.println(lt.lengthOfLastWord("   fly me   to   the moon  "));
        System.out.println(lt.lengthOfLastWord("luffy is still joyboy"));
    }
}
