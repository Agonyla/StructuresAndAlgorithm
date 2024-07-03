package algorithm_journey.class039;

/**
 * @author: Agony
 * @create: 2024/7/3 09:46
 * @describe:
 * @link:
 */
public class Code03_NumberOfAtoms {

    // todo

    // 含有嵌套的分子式求原子数量

    // 思路
    // 实现函数 TreeMap<String, Integer> f(char[] s, int index), 全局变量 int where ->
    // s -> 提供的字符串，
    // index -> 字符串的下标位置
    // 函数内局部变量
    // StringBuilder element -> 用于收集元素
    // TreeMap<String, Integer> ans -> key: 出现的元素，value：出现的次数。作为答案返回
    // TreeMap<String, Integer> pre -> 用来记录下一层收集的答案
    // int count -> 统计元素出现的个数

    // 当遇到大写字母 或者 '(' 时，执行清算
    // 1. 把收集到的元素 element 和 次数 count 放入 ans 中，然后清零
    // 2. 或者把 调用 f() 函数的收集到的 pre * 次数 count 放入 ans 中，然后 把 pre 清空，count 清零
    // 1 和 2 只会执行一个
}
