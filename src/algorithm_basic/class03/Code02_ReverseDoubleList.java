package algorithm_basic.class03;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author Agony
 * @Create 2023/8/5 20:39
 * @Version 1.0
 */
public class Code02_ReverseDoubleList {

    public static class DoubleNode {
        int value;
        DoubleNode last;
        DoubleNode next;

        public DoubleNode(int value) {
            this.value = value;
        }
    }

    public static DoubleNode reverseDoubleList(DoubleNode head) {
        DoubleNode pre = null;
        DoubleNode next = null;
        while (head != null) {
            next = head.next;
            head.next = pre;
            head.last = next;
            pre = head;
            head = next;
        }
        return pre;
    }

    //
    public static DoubleNode testReverseDoubleList(DoubleNode head) {
        if (head == null) {
            return null;
        }
        ArrayList<DoubleNode> list = new ArrayList<>();
        while (head != null) {
            list.add(head);
            head = head.next;
        }
        list.get(0).next = null;
        DoubleNode pre = list.get(0);
        int N = list.size();
        for (int i = 1; i < N; i++) {
            DoubleNode cur = list.get(i);
            cur.last = null;
            cur.next = pre;
            pre.last = cur;
            pre = cur;
        }
        return list.get(N - 1);
    }

    /**
     * @param len   链表长度
     * @param value 链表最大值
     * @return 返回链表头节点
     */
    public static DoubleNode generateRandomDoubleList(int len, int value) {
        int size = (int) (Math.random() * (len + 1));
        if (size == 0) {
            return null;
        }

        DoubleNode head = new DoubleNode((int) (Math.random() * (value + 1)));
        size--;
        DoubleNode pre = head;
        while (size != 0) {
            DoubleNode cur = new DoubleNode((int) (Math.random() * (value + 1)));
            pre.next = cur;
            cur.last = pre;
            pre = cur;
            size--;
        }
        return head;
    }

    public static List<Integer> getDoubleListOriginOrder(DoubleNode head) {
        ArrayList<Integer> list = new ArrayList<>();
        while (head != null) {
            list.add(head.value);
            head = head.next;
        }
        return list;
    }

    /**
     * @param originList 反转之前的链表
     * @param head       反转之后的头节点
     * @return 是否反转成功
     */
    public static boolean checkDoubleListReverse(List<Integer> originList, DoubleNode head) {
        DoubleNode end = null;

        // 倒着检测一边
        for (int i = originList.size() - 1; i >= 0; i--) {
            if (!originList.get(i).equals(head.value)) {
                return false;
            }
            end = head;
            head = head.next;
        }
        // 正着检测一边  end 最后指向尾节点
        for (int i = 0; i < originList.size(); i++) {
            if (!originList.get(i).equals(end.value)) {
                return false;
            }
            end = end.last;
        }
        return true;
    }

    public static void main(String[] args) {

        int len = 10;
        int value = 10;
        int testTimes = 4;
        System.out.println("algorithm_basic.test begin");
        for (int i = 0; i < testTimes; i++) {
            DoubleNode head1 = generateRandomDoubleList(len, value);
            List<Integer> originOrder1 = getDoubleListOriginOrder(head1);
            head1 = reverseDoubleList(head1);
            if (!checkDoubleListReverse(originOrder1, head1)) {
                System.out.println("Oops1");
                while (head1 != null) {
                    System.out.print(head1.value + " ");
                    head1 = head1.next;
                }
                System.out.println(" ");

                for (Integer integer : originOrder1) {
                    System.out.print(integer + " ");
                }
                System.out.println(" ");

            }
            DoubleNode head2 = generateRandomDoubleList(len, value);
            List<Integer> originOrder2 = getDoubleListOriginOrder(head2);
            head2 = testReverseDoubleList(head2);
            if (!checkDoubleListReverse(originOrder2, head2)) {
                System.out.println("Oops2");
            }
        }
        System.out.println("algorithm_basic.test finish");
    }
}
