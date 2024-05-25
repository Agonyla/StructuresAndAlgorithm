package algorithm_basic.leetcode;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @Author Agony
 * @Create 2023/8/31 14:32
 * @Version 1.0
 * <a href="https://leetcode.cn/problems/binary-tree-maximum-path-sum/">二叉树中的最大路径和</a>
 */
public class MaxPathSum {

    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        public TreeNode(int val) {
            this.val = val;
        }
    }

    // 在递归过程中，我们需要记录的信息
    public static class Info1 {
        int maxPathSum; // 经过当前节点的最大路径和
        int maxSumFromRoot; // 从当前节点出发的最大路径和

        Info1(int maxPathSum, int maxSumFromRoot) {
            this.maxPathSum = maxPathSum;
            this.maxSumFromRoot = maxSumFromRoot;
        }
    }

    public static int maxPathSum1(TreeNode root) {
        return dfs(root).maxPathSum;
    }

    public static Info1 dfs(TreeNode node) {
        if (node == null) {
            return new Info1(Integer.MIN_VALUE, 0);
        }

        Info1 left = dfs(node.left);
        Info1 right = dfs(node.right);

        // 计算从当前节点出发的最大路径和
        int maxSumFromRoot = Math.max(0, Math.max(left.maxSumFromRoot, right.maxSumFromRoot)) + node.val;

        // 计算经过当前节点的最大路径和
        int maxPathSumThroughRoot = Math.max(left.maxSumFromRoot, 0) + Math.max(right.maxSumFromRoot, 0) + node.val;

        // 计算全局的最大路径和
        int maxPathSum = Math.max(Math.max(left.maxPathSum, right.maxPathSum), maxPathSumThroughRoot);

        return new Info1(maxPathSum, maxSumFromRoot);
    }


    public static int maxPathSum2(TreeNode root) {
        if (root == null) {
            return Integer.MIN_VALUE;
        }
        return process2(root).maxSize;
    }

    public static class Info {
        int maxSize;
        int sizeWithHead;

        public Info(int maxSize, int sizeWithHead) {
            this.maxSize = maxSize;
            this.sizeWithHead = sizeWithHead;
        }
    }

    public static Info process2(TreeNode head) {
        if (head == null) {
            return new Info(Integer.MIN_VALUE, Integer.MIN_VALUE);
        }
        Info leftInfo = process2(head.left);
        Info rightInfo = process2(head.right);
        int leftSize = Math.max(leftInfo.sizeWithHead, 0);
        int rightSize = Math.max(rightInfo.sizeWithHead, 0);

        // 沿一条路线的值
        int sizeWithHead = Math.max(leftSize, rightSize) + head.val;
        // 记录所有递归中的最大值
        int maxSize = Math.max(leftSize + rightSize + head.val, Math.max(leftInfo.maxSize, rightInfo.maxSize));
        return new Info(maxSize, sizeWithHead);
    }


    // for algorithm_basic.test
    public static TreeNode generateRandomBST(int maxLevel, int maxValue) {
        return generate(1, maxLevel, maxValue);
    }

    // for algorithm_basic.test
    public static TreeNode generate(int level, int maxLevel, int maxValue) {
        if (level > maxLevel || Math.random() < 0.5) {
            return null;
        }
        TreeNode head = new TreeNode((int) (Math.random() * maxValue) - (int) (Math.random() * maxValue));
        head.left = generate(level + 1, maxLevel, maxValue);
        head.right = generate(level + 1, maxLevel, maxValue);
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
        int maxLevel = 3;
        int maxValue = 10;
        int testTimes = 30000;
        for (int i = 0; i < testTimes; i++) {
            TreeNode head = generateRandomBST(maxLevel, maxValue);
            if (maxPathSum2(head) != maxPathSum1(head)) {
                System.out.println("sum2 = " + maxPathSum2(head));
                System.out.println("sum1 = " + maxPathSum1(head));
                Queue<String> queue = preSerialize(head);
                printQueue(queue);
            }
        }
        System.out.println("finish!");
    }
}
