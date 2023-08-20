package class09;

/**
 * @Author Agony
 * @Create 2023/8/20 20:36
 * @Version 1.0
 */
public class Code03_SmallerEqualBigger {

    public static class Node {
        public int value;
        public Node next;

        public Node(int value) {
            this.value = value;
        }
    }

    public static Node listPartition1(Node head, int target) {
        if (head == null) {
            return null;
        }
        // 记录链表长度
        Node cur = head;
        int i = 0;
        while (cur != null) {
            i++;
            cur = cur.next;
        }
        // 把链表放入数组中
        Node[] nodes = new Node[i];
        cur = head;
        for (int j = 0; j < nodes.length; j++) {
            nodes[j] = cur;
            cur = cur.next;
        }
        // partition
        arrPartition(nodes, target);
        // 把链表按数组顺序连起来
        for (int j = 1; j < nodes.length; j++) {
            nodes[j - 1].next = nodes[j];
        }
        // 最后一个指向null
        nodes[nodes.length - 1].next = null;
        return nodes[0];
    }

    public static void arrPartition(Node[] arr, int target) {
        int less = -1;
        int more = arr.length;
        int index = 0;

        // 不能等于
        while (index < more) {
            if (arr[index].value < target) {
                swap(arr, index++, ++less);
            } else if (arr[index].value > target) {
                swap(arr, index, --more);
            } else {
                index++;
            }
        }
    }

    public static void arrPartition(int[] arr, int target) {
        int less = -1;
        int more = arr.length;
        int index = 0;

        // 不知道能不呢等于
        while (index < more) {
            if (arr[index] < target) {
                swap(arr, index++, ++less);
            } else if (arr[index] > target) {
                swap(arr, index, --more);
            } else {
                index++;
            }
        }
    }


    public static void swap(Node[] arr, int i, int j) {
        Node temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }


    public static void printArr(int[] arr) {
        for (int i : arr) {
            System.out.print(i + " ");
        }
        System.out.println();
    }

    public static void printList(Node head) {
        while (head != null) {
            System.out.print(head.value + " ");
            head = head.next;
        }
        System.out.println();
    }

    public static void main(String[] args) {
        int[] arr = {5, 3, 6, 32, 7, 8, 3, 1, 2};
        int target = 3;
        printArr(arr);
        arrPartition(arr, target);
        printArr(arr);
        System.out.println("============================");
        Node n1 = new Node(5);
        Node n2 = new Node(3);
        Node n3 = new Node(6);
        Node n4 = new Node(32);
        Node n5 = new Node(7);
        Node n6 = new Node(8);
        Node n7 = new Node(3);
        Node n8 = new Node(1);
        Node n9 = new Node(2);
        n1.next = n2;
        n2.next = n3;
        n3.next = n4;
        n4.next = n5;
        n5.next = n6;
        n6.next = n7;
        n7.next = n8;
        n8.next = n9;
        printList(n1);
        Node head = listPartition1(n1, 3);
        printList(head);
    }

}
