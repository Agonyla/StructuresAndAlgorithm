package algorithm_journey.class037;

import algorithm_journey.utils.MathUtils.TreeNode;

/**
 * 二叉树的最近公共祖先
 *
 * @author: Agony
 * @create: 2024/6/21 14:05
 * @describe: 给定一个二叉树, 找到该树中两个指定节点的最近公共祖先。
 * <p>
 * 百度百科中最近公共祖先的定义为：“对于有根树 T 的两个节点 p、q，最近公共祖先表示为一个节点 x，
 * 满足 x 是 p、q 的祖先且 x 的深度尽可能大（一个节点也可以是它自己的祖先）。”
 * @link: <a href="https://leetcode.cn/problems/lowest-common-ancestor-of-a-binary-tree/description/">二叉树的最近公共祖先</a>
 */
public class Code01_LowestCommonAncestor {

    public static void main(String[] args) {

    }


    // todo

    // 普通二叉树上寻找两个节点的最近公共祖先
    //
    // q，p 两个节点
    // 1. 两个节点存在包含关系
    // -> 遇到其中一个节点就直接返回，不需要往下递归了
    // 2. 两个节点属于两棵树
    // -> 当一个节点 它的左树返回了 p，右树返回了 q，那么就直接返回该节点

    /**
     * 普通二叉树上寻找两个节点的最近公共祖先
     *
     * @param root
     * @param p
     * @param q
     * @return
     */
    public static TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {

        if (root == null || root == p || root == q) {
            return root;
        }
        TreeNode left = lowestCommonAncestor(root.left, p, q);
        TreeNode right = lowestCommonAncestor(root.right, p, q);

        // 左树搜到了其中一个，右树搜到了另一个
        if (left != null && right != null) {
            return root;
        }
        // 都没搜到
        if (left == null && right == null) {
            return null;
        }
        // 表示左树搜到了其中一个节点，右树没搜到 -> 先搜到谁就返回谁，不需要继续往下搜
        // 或者右树搜到了其中一个节点，左树没搜到 -> 先搜到谁就返回谁，不需要继续往下搜
        return left != null ? left : right;
    }
}



