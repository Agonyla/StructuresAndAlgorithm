package algorithm_journey.class049;

/**
 * @author: Agony
 * @create: 2024/7/24 9:58
 * @describe:
 * @link:
 */
public class Code04_GasStation {

    // todo

    // 加油站
    // 以上题目都是用结尾情况下的答案
    // 该题是用开头讨论

    // 思路：
    // 右窗口r：表示要进来的数
    // r=(l+length++)%n
    // 可以用来表示循环
    // 如 数组长度n=7，当前l在3位置，此时数组长度length=0
    // r=(l+length++)%n=3%7=3,表示3位置的数字要进来了，length=1
    //
    // 左窗口从0开始，右窗口往右边扩
    // 如果累加和 sum>=0，右窗口不断往右扩
    // 当sum小于0时，
    // 跳出循环，左窗口往右移动，且窗口长度减1
    // sum一直>=0，右窗口不断向右扩
    // 直到窗口长度等于数组长度


}
