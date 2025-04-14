package algorithm_journey.hwod;

import java.util.Scanner;
import java.util.Stack;

/**
 * @author: Agony
 * @create: 2025/4/14 22:41
 * @describe: 四则运算
 * <a href="https://www.nowcoder.com/practice/9999764a61484d819056f807d2a91f1e?tpId=37&rp=1&sourceUrl=%2Fexam%2Foj%2Fta%3FtpId%3D37&difficulty=3&judgeStatus=&tags=&title=&gioEnter=menu">四则运算</a>
 */
public class HJ50 {

    public static void main(String[] args) {


        Scanner scanner = new Scanner(System.in);

        String s = scanner.nextLine();
        int ans = calculate(s);
        System.out.println(ans);


    }

    public static int index = 0;

    public static int calculate(String s) {


        Stack<Integer> stack = new Stack<>();
        char operator = '+';
        int num = 0;

        while (index < s.length()) {

            char c = s.charAt(index++);
            if (Character.isDigit(c)) {
                num = num * 10 + (c - '0');
            }

            if (c == '{' || c == '[' || c == '(') {
                num = calculate(s);
            }

            if (!Character.isDigit(c) || index == s.length()) {

                switch (operator) {
                    case '+':
                        stack.push(num);
                        break;
                    case '-':
                        stack.push(-num);
                        break;
                    case '*':
                        stack.push(stack.pop() * num);
                        break;
                    case '/':
                        stack.push(stack.pop() / num);
                        break;
                }

                operator = c;
                num = 0;

                if (c == '}' || c == ']' || c == ')') {
                    break;
                }
            }

        }

        int ans = 0;
        while (!stack.isEmpty()) {
            ans += stack.pop();
        }

        return ans;
    }
}
