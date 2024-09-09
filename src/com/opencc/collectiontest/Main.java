package com.opencc.collectiontest;

public class Main {
    public static void main(String[] args) {
        BinaryTree tree = new BinaryTree();
        tree.insert(5);
        tree.insert(3);
        tree.insert(7);
        tree.insert(2);
        tree.insert(4);
        tree.insert(6);
        tree.insert(8);

//        System.out.println("前序遍历：");
//        tree.preOrderTraversal();
        System.out.println("\n中序遍历：");
        tree.inOrderTraversal();
//        System.out.println("\n后序遍历：");
//        tree.postOrderTraversal();
    }
}
