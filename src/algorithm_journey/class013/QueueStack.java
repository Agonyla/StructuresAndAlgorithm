package algorithm_journey.class013;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * @author Agony
 * @create 2024/5/25 18:15
 * @describe: 队列、栈的实现
 */
public class QueueStack {

    // 队列实现

    /**
     * java内部实现
     */
    public static class Queue1 {

        public Queue<Integer> queue;

        public Queue1() {
            queue = new LinkedList<>();
        }

        // 调用任何方法之前，先调用这个方法来判断队列内是否有东西
        public boolean isEmpty() {
            return queue.isEmpty();
        }

        // 向队列中加入num，加到尾巴
        public void offer(int num) {
            queue.offer(num);
        }

        // 从队列拿，从头拿
        public int poll() {
            return queue.poll();
        }

        // 返回队列头的元素但是不弹出
        public int peek() {
            return queue.peek();
        }

        // 返回目前队列里有几个数
        public int size() {
            return queue.size();
        }
    }

    /**
     * 数组实现
     */
    public static class Queue2 {

        public int[] queue;
        public int l;
        public int r;

        public Queue2(int size) {
            this.queue = new int[size];
            l = 0;
            r = 0;
        }

        /**
         * 队列是否为空
         *
         * @return
         */
        public boolean isEmpty() {
            return r == l;
        }

        /**
         * 加入队列
         *
         * @param number
         */
        public void offer(int number) {
            queue[r++] = number;
        }

        /**
         * 弹出队列
         *
         * @return
         */
        public int poll() {
            return queue[l++];
        }

        /**
         * 队列头节点
         *
         * @return
         */
        public int head() {
            return queue[l];
        }

        /**
         * 队列尾节点
         *
         * @return
         */
        public int tail() {
            return queue[r - 1];
        }

        /**
         * 队列大小
         *
         * @return
         */
        public int size() {
            return r - l;
        }


    }


    // 栈实现

    /**
     * java内部实现
     */
    public static class Stack1 {
        public Stack<Integer> stack;

        public Stack1() {
            stack = new Stack<>();
        }

        // 调用任何方法之前，先调用这个方法来判断栈内是否有东西
        public boolean isEmpty() {
            return stack.isEmpty();
        }

        public void push(int num) {
            stack.push(num);
        }

        public int pop() {
            return stack.pop();
        }

        public int peek() {
            return stack.peek();
        }

        public int size() {
            return stack.size();
        }
    }

    /**
     * 数组实现
     */
    public static class Stack2 {

        public int[] stack;
        public int size;

        public Stack2(int number) {
            stack = new int[number];
            size = 0;
        }

        // 调用任何方法之前，先调用这个方法来判断栈内是否有东西
        public boolean isEmpty() {
            return size == 0;
        }

        public void push(int num) {
            stack[size++] = num;
        }

        public int pop() {
            return stack[--size];
        }

        public int peek() {
            return stack[size - 1];
        }

        public int size() {
            return size;
        }
    }
}
