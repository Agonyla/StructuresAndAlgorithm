package class13;

import java.util.ArrayList;

/**
 * @Author Agony
 * @Create 2023/8/29 20:54
 * @Version 1.0
 * 给定一棵二叉树的头节点head，返回这颗二叉树中最大的二叉搜索子树的头节点
 */
public class Code02_MaxSubBSTHead {
    public static class Node {
        int value;
        Node left;
        Node right;

        public Node(int value) {
            this.value = value;
        }
    }

    public static Node maxSubBSTHead1(Node head) {
        if (head == null) {
            return null;
        }
        if (getBSTSize(head) != 0) {
            return head;
        }
        Node leftAns = maxSubBSTHead1(head.left);
        Node rightAns = maxSubBSTHead1(head.right);
        return getBSTSize(leftAns) >= getBSTSize(rightAns) ? leftAns : rightAns;
    }

    public static int getBSTSize(Node head) {
        if (head == null) {
            return 0;
        }
        ArrayList<Node> arr = new ArrayList<>();
        in(head, arr);
        for (int i = 1; i < arr.size(); i++) {
            if (arr.get(i).value <= arr.get(i - 1).value) {
                return 0;
            }
        }
        return arr.size();
    }

    public static void in(Node head, ArrayList<Node> arr) {
        if (head == null) {
            return;
        }
        in(head.left, arr);
        arr.add(head);
        in(head.right, arr);
    }

    public static Node maxSubBSTHead2(Node head) {
        if (head == null) {
            return null;
        }
        return process(head).head;
    }

    public static class Info {
        boolean isBST;
        int max;
        int min;
        Node head;
        int subMaxBSTSize;

        public Info(boolean isBST, int max, int min, Node head, int subMaxBSTSize) {
            this.isBST = isBST;
            this.max = max;
            this.min = min;
            this.head = head;
            this.subMaxBSTSize = subMaxBSTSize;
        }
    }

    public static Info process(Node head) {
        if (head == null) {
            return null;
        }
        Info leftInfo = process(head.left);
        Info rightInfo = process(head.right);
        int max = head.value;
        int min = head.value;
        Node x = null;
        int subMaxBSTSize = 0;
        if (leftInfo != null) {
            max = Math.max(max, leftInfo.max);
            min = Math.min(max, leftInfo.min);
            x = leftInfo.head;
            subMaxBSTSize = leftInfo.subMaxBSTSize;
        }
        if (rightInfo != null) {
            max = Math.max(max, rightInfo.max);
            min = Math.min(min, rightInfo.min);
            if (rightInfo.subMaxBSTSize > subMaxBSTSize) {
                x = rightInfo.head;
                subMaxBSTSize = rightInfo.subMaxBSTSize;
            }
        }
        boolean isBST = true;
        if (leftInfo != null && !leftInfo.isBST) {
            isBST = false;
        }
        if (leftInfo != null && leftInfo.max >= head.value) {
            isBST = false;
        }
        if (rightInfo != null && !rightInfo.isBST) {
            isBST = false;
        }
        if (rightInfo != null && rightInfo.min <= head.value) {
            isBST = false;
        }

        boolean leftBST = leftInfo == null || leftInfo.isBST;
        boolean rightBST = rightInfo == null || rightInfo.isBST;
        if (leftBST && rightBST && isBST) {
            int leftSubBSTSize = leftInfo == null ? 0 : leftInfo.subMaxBSTSize;
            int rightSubBSTSize = rightInfo == null ? 0 : rightInfo.subMaxBSTSize;
            subMaxBSTSize = leftSubBSTSize + rightSubBSTSize + 1;
            x = head;
        }
        return new Info(isBST, max, min, x, subMaxBSTSize);
    }


    // for test
    public static Node generateRandomBST(int maxLevel, int maxValue) {
        return generate(1, maxLevel, maxValue);
    }

    // for test
    public static Node generate(int level, int maxLevel, int maxValue) {
        if (level > maxLevel || Math.random() < 0.5) {
            return null;
        }
        Node head = new Node((int) (Math.random() * maxValue));
        head.left = generate(level + 1, maxLevel, maxValue);
        head.right = generate(level + 1, maxLevel, maxValue);
        return head;
    }

    public static void main(String[] args) {
        int maxLevel = 4;
        int maxValue = 100;
        int testTimes = 1000000;
        for (int i = 0; i < testTimes; i++) {
            Node head = generateRandomBST(maxLevel, maxValue);
            if (maxSubBSTHead1(head) != maxSubBSTHead2(head)) {
                System.out.println("Oops!");
            }
        }
        System.out.println("finish!");
    }
}
