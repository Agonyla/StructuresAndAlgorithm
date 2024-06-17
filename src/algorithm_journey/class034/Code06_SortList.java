package algorithm_journey.class034;

/**
 * 排序链表
 *
 * @author: Agony
 * @create: 2024/6/13 11:20
 * @describe: 给你链表的头结点 head ，请将其按 升序 排列并返回 排序后的链表 。
 * @link: <a href="https://leetcode.cn/problems/sort-list/description/">排序链表</a>
 */
public class Code06_SortList {


    // todo

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

    public ListNode sortList(ListNode head) {


        return null;
    }


    public static void merge() {
    }


}
