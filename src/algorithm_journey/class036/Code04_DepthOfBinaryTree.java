package algorithm_journey.class036;

import algorithm_journey.utils.MathUtils.TreeNode;

/**
 * 求二叉树的最大、最小深度
 *
 * @author: Agony
 * @create: 2024/6/20 10:44
 * @describe:
 * @link:
 */
public class Code04_DepthOfBinaryTree {


    public static void main(String[] args) {
        TreeNode head = new TreeNode(3);
        head.left = new TreeNode(9);
        head.right = new TreeNode(20);
        head.right.left = new TreeNode(15);
        head.right.right = new TreeNode(7);

        System.out.println(maxDepth(head));


        head = new TreeNode(2);
        head.right = new TreeNode(3);
        head.right.right = new TreeNode(4);
        head.right.right.right = new TreeNode(5);
        head.right.right.right.right = new TreeNode(6);


        System.out.println(minDepth(head));
    }


    // 求二叉树的最大、最小深度
    //
    // 最大深度，递归求最大深度直接返回
    // 最小深度，该节点有叶节点存在就算有高度，直到没有叶节点了才停止


    /**
     * 二叉树的最大深度
     * <a href="https://leetcode.cn/problems/maximum-depth-of-binary-tree/description/">二叉树的最大深度</a>
     * 给定一个二叉树 root ，返回其最大深度。
     * <p>
     * 二叉树的 最大深度 是指从根节点到最远叶子节点的最长路径上的节点数。
     *
     * @param root
     * @return
     */
    public static int maxDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int left = maxDepth(root.left);
        int right = maxDepth(root.right);
        return Math.max(left, right) + 1;

        //
        // return root == null ? 0 : Math.max(maxDepth(root.left), maxDepth(root.right)) + 1;
    }


    /**
     * 二叉树的最小深度
     * <a href="https://leetcode.cn/problems/minimum-depth-of-binary-tree/description/">二叉树的最小深度</a>
     * 给定一个二叉树，找出其最小深度。
     * <p>
     * 最小深度是从根节点到最近叶子节点的最短路径上的节点数量。
     *
     * @param root
     * @return
     */
    public static int minDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        if (root.left == null && root.right == null) {
            return 1;
        }
        int left = Integer.MAX_VALUE;
        int right = Integer.MAX_VALUE;
        if (root.left != null) {
            left = minDepth(root.left);
        }
        if (root.right != null) {
            right = minDepth(root.right);
        }
        return Math.min(left, right) + 1;
    }


}
