package algorithm_basic.class12;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

/**
 * @Author Agony
 * @Create 2023/8/27 19:36
 * @Version 1.0
 * 给定一棵二叉树的头节点head，返回这颗二叉树中最大的二叉搜索子树的大小
 */
public class Code05_MaxSubBSTSize {
    public static class Node {
        int value;
        Node left;
        Node right;

        public Node(int value) {
            this.value = value;
        }
    }

    public static int maxSubBSTSize1(Node head) {
        if (head == null) {
            return 0;
        }
        int h = getBSTSize(head);
        if (h != 0) {
            return h;
        }
        return Math.max(maxSubBSTSize1(head.left), maxSubBSTSize1(head.right));
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

    public static int maxSubBSTSize2(Node head) {
        if (head == null) {
            return 0;
        }
        return process(head).subBSTSize;
    }

    // 信息
    // 大小 => 二叉搜索树的节点个数
    // isBST
    // max
    // min
    //
    public static class Info {
        boolean isBST;
        int max;
        int min;
        int subBSTSize;

        public Info(boolean isBST, int max, int min, int subBSTSize) {
            this.isBST = isBST;
            this.max = max;
            this.min = min;
            this.subBSTSize = subBSTSize;
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
        if (leftInfo != null) {
            max = Math.max(max, leftInfo.max);
            min = Math.min(max, leftInfo.min);
        }
        if (rightInfo != null) {
            max = Math.max(max, rightInfo.max);
            min = Math.min(min, rightInfo.min);
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

        // 左树的最大二叉字数大小
        int p1 = 0;
        if (leftInfo != null) {
            p1 = leftInfo.subBSTSize;
        }
        // 右树的最大二叉字数大小
        int p2 = 0;
        if (rightInfo != null) {
            p2 = rightInfo.subBSTSize;
        }
        // 左右是，自己也是
        int p3 = 0;
        boolean leftBST = leftInfo == null || leftInfo.isBST;
        boolean rightBST = rightInfo == null || rightInfo.isBST;
        if (leftBST && rightBST && isBST) {
            int leftSubBSTSize = leftInfo == null ? 0 : leftInfo.subBSTSize;
            int rightSubBSTSize = rightInfo == null ? 0 : rightInfo.subBSTSize;
            p3 = leftSubBSTSize + rightSubBSTSize + 1;
        }
        int subBSTSize = Math.max(p1, Math.max(p2, p3));
        return new Info(isBST, max, min, subBSTSize);
    }

    // for algorithm_basic.test
    public static Node generateRandomBST(int maxLevel, int maxValue) {
        return generate(1, maxLevel, maxValue);
    }

    // for algorithm_basic.test
    public static Node generate(int level, int maxLevel, int maxValue) {
        if (level > maxLevel || Math.random() < 0.5) {
            return null;
        }
        Node head = new Node((int) (Math.random() * maxValue));
        head.left = generate(level + 1, maxLevel, maxValue);
        head.right = generate(level + 1, maxLevel, maxValue);
        return head;
    }

    public static Queue<String> preSerialize(Node head) {
        LinkedList<String> queue = new LinkedList<>();
        preProcess(head, queue);
        return queue;
    }

    public static void preProcess(Node head, Queue<String> queue) {
        if (head == null) {
            queue.add(null);
        } else {
            queue.add(String.valueOf(head.value));
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
        int maxLevel = 4;
        int maxValue = 100;
        int testTimes = 1000000;
        for (int i = 0; i < testTimes; i++) {
            Node head = generateRandomBST(maxLevel, maxValue);
            if (maxSubBSTSize1(head) != maxSubBSTSize2(head)) {
                System.out.println("Oops!");
                Queue<String> queue = preSerialize(head);
                printQueue(queue);
                break;
            }
        }
        System.out.println("finish!");
    }
}
