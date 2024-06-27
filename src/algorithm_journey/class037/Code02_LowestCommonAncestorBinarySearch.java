package algorithm_journey.class037;

import algorithm_journey.utils.MathUtils.TreeNode;

/**
 * 二叉搜索树的最近公共祖先
 *
 * @author: Agony
 * @create: 2024/6/24 19:51
 * @describe: 给定一个二叉搜索树, 找到该树中两个指定节点的最近公共祖先。
 * <p>
 * 百度百科中最近公共祖先的定义为：“对于有根树 T 的两个结点 p、q，最近公共祖先表示为一个结点 x，满足 x 是 p、q 的祖先且 x 的深度尽可能大（一个节点也可以是它自己的祖先）。”
 * @link: <a href="https://leetcode.cn/problems/lowest-common-ancestor-of-a-binary-search-tree/">二叉搜索树的最近公共祖先</a>
 */
public class Code02_LowestCommonAncestorBinarySearch {


    public static void main(String[] args) {

    }


    // 搜索二叉树上寻找两个节点的最近公共祖先
    //
    // 搜索二叉树 -> 树上没有重复的值 且 每一个节点左边整体的结果 < 头 < 右边整体的结果
    // 思路
    // 假设两个数 p < q
    // 1. 当前节点 < p -> 去右树找
    // 2. 当前节点 > q -> 去左数找
    // 3. p < 当前节点 < q -> 但前节点就是公共祖先
    // 4. 先遇到谁，谁就是公共祖先 --> 如果一个在左一个在右那么肯定会先出现第三种情况


    /**
     * 搜索二叉树上寻找两个节点的最近公共祖先
     * 递归实现
     *
     * @param root
     * @param p
     * @param q
     * @return
     */
    public static TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        return f(root, p, q);
    }


    /**
     * @param head
     * @param p
     * @param q
     * @return
     */
    public static TreeNode f(TreeNode head, TreeNode p, TreeNode q) {
        if (head == null || head == p || head == q) {
            return head;
        }
        if (head.val < Math.min(p.val, q.val)) {
            return f(head.right, p, q);
        }
        if (head.val > Math.max(p.val, q.val)) {
            return f(head.left, p, q);
        }
        // 不需要这个
        // if (Math.min(p.val, q.val) < head.val && Math.max(p.val, q.val) > head.val) {
        //     return head;
        // }
        return head;
    }


    /**
     * 搜索二叉树上寻找两个节点的最近公共祖先
     * 非递归实现
     * 时间复杂度是一样的 嘿嘿😁
     *
     * @param head
     * @param p
     * @param q
     * @return
     */
    public static TreeNode lowestCommonAncestorBetter(TreeNode head, TreeNode p, TreeNode q) {


        // head 一直都没有 p 或 q
        while (head.val != p.val && head.val != q.val) {

            // head 在两者之间
            if (Math.min(p.val, q.val) < head.val && Math.max(p.val, q.val) > head.val) {
                return head;
            }
            // 两者最小值大于head就往右边遍历，否则就往左边遍历
            head = head.val < Math.min(p.val, q.val) ? head.right : head.left;
        }
        return head;
    }
}
















