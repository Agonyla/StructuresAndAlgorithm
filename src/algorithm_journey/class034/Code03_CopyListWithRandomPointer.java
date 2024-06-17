package algorithm_journey.class034;

/**
 * 随机链表的复制
 *
 * @author: Agony
 * @create: 2024/6/13 10:29
 * @describe: 给你一个长度为 n 的链表，每个节点包含一个额外增加的随机指针 random ，该指针可以指向链表中的任何节点或空节点。
 * <p>
 * 构造这个链表的 深拷贝。 深拷贝应该正好由 n 个 全新 节点组成，其中每个新节点的值都设为其对应的原节点的值。
 * 新节点的 next 指针和 random 指针也都应指向复制链表中的新节点，并使原链表和复制链表中的这些指针能够表示相同的链表状态。
 * 复制链表中的指针都不应指向原链表中的节点 。
 * <p>
 * 例如，如果原链表中有 X 和 Y 两个节点，其中 X.random --> Y 。那么在复制链表中对应的两个节点 x 和 y ，同样有 x.random --> y 。
 * <p>
 * 返回复制链表的头节点。
 * @link: <a href="https://leetcode.cn/problems/copy-list-with-random-pointer/description/">随机链表的复制</a>
 */
public class Code03_CopyListWithRandomPointer {
    

    // 复制带随机指针的链表
    // 容器方法
    // 准备一个 hashmap ，key 装原链表节点，value 装新的节点
    // 遍历该 hashmap，在value中的节点的next指针，指向key中的节点的next指针所对应的value节点
    // 如
    // key: 1, value: 1'
    // key: 2, value: 2'
    // key: 3, value: 3'
    // key: 4, value: 4'
    // 遍历到 1，1的next指针指向2，2所对应的value是2'，所以 1'的next指针就指向2'
    // 即 通过 1 的next 找到 2，再通过 key 是 2 找到 value 2'
    // random指针同理

    public static class Node {
        int val;
        Node next;
        Node random;

        public Node(int val) {
            this.val = val;
            this.next = null;
            this.random = null;
        }
    }

    /**
     * 随机链表的复制
     * <p>
     * 思路
     * 在每个节点后面新建一个节点，拷贝上一个节点的值
     * a -> b -> c -> d
     * a -> a' -> b -> b' -> c -> c' -> d -> d'
     * 然后通过遍历链表，实现随机节点的复制
     * 再分离新老链表
     *
     * @param head
     * @return
     */
    public static Node copyRandomList(Node head) {

        if (head == null) {
            return head;
        }
        Node cur = head;
        Node next = null;
        // 复制上一个节点，并将其插入链表中
        while (cur != null) {
            next = cur.next;
            cur.next = new Node(cur.val);
            cur.next.next = next;
            cur = next;
        }

        // 复制随机节点
        cur = head;
        Node copy = null;
        while (cur != null) {
            next = cur.next.next;
            copy = cur.next;
            copy.random = cur.random != null ? cur.random.next : null;
            cur = next;
        }

        // 新老链表分离
        Node ans = head.next;
        cur = head;
        while (cur != null) {
            next = cur.next.next;
            copy = cur.next;
            cur.next = next;
            copy.next = next != null ? next.next : null;
            cur = next;
        }
        return ans;
    }


}









