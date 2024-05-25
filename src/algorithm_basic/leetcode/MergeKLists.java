package algorithm_basic.leetcode;

/**
 * @Author Agony
 * @Create 2023/8/12 20:56
 * @Version 1.0
 * <a href="https://leetcode.cn/problems/merge-k-sorted-lists/">合并 K 个升序链表</a>
 */
public class MergeKLists {
    public static class ListNode {
        int val;
        ListNode next;

        ListNode() {
        }

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }

    public ListNode mergeKLists(ListNode[] lists) {
        if (lists == null || lists.length < 1) {
            return null;
        }
        ListNode head = lists[0];
        for (int i = 1; i < lists.length; i++) {
            head = merge(head, lists[i]);
        }
        return head;
    }

    public static ListNode merge(ListNode head1, ListNode head2) {
        if (head1 == null || head2 == null) {
            return head1 != null ? head1 : head2;
        }
        ListNode head = new ListNode();
        ListNode P1 = head1;
        ListNode P2 = head2;
        if (head1.val <= head2.val) {
            head = head1;
            P1 = P1.next;
        } else {
            head = head2;
            P2 = P2.next;
        }
        ListNode cur = head;
        while (P1 != null && P2 != null) {
            if (P1.val <= P2.val) {
                cur.next = P1;
                P1 = P1.next;
            } else {
                cur.next = P2;
                P2 = P2.next;
            }
            cur = cur.next;
        }
        while (P1 != null) {
            cur.next = P1;
            P1 = P1.next;
            cur = cur.next;
        }
        while (P2 != null) {
            cur.next = P2;
            P2 = P2.next;
            cur = cur.next;
        }
        return head;
    }

    public static ListNode mergeTwoLists(ListNode head1, ListNode head2) {
        if (head1 == null || head2 == null) {
            return head1 != null ? head1 : head2;
        }
        if (head1.val < head2.val) {
            head1.next = mergeTwoLists(head1.next, head2);
            return head1;
        } else {
            head2.next = mergeTwoLists(head1, head2.next);
            return head2;
        }
    }

    public static void main(String[] args) {

    }
}
