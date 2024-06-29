package algorithm_journey.class037;

import algorithm_journey.utils.MathUtils;
import algorithm_journey.utils.MathUtils.TreeNode;

/**
 * 修剪二叉搜索树
 *
 * @author: Agony
 * @create: 2024/6/27 10:10
 * @describe: 给你二叉搜索树的根节点 root ，同时给定最小边界low 和最大边界 high。
 * 通过修剪二叉搜索树，使得所有节点的值在[low, high]中。
 * 修剪树 不应该 改变保留在树中的元素的相对结构 (即，如果没有被移除，原有的父代子代关系都应当保留)。
 * 可以证明，存在 唯一的答案 。
 * <p>
 * 所以结果应当返回修剪好的二叉搜索树的新的根节点。注意，根节点可能会根据给定的边界发生改变。
 * @link: <a href="https://leetcode.cn/problems/trim-a-binary-search-tree/description/">修剪二叉搜索树</a>
 */
public class Code06_TrimBinarySearchTree {


    public static void main(String[] args) {
        TreeNode head = new TreeNode(1);
        head.left = new TreeNode(0);
        head.right = new TreeNode(2);

        TreeNode ans = f(head, 1, 2);

        MathUtils.bfs(ans);
    }


    // 修剪搜索二叉树
    //
    // 思路
    // 已知搜索二叉树 -> 左树 < 当前值 < 右树
    // 当前值 < 最小值
    // -> 去左树找，返回左树返回的值
    // 当前值 > 最大值
    // -> 去右树找，返回右树返回的值
    // 当前值在这个范围内
    // 当前值的左树连接 左树的返回值
    // 当前值的右树连接 右树的返回值
    // 如果当前值的左树 < 范围，那么左树的左树必然小于这个范围，返回右树返回的结果，head.left 去连接这个结果
    // 如果当前值的右树 > 范围，那么右树的右树必然大于这个范围，返回左树返回的结果，head.right去连接这个结果


    /**
     * 修剪搜索二叉树
     *
     * @param root
     * @param low
     * @param high
     * @return
     */
    public TreeNode trimBST(TreeNode root, int low, int high) {
        if (root == null) {
            return null;
        }
        return f(root, low, high);
    }


    /**
     * @param head 当前节点
     * @param low  最小值
     * @param high 最大值
     * @return 返回修建后的头节点
     */
    public static TreeNode f(TreeNode head, int low, int high) {

        if (head == null) {
            return null;
        }

        // 头节点小于最小值，那么就到右树去找，返回右树返回的值
        if (head.val < low) {
            return f(head.right, low, high);
        }

        // 头节点大于最大值，就从左树去找，返回左树返回的值
        if (head.val > high) {
            return f(head.left, low, high);
        }

        // 头节点位于范围之间
        // 该节点的左树就从左数去找
        // 该节点的右树就从右树去找
        head.left = f(head.left, low, high);
        head.right = f(head.right, low, high);
        return head;
    }
}

















