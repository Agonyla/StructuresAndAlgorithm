package algorithm_journey.class014;

import java.util.Stack;

/**
 * @author Agony
 * @create 2024/5/25 22:02
 * @describe: 请你仅使用两个栈实现先入先出队列。队列应当支持一般队列支持的所有操作（push、pop、peek、empty）：
 * <p>
 * 实现 MyQueue 类：
 * <p>
 * void push(int x) 将元素 x 推到队列的末尾
 * int pop() 从队列的开头移除并返回元素
 * int peek() 返回队列开头的元素
 * boolean empty() 如果队列为空，返回 true ；否则，返回 false
 * @link: <a href="https://leetcode.cn/problems/implement-queue-using-stacks/description/">用栈实现队列</a>
 */
public class StackToQueue {

    /**
     * 使用两个栈实现队列
     * 加入的数添加到in栈，再由in栈弹出到out栈
     * out栈弹出实现队列先进先出
     */
    static class MyQueue {

        public Stack<Integer> in;
        public Stack<Integer> out;


        public MyQueue() {
            in = new Stack<>();
            out = new Stack<>();
        }

        /**
         * in 栈向 out 栈倒数据
         * 1. out栈空了才能倒数据
         * 2. in栈要一次性倒完所有数据
         */
        public void inToOut() {
            if (empty()) {
                return;
            }
            if (out.isEmpty()) {
                while (!in.isEmpty()) {
                    out.push(in.pop());
                }
            }
        }

        public void push(int x) {
            in.push(x);
            inToOut();
        }

        public int pop() {
            inToOut();
            return out.pop();
        }

        public int peek() {
            inToOut();
            return out.peek();
        }

        public boolean empty() {
            return in.isEmpty() && out.isEmpty();
        }
    }
}
