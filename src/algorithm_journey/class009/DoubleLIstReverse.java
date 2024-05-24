package algorithm_journey.class009;

/**
 * @author Agony
 * @create 2024/5/24 16:34
 * @describe: 双链表反转
 */
public class DoubleLIstReverse {

    // 双链表节点
    public static class DoubleListNode {
        public int value;
        public DoubleListNode last;
        public DoubleListNode next;

        public DoubleListNode(int v) {
            value = v;
        }
    }

    public static DoubleListNode reverseDoubleList(DoubleListNode head) {
        DoubleListNode pre = null;
        DoubleListNode next;
        while (head != null) {
            next = head.next;
            head.next = pre;
            head.last = next;
            pre = head;
            head = next;
        }
        return pre;
    }


}
