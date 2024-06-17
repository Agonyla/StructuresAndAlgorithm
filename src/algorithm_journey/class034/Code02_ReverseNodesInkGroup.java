package algorithm_journey.class034;

/**
 * K 个一组翻转链表
 *
 * @author: Agony
 * @create: 2024/6/13 10:27
 * @describe: 给你链表的头节点 head ，每 k 个节点一组进行翻转，请你返回修改后的链表。
 * <p>
 * k 是一个正整数，它的值小于或等于链表的长度。如果节点总数不是 k 的整数倍，那么请将最后剩余的节点保持原有顺序。
 * <p>
 * 你不能只是单纯的改变节点内部的值，而是需要实际进行节点交换。
 * @link: <a href="https://leetcode.cn/problems/reverse-nodes-in-k-group/description/">K 个一组翻转链表</a>
 */
public class Code02_ReverseNodesInkGroup {


    // 每k个节点一组翻转链表
    // 容器方法
    // 准备一个数组，把链表按序填充的数组中
    // 把数组中每 k 个一组做逆序
    // 再把返回逆序之后数组重新串一下返回头节点

    public static void main(String[] args) {

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
     * 以 k 个节点为一组反转链表
     * <p>
     * a -> b -> c -> d -> e -> f -> g -> h
     * 0000000000000|-------------|
     * a <- b <- c  d <- e <- f   g -> h
     * |----------------------|
     *
     * @param head
     * @param k
     * @return
     */
    public static ListNode reverseKGroup(ListNode head, int k) {

        ListNode start = head;
        ListNode end = teamEnd(start, k);
        // 链表长度小于 k 就直接返回头节点
        if (end == null) {
            return head;
        }
        // 第一组牵扯到头节点需要单独处理
        head = end;
        reverse(start, end);
        // 处理后面的节点
        // 上一组的尾节点
        ListNode lastTeamEnd = start;
        while (lastTeamEnd.next != null) {
            start = lastTeamEnd.next;
            end = teamEnd(start, k);
            // end 为 null 表示这一组节点数量不足 k 个，不需要反转
            if (end == null) {
                break;
            }
            reverse(start, end);
            // 上组尾节点的next指向反转后链表的头节点
            lastTeamEnd.next = end;
            // 上组尾节点来到这组链表的尾节点
            lastTeamEnd = start;
        }
        return head;
    }


    /**
     * 找到一组链表的尾节点
     *
     * @param start 一组链表的头节点
     * @param k     k个为一组
     * @return 返回这组链表的尾节点
     */
    public static ListNode teamEnd(ListNode start, int k) {
        // 要先自减少 1，如 k=3，相当于往后移动 2 次
        while (--k != 0 && start != null) {
            start = start.next;
        }
        return start;
    }

    /**
     * 反转链表
     * <p>
     * start -> a -> b -> c -> end -> 下一组的开始节点
     * ->
     * end -> c -> b -> a -> start -> 下一组的开始节点
     *
     * @param start
     * @param end
     */
    public static void reverse(ListNode start, ListNode end) {

        // end 先来到下一组的头节点
        end = end.next;
        ListNode pre = null;
        ListNode cur = start;
        ListNode next = null;
        while (cur != end) {
            next = cur.next;
            cur.next = pre;
            pre = cur;
            cur = next;
        }
        start.next = end;
    }
}





















