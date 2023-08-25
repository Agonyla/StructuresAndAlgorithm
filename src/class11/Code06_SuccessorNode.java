package class11;

/**
 * @Author Agony
 * @Create 2023/8/25 18:55
 * @Version 1.0
 * 给你二叉树的某个节点，找到该节点的后继节点
 * 后继节点：在中序遍历中某一节点的后一个节点
 */
public class Code06_SuccessorNode {

    public static class Node {
        int value;
        Node left;
        Node right;
        Node parent;

        public Node(int value) {
            this.value = value;
        }
    }

    public static Node getSuccessorNode(Node x) {
        if (x == null) {
            return null;
        }
        // 有右孩子 则找到该右树的最左孩子
        if (x.right != null) {
            return getMostLeft(x.right);
        } else {
            Node parent = x.parent;
            while (parent != null && parent.right == x) {
                x = parent;
                parent = x.parent;
            }
            return parent;
        }
    }

    public static Node getMostLeft(Node head) {
        if (head == null) {
            return null;
        }
        while (head.left != null) {
            head = head.left;
        }
        return head;
    }

    public static void printBT(Node head) {
        if (head == null) {
            return;
        }
        printBT(head.left);
        System.out.print(head.value + " ");
        printBT(head.right);
    }

    public static void main(String[] args) {
        Node head = new Node(6);
        head.parent = null;
        head.left = new Node(3);
        head.left.parent = head;
        head.left.left = new Node(1);
        head.left.left.parent = head.left;
        head.left.left.right = new Node(2);
        head.left.left.right.parent = head.left.left;
        head.left.right = new Node(4);
        head.left.right.parent = head.left;
        head.left.right.right = new Node(5);
        head.left.right.right.parent = head.left.right;
        head.right = new Node(9);
        head.right.parent = head;
        head.right.left = new Node(8);
        head.right.left.parent = head.right;
        head.right.left.left = new Node(7);
        head.right.left.left.parent = head.right.left;
        head.right.right = new Node(10);
        head.right.right.parent = head.right;


        printBT(head);
        System.out.println();


        Node test = head.left.right.right;
        System.out.println(test.value + " next: " + getSuccessorNode(test).value);


        test = head;
        System.out.println(test.value + " next: " + getSuccessorNode(test).value);


        test = head.right.left;
        System.out.println(test.value + " next: " + getSuccessorNode(test).value);

    }
}
