package algorithm_journey.class037;

import algorithm_journey.utils.MathUtils.TreeNode;

/**
 * 平衡二叉树
 *
 * @author: Agony
 * @create: 2024/6/25 11:34
 * @describe: 给定一个二叉树，判断它是否是
 * 平衡二叉树
 * @link: <a href="https://leetcode.cn/problems/balanced-binary-tree/description/">平衡二叉树</a>
 */
public class Code04_BalancedBinaryTree {


    public static void main(String[] args) {
        TreeNode head = new TreeNode(3);
        head.left = new TreeNode(9);
        head.right = new TreeNode(20);
        head.right.left = new TreeNode(15);
        head.right.right = new TreeNode(7);
        System.out.println(isBalanced(head));

        head = new TreeNode(1);
        head.right = new TreeNode(2);
        head.left = new TreeNode(2);
        head.left.right = new TreeNode(3);
        head.left.left = new TreeNode(3);
        head.left.left.left = new TreeNode(4);
        head.left.left.right = new TreeNode(4);

        System.out.println(isBalanced(head));
    }


    // 验证平衡二叉树
    // 平衡二叉树 -> 对于每一个节点 左树的高度 和 右树的高度 相差不超过 1
    // 思路
    // 递归判断

    /**
     * 验证平衡二叉树
     *
     * @param root
     * @return
     */
    public static boolean isBalanced(TreeNode root) {
        if (root == null) {
            return true;
        }
        balance = true;
        f(root);
        return balance;
    }

    public static boolean balance;

    /**
     * 返回树的高度
     *
     * @param head 头节点
     * @return 树的高度
     */
    public static int f(TreeNode head) {

        // 优化
        // 如果在某棵树上已经不是平衡二叉树了，就直接返回
        if (!balance || head == null) {
            return 0;
        }
        int left = f(head.left);
        int right = f(head.right);
        if (Math.abs(left - right) > 1) {
            balance = false;
        }
        return Math.max(left, right) + 1;
    }
}
