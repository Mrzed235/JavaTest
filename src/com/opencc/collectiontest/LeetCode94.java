package com.opencc.collectiontest;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * 二叉树的中序遍历
 * <a href="https://leetcode.cn/problems/binary-tree-inorder-traversal/description/?envType=problem-list-v2&envId=binary-tree">...</a>
 */

public class LeetCode94 {
    public TreeNode constructBinaryTree(String[] arr) {
        if (arr == null || arr.length == 0) {
            return null;
        }
        return buildTree(arr, 0);
    }

    private TreeNode buildTree(String[] arr, int index) {
        if (index >= arr.length || arr[index].equals("null")) {
            return null;
        }
        TreeNode node = new TreeNode(Integer.parseInt(arr[index]));
        node.left = buildTree(arr, 2 * index + 1);
        node.right = buildTree(arr, 2 * index + 2);
        return node;
    }


    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> result =  new ArrayList<>();
        inorder(root, result);
        return result;
    }

    private void inorder(TreeNode root, List<Integer> result) {
        if (root == null) {
            return;
        }
        inorder(root.left, result);
        result.add(root.val);
        inorder(root.right, result);
    }

    public static void main(String[] args) {
        String[] arr = {"1", "2", "3", "4", "5", "null", "8", "null", "null", "6", "7", "9"};
        LeetCode94 lt = new LeetCode94();
        TreeNode root = lt.constructBinaryTree(arr);
        System.out.println(lt.inorderTraversal(root));

    }
}
