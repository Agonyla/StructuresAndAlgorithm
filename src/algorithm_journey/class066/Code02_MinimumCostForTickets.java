package algorithm_journey.class066;

import java.util.Arrays;

/**
 * 最低票价
 *
 * @author: Agony
 * @create: 2024/7/25 09:58
 * @describe: 最低票价
 * 在一个火车旅行很受欢迎的国度，你提前一年计划了一些火车旅行。在接下来的一年里，你要旅行的日子将以一个名为 days 的数组给出。每一项是一个从 1 到 365 的整数。
 * <p>
 * 火车票有 三种不同的销售方式 ：
 * <p>
 * 一张 为期一天 的通行证售价为 costs[0] 美元；
 * 一张 为期七天 的通行证售价为 costs[1] 美元；
 * 一张 为期三十天 的通行证售价为 costs[2] 美元。
 * 通行证允许数天无限制的旅行。 例如，如果我们在第 2 天获得一张 为期 7 天 的通行证，那么我们可以连着旅行 7 天：第 2 天、第 3 天、第 4 天、第 5 天、第 6 天、第 7 天和第 8 天。
 * <p>
 * 返回 你想要完成在给定的列表 days 中列出的每一天的旅行所需要的最低消费 。
 * @link: <a href="https://leetcode.cn/problems/minimum-cost-for-tickets/description/">最低票价</a>
 */
public class Code02_MinimumCostForTickets {


    public static void main(String[] args) {


        //     days = [1,4,6,7,8,20], costs = [2,7,15]
        int[] days = {1, 4, 6, 7, 8, 20};
        int[] costs = {2, 7, 15};
        System.out.println(mincostTickets1(days, costs));
        System.out.println(mincostTickets2(days, costs));
        System.out.println(mincostTickets3(days, costs));


        days = new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 30, 31};
        costs = new int[]{2, 7, 15};
        System.out.println(mincostTickets1(days, costs));
        System.out.println(mincostTickets2(days, costs));
        System.out.println(mincostTickets3(days, costs));
    }

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


    public static int[] durations = {1, 7, 30};

    /**
     * 最低票价
     * 暴力递归
     *
     * @param days
     * @param costs
     * @return
     */
    public static int mincostTickets1(int[] days, int[] costs) {

        return f1(days, costs, 0);
    }

    /**
     * 暴力递归
     *
     * @param days
     * @param costs
     * @param i     当前来到第i天
     * @return
     */
    public static int f1(int[] days, int[] costs, int i) {

        if (i == days.length) {
            return 0;
        }

        int ans = Integer.MAX_VALUE;

        // 三种选择
        // j 表示当前天数
        for (int choice = 0, j = i; choice < costs.length; choice++) {

            while (j < days.length && days[j] < days[i] + durations[choice]) {
                j++;
            }
            ans = Math.min(ans, costs[choice] + f1(days, costs, j));
        }
        return ans;
    }

    /**
     * 最低票价
     * 记忆化搜索
     *
     * @param days
     * @param costs
     * @return
     */
    public static int mincostTickets2(int[] days, int[] costs) {

        int[] dp = new int[days.length + 1];
        Arrays.fill(dp, -1);
        return f2(days, costs, 0, dp);
    }

    /**
     * 记忆化搜索
     *
     * @param days
     * @param costs
     * @param i
     * @param dp    dp表
     * @return
     */
    public static int f2(int[] days, int[] costs, int i, int[] dp) {

        if (i == days.length) {
            return 0;
        }
        if (dp[i] != -1) {
            return dp[i];
        }
        int ans = Integer.MAX_VALUE;

        // 三种选择
        // j 表示当前天数
        for (int choice = 0, j = i; choice < costs.length; choice++) {

            while (j < days.length && days[j] < days[i] + durations[choice]) {
                j++;
            }
            ans = Math.min(ans, costs[choice] + f2(days, costs, j, dp));
        }
        dp[i] = ans;
        return ans;
    }


    /**
     * 最低票价
     * 从底到顶
     *
     * @param days
     * @param costs
     * @return
     */
    public static int mincostTickets3(int[] days, int[] costs) {

        int n = days.length;
        int[] dp = new int[n + 1];
        Arrays.fill(dp, Integer.MAX_VALUE);

        dp[n] = 0;

        for (int i = n - 1; i >= 0; i--) {
            for (int choice = 0, j = i; choice < costs.length; choice++) {

                while (j < days.length && days[j] < days[i] + durations[choice]) {
                    j++;
                }
                dp[i] = Math.min(dp[i], costs[choice] + dp[j]);
            }
        }
        return dp[0];
    }
}











