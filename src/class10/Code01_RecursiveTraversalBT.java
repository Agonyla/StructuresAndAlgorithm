package class10;

/**
 * @Author Agony
 * @Create 2023/8/22 20:08
 * @Version 1.0
 * 二叉树先序、中序、后序遍历
 */
public class Code01_RecursiveTraversalBT {

    public static class Node {
        int value;
        Node left;
        Node right;

        public Node(int value) {
            this.value = value;
        }
    }

    // 递归序遍历
    public static void f(Node head) {
        if (head == null) {
            return;
        }
        // 在前打印就是先序
        f(head.left);
        // 中序
        f(head.right);
        // 后序
    }

    // 先序遍历
    public static void pre(Node head) {
        if (head == null) {
            return;
        }
        System.out.print(head.value + " ");
        pre(head.left);
        pre(head.right);
    }

    // 中序遍历
    public static void in(Node head) {
        if (head == null) {
            return;
        }
        in(head.left);
        System.out.print(head.value + " ");
        in(head.right);
    }

    // 后续遍历
    public static void pos(Node head) {
        if (head == null) {
            return;
        }
        pos(head.left);
        pos(head.right);
        System.out.print(head.value + " ");
    }

    public static void main(String[] args) {
        Node n1 = new Node(1);
        Node n2 = new Node(2);
        Node n3 = new Node(3);
        Node n4 = new Node(4);
        Node n5 = new Node(5);
        Node n6 = new Node(6);
        Node n7 = new Node(7);
        n1.left = n2;
        n1.right = n3;
        n2.left = n4;
        n2.right = n5;
        n3.left = n6;
        n3.right = n7;
        pre(n1);
        System.out.println();
        in(n1);
        System.out.println();
        pos(n1);

    }

}
