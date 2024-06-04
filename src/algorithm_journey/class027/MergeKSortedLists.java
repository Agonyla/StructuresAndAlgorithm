package algorithm_journey.class027;

import java.util.PriorityQueue;

/**
 * 合并 K 个升序链表 （小根堆实现）
 *
 * @author Agony
 * @create 2024/6/3 21:29
 * @describe: 给你一个链表数组，每个链表都已经按升序排列。
 * <p>
 * 请你将所有链表合并到一个升序链表中，返回合并后的链表。
 * @link: <a href="https://leetcode.cn/problems/merge-k-sorted-lists/description/">合并 K 个升序链表</a>
 */
public class MergeKSortedLists {


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

    public static void main(String[] args) {
        ListNode h1 = new ListNode(1);
        h1.next = new ListNode(4);
        h1.next.next = new ListNode(5);

        ListNode h2 = new ListNode(1);
        h2.next = new ListNode(3);
        h2.next.next = new ListNode(4);

        ListNode h3 = new ListNode(2);
        h3.next = new ListNode(6);

        ListNode[] lists = new ListNode[3];
        lists[0] = h1;
        lists[1] = h2;
        lists[2] = h3;

        ListNode cur = h1;
        System.out.print("h1 : ");
        while (cur != null) {
            System.out.print(cur.val + " ");
            cur = cur.next;
        }
        System.out.println();

        cur = h2;
        System.out.print("h2 : ");
        while (cur != null) {
            System.out.print(cur.val + " ");
            cur = cur.next;
        }
        System.out.println();

        cur = h3;
        System.out.print("h3 : ");
        while (cur != null) {
            System.out.print(cur.val + " ");
            cur = cur.next;
        }
        System.out.println();

        ListNode head = mergeKLists(lists);

        cur = head;
        System.out.print("head : ");
        while (cur != null) {
            System.out.print(cur.val + " ");
            cur = cur.next;
        }
        System.out.println();

    }


    /**
     * 把每个链表的头节点都放入到小根堆
     * 弹出堆顶节点
     * 串出一条新的listNode
     * 把该节点的 next 节点再放入堆
     * 直到堆size==0
     *
     * @param lists 链表数组
     * @return
     */
    public static ListNode mergeKLists(ListNode[] lists) {
        if (lists == null || lists.length == 0) {
            return null;
        }
        // 头节点
        ListNode head = null;
        // 尾节点
        ListNode tail = null;
        // 堆顶弹出节点
        ListNode cur = null;
        PriorityQueue<ListNode> heap = new PriorityQueue<>((a, b) -> a.val - b.val);

        // 把数组的每个头节点都放入小根堆
        for (ListNode list : lists) {
            if (list != null) {
                heap.add(list);
            }
        }
        if (heap.isEmpty()) {
            return null;
        }

        // 先弹出一个节点最为头节点
        cur = heap.poll();
        head = cur;
        tail = cur;
        if (cur.next != null) {
            heap.add(cur.next);
        }
        // 把小根堆的顶依次弹出
        while (!heap.isEmpty()) {
            cur = heap.poll();
            tail.next = cur;
            tail = cur;
            if (cur.next != null) {
                heap.add(cur.next);
            }
        }
        return head;
    }
}




















