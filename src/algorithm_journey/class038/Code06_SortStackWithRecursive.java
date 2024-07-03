package algorithm_journey.class038;

import java.util.Stack;

/**
 * @author: Agony
 * @create: 2024/7/1 20:16
 * @describe:
 * @link:
 */
public class Code06_SortStackWithRecursive {


    public static void main(String[] args) {
        Stack<Integer> stack = new Stack<>();

        stack.push(3);
        stack.push(4);
        stack.push(5);
        stack.push(5);
        stack.push(2);

        int depth = a(stack);
        System.out.println("depth = " + depth);
        int max = b(stack, depth);
        System.out.println("max = " + max);
        int num = c(stack, depth, max);
        System.out.println("num = " + num);

        while (!stack.isEmpty()) {
            System.out.println(stack.pop());
        }
        stack.push(3);
        stack.push(4);
        stack.push(5);
        stack.push(5);
        stack.push(2);

        System.out.println("沉底测试：========");
        d(stack, depth, max, num);
        while (!stack.isEmpty()) {
            System.out.println(stack.pop());
        }

        System.out.println("排序测试：（forEach打印是从栈底到栈顶打印的）");
        stack.push(3);
        stack.push(4);
        stack.push(5);
        stack.push(5);
        stack.push(2);
        System.out.println("排序前：======");
        stack.forEach(System.out::println);

        System.out.println("排序后：=======");
        sortStack(stack);
        stack.forEach(System.out::println);
    }

    // 用递归函数排序栈

    // 用递归函数 a(Stack<Integer> stack) -> 统计栈的深度
    // 用递归函数 b(Stack<Integer> stack, int depth) -> 求出栈的最大值
    // 用递归函数 c(Stack<Integer> stack, int depth, int max) -> 找出最大值的个数
    // 用递归函数 d(Stack<Integer> stack, int depth, int max, int times) -> 让所有的最大值撑底部, 其他数字相对次序不变

    // b(depth - times) -> 新的最大值
    // c(depth - times, max) -> 新的最大值出现的个数
    // d(depth - times, max, newTimes) -> 让新的所有的最大值撑底部, 其他数字相对次序不变
    // ...
    // 循环执行，指导 depth = 0


    /**
     * 排序栈
     *
     * @param stack 栈
     */
    public static void sortStack(Stack<Integer> stack) {
        int depth = a(stack);
        while (depth != 0) {
            int max = b(stack, depth);
            int num = c(stack, depth, max);
            d(stack, depth, max, num);
            depth -= num;
        }
    }


    /**
     * 统计栈的深度
     * <p>
     * 先把栈顶弹出
     * 调用下层，获得下层深度
     * 把栈顶压入
     * 返回深度
     *
     * @param stack 栈
     * @return 返回栈的深度
     */
    public static int a(Stack<Integer> stack) {
        if (stack.isEmpty()) {
            return 0;
        }
        Integer top = stack.pop();
        int depth = a(stack);
        stack.push(top);
        return depth + 1;
    }


    /**
     * 求出栈的最大值
     * <p>
     * 先把栈顶弹出
     * 调用下层获得下层返回的最大值
     * 把栈顶压入
     * 比较返回最大值
     *
     * @param stack 栈
     * @param depth 栈的深度
     * @return 返回栈内元素的最大值
     */
    public static int b(Stack<Integer> stack, int depth) {


        if (depth == 0) {
            return Integer.MIN_VALUE;
        }
        int top = stack.pop();
        int maxValue = b(stack, depth - 1);
        stack.push(top);

        return Math.max(top, maxValue);
    }


    /**
     * 找出最大值的个数
     *
     * @param stack 栈
     * @param depth 栈深
     * @param max   栈的最大值
     * @return 最大值的个数
     */
    public static int c(Stack<Integer> stack, int depth, int max) {

        if (depth == 0) {
            return 0;
        }
        Integer top = stack.pop();
        int num = c(stack, depth - 1, max);
        stack.push(top);
        return num + (top == max ? 1 : 0);
    }


    /**
     * 让最大数沉底，其他次序相对不变
     * 栈顶弹出，调用下层函数
     * 当depth==0时，直接把最大值压入
     * 调用函数返回后，如果栈顶的值不是最大值就压入
     *
     * @param stack 栈
     * @param depth 栈深
     * @param max   栈的最大值
     * @param num   最大值的个数
     */
    public static void d(Stack<Integer> stack, int depth, int max, int num) {


        // 这里不能用 stack.isEmpty() 判断
        // 如 stack -> 3,2,4,5,5,1
        // 沉底 -> 3,2,4,1,5,5
        // 之后在想让最大值4沉底 depth=4
        // 上面 a,b,c那几个函数也类似
        if (depth == 0) {
            for (int i = 0; i < num; i++) {
                stack.push(max);
            }
            return;
        }
        Integer top = stack.pop();
        d(stack, depth - 1, max, num);
        if (top != max) {
            stack.push(top);
        }
    }

}














