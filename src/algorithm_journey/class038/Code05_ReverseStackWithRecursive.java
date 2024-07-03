package algorithm_journey.class038;

import java.util.Stack;

/**
 * @author: Agony
 * @create: 2024/7/1 20:04
 * @describe:
 * @link:
 */
public class Code05_ReverseStackWithRecursive {


    public static void main(String[] args) {

        Stack<Integer> stack = new Stack<>();
        stack.push(1);
        stack.push(2);
        stack.push(3);
        stack.push(4);
        stack.push(5);

        stack.forEach(num -> System.out.print(num + " "));
        System.out.println();

        reverse(stack);

        stack.forEach(num -> System.out.print(num + " "));
        System.out.println();
    }

    // 用递归函数逆序栈
    // 熟练掌握 Code05, Code06 对递归的熟悉程度会有质的飞跃

    // 实现 bottomOut(Stack<Integer> stack) -> 返回栈底的元素，上面的元素压下来
    // 用递归函数自己带空间，先取出来，再放进去

    // reverse 函数， 调用 bottomOut 拿出栈底的元素，然后调用自己，再把拿出来的元素放进去


    /**
     * 逆序栈 -> 把栈逆序
     * <p>
     * 拿出底部元素，让下层把栈逆序
     * 此时栈已逆序，再把底部元素压进去
     *
     * @param stack
     */
    public static void reverse(Stack<Integer> stack) {
        // base case
        if (stack.isEmpty()) {
            return;
        }
        int bottom = bottomOut(stack);
        reverse(stack);
        stack.push(bottom);
    }

    /**
     * 返回栈底元素，上面元素保持相对次序压下来
     * <p>
     * 相当于一层一层调用，每一层把栈顶元素拿出来，然后用变量记录
     * 每一层拿一个，到最后一个了拿完直接返回给上层，上层把之前记录的变量压入，再把拿到的值返回给上层
     * 有一种从上往下把一层层的东西移开，把最下面的取出来，再把一层层复原的感觉
     *
     * @param stack
     * @return
     */
    public static int bottomOut(Stack<Integer> stack) {

        Integer ans = stack.pop();
        if (stack.isEmpty()) {
            return ans;
        }

        int last = bottomOut(stack);
        stack.push(ans);
        return last;
    }

}













