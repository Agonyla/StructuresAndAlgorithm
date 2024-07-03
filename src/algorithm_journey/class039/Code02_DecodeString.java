package algorithm_journey.class039;

/**
 * @author: Agony
 * @create: 2024/7/3 09:45
 * @describe:
 * @link:
 */
public class Code02_DecodeString {

    // todo

    // 含有嵌套的字符串解码

    // 思路
    // 实现一个函数 f(int index)，全局变量 int where
    // index -> 下标位置
    // 函数内部局部变量 String path -> 路径，int count -> 出现的数字
    // 当在 j 位置遇到 '[' 时，调用 f(j+1)，把下一层的返回和 count 相乘(往 path 中添加 count 次数)，count清空。
    // 当在 k 位置遇到 ']' 时，将 where 更新成 k，并将改层的 path 返回。
    // 上层从 where + 1 处继续。
}
