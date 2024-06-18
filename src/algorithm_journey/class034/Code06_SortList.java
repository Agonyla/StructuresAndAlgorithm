package algorithm_journey.class034;

import algorithm_journey.utils.MathUtils;
import algorithm_journey.utils.MathUtils.ListNode;

/**
 * 排序链表
 *
 * @author: Agony
 * @create: 2024/6/13 11:20
 * @describe: 给你链表的头结点 head ，请将其按 升序 排列并返回 排序后的链表 。
 * @link: <a href="https://leetcode.cn/problems/sort-list/description/">排序链表</a>
 */
public class Code06_SortList {


    public static void main(String[] args) {
        ListNode head = new ListNode(3);
        head.next = new ListNode(4);
        head.next.next = new ListNode(1);
        // head.next.next.next = new ListNode(4);
        // head.next.next.next.next = new ListNode(0);
        MathUtils.printList(head);

        ListNode listNode = sortList(head);
        MathUtils.printList(listNode);
    }
    

    // 排序链表
    // 递归版本，空间复杂度是 o(logn)
    // 每次递归调用都会消耗一定的栈空间来保存局部变量和返回地址。递归的深度大约是log n ，
    // 其中 n 是链表的长度。
    // 因此，递归调用本身也需要 o(log n) 的栈空间。所以，递归版本的归并排序对链表的空间复杂度为 o(log n) 。
    //
    // 递归版本 空间复杂度是  o(1)
    // 不使用递归调用，因此不依赖栈空间。
    // 在处理链表时，非递归归并排序通过迭代方式合并链表的各个部分。
    // 由于链表的节点合并可以通过调整节点间的指针完成，不需要额外的存储空间来创建合并后的链表。
    // 因此，非递归归并排序的空间复杂度为  o(1) ，即常数级空间复杂度。

    // public static class ListNode {
    //     int val;
    //     ListNode next;
    //
    //     ListNode() {
    //     }
    //
    //     ListNode(int val) {
    //         this.val = val;
    //     }
    //
    //     ListNode(int val, ListNode next) {
    //         this.val = val;
    //         this.next = next;
    //     }
    // }


    /**
     * 链表排序
     *
     * @param head 链表头节点
     * @return
     */
    public static ListNode sortList(ListNode head) {
        return mergeSort(head);
    }


    public static ListNode mergeSort(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode mid = findMid(head);
        // 从中间节点的下一个节点开始
        ListNode next = mid.next;
        mid.next = null;

        // 把左边的链表排序
        ListNode left = mergeSort(head);
        // 把右边的链表排序
        ListNode right = mergeSort(next);
        // 两边排序
        return merge(left, right);
    }


    /**
     * 找到链表的中间节点
     * 也可以用
     * ListNode slow = head;
     * ListNode fast = head.next;
     * while (fast != null && fast.next != null) 来判断
     *
     * @param head 链表头节点
     * @return 链表中间节点
     */
    public static ListNode findMid(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode slow = head;
        ListNode fast = head;
        while (fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }


    /**
     * 归并排序两个生序链表
     *
     * @param h1 链表1的头节点
     * @param h2 链表2的头节点
     * @return 排序后链表的头节点
     */
    public static ListNode merge(ListNode h1, ListNode h2) {

        // 头节点，但不返回，返回头节点的next节点
        ListNode head = new ListNode(-1);
        ListNode cur = head;
        ListNode cur1 = h1;
        ListNode cur2 = h2;
        while (cur1 != null && cur2 != null) {
            if (cur1.val < cur2.val) {
                cur.next = cur1;
                cur1 = cur1.next;
            } else {
                cur.next = cur2;
                cur2 = cur2.next;
            }
            cur = cur.next;
        }

        if (cur1 != null) {
            cur.next = cur1;
        } else {
            cur.next = cur2;
        }

        return head.next;
    }


}









