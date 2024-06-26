package algorithm_journey.class036;

import algorithm_journey.utils.MathUtils.TreeNode;

/**
 * 完全二叉树的节点个数
 *
 * @author: Agony
 * @create: 2024/6/20 14:48
 * @describe: 给你一棵 完全二叉树 的根节点 root ，求出该树的节点个数。
 * <p>
 * 完全二叉树 的定义如下：在完全二叉树中，除了最底层节点可能没填满外，其余每层节点数都达到最大值，
 * 并且最下面一层的节点都集中在该层最左边的若干位置。
 * 若最底层为第 h 层，则该层包含 1~ 2h 个节点。
 * @link: <a href="https://leetcode.cn/problems/count-complete-tree-nodes/description/">完全二叉树的节点个数</a>
 */
public class Code09_CountCompleteTreeNodes {


    public static void main(String[] args) {
        TreeNode head = new TreeNode(1);
        head.left = new TreeNode(2);
        head.right = new TreeNode(3);
        head.left.left = new TreeNode(4);
        head.left.right = new TreeNode(5);
        head.right.left = new TreeNode(6);

        System.out.println(countNodes(head));
    }

    // 求完全二叉树的节点个数
    //
    // 思路
    // 递归
    // 左数的左边界求出总高度
    // 右树左边界能不能找扎到最底，能 -> 左数是满树。
    // 右侧递归调用
    //
    // 不能 -> 右树是满的 高度 -1
    // 左侧递归调用


    /**
     * 求完全二叉树的节点个数
     *
     * @param root
     * @return
     */
    public static int countNodes(TreeNode root) {
        if (root == null) {
            return 0;
        }
        return getAllNum(root);
    }

    public static int getAllNum(TreeNode head) {
        if (head == null) {
            return 0;

        }
        // 左树扎到底的高度
        int leftHeight = f(head.left);
        // 右树扎到底的高度
        int rightHeight = f(head.right);
        // 说明右树是满树
        // (1 << rightHeight) 表示 头节点和右树的节点
        if (leftHeight > rightHeight) {
            return (1 << rightHeight) + getAllNum(head.left);
        } else { // 说明左数是满树
            return (1 << leftHeight) + getAllNum(head.right);
        }

    }


    /**
     * 求左树扎到底的高度 即 树的高度
     *
     * @param head
     * @return
     */
    public static int f(TreeNode head) {
        if (head == null) {
            return 0;
        }
        return f(head.left) + 1;
    }
}














