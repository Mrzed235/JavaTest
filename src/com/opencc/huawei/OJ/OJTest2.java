package com.opencc.huawei.OJ;

import java.io.*;
import java.util.*;

/**
 * Boss的收入
 * <a href="https://www.nowcoder.com/discuss/661349470075969536?sourceSSR=search">...</a>
 * 题目描述
 * 一个XX产品行销总公司，只有一个 boss，其有若干一级分销，一级分销又有若干二级分销，每个分销只有唯一的上级分销。规定每个月，
 * 下级分销需要将自己的总收入(自己的+下级上交的)每满100元上交15元给自己的上级.现给出一组分销的关系，和每个分销的收入，
 * 请找出 boss并计算出这 boss 的收入。比如:收入100元上交15元,收入199元(9元不够100)上交15元，收入200元，上交30元。
 * 分销关系和收入:分销id 上级分销的ld 收入
 * 分销ID范围0…65535
 * 收入范围:0…65535,单位元
 * 提示: 输入的数据只存在1个 boss，不存在环路
 * 输入描述
 * 第1行输入关系的总数量N
 * 第2行开始，输入关系信息，格式:分销ID 上级分销ID 收入
 * 示例1
 * 输入
 * 5
 * 1 0 100
 * 2 0 200
 * 3 0 300
 * 4 0 200
 * 5 0 200
 * 输出
 * 0 150
 * 示例2
 * 输入
 * 3
 * 1 0 223
 * 2 0 323
 * 3 2 1203
 * 输出
 * 0 105
 * 输入
 * 5
 * 1 0 100
 * 2 1 200
 * 3 2 300
 * 4 3 200
 * 5 4 200
 * 输出
 * 0 15
 */


import java.io.IOException;

class Node {
    int id;
    int income;
    Node parent;
    List<Node> children;

    public Node(int id, int income) {
        this.id = id;
        this.income = income;
        this.children = new ArrayList<>();
    }

    public void setIncome(int income) {
        this.income = income;
    }
}

public class OJTest2 {
    public static void main(String[] args) throws IOException {
        Scanner in = new Scanner(System.in);
        Map<Integer, Node> nodeMap = new HashMap<>();
        int n = Integer.parseInt(in.nextLine()); //输入需要的行数
        String[] inputs = new String[n];
        for (int i = 0; i < inputs.length; i++) {
            inputs[i] = in.nextLine();
        }
        int bossId = Integer.MAX_VALUE;
        for (String input : inputs) {
            String[] s = input.split(" ");
            bossId = Math.min(Integer.parseInt(s[1]), bossId);
        }
        Node bossNode = new Node(bossId, 0);
        nodeMap.put(bossId, bossNode);
        for (int i = 0; i < inputs.length; i++) {
            String[] parts = inputs[i].split(" ");
            int id = Integer.parseInt(parts[0]);
            int parentId = Integer.parseInt(parts[1]);
            int income = Integer.parseInt(parts[2]);

            Node node = new Node(id, income);

            if (!nodeMap.containsKey(parentId)) {
                node.parent = null;
            } else {
                Node parentNode = nodeMap.get(parentId);
                node.parent = parentNode;
                parentNode.children.add(node);
            }

            nodeMap.put(id, node);
        }
        int result = resovle(nodeMap.get(bossId), 0);
        nodeMap.get(bossId).setIncome(result);
        System.out.println(nodeMap.get(bossId).id + " " + nodeMap.get(bossId).income);
    }

    private static int resovle(Node node, int level) {
        int income = node.income;
        for (Node child : node.children) {
            int childIncome = dfs(child, level + 1);
            income += childIncome;
        }

        // Calculate the amount to be transferred to the boss
        return income;
    }


    private static int dfs(Node node, int level) {
        //todo 递归中止条件
        if (node.children.isEmpty()) {
            return node.income / 100 * 15;
        }
        for (Node child : node.children) {
            int childIncome = dfs(child, level + 1);
            node.income += childIncome;
        }
        return node.income / 100 * 15;
    }
}
