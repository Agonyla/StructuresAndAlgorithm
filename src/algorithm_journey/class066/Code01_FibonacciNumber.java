package algorithm_journey.class066;

/**
 * @author: Agony
 * @create: 2024/7/24 21:37
 * @describe:
 * @link:
 */
public class Code01_FibonacciNumber {

    // todo

    // 斐波那契数

    // 思路：
    // fib1
    // -> 暴力递归遇到每一项都展开
    // fib2
    // -> 记忆化搜索，从顶到底，遇到每一项先展开，然后将得到的结果记录到数组中
    // 下次再次遇到直接从数组中查询
    // fib3
    // -> 从底到顶，F(i)=F(i-1)+F(i-2)
    // 新建一个长度n+1的数组 int[] dp = new int[n+1]
    // 初始化前两项，for循环，每一项由前两项相加
    // 最后返回 dp[n]
    // fib4
    // -> 在fib3的基础上优化，
    // 不用数组，用两个变量记录，
    // 每次加完之后更新两个变量的值

}
