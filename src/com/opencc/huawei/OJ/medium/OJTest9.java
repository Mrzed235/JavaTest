package com.opencc.huawei.OJ.medium;


import java.util.Scanner;

/**
 * 计算三叉搜索树的高度
 * <a href="https://app5938.acapp.acwing.com.cn/contest/11/problem/OD1031">...</a>
 * K小姐正在学习数据结构，她了解到三叉搜索树的构造规则如下：
 * 每个节点都存有一个数，当插入一个新的数时，从根节点向下寻找，直到找到一个合适的空节点插入。查找的规则是：
 * 1. 如果数小于节点的数减去500，则将数插入节点的左子树。
 * 2. 如果数大于节点的数加上500，则将数插入节点的右子树。
 * 3. 否则，将数插入节点的中子树。
 * 现在，K小姐有一系列数，请按以上规则，按顺序将数插入树中，构建出一棵三叉搜索树，最后输出树的高度。
 * Input
 * 第一行为一个正整数N，表示有N个数,1≤N≤10000。
 * 第二行为N个空格分隔的正整数，每个数的范围为[1,10000]。
 * Output
 * 输出树的高度（根节点的高度为1）
 * Sample Input 1
 * 5
 * 5000 2000 5000 8000 1800
 * Sample Output 1
 * 3
 * Sample Input 2
 * 3
 * 5000 4000 3000
 * Sample Output 2
 * 3
 * Sample Input 3
 * 9
 * 5000 2000 5000 8000 1800 7500 4500 1400 8100
 * Sample Output 3
 * 4
 */
class TreeNodeC {
    int val;
    TreeNodeC left;
    TreeNodeC mid;
    TreeNodeC right;
    int height;

    public TreeNodeC(int val) {
        this.val = val;
        this.left = null;
        this.mid = null;
        this.right = null;
        this.height = 1;
    }

    public int getHeight() {
        return height;
    }
}

class TernaryTree {
    TreeNodeC root;

    public TernaryTree() {
        this.root = null;
    }

    public void insertNode(int val) {
        root = insertRec(root, val);
    }

    private TreeNodeC insertRec(TreeNodeC node, int val) {
        //先判断当前进来的root是否是空，如果为空，需要创建一个节点，如果不是null，走左中右子树的判断：
        if (node == null) {
            return new TreeNodeC(val);
        }
        if (val < node.val - 500) {
            node.left = insertRec(node.left, val);
        } else if (val > node.val + 500) {
            node.right = insertRec(node.right, val);
        } else {
            node.mid = insertRec(node.mid, val);
        }
        node.height = 1 + Math.max(height(node.left), Math.max(height(node.mid), height(node.right)));

        return node;
    }

    private int height(TreeNodeC node) {
        if (node == null) {
            return 0;
        }
        return node.height;
    }

    public int treeHeight() {
        return treeHeightCount(root);
    }

    private int treeHeightCount(TreeNodeC node) {
        if (node == null) {
            return 0;
        }
        int leftH = treeHeightCount(node.left);
        int midH = treeHeightCount(node.mid);
        int rightH = treeHeightCount(node.right);
        return Math.max(leftH, Math.max(midH, rightH)) + 1;
    }
}

public class OJTest9 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        TernaryTree root = new TernaryTree();
        int n = in.nextInt();
        for (int i = 0; i < n; i++) {
            root.insertNode(in.nextInt());
        }
        int height = root.treeHeight();
        System.out.println(height);
        System.out.println(root.root.getHeight());
    }
}
