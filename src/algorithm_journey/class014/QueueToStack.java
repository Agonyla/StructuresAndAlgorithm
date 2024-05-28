package algorithm_journey.class014;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @author Agony
 * @create 2024/5/26 11:48
 * @describe: 请你仅使用两个队列实现一个后入先出（LIFO）的栈，并支持普通栈的全部四种操作（push、top、pop 和 empty）。
 * <p>
 * 实现 MyStack 类：
 * <p>
 * void push(int x) 将元素 x 压入栈顶。
 * int pop() 移除并返回栈顶元素。
 * int top() 返回栈顶元素。
 * boolean empty() 如果栈是空的，返回 true ；否则，返回 false 。
 * @link: <a href="https://leetcode.cn/problems/implement-stack-using-queues/description/">用队列实现栈</a>
 */
public class QueueToStack {


    /**
     * 每次添加新元素的时候都把前面的元素依次弹出添加到队尾实现栈
     */
    static class MyStack {

        public Queue<Integer> queue;

        public MyStack() {
            queue = new LinkedList<>();
        }

        /**
         * 每次添加新元素的时候都把前面的元素依次弹出添加到队尾
         *
         * @param x
         */
        public void push(int x) {
            queue.offer(x);
            int size = queue.size();
            for (int i = 0; i < size - 1; i++) {
                queue.offer(queue.poll());
            }
        }

        public int pop() {
            return queue.poll();
        }

        public int top() {
            return queue.peek();
        }

        public boolean empty() {
            return queue.isEmpty();
        }
    }


    // 另一种实现
    static class MyStack1 {

        public Queue<Integer> queue;
        public Queue<Integer> help;

        public MyStack1() {
            this.queue = new LinkedList<Integer>();
            this.help = new LinkedList<Integer>();
        }

        public void push(int x) {
            queue.offer(x);
        }

        public int pop() {
            while (queue.size() > 1) {
                help.offer(queue.poll());
            }
            int ans = queue.poll();
            Queue<Integer> temp = queue;
            queue = help;
            help = temp;
            return ans;
        }

        public int top() {
            while (queue.size() > 1) {
                help.offer(queue.poll());
            }
            int ans = queue.poll();
            help.offer(ans);
            Queue<Integer> temp = queue;
            queue = help;
            help = temp;
            return ans;
        }

        public boolean empty() {
            return queue.isEmpty() && help.isEmpty();
        }
    }
}
