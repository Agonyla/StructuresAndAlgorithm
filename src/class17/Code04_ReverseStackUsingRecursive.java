package class17;

import java.util.Stack;

/**
 * @Author Agony
 * @Create 2023/9/19 10:18
 * @Version 1.0
 * 给定一个栈，请逆序这个栈，不能申请额外的数据结构，只能使用递归函数
 */
public class Code04_ReverseStackUsingRecursive {

    public static void reverse(Stack<Integer> stack) {
        if (stack.isEmpty()) {
            return;
        }
        int i = f(stack);
        reverse(stack);
        stack.push(i);
    }

    // 栈底元素移除掉
    // 上面的元素盖下来
    // 返回移除掉的栈底元素
    public static int f(Stack<Integer> stack) {
        // base case
        int res = stack.pop();
        if (stack.isEmpty()) {
            return res;
        } else {
            int last = f(stack);
            stack.push(res);
            return last;
        }

    }

    public static void main(String[] args) {
        Stack<Integer> sta = new Stack<>();

        sta.add(3);
        sta.add(2);
        sta.add(1);
        reverse(sta);
        while (!sta.isEmpty()) {
            System.out.println(sta.pop());
        }
    }
}
