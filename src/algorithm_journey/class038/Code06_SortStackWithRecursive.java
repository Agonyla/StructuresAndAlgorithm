package algorithm_journey.class038;

/**
 * @author: Agony
 * @create: 2024/7/1 20:16
 * @describe:
 * @link:
 */
public class Code06_SortStackWithRecursive {

    // todo

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
}
