package algorithm_journey.class012;

/**
 * @author Agony
 * @create 2024/5/25 16:36
 * @describe: 给你一个链表的头节点 head 和一个特定值 x ，请你对链表进行分隔，使得所有 小于 x 的节点都出现在 大于或等于 x 的节点之前。
 * 你应当 保留 两个分区中每个节点的初始相对位置。
 * @link: <a href="https://leetcode.cn/problems/partition-list/description/">分隔链表</a>
 */
public class PartitionList {


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

    public ListNode partition(ListNode head, int x) {
        ListNode lHead = null;
        ListNode lTail = null;
        ListNode rHead = null;
        ListNode rTail = null;
        ListNode next = null;
        while (head != null) {
            next = head.next;
            head.next = null;
            if (head.val < x) {
                // 左边头节点还没挂节点时
                if (lHead == null) {
                    lHead = head;
                } else {
                    lTail.next = head;
                }
                lTail = head;
            } else {
                // 右边头节点还没挂节点时
                if (rHead == null) {
                    rHead = head;
                } else {
                    rTail.next = head;
                }
                rTail = head;
            }
            head = next;
        }
        // 链表中的数都大于 x ，左链表为空
        if (lHead == null) {
            return rHead;
        }
        lTail.next = rHead;
        return lHead;
    }

}
