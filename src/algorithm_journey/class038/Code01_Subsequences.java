package algorithm_journey.class038;

/**
 * @author: Agony
 * @create: 2024/6/28 11:04
 * @describe:
 * @link:
 */
public class Code01_Subsequences {

    // todo

    // 字符串的全部子序列

    // 前言：任何递归都是深度优先遍历！！！

    // 思路
    // 准备 char[] path -> 放置每个路径字符
    // hashset set -> 收集所有字符串
    // 设计 f(char[] arr, int i, int size) 函数
    // arr -> 字符串转成的字符数组
    // i -> 表示当前来到数组的位置
    // size -> 表示 path 路径中存放的字符数量
    // 当我调用 f(arr, i + 1, size+1) -> 表示来到arr的下一个位置，然后把当前字符加入到path中
    // f(arr,i+1,size) -> 表示来到arr的下一个位置，当前字符不加入到path中
    // 因为 path 是数组结构，可以直接覆盖，不需要像 stringBuilder 或 arrayList 做回溯擦除工作
    // 时间复杂度：o(n * 2^n)
}
