package com.opencc.collectiontest;

public class BinarySearchTree {

    TreeNode root;

    public BinarySearchTree() {
        this.root = null;
    }

    //插入数据，构造二叉搜索树，左子树都小于根节点，右子树都大于根节点
    public void insert(int val) {
        root = insertTree(root, val);
    }

    private TreeNode insertTree(TreeNode node, int val) {
        //判断当前的node是否为空，如果为空的话说明当前的节点不存在，需要创建
        if (node == null) {
            return new TreeNode(val);
        }
        if (val < node.val) {
            node.left = insertTree(node.left, val);
        } else {
            node.right = insertTree(node.right, val);
        }
        return node;
    }

    // 中序遍历（升序输出节点值）
    public void inorderTraversal() {
        System.out.print("中序遍历：");
        inorder(root);
        System.out.println();
    }

    private void inorder(TreeNode root) {
        if (root!= null) {
            inorder(root.left);
            System.out.print(root.val + " ");
            inorder(root.right);
        }
    }

    //查找节点是否存在，不存在的话，就返回false
    public Boolean searchBinary(int val) {
        return searchRec(root, val);
    }

    private Boolean searchRec(TreeNode node, int val) {
        if (node == null) {
            return false;
        }
        if (val == node.val) {
            return true;
        } else if (val < node.val) {
            return searchRec(root.left, val);
        } else {
            return searchRec(root.right, val);
        }
    }

    //删除节点

    /**
     *
     //这个方法是用于在以给定节点root为根的二叉搜索树中删除值为val的节点，并返回更新后的树的根节点。
     //一、查找要删除的节点
     //首先进行常规的二叉搜索树遍历，通过比较要删除的值val和当前节点的值root.val来确定在左子树还是右子树中继续查找。
     //如果val < root.val，说明要删除的节点在左子树中，递归调用deleteRec方法在左子树中进行删除操作，并更新当前节点的左子树指针。
     //如果val > root.val，同理在右子树中进行查找和删除操作。
     //二、处理三种删除情况
     //当找到要删除的节点时（即val == root.val）：
     //如果该节点没有左子树（root.left == null），直接返回该节点的右子树作为新的子树，相当于将该节点删除并由其右子树替代它的位置。
     //如果该节点没有右子树（root.right == null），则返回左子树作为新的子树，将该节点删除并由其左子树替代。
     //如果该节点有两个子树：
     //首先找到该节点右子树中的最小节点值（通过findMinValue方法实现）。
     //将该最小节点值赋给当前要删除的节点的值root.val，这样就相当于用右子树中的最小节点值替换了要删除的节点的值。
     //然后在右子树中递归调用deleteRec方法删除这个最小节点，确保右子树仍然是一棵合法的二叉搜索树。
     //三、返回更新后的子树
     //无论哪种情况，最后都返回更新后的子树，这个子树将作为上一层递归调用的结果，逐步更新整棵树。
     //综上所述，deleteRec方法通过递归的方式在二叉搜索树中准确地找到要删除的节点，并根据节点的不同情况进行恰当的处理，以保持二叉搜索树的性质。
     * @param val
     */
    public void deleteNode(int val) {
        root = deleteRec(root, val);
    }

    private TreeNode deleteRec(TreeNode node, int val) {
        if (node == null) {
            return node;
        }
        if (val < node.val) {//如果val小于当前节点的值，则val在左子树上
            node.left = deleteRec(node.left, val);
        } else if (val > node.val) { //如果val大于当前节点的值，则val在右子树上
            node.right = deleteRec(node.right, val);
        }else {
            //找到要删除的点
            if (node.left == null) { //如果要删除的节点的左子树为空，用当前节点的右子树代替他的引用即可，因为右子树所有的node.val都大于当前节点本身的值
                return node.right;
            }else if (node.right == null){//如果要删除的节点的右子树为空，用左子树节点去代替的引用，相当于删除当前节点了。
                return node.left;
            }
            // 节点有两个子节点，就要从右子树中选择最小的值来替换当前node的值，并且从右子树中删除那个最小的数（也可以选择左子树中最大的数）
            node.val = findMinValue(node.right);//从当前节点的右子树找最小的值，并赋值给node.val
            node.right = deleteRec(node.right, node.val); //删除当前节点右子树中值为node.val的节点。
        }

        return node;
    }

    private int findMinValue(TreeNode node) {
        int minValue = node.val;
        while (node.left!= null) {
            minValue = node.left.val;
            node = node.left;
        }
        return minValue;
    }

    public static void main(String[] args) {
        System.out.println("name".hashCode());
        int[] nums = {8, 5, 6, 3, 12, 23, 39, 22, 14, 15};
        //todo 创建一个根节点
        BinarySearchTree bst = new BinarySearchTree();
        for (int num : nums) {
            bst.insert(num);
        }
        bst.inorderTraversal();

        bst.deleteNode(12);
        bst.inorderTraversal();
    }

}
