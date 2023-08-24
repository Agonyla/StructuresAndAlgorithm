package class11;

import java.util.LinkedList;

/**
 * @Author Agony
 * @Create 2023/8/24 21:32
 * @Version 1.0
 * 求二叉树最宽的层有多少个节点
 * 按层遍历
 */
public class Code05_TreeMaxWidth {
    public static class Node {
        int value;
        Node left;
        Node right;

        public Node(int value) {
            this.value = value;
        }
    }

    // 不使用容器
    public static int getMaxLevelNum(Node head) {
        if (head == null) {
            return 0;
        }
        LinkedList<Node> queue = new LinkedList<>();
        queue.add(head);
        Node cur = null;
        Node curEnd = head;
        Node nextEnd = null;
        int max = 0;
        int curLevelNodes = 0;
        while (!queue.isEmpty()) {
            cur = queue.poll();
            curLevelNodes++;
            if (cur.left != null) {
                queue.add(cur.left);
                nextEnd = cur.left;
            }
            if (cur.right != null) {
                queue.add(cur.right);
                nextEnd = cur.right;
            }
            if (cur == curEnd) {
                max = Math.max(max, curLevelNodes);
                curLevelNodes = 0;
                curEnd = nextEnd;
            }
        }
        return max;
    }

    public static void main(String[] args) {
        Node n1 = new Node(1);
        Node n2 = new Node(2);
        Node n3 = new Node(3);
        Node n4 = new Node(4);
        Node n5 = new Node(5);
        Node n6 = new Node(6);
        n1.left = n2;
        n1.right = n3;
        n2.left = n4;
        n2.right = n5;
        n3.right = n6;

        int maxLevelNum = getMaxLevelNum(n1);
        System.out.println(maxLevelNum);
    }

}
