package com.opencc.huawei.OJ.medium;

import java.util.Arrays;
import java.util.Scanner;

/**
 * 传递悄悄话的最长时间
 * <a href="https://www.nowcoder.com/issue/tutorial?zhuanlanId=MgbyJj&uuid=786d6c302ef148b58255c12639713f00">...</a>
 * 输入：0 9 20 -1 -1 15 7 -1 -1 -1 -1 3 2
 * 输出：38
 */
class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    int maxVal;

    TreeNode(int val) {
        this.val = val;
    }

    public TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
}

public class OJTest13 {
    static int maxTime = 0;
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int[] nums = Arrays.stream(in.nextLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        TreeNode root = constructBinaryTree(nums);
        System.out.println(root.maxVal);
    }


    private static TreeNode constructBinaryTree(int[] nums) {
        if (nums == null || nums.length == 0) {
            return null;
        }
        return buildTree(nums, 0);
    }

    private static TreeNode buildTree(int[] nums, int index) {
        if (index >= nums.length || nums[index] == -1) {
            return null;
        }
        TreeNode node = new TreeNode(nums[index]);
        node.left = buildTree(nums, index * 2 + 1);
        node.right = buildTree(nums, index * 2 + 2);
        node.maxVal = Math.max(height(node.left), height(node.right)) + node.val;
        return node;
    }

    private static int height(TreeNode node) {
        if (node == null) {
            return 0;
        }
        return node.maxVal;
    }
}
