package class11;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;

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


    // 使用容器
    public static int maxWidthUseMap(Node head) {
        if (head == null) {
            return 0;
        }
        Queue<Node> queue = new LinkedList<>();
        queue.add(head);
        // key 在 哪一层，value
        HashMap<Node, Integer> levelMap = new HashMap<>();
        levelMap.put(head, 1);
        int curLevel = 1; // 当前你正在统计哪一层的宽度
        int curLevelNodes = 0; // 当前层curLevel层，宽度目前是多少
        int max = 0;
        while (!queue.isEmpty()) {
            Node cur = queue.poll();
            int curNodeLevel = levelMap.get(cur);
            if (cur.left != null) {
                levelMap.put(cur.left, curNodeLevel + 1);
                queue.add(cur.left);
            }
            if (cur.right != null) {
                levelMap.put(cur.right, curNodeLevel + 1);
                queue.add(cur.right);
            }
            if (curNodeLevel == curLevel) {
                curLevelNodes++;
            } else {
                max = Math.max(max, curLevelNodes);
                curLevel++;
                curLevelNodes = 1;
            }
        }
        max = Math.max(max, curLevelNodes);
        return max;
    }

    // 不使用容器
    // curEnd 当层的最后一个节点
    // nextEnd 下一层的最后一个节点
    // curLevelNodes 当层节点的个数
    // 当cur == curEnd时
    // 比较max与curLevelNodes谁是最大值
    // 同时让 curEnd = nextEnd
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

        int i = maxWidthUseMap(n1);
        System.out.println(i);

        System.out.println("====================");
        int maxLevel = 10;
        int maxValue = 20;
        int testTimes = 100000;
        for (int i1 = 0; i1 < testTimes; i1++) {
            Node head = generateRandomBST(maxLevel, maxValue);
            if (maxWidthUseMap(head) != getMaxLevelNum(head)) {
                System.out.println("Oops");
            }
        }
        System.out.println("finish!");
    }

}
