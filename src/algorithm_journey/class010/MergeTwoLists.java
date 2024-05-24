package algorithm_journey.class010;

/**
 * @author Agony
 * @create 2024/5/24 16:45
 * @describe: 将两个升序链表合并为一个新的升序链表并返回。新链表是通过拼接给定的两个链表的所有节点组成的。
 * @link: <a href="https://leetcode.cn/problems/merge-two-sorted-lists/">合并两个有序链表</a>
 */
public class MergeTwoLists {


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

    public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        // 非空判断
        if (list1 == null || list2 == null) {
            return list1 == null ? list2 : list1;
        }
        ListNode head = list1.val <= list2.val ? list1 : list2;
        // cur1 指向头节点较小的链表的第二个节点
        ListNode cur1 = head.next;
        ListNode cur2 = head == list1 ? list2 : list1;
        // 用来指向合并链表的最后一个节点
        ListNode next = head;
        while (cur1 != null && cur2 != null) {
            if (cur1.val <= cur2.val) {
                next.next = cur1;
                cur1 = cur1.next;
            } else {
                next.next = cur2;
                cur2 = cur2.next;
            }
            next = next.next;
        }
        // 将链表结束的最后一个节点指向另一条链表
        next.next = cur1 == null ? cur2 : cur1;
        return head;
    }

}
