package algorithm_basic.class03;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author Agony
 * @Create 2023/8/5 19:33
 * @Version 1.0
 * 反转单链表
 */
public class Code01_ReverseList {

    public static class Node {
        public int value;
        public Node next;

        public Node(int value) {
            this.value = value;
        }
    }

    // 反转单链表
    public static Node reverseList(Node head) {
        Node pre = null;
        Node next = null;
        while (head != null) {
            next = head.next;
            head.next = pre;
            pre = head;
            head = next;
        }
        return pre;
    }

    // 验证方法
    public static Node testReverseList(Node head) {
        if (head == null) {
            return null;
        }
        ArrayList<Node> list = new ArrayList<>();
        // 先把链表都添加到list中
        while (head != null) {
            list.add(head);
            head = head.next;
        }
        int N = list.size();
        // 先让第一个节点的next指向null
        list.get(0).next = null;
        for (int i = 1; i < N; i++) {
            // 让后一个节点的next指向前一个节点
            list.get(i).next = list.get(i - 1);
        }
        return list.get(N - 1);

    }

    //

    /**
     * @param len   链表长度
     * @param value 链表值的最大值
     * @return 头节点
     * 生产随机链表
     */
    public static Node generateRandomList(int len, int value) {
        if (len == 0) {
            return null;
        }
        int size = (int) (Math.random() * (len + 1));
        Node head = new Node((int) (Math.random() * (value + 1)));
        size--;
        Node pre = head;
        while (size != 0) {
            Node cur = new Node((int) (Math.random() * (value + 1)));
            pre.next = cur;
            pre = cur;
            size--;
        }
        return head;
    }

    /**
     * @param head 头节点
     * @return 反转前的链表
     */
    public static List<Integer> getListOriginOrder(Node head) {
        ArrayList<Integer> originList = new ArrayList<>();
        while (head != null) {
            originList.add(head.value);
            head = head.next;
        }
        return originList;
    }

    /**
     * @param originList 反转之前的链表
     * @param head       反转之后的链表头节点
     * @return 检查是否反转成功
     */
    public static boolean checkListReverse(List<Integer> originList, Node head) {
        for (int i = originList.size() - 1; i >= 0; i--) {
            if (!originList.get(i).equals(head.value)) {
                return false;
            }
            head = head.next;
        }
        return true;
    }

    public static void main(String[] args) {
        int len = 10;
        int value = 10;
        int testTimes = 3;
        System.out.println("algorithm_basic.test begin");
        for (int i = 0; i < testTimes; i++) {
            Node head1 = generateRandomList(len, value);
            List<Integer> listOriginOrder1 = getListOriginOrder(head1);
            head1 = reverseList(head1);
            if (!checkListReverse(listOriginOrder1, head1)) {
                System.out.println("Oops1");
            }

            Node head2 = generateRandomList(len, value);
            List<Integer> listOriginOrder2 = getListOriginOrder(head2);
            head2 = testReverseList(head2);
            if (!checkListReverse(listOriginOrder2, head2)) {
                System.out.println("Oops2");
            }
        }
        System.out.println("algorithm_basic.test finish");
    }
}
