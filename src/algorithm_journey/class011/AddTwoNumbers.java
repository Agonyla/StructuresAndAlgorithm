package algorithm_journey.class011;

/**
 * @author Agony
 * @create 2024/5/24 17:09
 * @describe: 给你两个 非空 的链表，表示两个非负的整数。它们每位数字都是按照 逆序 的方式存储的，并且每个节点只能存储 一位 数字。
 * 请你将两个数相加，并以相同形式返回一个表示和的链表。
 * 你可以假设除了数字 0 之外，这两个数都不会以 0 开头。
 * @link: <a href="https://leetcode.cn/problems/add-two-numbers/">两数相加</a>
 */
public class AddTwoNumbers {


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

    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode head = null;
        ListNode cur = null;
        // 表示进位
        int carry = 0;
        for (int sum, val;
            // 终止条件，只有当 l1 和 l2 都为null时才终止
             l1 != null || l2 != null;
             l1 = l1 != null ? l1.next : null, l2 = l2 != null ? l2.next : null) {

            // 为 null 就加 0
            sum = (l1 == null ? 0 : l1.val)
                    + (l2 == null ? 0 : l2.val)
                    + carry;
            val = sum % 10;
            carry = sum / 10;
            if (head == null) {
                head = new ListNode(val);
                cur = head;
            } else {
                cur.next = new ListNode(val);
                cur = cur.next;
            }
        }
        if (carry == 1) {
            cur.next = new ListNode(carry);
        }
        return head;
    }
}
