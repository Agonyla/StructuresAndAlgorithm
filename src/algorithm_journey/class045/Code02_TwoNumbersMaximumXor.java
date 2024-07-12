package algorithm_journey.class045;

/**
 * @author: Agony
 * @create: 2024/7/12 10:12
 * @describe:
 * @link:
 */
public class Code02_TwoNumbersMaximumXor {


    // todo

    // 数组中两个数的最大异或值

    // 思路
    // 前缀树实现
    // 把数组中的所有数构建成一棵前缀树
    // 构建的时候先把数组中的最大值取出，int 类型 31 位，如果最大值最高位在15位，那么前的0，就可以直接省略了，不需要浪费空间去构建
    //
    // 数组中的每一个数和前缀树中的值去异或求最大值

    // hashset实现
    // 先取出最大值，从最大值的最高位开始构建
    // want -> 想要的状态位
    // num -> 实际的状态位，只保留最高位到i位置的状态，i一下的位置都置0
    // 如果 num 和 set 中的某个数 x 异或 能达成 want，就让 ans = want
    // 即 want = num ^ x
    // 那么 只要去set中查询是否存在 x ，能达成want
    // -> set.contains(x) -> x = num * want

    
}
