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

        s = " 3+5 / 2 ";
        System.out.println(calculate(s));
    }


    public static int calculate(String s) {

        s = s.replaceAll("\\s", "");

        Stack<Integer> stack = new Stack<>();

        int ans = 0;
        int currentNum = 0;
        char operation = '+';
        for (int i = 0; i < s.length(); i++) {

            char currentChar = s.charAt(i);

            if (Character.isDigit(currentChar)) {
                currentNum = currentNum * 10 + (currentChar - '0');
            }

            if (!Character.isDigit(currentChar) || i == s.length() - 1) {
                if (operation == '+') {
                    stack.push(currentNum);
                } else if (operation == '-') {
                    stack.push(-currentNum);
                } else if (operation == '*') {
                    stack.push(stack.pop() * currentNum);
                } else if (operation == '/') {
                    stack.push(stack.pop() / currentNum);
                }
                currentNum = 0;
                operation = currentChar;
            }
        }

        while (!stack.isEmpty()) {
            ans += stack.pop();
        }

        return ans;
    }
}
