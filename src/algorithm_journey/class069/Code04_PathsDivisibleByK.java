package algorithm_journey.class069;

/**
 * 矩阵中和能被 K 整除的路径
 *
 * @author: Agony
 * @create: 2024/9/11 11:12
 * @describe: 给你一个下标从 0 开始的 m x n 整数矩阵 grid 和一个整数 k 。你从起点 (0, 0) 出发，每一步只能往 下 或者往 右 ，你想要到达终点 (m - 1, n - 1) 。
 * <p>
 * 请你返回路径和能被 k 整除的路径数目，由于答案可能很大，返回答案对 109 + 7 取余 的结果。
 * @link: <a href="https://leetcode.cn/problems/paths-in-matrix-whose-sum-is-divisible-by-k/description/">矩阵中和能被 K 整除的路径</a>
 */
public class Code04_PathsDivisibleByK {


    // 矩阵中和能被 K 整除的路径
    //
    // 暴力递归
    // 设计 int[] f1(int[][] grid, int m, int n, int k, int i, int j, int r)
    // -> 返回从(i,j)位置出发到(m-1,n-1)有多少路径，累积和%k的余数是r
    // grid -> 题目提供的网格
    // m -> m行
    // n -> n列
    // k -> 要求被k整除
    // i -> 从i行出发
    // j -> 从j列出发
    // r -> 剩下的余数
    // 边界条件：
    // i==m-1 && j==n-1 -> return grid[i][j]%k==r?1:0
    // 剩下的余数：
    // 如果要求 k=7，r=3
    // 当前数余2，那么剩下的余数为1
    // 当前书余4，那么剩下的余数为6
    // -> 剩下的余数 = (k+r - 当前余数) % k   (类似的之前同余原理有提到过)
    // 可能性分析：
    // 1️⃣如果能往下走 i+1<m -> 调用f
    // 2️⃣如果能往右走 j+1<n -> 调用f
    // 两种可能性相加
    //
    // 记忆化搜索
    // ...
    //
    // 动态规划 品一下❕❕❕
    // 设计 int[][][] dp = new int[m][n][k];
    // 这次不要把它想象成三维坐标
    // 把它想象成二维坐标，每一个位置里是一个一维数组
    // 如来到 (i,j) 位置，里面是一个一维数组: [0...k-1]   (如k=7，那么余数就只有0～6，所以一维数组s[3]就表示余数为3复合要求的路径和)
    // 位置依赖：
    // 这样从递归中就可以看到来到(i,j)位置都依赖其右边和下边的位置
    // 填报顺序：
    // 从下往上，从右往左
    // 特殊位置分析：
    // 右下角位置：dp[m-1][n-1][grid[m-1][n-1]%k]=1，表格中右下角位置的数能整除k就说明该位置是答案，有一条路径
    // 最后一行：(k+r - 后一列%k)%k
    // 最后一列：(k+r - 后一行%k)%k


    public static void main(String[] args) {

        int[][] grid = new int[][]{{5, 2, 4}, {3, 0, 5}, {0, 7, 2}};
        int k = 3;
        System.out.println(numberOfPaths1(grid, k));
        System.out.println(numberOfPaths2(grid, k));
        System.out.println(numberOfPaths3(grid, k));


        grid = new int[][]{{0, 0}};
        k = 5;
        System.out.println(numberOfPaths1(grid, k));
        System.out.println(numberOfPaths2(grid, k));
        System.out.println(numberOfPaths3(grid, k));

        grid = new int[][]{{7, 3, 4, 9}, {2, 3, 6, 2}, {2, 3, 7, 0}};
        k = 1;
        System.out.println(numberOfPaths1(grid, k));
        System.out.println(numberOfPaths2(grid, k));
        System.out.println(numberOfPaths3(grid, k));
    }


    /**
     * 矩阵中和能被 K 整除的路径 - 暴力递归
     *
     * @param grid
     * @param k
     * @return
     */
    public static int numberOfPaths1(int[][] grid, int k) {


        return f1(grid, grid.length, grid[0].length, k, 0, 0, 0);
    }


