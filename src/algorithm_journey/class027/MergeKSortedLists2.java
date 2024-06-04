package algorithm_journey.class027;

/**
 * 合并 K 个升序链表 （递归实现）
 *
 * @author Agony
 * @create 2024/6/4 16:31
 */
public class MergeKSortedLists2 {


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
        if (lists == null || lists.length == 0) {
            return null;
        }
        return merge(lists, 0, lists.length - 1);
    }

    public static ListNode merge(ListNode[] lists, int L, int R) {
        if (L == R) {
            return lists[L];
        }
        int mid = L + (R - L) / 2;
        ListNode L1 = merge(lists, L, mid);
        ListNode L2 = merge(lists, mid + 1, R);
        return mergeTwoLists(L1, L2);
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
}
