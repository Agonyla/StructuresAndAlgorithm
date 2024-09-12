package algorithm_journey.class068;

import java.util.Arrays;

/**
 * 有效涂色问题
 *
 * @author: Agony
 * @create: 2024/9/6 11:56
 * @describe: 给定n、m两个参数
 * 一共有n个格子，每个格子可以涂上一种颜色，颜色在m种里选
 * 当涂满n个格子，并且m种颜色都使用了，叫一种有效方法
 * 求一共有多少种有效的涂色方法
 * 1 <= n, m <= 5000
 * 结果比较大请 % 1000000007 之后返回
 * 对数器验证
 * @link:
 */
public class Code04_FillCellsUseAllColorsWays {


    // 有效涂色问题
    //
    // 对数器验证：暴力递归 -> 自己写
    //
    // 动态规划
    //
    // 设计 int[][] dp = new int[][]
    // dp[i][j] 表示 前i个格子已经涂好了j种颜色的方法
    // 可能性分析：
    // 1. 前i-1个格子已经涂好了j种颜色
    // -> dp[i][j] = dp[i-1][j] * j (从之前涂过的颜色中选一种涂)
    // 2. 前i-1个格子已经涂好了j-1种颜色
    // -> dp[i][j] = dp[i-1][j-1] * (m - (j-1))
    // 位置依赖：
    // 每个位置依赖上面和左上的值
    // 填表顺序：
    // 从左往右，从上往下
    // 特殊位置分析：
    // 第0行，0个格子，多种颜色 -> 都是0
    // 第0列，0种颜色，多个格子 -> 都是0
    // 第1行，1个格子，1种颜色 -> dp[1][1]=m ; 1个格子，多种颜色 -> dp[1][2...]=0
    // 第1列，一种颜色，多个格子 -> dp[2...][1]=m
    //
    // 动态规划 + 空间压缩
    // 自己写❗❗❗


    public static void main(String[] args) {


        int N = 9;
        int M = 9;
        System.out.println("功能测试开始");
        for (int n = 1; n <= N; n++) {
            for (int m = 1; m <= M; m++) {
                int ans1 = ways1(n, m);
                int ans2 = waysTest(n, m);
                int ans3 = ways2(n, m);
                if (ans1 != ans2 || ans1 != ans3) {
                    System.out.println("出错了!");
                }
            }
        }
        System.out.println("功能测试结束");


    }

    /**
     * 有效涂色问题 - 暴力递归
     *
     * @param n 格子总数n
     * @param m 颜色数量m
     * @return
     */
    public static int ways1(int n, int m) {

        return f(n, m, n, m);
    }


    /**
     * 暴力递归  好像有问题❓❓❓ -> 没有问题❕❕❕
     *
     * @param n 格子数量
     * @param m 颜色数量
     * @param i 前i个格子
     * @param j 用了j种颜色
     * @return 返回前i个格子涂了j种颜色方法数
     */
    public static int f(int n, int m, int i, int j) {

        if (i < j) {
            return 0;
        }
        if (i == 0 && j == 0) {
            return 1;
        }
        if (i == 0 || j == 0) {
            return 0;
        }

        // 前i-1个格子已经涂好了j种颜色
        int p1 = f(n, m, i - 1, j) * j;
        // 前i-1个格子已经涂了j-1种颜色
        int p2 = f(n, m, i - 1, j - 1) * (m - j + 1);

        return p1 + p2;
    }


    /**
     * 有效涂色问题 - 动态规划
     *
     * @param n
     * @param m
     * @return
     */
    public static int ways2(int n, int m) {


        int[][] dp = new int[n + 1][m + 1];
        dp[0][0] = 1;

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                int p1 = dp[i - 1][j] * j;
                int p2 = dp[i - 1][j - 1] * (m - j + 1);
                dp[i][j] = p1 + p2;
            }
        }
        return dp[n][m];
    }


    /**
     * 测试
     *
     * @param n
     * @param m
     * @return
     */
    public static int waysTest(int n, int m) {
        return fTest(new int[n], new boolean[m + 1], 0, n, m);
    }

    // 把所有填色的方法暴力枚举
    // 然后一个一个验证是否有效
    // 这是一个带路径的递归
    // 无法改成动态规划
    public static int fTest(int[] path, boolean[] set, int i, int n, int m) {
        if (i == n) {
            Arrays.fill(set, false);
            int colors = 0;
            for (int c : path) {
                if (!set[c]) {
                    set[c] = true;
                    colors++;
                }
            }
            return colors == m ? 1 : 0;
        } else {
            int ans = 0;
            for (int j = 1; j <= m; j++) {
                path[i] = j;
                ans += fTest(path, set, i + 1, n, m);
            }
            return ans;
        }
    }

}









