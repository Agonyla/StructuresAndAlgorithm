package algorithm_journey.class041;

/**
 * @author: Agony
 * @create: 2024/7/5 15:19
 * @describe:
 * @link:
 */
public class Code02_NthMagicalNumber {

    // todo

    // 一个正整数如果能被 a 或 b 整除，那么它是神奇的。
    // 给定三个整数 n , a , b ，返回第 n 个神奇的数字。

    // 思路
    // 第 n 个神奇的数字一定在[0,  min(a,b)*n] 这个范围内
    // 设计函数 int f(int x) -> 实现在 [1, x] 范围内一共有多少个神奇的数字
    // 个数 =  x/a+ x/b - x/(a和b的最小公倍数)
    // 左边界 0，右边界 min(a,b)*n
    // 如果多了往左二分，如果少了往右二分

}
