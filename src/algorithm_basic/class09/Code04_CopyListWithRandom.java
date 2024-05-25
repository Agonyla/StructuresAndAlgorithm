package algorithm_basic.class09;

import java.util.HashMap;

/**
 * @Author Agony
 * @Create 2023/8/21 20:26
 * @Version 1.0
 * 随机链表的复制
 */
public class Code04_CopyListWithRandom {

    public static class Node {
        int value;
        Node next;
        Node random;

        public Node(int value) {
            this.value = value;
        }
    }

    // 使用HashMap作为容器，key存放就旧节点，value存放新节点
    public static Node copyListWithRand1(Node head) {
        HashMap<Node, Node> map = new HashMap<>();
        Node cur = head;
        while (cur != null) {
            map.put(cur, new Node(cur.value));
            cur = cur.next;
        }

        cur = head;
        while (cur != null) {
            map.get(cur).next = cur.next;
            map.get(cur).random = cur.random;
            cur = cur.next;
        }
        return map.get(head);
    }

    // 不适用容器
    public static Node copyListWithRand2(Node head) {
        if (head == null) {
            return null;
        }
        Node cur = head;
        Node next = null;
        // 1. 生成各个节点的复制节点，并放在被复制节点的后面
        while (cur != null) {
            next = cur.next;
            cur.next = new Node(cur.value);
            cur.next.next = next;
            cur = next;
        }
        // 2. 复制生成节点的random节点
        Node copyHead = null;
        cur = head;
        while (cur != null) {
            next = cur.next.next;
            copyHead = cur.next;
            copyHead.random = cur.random != null ? cur.random.next : null;
            cur = next;
        }

        // 分离节点
        Node res = head.next;
        cur = head;
        while (cur != null) {
            next = cur.next.next;
            copyHead = cur.next;
            cur.next = next;
            copyHead.next = next != null ? next.next : null;
            cur = next;
        }
        return res;
    }

    public static void printRandLinkedList(Node head) {
        Node cur = head;
        System.out.print("order: ");
        while (cur != null) {
            System.out.print(cur.value + " ");
            cur = cur.next;
        }
        System.out.println();
        cur = head;
        System.out.print("rand:  ");
        while (cur != null) {
            System.out.print(cur.random == null ? "- " : cur.random.value + " ");
            cur = cur.next;
        }
        System.out.println();
    }


    public static void main(String[] args) {
        Node head = new Node(1);
        head.next = new Node(2);
        head.next.next = new Node(3);
        head.next.next.next = new Node(4);
        head.next.next.next.next = new Node(5);
        head.next.next.next.next.next = new Node(6);

        head.random = head.next.next.next.next.next; // 1 -> 6
        head.next.random = head.next.next.next.next.next; // 2 -> 6
        head.next.next.random = head.next.next.next.next; // 3 -> 5
        head.next.next.next.random = head.next.next; // 4 -> 3
        head.next.next.next.next.random = null; // 5 -> null
        head.next.next.next.next.next.random = head.next.next.next; // 6 -> 4

        printRandLinkedList(head);

        Node head1 = copyListWithRand1(head);
        Node head2 = copyListWithRand2(head);
        printRandLinkedList(head1);
        printRandLinkedList(head2);
    }


}
