package algorithm_journey.leetcode;

import java.util.Stack;

/**
 * @author: Agony
 * @create: 2025/4/5 20:19
 * @describe: 面试题 16.26. 计算器
 * <a href="https://leetcode.cn/problems/calculator-lcci/description/?envType=company&envId=huawei&favoriteSlug=huawei-three-months">面试题 16.26. 计算器</a>
 */
public class LeetCodeCalculate {


    public static void main(String[] args) {
        String s = "3+2*2";
        System.out.println(calculate(s));
    }

    public static int calculate(String s) {
        // 移除所有空格
        s = s.replaceAll("\\s", "");

        // 使用栈来保存中间结果
        Stack<Integer> stack = new Stack<>();

        // 记录当前数字和当前操作符
        int currentNumber = 0;
        char operation = '+';

        for (int i = 0; i < s.length(); i++) {
            char currentChar = s.charAt(i);

            // 如果是数字，更新当前数字
            if (Character.isDigit(currentChar)) {
                currentNumber = currentNumber * 10 + (currentChar - '0');
            }

            // 如果是操作符或者到达字符串末尾，处理当前数字和操作符
            if (!Character.isDigit(currentChar) || i == s.length() - 1) {
                // 根据上一个操作符处理当前数字
                if (operation == '+') {
                    stack.push(currentNumber);
                } else if (operation == '-') {
                    stack.push(-currentNumber);
                } else if (operation == '*') {
                    stack.push(stack.pop() * currentNumber);
                } else if (operation == '/') {
                    stack.push(stack.pop() / currentNumber);
                }

                // 更新操作符和重置当前数字
                operation = currentChar;
                currentNumber = 0;
            }
        }

        // 计算最终结果
        int result = 0;
        while (!stack.isEmpty()) {
            result += stack.pop();
        }

        return result;
    }
}
