package algorithm_journey.class034;

/**
 * 环形链表 II
 *
 * @author: Agony
 * @create: 2024/6/13 10:50
 * @describe: 如果链表中有某个节点，可以通过连续跟踪 next 指针再次到达，则链表中存在环。
 * 为了表示给定链表中的环，评测系统内部使用整数 pos 来表示链表尾连接到链表中的位置（索引从 0 开始）。
 * 如果 pos 是 -1，则在该链表中没有环。注意：pos 不作为参数进行传递，仅仅是为了标识链表的实际情况。
 * <p>
 * 不允许修改 链表。
 * @link: <a href="https://leetcode.cn/problems/linked-list-cycle-ii/description/">环形链表 II</a>
 */
public class Code05_LinkedListCycleII {


    // todo

    // 返回链表的第一个入环节点
    // 容器方法
    // 准备一个 hashset ，遍历链表，过程中先检查该节点在不在链表中，不在则放入该节点，在就直接返回

    // 快慢指针
    // 直接记流程❕❕❕无需证明
    // 快指针 F，慢指针 S，F走两步数，S走一步
    // 若 F.next==null || F.next.next ==null 说明不是环形链表
    // 若 F==S，两个指针相遇了
    // F 回到头节点，每次走一步，S待在原处，每次走一步
    // 之后一定会在入环节点出相遇

    public static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
            next = null;
        }
    }

    public static ListNode detectCycle(ListNode head) {

        if (head == null || head.next == null || head.next.next == null) {
            return null;
        }
        ListNode slow = head.next;
        ListNode fast = head.next.next;
        while (slow != fast) {
            if (fast.next == null || fast.next.next == null) {
                return null;
            }
            slow = slow.next;
            fast = fast.next.next;
        }
        fast = head;
        while (fast != slow) {
            fast = fast.next;
            slow = slow.next;
        }
        return slow;
    }

}
