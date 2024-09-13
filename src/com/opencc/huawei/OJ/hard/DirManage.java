package com.opencc.huawei.OJ.hard;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

//同OJTest2
class DirNode {
    private String dirName; //定义当前节点的目录名称
    private DirNode parent;//定义父类的Node节点
    private List<DirNode> children; //定义子类的节点列表，可能有多个同级子目录

    public DirNode(String dirName) {
        this.dirName = dirName;
        parent = null;
        children = new ArrayList<>();
    }

    public void addChildren(DirNode node) {
        children.add(node);
        node.parent = this;
    }

    public String getDirName() {
        return dirName;
    }


    public DirNode getParent() {
        return parent;
    }

    public List<DirNode> getChildren() {
        return children;
    }
}

class DirectoryDir {
    DirNode root;

    public DirectoryDir() {
        this.root = new DirNode("/");
    }

    public void mkdir(String path) {
        if (path.startsWith("/")) {
            path = path.substring(1);
        }
        if (!path.contains("/")) { //如果path中没有嵌套目录的话，才能在当前root节点下创建目录
            boolean flag = false;
            for (DirNode child : root.getChildren()) {
                if (child.getDirName().equals(path)) {
                    flag = true;
                    break;
                }
            }
            if (!flag) {
                DirNode node = new DirNode(path);
                root.addChildren(node);
            }
        }
    }

    public void cd(String path) {
        if (path.equals("..")) {
            if (root.getParent() != null) {
                root = root.getParent();
            }
        } else {
            if (path.startsWith("/")) {
                path = path.substring(1);
            }
            if (!path.contains("/")) {
                DirNode current = root;
                for (DirNode child : current.getChildren()) {
                    if (child.getDirName().equals(path)) {
                        current = child;
                        break;
                    }
                }
                root = current;
            }
        }
    }

    public String pwd() {
        StringBuilder bd = new StringBuilder();
        DirNode current = root;
        List<String> lists = new ArrayList<>();
        if (!current.getDirName().equals("/")) { //如果当前不在根目录
            do {
                lists.add(current.getDirName());
                current = current.getParent();
            } while (current != null);
        } else {
            return bd.append("/").toString();
        }
        for (int i = lists.size() - 1; i >= 0; i--) {
            bd.append(lists.get(i)).append("/");
        }
        return bd.substring(1);
    }
}

public class DirManage {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        DirectoryDir directoryTree = new DirectoryDir();
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
    }
}