    /**
     * 暴力递归
     *
     * @param grid 棋盘
     * @param m    m行
     * @param n    n列
     * @param k    要求被k整除
     * @param i    当前来到第i行
     * @param j    当前来动第j列
     * @param r    剩下要满足的余数
     * @return 返回路径和能被k整除的路径数目
     */
    public static int f1(int[][] grid, int m, int n, int k, int i, int j, int r) {

        if (i == m - 1 && j == n - 1) {
            return grid[i][j] % k == r ? 1 : 0;
        }

        // 剩下的余数 （同余原理）
        int rest = (k + r - grid[i][j] % k) % k;

        int p1 = 0;
        int p2 = 0;
        // 往下走
        if (i + 1 < m) {
            p1 = f1(grid, m, n, k, i + 1, j, rest);
        }
        // 往右走
        if (j + 1 < n) {
            p2 = f1(grid, m, n, k, i, j + 1, rest);
        }
        return p1 + p2;
    }


    private static final int mod = 1000000007;


    /**
     * 返回路径和能被 k 整除的路径数目 - 记忆化搜索
     *
     * @param grid
     * @param k
     * @return
     */
    public static int numberOfPaths2(int[][] grid, int k) {
        int m = grid.length;
        int n = grid[0].length;


        // 这里不需要k+1，如果余数是k，说明就能被k整除就变成0了
        int[][][] dp = new int[m][n][k];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                for (int r = 0; r < k; r++) {
                    dp[i][j][r] = -1;
                }
            }
        }
        return f2(grid, m, n, k, 0, 0, 0, dp);
    }


    /**
     * 记忆化搜索
     *
     * @param grid
     * @param m
     * @param n
     * @param k
     * @param i
     * @param j
     * @param r
     * @param dp
     * @return
     */
    public static int f2(int[][] grid, int m, int n, int k, int i, int j, int r, int[][][] dp) {

        if (i == m - 1 && j == n - 1) {
            return grid[i][j] % k == r ? 1 : 0;
        }

        if (dp[i][j][r] != -1) {
            return dp[i][j][r];
        }

        int rest = (k + r - grid[i][j] % k) % k;

        int p1 = 0;
        int p2 = 0;
        // 往下走
        if (i + 1 < m) {
            p1 = f2(grid, m, n, k, i + 1, j, rest, dp);
        }
        // 往右走
        if (j + 1 < n) {
            p2 = f2(grid, m, n, k, i, j + 1, rest, dp);
        }
        dp[i][j][r] = (p1 + p2) % mod;
        return dp[i][j][r];
    }


    /**
     * 返回路径和能被 k 整除的路径数目 - 动态规划
     *
     * @param grid
     * @param k
     * @return
     */
    public static int numberOfPaths3(int[][] grid, int k) {

        int m = grid.length;
        int n = grid[0].length;
        int[][][] dp = new int[m][n][k];

        dp[m - 1][n - 1][grid[m - 1][n - 1] % k] = 1;

        // 最后一行
        for (int j = n - 2; j >= 0; j--) {
            for (int r = 0; r < k; r++) {
                dp[m - 1][j][r] = dp[m - 1][j + 1][(k + r - grid[m - 1][j] % k) % k];
            }
        }
        // 最后一列
        for (int i = m - 2; i >= 0; i--) {
            for (int r = 0; r < k; r++) {
                dp[i][n - 1][r] = dp[i + 1][n - 1][(k + r - grid[i][n - 1] % k) % k];
            }
        }

        for (int i = m - 2; i >= 0; i--) {
            for (int j = n - 2; j >= 0; j--) {
                for (int r = 0; r < k; r++) {
                    int rest = (k + r - grid[i][j] % k) % k;
                    dp[i][j][r] = (dp[i + 1][j][rest] + dp[i][j + 1][rest]) % mod;
                }
            }
        }
        return dp[0][0][0];
    }


}












