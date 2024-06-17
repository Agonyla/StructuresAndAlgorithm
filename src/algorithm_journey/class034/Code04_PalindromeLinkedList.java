package algorithm_journey.class034;

/**
 * 回文链表
 *
 * @author: Agony
 * @create: 2024/6/13 10:36
 * @describe: 给你一个单链表的头节点 head ，请你判断该链表是否为
 * 回文链表
 * 。如果是，返回 true ；否则，返回 false 。
 * @link: <a href="https://leetcode.cn/problems/palindrome-linked-list/description/">回文链表</a>
 */
public class Code04_PalindromeLinkedList {

    // 判断链表是否是回文结构
    // 容器方法
    // 新建一个栈，把链表依次押入栈中
    // 从头遍历链表，依次弹出栈，判断节点时候一致
    // 回文则一致，不回文则不一致


    public static void main(String[] args) {
        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(3);
        head.next.next.next = new ListNode(3);
        head.next.next.next.next = new ListNode(2);
        head.next.next.next.next.next = new ListNode(1);

        printList(head);
        System.out.println(isPalindrome(head));
        printList(head);

    }

    public static void printList(ListNode head) {
        while (head != null) {
            if (head.next != null) {
                System.out.print(head.val + " -> ");
            } else {
                System.out.println(head.val);
            }
            head = head.next;
        }
    }

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

    /**
     * 回文链表
     * <p>
     * 思路
     * 找到链表的中间节点
     * 用快慢指针
     * slow，fast
     * slow=head, fast=head
     * 当fast.next==null or fast.next.next==null时
     * 此时slow所在节点即为链表中间节点
     * <p>
     * a -> b -> c -> c -> b -> a
     * a -> b -> c -> b -> a
     * <p>
     * 反转中间节点到尾节点
     * 从头和尾开始遍历，判断节点是否一致
     *
     * @param head
     * @return
     */
    public static boolean isPalindrome(ListNode head) {
        if (head == null || head.next == null) {
            return true;
        }
        ListNode slow = head;
        ListNode fast = head;
        // 找到中间节点
        while (fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        // 从中间节点开始，翻转后面的节点
        ListNode cur = slow;
        ListNode pre = null;
        ListNode next = null;
        while (cur != null) {
            next = cur.next;
            cur.next = pre;
            pre = cur;
            cur = next;
        }
        // 此时链表变成 ->   a -> b -> c <- c <- b <- a 这种形状
        // pre 来到了尾节点
        // 遍历验证是否是回文
        boolean ans = true;
        ListNode left = head;
        ListNode right = pre;
        while (left != null && right != null) {
            if (left.val != right.val) {
                ans = false;
                break;
            }
            left = left.next;
            right = right.next;
        }

        // b把链表恢复成原来的样子
        cur = pre;
        pre = null;
        next = null;
        while (cur != null) {
            next = cur.next;
            cur.next = pre;
            pre = cur;
            cur = next;
        }
        return ans;
    }
}








