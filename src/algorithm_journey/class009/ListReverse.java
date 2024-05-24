package algorithm_journey.class009;

/**
 * @author Agony
 * @create 2024/5/24 10:03
 * @describe: 单链表反转
 * <a href="https://leetcode.cn/problems/reverse-linked-list/">单链表反转</a>
 */
public class ListReverse {

    // 单链表节点
    public static class ListNode {
        public int val;
        public ListNode next;

        public ListNode(int val) {
            this.val = val;
        }

        public ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }

    public static ListNode reverseList(ListNode head) {
        ListNode pre = null;
        ListNode next;
        while (head != null) {
            next = head.next;
            head.next = pre;
            pre = head;
            head = next;
        }
        return pre;
    }


}


