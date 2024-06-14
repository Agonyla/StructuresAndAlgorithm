package algorithm_journey.class034;

/**
 * 相交链表
 *
 * @author: Agony
 * @create: 2024/6/13 10:23
 * @describe: 给你两个单链表的头节点 headA 和 headB ，请你找出并返回两个单链表相交的起始节点。如果两个链表不存在相交节点，返回 null 。
 * @link: <a href="https://leetcode.cn/problems/intersection-of-two-linked-lists/description/">相交链表</a>
 */
public class Code01_IntersectionOfTwoLinkedLists {


    // 返回两个无环链表相交的第一个节点
    // 容器方法 不需要实现，知道思路就行
    // 用 hashset 把一条链表的所有节点全部放进去
    // 然后遍历另一条链表，遍历到当前节点存在 hashset 中就直接返回该节点


    public static void main(String[] args) {

    }

    public static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
            next = null;
        }
    }


    /**
     * 返回两个无环链表相交的第一个节点
     * <p>
     * 两个链表遍历，记录两个链表的长度，
     * l1 = 9, l2 = 5
     * 就让 h1 先走 4 步
     * 之后判断是否来到了同一个节点，是的话就直接返回该节点
     *
     * @param h1 链表1的头节点
     * @param h2 链表2的头节点
     * @return 相交的第一个节点
     */
    public static ListNode getIntersectionNode(ListNode h1, ListNode h2) {
        if (h1 == null || h2 == null) {
            return null;
        }
        int step = 0;
        ListNode cur1 = h1;
        ListNode cur2 = h2;
        // 通过step的正负找到较长的链表
        while (cur1.next != null) {
            step++;
            cur1 = cur1.next;
        }
        while (cur2.next != null) {
            step--;
            cur2 = cur2.next;
        }
        // 链表1的最后一个节点和链表2的最后一个节点不是同一个节点
        if (cur1 != cur2) {
            return null;
        }
        // 最后一个节点是同一个节点
        // 说明两条链表已经相遇了
        // cur1 指向较长的链表
        cur1 = step >= 0 ? h1 : h2;
        cur2 = cur1 == h1 ? h2 : h1;

        // 较长的链表先走step
        step = step >= 0 ? step : -step;
        for (int i = 0; i < step; i++) {
            cur1 = cur1.next;
        }
        // 找到相遇的第一个节点
        while (cur1 != cur2) {
            cur1 = cur1.next;
            cur2 = cur2.next;
        }
        return cur1;
    }
}
