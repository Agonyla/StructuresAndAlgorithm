package algorithm_basic.class11;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * @Author Agony
 * @Create 2023/8/24 21:29
 * @Version 1.0
 * <a href="https://leetcode.com/problems/encode-n-ary-tree-to-binary-tree">将 N 叉树编码为二叉树</a>
 */
public class Code03_EncodeNaryTreeToBinaryTree {

    public static class Node {
        public int val;
        public List<Node> children;

        public Node() {
        }

        public Node(int _val) {
            val = _val;
        }

        public Node(int _val, List<Node> _children) {
            val = _val;
            children = _children;
        }
    }

    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        public TreeNode(int val) {
            this.val = val;
        }
    }

    // 把该节点的子节点放在左树的右边界
    public static TreeNode encode(Node head) {
        if (head == null) {
            return null;
        }
        TreeNode treeHead = new TreeNode(head.val);
        treeHead.left = en(head.children);
        return treeHead;
    }

    public static TreeNode en(List<Node> children) {
        if (children == null) {
            return null;
        }
        TreeNode head = null;
        TreeNode cur = null;
        for (Node child : children) {
            TreeNode node = new TreeNode(child.val);
            if (head == null) {
                head = node;
            } else {
                cur.right = node;
            }
            cur = node;
            cur.left = en(child.children);
        }
        return head;
    }

    //
    public static Node decode(TreeNode head) {
        if (head == null) {
            return null;
        }

        return new Node(head.val, de(head.left));
    }

    public static List<Node> de(TreeNode head) {
        ArrayList<Node> children = new ArrayList<>();
        while (head != null) {
            Node cur = new Node(head.val, de(head.left));
            children.add(cur);
            head = head.right;
        }
        return children;
    }

    // 先序打印二叉树
    public static void printBT(TreeNode head) {
        if (head == null) {
            return;
        }
        System.out.print(head.val + " ");
        printBT(head.left);
        printBT(head.right);
    }

    // 打印N-ary tree
    public static void printNaryTree(Node head) {
        if (head == null) {
            return;
        }
        System.out.print(head.val + " ");
        if (head.children == null) {
            return;
        }
        for (Node child : head.children) {
            printNaryTree(child);
        }
    }

    public static void main(String[] args) {
        Node n1 = new Node(1);
        Node n2 = new Node(2);
        Node n3 = new Node(3);
        Node n4 = new Node(4);
        Node n5 = new Node(5);
        Node n6 = new Node(6);
        LinkedList<Node> nodes1 = new LinkedList<>();
        LinkedList<Node> nodes2 = new LinkedList<>();
        nodes1.add(n3);
        nodes1.add(n2);
        nodes1.add(n4);
        nodes2.add(n5);
        nodes2.add(n6);
        n1.children = nodes1;
        n3.children = nodes2;

        printNaryTree(n1);
        System.out.println();

        TreeNode treeN1 = encode(n1);
        printBT(treeN1);
        System.out.println();

        Node n11 = decode(treeN1);
        printNaryTree(n11);
    }
}
