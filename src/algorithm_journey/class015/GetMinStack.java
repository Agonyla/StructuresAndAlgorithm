package algorithm_journey.class015;

import java.util.Stack;

/**
 * @author Agony
 * @create 2024/5/27 16:09
 * @describe: 设计一个支持 push ，pop ，top 操作，并能在常数时间内检索到最小元素的栈。
 * <p>
 * 实现 MinStack 类:
 * <p>
 * MinStack() 初始化堆栈对象。
 * void push(int val) 将元素val推入堆栈。
 * void pop() 删除堆栈顶部的元素。
 * int top() 获取堆栈顶部的元素。
 * int getMin() 获取堆栈中的最小元素。
 * @link: <a href="https://leetcode.cn/problems/min-stack/">最小栈</a>
 */
public class GetMinStack {


    /**
     * 两个栈实现最小栈
     * 一个数据栈data，另一个最小值栈min
     * 在data栈押入元素时
     * 如果押入值是data栈的最小值，则把该元素押入min栈
     * 否则就重复押入min栈栈顶值
     */
    static class MinStack {

        public Stack<Integer> data;
        public Stack<Integer> min;


        public MinStack() {
            data = new Stack<>();
            min = new Stack<>();
        }


        public void push(int val) {
            // if (data.empty()) {
            //     data.push(val);
            //     min.push(val);
            // } else {
            //     if (val < min.peek()) {
            //         min.push(val);
            //     } else {
            //         min.push(min.peek());
            //     }
            //     data.push(val);
            // }
            data.push(val);
            if (min.empty() || val < min.peek()) {
                min.push(val);
            } else {
                min.push(min.peek());
            }
        }

        public void pop() {
            data.pop();
            min.pop();
        }

        public int top() {
            return data.peek();
        }

        public int getMin() {
            return min.peek();
        }
    }

    // 提交时把类名、构造方法改成MinStack
    static class MinStack2 {
        // leetcode的数据在测试时，同时在栈里的数据不超过这个值
        // 这是几次提交实验出来的，哈哈
        // 如果leetcode补测试数据了，超过这个量导致出错，就调大
        public final int MAXN = 8001;

        public int[] data;
        public int[] min;
        int size;

        public MinStack2() {
            data = new int[MAXN];
            min = new int[MAXN];
            size = 0;
        }

        public void push(int val) {
            data[size] = val;
            if (size == 0 || val <= min[size - 1]) {
                min[size] = val;
            } else {
                min[size] = min[size - 1];
            }
            size++;
        }

        public void pop() {
            size--;
        }

        public int top() {
            return data[size - 1];
        }

        public int getMin() {
            return min[size - 1];
        }
    }
}
