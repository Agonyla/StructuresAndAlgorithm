package algorithm_basic.class09;

import java.util.Stack;

/**
 * @Author Agony
 * @Create 2023/8/18 21:55
 * @Version 1.0
 * 判断链表是否为回文结构
 */
public class Code02_IsPalindromeList {

    public static class Node {
        public int value;
        public Node next;

        public Node(int value) {
            this.value = value;
        }
    }

    // 使用容器stack
    public static boolean isPalindromeList1(Node head) {
        if (head == null) {
            return false;
        }
        Node cur = head;
        Stack<Node> nodes = new Stack<>();
        while (cur != null) {
            nodes.push(cur);
            cur = cur.next;
        }
        cur = head;
        while (cur != null) {
            if (cur.value != nodes.pop().value) {
                return false;
            }
            cur = cur.next;
        }
        return true;
    }

    // 不使用容器
    public static boolean isPalindromeList2(Node head) {
        if (head == null) {
            return false;
        }
        // 找到中点位置  slow就是中点位置
        Node slow = head;
        Node fast = head;
        while (fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        // 反转中点之后的链表  cur就是最后节点位置
        Node midHead = slow;
        Node cur = null;
        Node next = null;
        while (midHead != null) {
            next = midHead.next;
            midHead.next = cur;
            cur = midHead;
            midHead = next;
        }
        Node lastNode = cur;

        // 开始遍历
        Node n1 = head;
        boolean res = true;
        while (n1 != null && lastNode != null) {
            if (n1.value != lastNode.value) {
                res = false;
                break;
            }
            n1 = n1.next;
            lastNode = lastNode.next;
        }

        // 还原链表
        Node n2 = null; // cur
        Node n3 = null; // next
        lastNode = cur;
        while (lastNode != null) {
            n3 = lastNode.next;
            lastNode.next = n2;
            n2 = lastNode;
            lastNode = n3;
        }

        return res;
    }

    public static Node generateLists(int maxLen, int maxValue) {
        int size = (int) (Math.random() * maxLen) + 1;
        Node head = new Node((int) (Math.random() * maxValue) + 1);
        Node cur = head;
        for (int i = 0; i < size - 1; i++) {
            cur.next = new Node((int) (Math.random() * maxValue) + 1);
            cur = cur.next;
        }
        return head;
    }

    public static void printList(Node head) {
        if (head == null) {
            return;
        }
        Node cur = head;
        System.out.print(cur.value);
        while (cur.next != null) {
            System.out.print("->" + cur.value);
            cur = cur.next;
        }
        System.out.println();
    }

    public static void main(String[] args) {
        int maxLen = 10;
        int maxValue = 20;
        Node head = generateLists(maxLen, maxValue);
        printList(head);

        System.out.println("=====================");
        Node n1 = new Node(1);
        Node n2 = new Node(2);
        Node n3 = new Node(1);
        Node n4 = new Node(2);
        Node n5 = new Node(1);
        n1.next = n2;
        n2.next = n3;
        n3.next = n4;
        n4.next = n5;
        printList(n1);
        System.out.println(isPalindromeList1(head));
        printList(head);
        System.out.println(isPalindromeList2(head));
        printList(head);


    }
}
