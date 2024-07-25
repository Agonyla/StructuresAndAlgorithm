package algorithm_journey.class066;

/**
 * @author: Agony
 * @create: 2024/7/25 09:58
 * @describe:
 * @link:
 */
public class Code02_MinimumCostForTickets {

    // todo

    // 最低票价

    // 思路：
    // minCostTickets1
    // -> 暴力递归
    // 设计 int f1(int[] days, int costs[], int i)
    // return -> 从i位置出发，返回最小的的花费
    // days -> 天数数组
    // costs -> 消费数组
    // i -> days 数组下标
    // 在当前位置枚举 3 种不同方案，调用下层函数，比较返回最小值
    // 当前位置为 i， j=i
    // days[i]+方案持续的天数>days[j], j++
    //
    // minCostTickets2
    // 记忆化搜索，从顶到底的动态规划
    // 三种方案的选择可能都会涉及到，从第 i 天出发的最小花费
    // 存在大量重复行为
    // 设计缓存表。 int[] dp = new int[days.length]
    // 表示从第 i 天出发的最小花费
    // 在返回结果前，更新缓存表，下次直接从表中取
    //
    // minCostTickets3
    // 严格位置依赖的动态规划
    // 从底到顶的动态规划
    // 题目要求从第0天出发，需要的最小花费
    // 那么就从右往左开始，把dp表填满，返回dp[0]
    // 从右往左返回都是最优解
    // 依然三种方案枚举尝试
    // 然后比较方案选择的最小值
    // 如 days=[1,2,3,4,6], costs=[2,7,15]
    // dp[5]=0
    // dp[4]=2
    // dp[3]=4
    // dp[2]=6
    // dp[1]，在第2天
    // 方案1:
    // i=1，j=1，持续天数=1，days[i]+持续天数>days[j]
    // -> j=2
    // 此时 2+1 == 3
    // dp[1]=costs[0]+dp[2]=2+6=8
    // 方案2:
    // i=1, j=1, 持续天数=7，days[i]+持续天数>days[j]
    // -> j=5
    // dp[1]=costs[1]+dp[5]=7
    // 方案3:同理略
    // 同理：dp[0] = min(cost[0]+dp[6], cost[1]+dp[5])=7


}
