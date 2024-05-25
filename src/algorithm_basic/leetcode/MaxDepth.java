package algorithm_basic.leetcode;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @Author Agony
 * @Create 2023/8/30 21:06
 * @Version 1.0
 * <a href="https://leetcode.cn/problems/maximum-depth-of-binary-tree/">二叉树的最大深度</a>
 */
public class MaxDepth {


    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() {
        }

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

    public static int maxDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        return process(root).height;
    }

    public static class Info {
        int height;

        public Info(int height) {
            this.height = height;
        }
    }

    public static Info process(TreeNode head) {
        if (head == null) {
            return new Info(0);
        }
        Info leftInfo = process(head.left);
        Info rightInfo = process(head.right);

        int height = Math.max(leftInfo.height, rightInfo.height) + 1;
        return new Info(height);
    }

    public static TreeNode generateBST(int maxLevel, int maxValue) {
        return generate(1, maxLevel, maxValue);
    }

    public static TreeNode generate(int i, int maxLevel, int maxValue) {
        if (i > maxLevel || Math.random() < 0.5) {
            return null;
        }
        TreeNode head = new TreeNode((int) (Math.random() * maxValue));
        head.left = generate(i + 1, maxLevel, maxValue);
        head.right = generate(i + 1, maxLevel, maxValue);
        return head;
    }

    public static Queue<String> preSerialize(TreeNode head) {
        LinkedList<String> queue = new LinkedList<>();
        preProcess(head, queue);
        return queue;
    }

    public static void preProcess(TreeNode head, Queue<String> queue) {
        if (head == null) {
            queue.add(null);
        } else {
            queue.add(String.valueOf(head.val));
            preProcess(head.left, queue);
            preProcess(head.right, queue);
        }
    }

    public static void printQueue(Queue<String> queue) {
        if (queue == null || queue.isEmpty()) {
            return;
        }
        for (String s : queue) {
            if (s == null) {
                System.out.print("#" + " ");
            } else {
                System.out.print(s + " ");
            }
        }
        System.out.println();
    }

    public static void main(String[] args) {
        int maxLevel = 10;
        int maxValue = 50;
        TreeNode head = generateBST(maxLevel, maxValue);
        int depth = maxDepth(head);
        System.out.println(depth);
        Queue<String> queue = preSerialize(head);
        printQueue(queue);
        System.out.println();
    }

}
