package algorithm_basic.class10;

import java.util.Stack;

/**
 * @Author Agony
 * @Create 2023/8/22 20:19
 * @Version 1.0
 * // 非递归实现二叉树先序、中序、后序遍历
 */
public class Code02_UnRecursiveTraversalBT {

    public static class Node {
        int value;
        Node left;
        Node right;

        public Node(int value) {
            this.value = value;
        }
    }

    // 先序遍历
    // 借助栈实现
    // 栈顶弹出记为cur
    // 有右压入右，有左压入左
    // 栈空停止
    public static void pre(Node head) {
        if (head == null) {
            return;
        }
        Stack<Node> stack = new Stack<>();
        stack.push(head);
        while (!stack.isEmpty()) {
            Node cur = stack.pop();
            System.out.print(cur.value + " ");
            if (cur.right != null) {
                stack.push(cur.right);
            }
            if (cur.left != null) {
                stack.push(cur.left);
            }
        }
        System.out.println();
    }

    // 中序遍历
    // 1.当前节点cur，以cur为头的树，整条左边界进栈，直到为null
    // 2.弹出节点记为cur 并打印  -> 1
    // 直到栈为空
    public static void in(Node head) {
        if (head == null) {
            return;
        }
        Stack<Node> stack = new Stack<>();
        while (!stack.isEmpty() || head != null) {
            if (head != null) {
                stack.push(head);
                head = head.left;
            } else {
                head = stack.pop();
                System.out.print(head.value + " ");
                head = head.right;
            }
        }
        System.out.println();
    }

    // 后序遍历
    // 两个栈
    // 有左进左，再进右，弹出不打印放入第二个栈
    // 第二个栈弹出打印
    public static void pos(Node head) {
        if (head == null) {
            return;
        }
        Stack<Node> stack1 = new Stack<>();
        Stack<Node> stack2 = new Stack<>();
        stack1.push(head);
        while (!stack1.isEmpty()) {
            Node cur = stack1.pop();
            stack2.push(cur);
            if (cur.left != null) {
                stack1.push(cur.left);
            }
            if (cur.right != null) {
                stack1.push(cur.right);
            }
        }
        while (!stack2.isEmpty()) {
            System.out.print(stack2.pop().value + " ");
        }
        System.out.println();
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
        // 先序 1 2 4 5 3 6 7
        // 中序 4 2 5 1 6 3 7
        // 后序 4 5 2 6 7 3 1
        pre(n1);
        in(n1);
        pos(n1);


    }

}
