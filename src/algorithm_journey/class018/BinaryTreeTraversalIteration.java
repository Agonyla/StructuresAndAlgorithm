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


        System.out.println("=======先序打印=======");
        preOrder(head);
        List<Integer> list = preorderTraversal(head);
        list.forEach(val -> System.out.print(val + " "));
        System.out.println();


        System.out.println("=======中序打印=======");
        inOrder(head);
        list = inorderTraversal(head);
        list.forEach(val -> System.out.print(val + " "));
        System.out.println();


        System.out.println("=======后序打印=======");
        afterOrderTwoStacks(head);
        afterOrderOneStack(head);
        list = postorderTraversal(head);
        list.forEach(val -> System.out.print(val + " "));
        System.out.println();
        list = postorderTraversalOneStack(head);
        list.forEach(val -> System.out.print(val + " "));
        System.out.println();

        System.out.println("------------");

        list = postorder(head);
        list.forEach(val -> System.out.print(val + " "));
        System.out.println();
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

    /**
     * 栈实现中序遍历 - 非递归
     * 一直让左边界进栈，直到head==null
     * 弹出节点打印，从该节点的右树开始，重复1 让该节点的右树左边界进栈 (head = head.right...)
     * 直到没有子树并且栈空
     *
     * @param head 头节点
     */
    public static void inOrder(TreeNode head) {
        if (head == null) {
            return;
        }
        Stack<TreeNode> stack = new Stack<>();
        while (!stack.empty() || head != null) {
            if (head != null) {
                stack.push(head);
                head = head.left;
            } else {
                head = stack.pop();
                System.out.print(head.val + " ");
                head = head.right;
            }
        }
        System.out.println();
    }


    /**
     * 二叉树的中序遍历
     *
     * @param root 头节点
     * @return 中序遍历的链表
     * @describe: 给定一个二叉树的根节点 root ，返回 它的 中序 遍历 。
     * @link: <a href="https://leetcode.cn/problems/binary-tree-inorder-traversal/description/">二叉树的中序遍历</a>
     */
    public static List<Integer> inorderTraversal(TreeNode root) {
        ArrayList<Integer> ans = new ArrayList<>();
        if (root == null) {
            return ans;
        }
        Stack<TreeNode> stack = new Stack<>();
        while (!stack.empty() || root != null) {
            if (root != null) {
                stack.push(root);
                root = root.left;
            } else {
                root = stack.pop();
                ans.add(root.val);
                root = root.right;
            }
        }
        return ans;
    }


    /**
     * 栈实现后序遍历 - 非递归
     * 两个栈
     * 先序遍历 -> 中左右
     * 实现 中右左
     * 再实现 左右中
     *
     * @param head 头节点
     */
    public static void afterOrderTwoStacks(TreeNode head) {
        if (head == null) {
            return;
        }
        Stack<TreeNode> stack = new Stack<>();
        Stack<TreeNode> ans = new Stack<>();
        stack.push(head);
        while (!stack.empty()) {
            head = stack.pop();
            ans.push(head);
            if (head.left != null) {
                stack.push(head.left);
            }
            if (head.right != null) {
                stack.push(head.right);
            }
        }
        while (!ans.empty()) {
            System.out.print(ans.pop().val + " ");
        }
        System.out.println();
    }

    /**
     * 栈实现后序遍历 - 非递归
     * 一个栈
     * <p>
     * 如果始终没有打印过节点，h就一直是头节点
     * 一旦打印过节点，h就指向该打印节点（栈顶节点）
     * 之后h的含义 : 上一次打印的节点
     *
     * @param head 头节点
     */
    public static void afterOrderOneStack(TreeNode head) {
        if (head == null) {
            return;
        }
        TreeNode cur = null;
        Stack<TreeNode> stack = new Stack<>();
        stack.push(head);
        // 用于指向stack的栈顶节点
        while (!stack.empty()) {
            cur = stack.peek();

            // head != cur.left 表示左树没处理过
            // head != cur.right 表示右树没处理过
            // 为什么要判断 head == cur.right ???
            // 因为当 head == cur.right 时表示右树都处理完了
            // 先处理左数再处理右树，此时子树全都处理完了
            // 如果不判断的话，就会重新把左树再处理一遍
            if (cur.left != null && head != cur.left && head != cur.right) {
                // 有左树且左树没处理过
                stack.push(cur.left);


                // 这里 head ！= cur.left 不需要判定
                // 此时head == cur.left 表示左树都处理完了
                // head != cur.right 表示右树还没处理
            } else if (cur.right != null && head != cur.right) {
                // 有右树且右树没处理过
                stack.push(cur.right);
            } else {
                // 左右树都没了 或者 都处理过了
                System.out.print(cur.val + " ");
                head = stack.pop();
            }
        }
        System.out.println();
    }


    /**
     * 二叉树的后序遍历 - 两个栈
     *
     * @param root 头节点
     * @return 后序遍历的链表
     * @describe: 给你一棵二叉树的根节点 root ，返回其节点值的 后序遍历 。
     * @link: <a href="https://leetcode.cn/problems/binary-tree-postorder-traversal/description/">二叉树的后序遍历</a>
     */
    public static List<Integer> postorderTraversal(TreeNode root) {
        if (root == null) {
            return new ArrayList<>();
        }
        ArrayList<Integer> ans = new ArrayList<>();
        Stack<TreeNode> stack = new Stack<>();
        Stack<TreeNode> collect = new Stack<>();
        stack.push(root);
        while (!stack.empty()) {
            root = stack.pop();
            collect.push(root);
            if (root.left != null) {
                stack.push(root.left);
            }
            if (root.right != null) {
                stack.push(root.right);
            }
        }
        while (!collect.empty()) {
            ans.add(collect.pop().val);
        }
        return ans;
    }


    /**
     * 二叉树的后序遍历 - 一个栈
     *
     * @param root 头节点
     * @return 后序遍历的链表
     * @describe: 给你一棵二叉树的根节点 root ，返回其节点值的 后序遍历 。
     * @link: <a href="https://leetcode.cn/problems/binary-tree-postorder-traversal/description/">二叉树的后序遍历</a>
     */
    public static List<Integer> postorderTraversalOneStack(TreeNode root) {
        if (root == null) {
            return new ArrayList<>();
        }
        ArrayList<Integer> ans = new ArrayList<>();
        Stack<TreeNode> stack = new Stack<>();
        TreeNode cur = null;
        stack.push(root);
        while (!stack.empty()) {
            cur = stack.peek();
            if (cur.left != null && root != cur.left && root != cur.right) {
                stack.push(cur.left);
            } else if (cur.right != null && root != cur.right) {
                stack.push(cur.right);
            } else {
                ans.add(cur.val);
                root = stack.pop();
            }
        }
        return ans;
    }

    /**
     * 后序遍历 - 递归
     *
     * @param root 头节点
     * @return 列表
     */
    public static List<Integer> postorder(TreeNode root) {
        ArrayList<Integer> ans = new ArrayList<>();
        post(root, ans);
        return ans;
    }

    public static void post(TreeNode root, List<Integer> list) {
        if (root == null) {
            return;
        }
        post(root.left, list);
        post(root.right, list);
        list.add(root.val);
    }
}




























