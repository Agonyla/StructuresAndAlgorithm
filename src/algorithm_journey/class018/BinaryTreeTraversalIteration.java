package algorithm_journey.class018;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * @author Agony
 * @create 2024/5/28 11:01
 * <p>
 * 不用递归，用迭代的方式实现二叉树的三序遍历
 */
public class BinaryTreeTraversalIteration {


    public static void main(String[] args) {
        TreeNode head = new TreeNode(1);
        head.left = new TreeNode(2);
        head.right = new TreeNode(3);
        head.left.left = new TreeNode(4);
        head.left.right = new TreeNode(5);
        head.right.left = new TreeNode(6);
        head.right.right = new TreeNode(7);

        preOrder(head);

        List<Integer> list = preorderTraversal(head);
        list.forEach(val -> System.out.print(val + " "));

    }

    public static class TreeNode {
        public int val;
        public TreeNode left;
        public TreeNode right;

        public TreeNode(int v) {
            val = v;
        }
    }

    /**
     * 使用栈实现先序遍历 - 非递归
     * 先把头节点放入，然后弹出打印
     * 然后放入当前节点的右节点，再放入左节点 (栈的结构)
     *
     * @param head 头节点
     */
    public static void preOrder(TreeNode head) {
        if (head == null) {
            return;
        }
        Stack<TreeNode> stack = new Stack<>();
        stack.push(head);
        while (!stack.isEmpty()) {
            head = stack.pop();
            System.out.print(head.val + " ");
            if (head.right != null) {
                stack.push(head.right);
            }
            if (head.left != null) {
                stack.push(head.left);
            }
        }
        System.out.println();
    }


    /**
     * 二叉树的先序遍历
     *
     * @param root 头节点
     * @return 先序遍历的链表
     * @describe: 给你二叉树的根节点 root ，返回它节点值的 前序 遍历
     * @link: <a href="https://leetcode.cn/problems/binary-tree-preorder-traversal/">二叉树的先序遍历</a>
     */
    public static List<Integer> preorderTraversal(TreeNode root) {
        ArrayList<Integer> ans = new ArrayList<>();
        if (root == null) {
            return ans;
        }
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);
        while (!stack.empty()) {
            root = stack.pop();
            ans.add(root.val);
            if (root.right != null) {
                stack.push(root.right);
            }
            if (root.left != null) {
                stack.push(root.left);
            }
        }
        return ans;
    }

}





















