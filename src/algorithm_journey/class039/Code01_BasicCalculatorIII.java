package algorithm_journey.class039;

import java.util.ArrayList;

/**
 * 基本计算器 III
 *
 * @author: Agony
 * @create: 2024/7/1 22:41
 * @describe: 实现一个基本的计算器来计算简单的表达式字符串。
 * <p>
 * 表达式字符串只包含非负整数，算符 +、-、*、/ ，左括号 ( 和右括号 ) 。整数除法需要 向下截断 。
 * <p>
 * 你可以假定给定的表达式总是有效的。所有的中间结果的范围均满足 [-231, 231 - 1] 。
 * <p>
 * 注意：你不能使用任何将字符串作为表达式求值的内置函数，比如 eval() 。
 * <p>
 * 输入：s = "2*(5+5*2)/3+(6/2+8)"
 * 输出：21
 * @link: <a href="https://leetcode.cn/problems/basic-calculator-iii/description/">基本计算器 III</a>
 */
public class Code01_BasicCalculatorIII {


    public static void main(String[] args) {

        ArrayList<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(3);
        list.add(2);
        list.add(4);
        list.add(5);

        ArrayList<Character> ops = new ArrayList<>();
        ops.add('+');
        ops.add('-');
        ops.add('*');
        ops.add('-');
        ops.add('+');

        System.out.println(myCompute(list, ops));

        System.out.println("函数计算：==========");

        // String s = "2*(1+4)+3";
        // ans = 13
        String s = "2*(5+5*2)/3+(6/2+8)";
        System.out.println(calculate(s));

    }

    // 含有嵌套的表达式求值

    // 思路
    //
    // 没有括号的表达式求值操作
    // 函数局部变量准备两个 list
    // ArrayList<Integer> numbers, ArrayList<Character> operators
    // 遇到数字操作
    // int cur = 当前数字 - '0', 两个字符相减转成数字
    // 又遇到数字了 cur = cur * 10 + 当前数字 - '0'
    // 直到遇到运算符号了，把当前数字 cur 加入 numbers，运算符号加入 operators，两个list同步加入，加完 cur 清零
    // 如果 operators 链表尾部是 * 或者 / 时，两个链表都拿出最后的元素，numbers 最后的数字 * 或者 / cur
    // 之后再把数字和操作符加入链表
    // 最后一个数字加入 numbers 时，operators 可以直接加入 + 或者 - ，都不影响


    // 表达式含有括号嵌套
    // 设计一个函数 int f(char[] str, int i) 递归调用，全局遍历 int where
    // return 自已负责的这一段计算的结果
    // i -> 下标
    // where -> 递归调用结束后更新现在位于字符数组的位置，为了让上游函数知道从哪继续
    // 过程种 遇到 '(' 调用 f(i + 1) -> cur = f(i+1)
    // f() 遇到 ')' 计算当前栈的 numbers 和 operators，返回结果，返回之前更新 where 的值


    // 嵌套类问题的解题套路
    // 大概过程:
    // 定义全局变量 int where
    // 递归函数f(i) : s[i..]，从i位置出发开始解析，遇到 字符串终止 或 嵌套条件终止 就返回
    // 返回值是f(i)负责这一段的结果
    // f(i)在返回前更新全局变量where，让上级函数通过where知道解析到了什么位置，进而继续
    //
    // 执行细节:
    // 如果f(i)遇到 嵌套条件开始，就调用下级递归去处理嵌套，下级会负责嵌套部分的计算结果
    // f(i)下级处理完成后，f(i)可以根据下级更新的全局变量where，知道该从什么位置继续解析


    /**
     * 含有嵌套的表达式求值
     *
     * @param s 字符串
     * @return 返回该字符串表达式的结果
     */
    public static int calculate(String s) {
        char[] str = s.toCharArray();
        where = 0;
        return f(str, 0);
    }


    // 记录下层结束的位置，上层从次数开始继续流程
    public static int where;

    /**
     * 计算该层的结果
     *
     * @param str 字符串转成的字符数组
     * @param i   数组下标
     * @return 返回计算结果
     */
    public static int f(char[] str, int i) {


        // 加入数字
        ArrayList<Integer> numbers = new ArrayList<>();
        // 加入操作符
        ArrayList<Character> operators = new ArrayList<>();
        // 当前数字
        int cur = 0;

        // 终止条件，来到尾部或者遇到 ')'
        while (i < str.length && str[i] != ')') {

            if (str[i] >= '0' && str[i] <= '9') {
                // 遇到数字
                cur = cur * 10 + str[i++] - '0';

            } else if (str[i] == '(') {
                // 遇到 '('
                // 下层计算得到的结果
                cur = f(str, i + 1);
                i = where + 1;
            } else {
                // 遇到操作符
                addToLists(numbers, operators, cur, str[i++]);
                cur = 0;
            }
        }

        // 处理剩下部分
        // 如 str = "3*(1+4)+3+2"
        // 下层函数遇到 ')' 退出循环，此时 cur=4，通过这里把值加入链表
        // 并标记')'的位置
        // 然后把自己层的两个链表计算结果返回上层
        // 上层用 cur 接受下层的计算结果
        // 然后从 ')' 的下一个位置开始
        // 此时上层就会从 + 开始依次执行后面的 "3+2"
        // 循环结束之后，此时 "3+:" 已经放入链表中
        // 这时候再把 cur=2，和 "+" 放到链表中
        addToLists(numbers, operators, cur, '+');
        // 标记 ')' 位置
        where = i;
        // 返回该层的计算结果给上层
        return compute(numbers, operators);
    }


    /**
     * 往链表添加操作
     *
     * @param numbers   数字链表
     * @param operators 符号链表
     * @param cur       当前数字
     * @param operator  当前操作符号
     */
    public static void addToLists(ArrayList<Integer> numbers, ArrayList<Character> operators, int cur, char operator) {

        int n = numbers.size();
        if (n == 0 || operators.get(n - 1) == '+' || operators.get(n - 1) == '-') {
            numbers.add(cur);
            operators.add(operator);
        } else {
            Character op = operators.get(n - 1);
            Integer last = numbers.get(n - 1);
            if (op == '*') {
                numbers.set(n - 1, last * cur);
                operators.set(n - 1, operator);
            } else {
                numbers.set(n - 1, last / cur);
                operators.set(n - 1, operator);
            }
        }
    }


    /**
     * 从两个链表中计算结果
     * 这里只需要处理 '+' '-' 即可
     * '*' '/' 已经在 addToLists 被处理了
     *
     * @param numbers   数字链表
     * @param operators 字符链表
     * @return 返回计算结果
     */
    public static int compute(ArrayList<Integer> numbers, ArrayList<Character> operators) {

        int n = numbers.size();
        int ans = numbers.get(0);

        for (int i = 1; i < n; i++) {
            if (operators.get(i - 1) == '+') {
                ans += numbers.get(i);
            } else {
                ans -= numbers.get(i);
            }
        }
        return ans;
    }


    /**
     * 测试两个链表的计算
     *
     * @param numbers   数字链表
     * @param operators 字符链表
     * @return 计算结果
     */
    @Deprecated
    public static int myCompute(ArrayList<Integer> numbers, ArrayList<Character> operators) {

        Integer ans = numbers.remove(0);

        while (!numbers.isEmpty()) {
            int num = numbers.remove(0);
            char op = operators.remove(0);
            if (op == '+') {
                ans += num;
            } else if (op == '-') {
                ans -= num;
            } else if (op == '*') {
                ans *= num;
            } else {
                ans /= num;
            }
        }
        return ans;
    }


}


























