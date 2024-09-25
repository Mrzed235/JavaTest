package com.opencc.huawei.OJ.medium;

import java.util.*;

/**
 * 生成哈夫曼树
 * <a href="https://www.nowcoder.com/issue/tutorial?zhuanlanId=MgbyJj&uuid=f14e030f3a15478bb4d84184e94b77a3">...</a>
 */

class Node {
    int weight;
    Node left;
    Node right;

    public Node(int weight) {
        this.weight = weight;
        left = null;
        right = null;
    }
}

class HuffmanTree {
    public static Node buildHuffmanTree(List<Integer> weights) {
        if (weights == null || weights.isEmpty()) {
            return null;
        }
        PriorityQueue<Node> pq = new PriorityQueue<>((a, b) -> {
            if (a.weight != b.weight) {
                return a.weight - b.weight;
            } else {
                int leftHeightA = getHeight(a.left);
                int leftHeightB = getHeight(b.left);
                if (leftHeightA != leftHeightB) {
                    return leftHeightA - leftHeightB;
                } else {
                    if (a.left.weight + a.right.weight != b.left.weight + b.right.weight) {
                        return (a.left.weight + a.right.weight) - (b.left.weight + b.right.weight);
                    } else {
                        return (b.left.weight + b.right.weight) - (a.left.weight + a.right.weight);
                    }
                }
            }
        });
        for (int weight : weights) {
            pq.add(new Node(weight));
        }
        while (pq.size() > 1) {
            Node left = pq.poll();
            Node right = pq.poll();
            Node parent = new Node(left.weight + right.weight);
            parent.left = left;
            parent.right = right;
            pq.add(parent);
        }
        return pq.poll();
    }

    public static int getHeight(Node node) {
        if (node == null) {
            return 0;
        }
        return 1 + Math.max(getHeight(node.left), getHeight(node.right));
    }

    public static void inorderTraversal(Node root) {
        if (root == null) {
            return;
        }
        inorderTraversal(root.left);
        System.out.print(root.weight + " ");
        inorderTraversal(root.right);
    }
}

public class OJTest15 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        List<Integer> nums = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            nums.add(in.nextInt());
        }
        Node root = HuffmanTree.buildHuffmanTree(nums);
        HuffmanTree.inorderTraversal(root);
        System.out.println();
    }
}

