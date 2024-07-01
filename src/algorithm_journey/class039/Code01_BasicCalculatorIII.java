package algorithm_journey.class039;

/**
 * @author: Agony
 * @create: 2024/7/1 22:41
 * @describe:
 * @link:
 */
public class Code01_BasicCalculatorIII {

    // todo

    // 含有嵌套的表达式求值

    // 思路
    //
    // 没有括号的表达式求值操作
    // 准备两个栈，Stack<Integer> stack1, Stack<char> stack2
    // 遇到数字操作
    // int cur = 当前数字 - '0', 两个字符相减转成数字
    // 又遇到数字了 cur = cur * 10 + 当前数字 - '0'
    // 直到遇到运算符号了，把当前数字压入 stack1，运算符号压入 stack2，cur = 0，两个栈同步压入
    // 如果 stack2 栈顶是 * 或者 / 时，两个栈都弹出栈顶元素，栈顶数字 * 或 / cur
    // 再把 结果 和 遍历得到的符号位 压入栈
    // 最后一个数字压入时，符号栈可以直接压入 + 或者 - ，都不影响

    // 表达式含有括号嵌套
    // 设计一个函数 int f(int i) 递归调用，全局遍历 int where
    // return 自已负责的这一段计算的结果
    // i -> 下标
    // where -> 递归调用结束后更新现在位于字符数组的位置，为了让上游函数知道从哪继续
    // 过程种 遇到 '(' 调用 f(i + 1) -> cur = f(i+1)
    // f() 遇到 ')' 计算当前栈的 stack1 和 stack2，返回结果，返回之前更新 where 的值
    // 可以不用栈，用 list 代替
    //
    // todo 补充一下ppt里的嵌套解析套路
}
