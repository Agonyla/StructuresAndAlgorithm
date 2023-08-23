package class11;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * @Author Agony
 * @Create 2023/8/22 21:25
 * @Version 1.0
 * // 二叉树的序列化与反序列化
 */
public class Code02_SerializeAndReconstructTree {
    /*
     * 二叉树可以通过先序、后序或者按层遍历的方式序列化和反序列化，
     * 以下代码全部实现了。
     * 但是，二叉树无法通过中序遍历的方式实现序列化和反序列化
     * 因为不同的两棵树，可能得到同样的中序序列，即便补了空位置也可能一样。
     * 比如如下两棵树
     *         __2
     *        /
     *       1
     *       和
     *       1__
     *          \
     *           2
     * 补足空位置的中序遍历结果都是{ null, 1, null, 2, null}
     * */
    public static class Node {
        int value;
        Node left;
        Node right;

        public Node(int value) {
            this.value = value;
        }
    }

    // 先序序列化
    public static Queue<String> preSerialize(Node head) {
        LinkedList<String> queue = new LinkedList<>();
        preProcess(head, queue);
        return queue;
    }

    public static void preProcess(Node head, Queue<String> queue) {
        if (head == null) {
            queue.add(null);
        } else {
            queue.add(String.valueOf(head.value));
            preProcess(head.left, queue);
            preProcess(head.right, queue);
        }
    }

    // 先序反序列化
    public static Node preDeserialize(Queue<String> queue) {
        if (queue == null || queue.isEmpty()) {
            return null;
        }
        return preDeProcess(queue);
    }

    public static Node preDeProcess(Queue<String> queue) {
        String value = queue.poll();
        if (value == null) {
            return null;
        }
        Node cur = new Node(Integer.parseInt(value));
        cur.left = preDeProcess(queue);
        cur.right = preDeProcess(queue);
        return cur;
    }

    // 中序序列化
    public static Queue<String> inSerialize(Node head) {
        LinkedList<String> queue = new LinkedList<>();
        inProcess(head, queue);
        return queue;
    }

    public static void inProcess(Node head, Queue<String> queue) {
        if (head == null) {
            queue.add(null);
        } else {
            inProcess(head.left, queue);
            queue.add(String.valueOf(head.value));
            inProcess(head.right, queue);
        }
    }

    // 后序序列化
    public static Queue<String> posSerialize(Node head) {
        LinkedList<String> queue = new LinkedList<>();
        posProcess(head, queue);
        return queue;
    }

    public static void posProcess(Node head, Queue<String> queue) {
        if (head == null) {
            queue.add(null);
        } else {
            posProcess(head.left, queue);
            posProcess(head.right, queue);
            queue.add(String.valueOf(head.value));
        }

    }

    // 后序反序列化
    // 队列是 左右头 的结构 --> 通过栈 --> 头右左 --> 先加右节点，再加左节点
    public static Node posDeserialize(Queue<String> queue) {
        if (queue == null || queue.isEmpty()) {
            return null;
        }
        Stack<String> stack = new Stack<>();
        while (!queue.isEmpty()) {
            stack.push(queue.poll());
        }
        return posDeProcess(stack);
    }
    
    public static Node posDeProcess(Stack<String> stack) {
        String value = stack.pop();
        if (value == null) {
            return null;
        }
        Node cur = new Node(Integer.parseInt(value));
        cur.right = posDeProcess(stack);
        cur.left = posDeProcess(stack);
        return cur;
    }

    // 打印队列
    public static void printQueue(Queue<String> queue) {
        if (queue == null || queue.isEmpty()) {
            return;
        }
        for (String s : queue) {
            if (s == null) {
                System.out.print("#" + " ");
            } else {
                System.out.print(s + " ");
            }
        }
        System.out.println();
        // for (String s : queue) {
        //     System.out.print(Objects.requireNonNullElse(s, "#") + " ");
        // }
        // System.out.println();
    }

    // 先序打印二叉树
    public static void printPreBT(Node head) {
        if (head == null) {
            return;
        }
        System.out.print(head.value + " ");
        printPreBT(head.left);
        printPreBT(head.right);
    }

    public static void main(String[] args) {
        Node n1 = new Node(1);
        Node n2 = new Node(2);
        Node n3 = new Node(3);
        Node n4 = new Node(4);
        Node n5 = new Node(5);
        n1.left = n2;
        n2.left = n4;
        n1.right = n3;
        n3.right = n5;

        System.out.println("=========先序==========");
        Queue<String> queue = preSerialize(n1);
        printQueue(queue);
        Node n11 = preDeserialize(queue);
        printPreBT(n11);
        System.out.println();


        System.out.println("==========中序===========");
        Queue<String> queue1 = inSerialize(n1);
        printQueue(queue1);

        System.out.println("==========后序===========");
        Queue<String> queue2 = posSerialize(n1);
        printQueue(queue2);
        Node n22 = posDeserialize(queue2);
        printPreBT(n22);
        System.out.println();
    }

}
