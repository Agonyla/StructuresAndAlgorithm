package algorithm_journey.hwod;

import java.util.HashMap;
import java.util.Scanner;

/**
 * @author: Agony
 * @create: 2025/4/14 22:06
 * @describe: 从单向链表中删除指定值的节点
 * <a href="https://www.nowcoder.com/practice/f96cd47e812842269058d483a11ced4f?tpId=37&tqId=21271&rp=1&sourceUrl=%2Fexam%2Foj%2Fta%3FtpId%3D37&difficulty=3&judgeStatus=undefined&tags=&title=">从单向链表中删除指定值的节点</a>
 */
public class HJ48 {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        int n = scanner.nextInt();
        int headVal = scanner.nextInt();
        Node head = new Node(headVal);

        HashMap<Integer, Node> map = new HashMap<>();
        map.put(headVal, head);

        for (int i = 0; i < n - 1; i++) {
            int val = scanner.nextInt();
            int current = scanner.nextInt();
            Node parentNode = map.get(current);
            Node newNode = new Node(val);
            map.put(val, newNode);


            newNode.next = parentNode.next;
            parentNode.next = newNode;
        }

        int k = scanner.nextInt();

        // while (head != null) {
        //
        //     if (head.next == null) {
        //         System.out.println(head.val);
        //     } else {
        //         System.out.print(head.val + " ");
        //     }
        //     head = head.next;
        // }

        if (head.val == k) {
            head = head.next;
        } else {
            Node cur = head;
            while (cur.next.val != k) {
                cur = cur.next;
            }
            cur.next = cur.next.next;
        }
        while (head != null) {

            if (head.next == null) {
                System.out.println(head.val);
            } else {
                System.out.print(head.val + " ");
            }
            head = head.next;
        }

    }


    static class Node {
        int val;
        Node next;

        public Node(int val, Node next) {
            this.val = val;
            this.next = next;
        }

        public Node(int val) {
            this.val = val;
        }


    }


}
