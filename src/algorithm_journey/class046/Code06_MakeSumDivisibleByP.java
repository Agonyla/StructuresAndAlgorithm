package algorithm_journey.class046;

/**
 * @author: Agony
 * @create: 2024/7/17 10:21
 * @describe:
 * @link:
 */
public class Code06_MakeSumDivisibleByP {

    // todo

    // 使数组和能被P整除

    // 思路
    // 设计一个map
    // key：前缀和%p的余数，value：最晚出现的位置

    // 整体的余数为mod，0～i余数为cur
    // 1.cur>=mod -> 找cur-mod最晚出现的位置
    // 2. cur<mod -> 找cur+p-mod最晚出现的位置
    // -> 可以统一写成 (cur+p-mod)%p 最晚出现的位置

    // 假设p=7，假设数组整体余数为4，mod=4
    // 0～i位置余数为6，cur=6
    // 那么i位置向左尽可能短的赵余数为4的子数组
    // -> 找前缀和为2，最晚出现的位置
    // 0～i位置余数为2，cur=2<mod
    // 那么就 p+cur-mod=7+2-4=5，找前缀和为5最早出现的位置
    // j位置的前缀和%p=5，j+1到i位置的累加和%p=4
    // 整体(5+4)%p=2
    // 同余原理：(a+b)%p = (a%p + b%p)%p
}
