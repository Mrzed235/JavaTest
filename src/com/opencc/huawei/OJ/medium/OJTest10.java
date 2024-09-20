package com.opencc.huawei.OJ.medium;

import java.util.*;

/**
 * 最富裕的小家庭
 * <a href="https://www.nowcoder.com/issue/tutorial?zhuanlanId=MgbyJj&uuid=df32f05a1a03479ca171d66c2727c7b7">...</a>
 * 样例 1
 * 4
 * 100 200 300 500
 * 1 2
 * 1 3
 * 2 4
 * 输出：
 * 700
 * 样例 2
 * 4
 * 100 200 300 500
 * 1 2
 * 1 3
 * 1 4
 * 输出：
 * 1100
 */
class NodeFam {
    int val;
    List<NodeFam> children;

    public NodeFam(int val) {
        this.val = val;
        this.children = new ArrayList<>();
    }
}

public class OJTest10 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int N = in.nextInt();
        int[] wealths = new int[N];
        for (int i = 0; i < wealths.length; i++) {
            wealths[i] = in.nextInt();
        }
        NodeFam[] nodes = new NodeFam[N];
        for (int i = 0; i < N; i++) {
            nodes[i] = new NodeFam(wealths[i]);
        }
        for (int i = 0; i < N - 1; i++) {
            int parent = in.nextInt();
            int child = in.nextInt();
            nodes[parent - 1].children.add(nodes[child - 1]);
        }
        int wealthCount = findMaxFamilyWealth(nodes[0]);
        System.out.println(wealthCount);
    }

    private static int findMaxFamilyWealth(NodeFam node) {
        if (node == null) {
            return 0;
        }
        int currentFamilyWealth = node.val;
        for (NodeFam child : node.children) {
            currentFamilyWealth += child.val;
        }
        int maxAmongChildren = 0;
        for (NodeFam child : node.children) {
            maxAmongChildren = Math.max(maxAmongChildren, findMaxFamilyWealth(child));
        }
        return Math.max(currentFamilyWealth, maxAmongChildren);
    }
}
