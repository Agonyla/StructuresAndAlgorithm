package algorithm_journey.leetcode;

import java.util.Arrays;

/**
 * @author: Agony
 * @create: 2025/4/5 13:58
 * @describe: 不同路径
 * <a href="https://leetcode.cn/problems/unique-paths/?envType=company&envId=huawei&favoriteSlug=huawei-three-months">不同路径</a>
 */
public class LeetCode62 {


    public static void main(String[] args) {


        System.out.println(uniquePaths(3, 7));
        System.out.println(uniquePaths(3, 2));
        System.out.println(uniquePaths(7, 3));
        System.out.println(uniquePaths(3, 3));

        System.out.println("----------------------");

        System.out.println(uniquePaths2(3, 7));
        System.out.println(uniquePaths2(3, 2));
        System.out.println(uniquePaths2(7, 3));
        System.out.println(uniquePaths2(3, 3));

    }

    public static int uniquePaths(int m, int n) {

        int[][] dp = new int[m][n];

        for (int i = 0; i < m; i++) {
            Arrays.fill(dp[i], -1);
        }


        return process(m, n, 0, 0, dp);
    }


    public static int process(int m, int n, int i, int j, int[][] dp) {

        if (i > m - 1 || j > n - 1) {
            return 0;
        }

        if (i == m - 1 && j == n - 1) {
            return 1;
        }
        if (dp[i][j] != -1) {
            return dp[i][j];
        }

        int p1 = process(m, n, i + 1, j, dp);
        int p2 = process(m, n, i, j + 1, dp);

        dp[i][j] = p1 + p2;
        return p1 + p2;

    }


    public static int uniquePaths2(int m, int n) {

        int[][] dp = new int[m][n];


        dp[m - 1][n - 1] = 1;

        for (int i = m - 2; i >= 0; i--) {
            dp[i][n - 1] += dp[i + 1][n - 1];
        }

        for (int j = n - 2; j >= 0; j--) {
            dp[m - 1][j] += dp[m - 1][j + 1];
        }

        for (int i = m - 2; i >= 0; i--) {

            for (int j = n - 2; j >= 0; j--) {

                dp[i][j] = dp[i + 1][j] + dp[i][j + 1];

            }
        }

        return dp[0][0];
    }


}
