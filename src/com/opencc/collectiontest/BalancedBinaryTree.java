package com.opencc.collectiontest;

/**
 * 平衡二叉树：
 * insert
 * 中序遍历
 * delete
 */
class TreeNodeB {
    int val;
    TreeNodeB left;
    TreeNodeB right;
    int height;

    public TreeNodeB(int val) {
        this.val = val;
        this.left = null;
        this.right = null;
        this.height = 1;
    }
}

public class BalancedBinaryTree {
    TreeNodeB root;

    public BalancedBinaryTree() {
        this.root = null;
    }

    //插入tree，构成平衡二叉树
    public void insertNode(int val) {
        root = insertRec(root, val);
    }

    private TreeNodeB insertRec(TreeNodeB root, int val) {
        if (root == null) {
            return new TreeNodeB(val);
        }

        if (val < root.val) {
            root.left = insertRec(root.left, val);
        } else if (val > root.val) {
            root.right = insertRec(root.right, val);
        } else {
            // 不允许插入重复值
            return root;
        }

        // 更新节点高度
        root.height = 1 + Math.max(height(root.left), height(root.right));

        // 检查平衡因子并进行调整
        int balance = balanceFactor(root);

        // 左子树不平衡且插入值小于左子节点值
        if (balance > 1 && val < root.left.val) {
            return rightRotate(root);
        }

        // 右子树不平衡且插入值大于右子节点值 RR型
        if (balance < -1 && val > root.right.val) {
            return leftRotate(root);
        }

        // 左子树不平衡且插入值大于左子节点值
        if (balance > 1 && val > root.left.val) {
            root.left = leftRotate(root.left);
            return rightRotate(root);
        }

        // 右子树不平衡且插入值小于右子节点值  RL型。先右旋再左旋节点本身
        if (balance < -1 && val < root.right.val) {
            root.right = rightRotate(root.right);
            return leftRotate(root);
        }

        return root;
    }

    private int height(TreeNodeB node) {
        if (node == null) {
            return 0;
        }
        return node.height;
    }

    private int balanceFactor(TreeNodeB node) {
        if (node == null) {
            return 0;
        }
        return height(node.left) - height(node.right);
    }

    private TreeNodeB rightRotate(TreeNodeB y) {
        TreeNodeB x = y.left;
        TreeNodeB T2 = x.right;

        x.right = y;
        y.left = T2;

        y.height = Math.max(height(y.left), height(y.right)) + 1;
        x.height = Math.max(height(x.left), height(x.right)) + 1;

        return x;
    }

    private TreeNodeB leftRotate(TreeNodeB x) {
        TreeNodeB y = x.right;
        TreeNodeB T2 = y.left;

        y.left = x;
        x.right = T2;

        x.height = Math.max(height(x.left), height(x.right)) + 1;
        y.height = Math.max(height(y.left), height(y.right)) + 1;

        return y;
    }

    //中序遍历
    public void inorderNode() {
        System.out.println("中序遍历：");
        inorderRec(root);
    }

    private void inorderRec(TreeNodeB root) {
        if (root == null) {
            return;
        }
        inorderRec(root.left);
        System.out.printf("%d ", root.val);
        inorderRec(root.right);
    }

    //删除节点；需要在删除的时候一层层的查看底层父类节点的平衡因子并调整。
    private void deleteNode(int val) {
        root = deleteRec(root, val);
    }

    private TreeNodeB deleteRec(TreeNodeB root, int val) {
        if (root == null) {
            return null; //如果当前删除的节点为叶子节点，直接返回null替换即可
        }
        if (val < root.val) { //删除的节点在当前节点的左子树上
            root.left = deleteRec(root.left, val);
        } else if (val > root.val) { //删除的节点在当前节点的右子树上
            root.right = deleteRec(root.right, val);
        } else { // 需要删除节点就是当前节点本身。
            //todo 删除前需要判断是否存在左子树和右子树
            // 找到要删除的节点
            if (root.left == null || root.right == null) {
                TreeNodeB temp = null;
                if (temp == root.left) {
                    temp = root.right;
                } else {
                    temp = root.left;
                }

                if (temp == null) {
                    temp = root;
                    root = null;
                } else {
                    root = temp;
                }
            } else {
                // 有两个子节点，找到右子树中的最小节点
                TreeNodeB temp = findMinValue(root.right);
                root.val = temp.val;
                root.right = deleteRec(root.right, temp.val);
            }
        }
        if (root == null) {
            return root;
        }

        // 更新节点高度
        root.height = 1 + Math.max(height(root.left), height(root.right));

        // 检查平衡因子并进行调整
        int balance = balanceFactor(root);

        // 左子树不平衡
        if (balance > 1) {
            // 左子树的左子树高度大于左子树的右子树高度
            if (balanceFactor(root.left) >= 0) {
                return rightRotate(root);
            } else {
                // 左子树的右子树高度大于左子树的左子树高度
                root.left = leftRotate(root.left);
                return rightRotate(root);
            }
        }

        // 右子树不平衡
        if (balance < -1) {
            // 右子树的右子树高度大于右子树的左子树高度
            if (balanceFactor(root.right) <= 0) {
                return leftRotate(root);
            } else {
                // 右子树的左子树高度大于右子树的右子树高度
                root.right = rightRotate(root.right);
                return leftRotate(root);
            }
        }

        return root;
    }
    private TreeNodeB findMinValue(TreeNodeB node) {
        TreeNodeB current = node;
        while (current.left!= null) {
            current = current.left;
        }
        return current;
    }

    public static void main(String[] args) {
        BalancedBinaryTree root = new BalancedBinaryTree();
        root.insertNode(10);
        root.insertNode(20);
        root.insertNode(30);
        root.insertNode(40);
        root.insertNode(50);
        root.insertNode(25);
        root.inorderNode();
        root.deleteNode(25);
    }


}
