package algorithm_journey.leetcode;

/**
 * @author: Agony
 * @create: 2025/3/18 16:55
 * @describe: 旋转链表
 * @link: <a href="https://leetcode.cn/problems/rotate-list/description/">旋转链表</a>
 */
public class LeetCode61 {

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

    public ListNode rotateRight(ListNode head, int k) {

        if (head == null || head.next == null || k == 0) {
            return head;
        }

        // 将tail移动到链表的最后一个节点
        ListNode tail = head;
        int length = 1;
        while (tail.next != null) {
            tail = tail.next;
            length++;
        }

        // 最后一个节点要移动的次数
        // 如 1 -> 2 -> 3 -> 4
        // 2 要作头节点，尾节点就要移动到 1， 需要移动1次
        int move = length - k % length;

        // 如果要移动 length 次 则直接返回原头节点
        if (move == length) {
            return head;
        }

        // 尾节点连接头节点形成环
        tail.next = head;
        while (move > 0) {
            tail = tail.next;
            move--;
        }
        head = tail.next;
        tail.next = null;
        return head;
    }
}
