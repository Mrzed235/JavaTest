package com.opencc.collectiontest;

import jdk.nashorn.api.tree.Tree;

/**
 * 二叉树
 */

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode(int val) {
        this.val = val;
    }

    public TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
}

public class BinaryTree {
    TreeNode root; //定义根节点

    public BinaryTree() {
        this.root = null; //初始化根节点赋值为空。
    }

    //定义插入方法，将数据插入到二叉树中，去构造
    public void insert(int value) {
        root = insertRec(root, value);
    }

    //用了递归的方式
    private TreeNode insertRec(TreeNode root, int value) {
        if (root == null) {
            return new TreeNode(value);
        }
        //如果当前要插入的值小于其父节点，则插入到父节点的左子树中,否则插入到右子树。
        if (value < root.val) {
            root.left = insertRec(root.left, value);
        } else {
            root.right = insertRec(root.right, value);
        }
        return root;
    }

    //前序遍历
    public void preOrderTraversal() {
        preOrder(root);
    }

    private void preOrder(TreeNode node) {
        if (node != null) {
            System.out.println(node.val + " ");
            preOrder(node.left);
            preOrder(node.right);
        }
    }

    //中序遍历
    public void inOrderTraversal() {
        inOrder(root);
    }

    private void inOrder(TreeNode node) {
        if (node != null) {
            inOrder(node.left);
            System.out.println(node.val+" ");
            inOrder(node.right);
        }
    }

}
