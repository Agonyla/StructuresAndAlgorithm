package algorithm_journey.leetcode;

/**
 * @author: Agony
 * @create: 2025/4/5 16:21
 * @describe: 爬楼梯
 * <a href="https://leetcode.cn/problems/climbing-stairs/description/?envType=company&envId=huawei&favoriteSlug=huawei-three-months">爬楼梯</a>
 */
public class LeetCode70 {

    public static void main(String[] args) {

        System.out.println(climbStairs(2));
        System.out.println(climbStairs(3));
    }

    public static int climbStairs(int n) {

        int[] dp = new int[n];

        return process(n, 0, dp);
    }


    public static int process(int n, int i, int[] dp) {

        if (i == n) {
            return 1;
        }
        if (i > n) {
            return 0;
        }
        if (dp[i] != 0) {
            return dp[i];
        }
        int p1 = process(n, i + 1, dp);
        int p2 = process(n, i + 2, dp);
        dp[i] = p1 + p2;
        return p1 + p2;
    }

}
