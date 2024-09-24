package com.opencc.huawei.OJ.hard;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * 模拟目录管理：支持嵌套目录
 * 目录管理器
 * <a href="https://www.nowcoder.com/issue/tutorial?zhuanlanId=MgbyJj&uuid=046b5650d7d44b35bfc15517c866384d">...</a>
 * <a href="https://www.nowcoder.com/discuss/661675346583990272?sourceSSR=search">...</a>
 * 题目描述
 * 实现一个模拟目录管理功能的软件，输入一个命令序列，输出最后一条命令运行结果。支持命令：创建目录命令：mkdir 目录名称，如 mkdir abc 为在当前目录创建abc目录，
 * 如果已存在同名目录则不执行任何操作。此命令无输出。进入目录命令：cd 目录名称，如 cd abc 为进入abc目录，特别地，cd .. 为返回上级目录，如果目录不存在则不执行任何操作。
 * 此命令无输出。查看当前所在路径命令：pwd，输出当前路径字符串。约束：目录名称仅支持小写字母；mkdir 和 cd 命令的参数仅支持单个目录，
 * 如：mkdir abc 和 cd abc；不支持嵌套路径和绝对路径，如 mkdir abc/efg，cd abc/efg，mkdir /abc/efg，cd /abc/efg 是不支持的。
 * 目录符号为/，根目录/作为初始目录。任何不符合上述定义的无效命令不做任何处理并且无输出。
 * 输入描述
 * 输入 N 行字符串，每一行字符串是一条命令。
 * 输出描述
 * 输出最后一条命令运行结果字符串。
 * 备注
 * 命令行数限制100行以内，目录名称限制10个字符以内。
 * 用例1
 * 输入
 * mkdir abc
 * cd abc
 * pwd
 * 输出
 * /abc/
 * 说明：在根目录创建一个abc的目录并进入abc目录中查看当前目录路径，输出当前路径/abc/
 */

class DirectoryNode {
    private String dirName; //定义目录名称
    private DirectoryNode parent; //定义父节点
    private List<DirectoryNode> children; //定义孩子节点

    public DirectoryNode(String dirName) {
        this.dirName = dirName;
        this.parent = null;
        this.children = new ArrayList<>();
    }

    //添加子目录节点
    public void addChild(DirectoryNode node) {
        children.add(node);
        node.parent = this;
    }

    //获取当前目录名称
    public String getDirName() {
        return dirName;
    }

    //获取父目录节点
    public DirectoryNode getParent() {
        return parent;
    }

    //获取子目录节点
    public List<DirectoryNode> getChildren() {
        return children;
    }
}

class DirectoryTree {
    private DirectoryNode root;

    public DirectoryTree() {
        this.root = new DirectoryNode("/");
    }

    //创建目录
    public void mkdir(String path) {
        if (path.startsWith("/")) {
            path = path.substring(1);
        }
        String[] dirNames = path.split("/");
        DirectoryNode current = root;
        for (String dirName : dirNames) {
            boolean found = false;
            for (DirectoryNode child : current.getChildren()) {
                if (child.getDirName().equals(dirName)) {
                    current = child;
                    found = true;
                    break;
                }
            }
            if (!found) {
                DirectoryNode newDir = new DirectoryNode(dirName);
                current.addChild(newDir);
                current = newDir;
            }
        }
    }

    public void cd(String path) {
        if (path.equals("..")) { //向后退一层目录到当前的parents层
            if (root.getParent() != null) {
                root = root.getParent();
            }
        } else { //进入到目录里
            if (path.startsWith("/")) {
                path = path.substring(1);
            }
            String[] dirNames = path.split("/");
            DirectoryNode current = root;
            for (String dirName : dirNames) {
                for (DirectoryNode child : current.getChildren()) {
                    if (child.getDirName().equals(dirName)) {
                        current = child;
                        break;
                    }
                }
            }
            root = current;
        }
    }

    public String pwd() {
        StringBuilder currentPath = new StringBuilder();
        DirectoryNode current = root;
        List<DirectoryNode> pathNodes = new ArrayList<>();
        while (current != null) {
            pathNodes.add(current);
            current = current.getParent();
        }
        for (int i = pathNodes.size() - 1; i >= 0; i--) {
            currentPath.append(pathNodes.get(i).getDirName()).append("/");
        }
        return currentPath.substring(1);
    }
}

public class OJTest2 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        DirectoryTree directoryTree = new DirectoryTree();

        while (in.hasNextLine()) {
            String command = in.nextLine();
            if (command.startsWith("mkdir")) {
                String dirName = command.split(" ")[1];
                directoryTree.mkdir(dirName);
            } else if (command.startsWith("cd")) {
                String dirName = command.split(" ")[1];
                directoryTree.cd(dirName);
            } else if (command.equals("pwd")) {
                System.out.println(directoryTree.pwd());
            }
        }
        in.close();
    }
}
