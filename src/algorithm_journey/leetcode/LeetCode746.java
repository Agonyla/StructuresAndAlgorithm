package algorithm_journey.leetcode;

import java.util.Arrays;

/**
 * @author: Agony
 * @create: 2025/4/6 17:06
 * @describe: 使用最小花费爬楼梯
 * <a href="https://leetcode.cn/problems/min-cost-climbing-stairs/?envType=company&envId=huawei&favoriteSlug=huawei-three-months">使用最小花费爬楼梯</a>
 */
public class LeetCode746 {


    public static void main(String[] args) {

        int[] cost = {10, 15, 20};
        System.out.println(minCostClimbingStairs(cost));


        cost = new int[]{1, 100, 1, 1, 1, 100, 1, 1, 100, 1};
        System.out.println(minCostClimbingStairs(cost));
    }

    public static int minCostClimbingStairs(int[] cost) {
        int n = cost.length;
        int[] dp = new int[n];

        Arrays.fill(dp, -1);

        int p1 = process(0, n, cost, dp);
        int p2 = process(1, n, cost, dp);

        return Math.min(p1, p2);
    }


    public static int process(int i, int n, int[] cost, int[] dp) {

        if (i >= n) {
            return 0;
        }

        if (dp[i] != -1) {
            return dp[i];
        }

        int p1 = cost[i] + process(i + 1, n, cost, dp);
        int p2 = cost[i] + process(i + 2, n, cost, dp);

        dp[i] = Math.min(p1, p2);
        return dp[i];
    }


    public int minCostClimbingStairs2(int[] cost) {
        int len = cost.length;
        int[] dp = new int[len + 1];
        dp[0] = 0;
        dp[1] = 0;
        for (int i = 2; i <= len; i++) {
            dp[i] = Math.min((dp[i - 1] + cost[i - 1]), (dp[i - 2] + cost[i - 2]));
        }
        return dp[len];
    }

}
