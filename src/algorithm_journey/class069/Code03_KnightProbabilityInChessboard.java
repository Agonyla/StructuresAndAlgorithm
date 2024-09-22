package algorithm_journey.class069;

/**
 * 骑士在棋盘上的概率
 *
 * @author: Agony
 * @create: 2024/9/11 10:32
 * @describe: 在一个 n x n 的国际象棋棋盘上，一个骑士从单元格 (row, column) 开始，并尝试进行 k 次移动。行和列是 从 0 开始 的，所以左上单元格是 (0,0) ，右下单元格是 (n - 1, n - 1) 。
 * <p>
 * 象棋骑士有8种可能的走法，如下图所示。每次移动在基本方向上是两个单元格，然后在正交方向上是一个单元格。
 * 每次骑士要移动时，它都会随机从8种可能的移动中选择一种(即使棋子会离开棋盘)，然后移动到那里。
 * <p>
 * 骑士继续移动，直到它走了 k 步或离开了棋盘。
 * <p>
 * 返回 骑士在棋盘停止移动后仍留在棋盘上的概率 。
 * @link: <a href="https://leetcode.cn/problems/knight-probability-in-chessboard/description/">骑士在棋盘上的概率</a>
 */
public class Code03_KnightProbabilityInChessboard {


    // 骑士在棋盘上的概率
    //
    // 暴力递归
    // 设计 double f(int n, int i, int j, int k)
    // -> 返回当前处于(i,j)位置还剩下k步不越界的概率
    // n -> 棋盘大小
    // i -> 当前来到第i行
    // j -> 当前来到第j列
    // k -> 还剩下k步
    // 边界条件：
    // i<0||i>=n||j<0||j>=n
    // 可能性分析：
    // 1️⃣k==0 -> 说明肯定不越界
    // 2️⃣k!=0 -> 继续往八个方向尝试，每一种都是等概率的所以要/8
    //
    // 动态规划
    // 讲解中没提，可以自己尝试一下 ❓❓❓ ➡️ 可能性太多了不需要改了


    public static void main(String[] args) {


        int n = 3;
        int k = 2;
        int row = 0;
        int column = 0;
        System.out.println(knightProbability1(n, k, row, column));
        System.out.println(knightProbability2(n, k, row, column));

        n = 1;
        k = 0;
        row = 0;
        column = 0;
        System.out.println(knightProbability1(n, k, row, column));
        System.out.println(knightProbability2(n, k, row, column));

    }

    /**
     * 骑士在棋盘上的概率 - 暴力递归
     *
     * @param n
     * @param k
     * @param row
     * @param column
     * @return
     */
    public static double knightProbability1(int n, int k, int row, int column) {

        return f1(n, row, column, k);
    }


    /**
     * 暴力递归
     *
     * @param n 棋盘大小 n*n
     * @param i 当前来到第i行
     * @param j 当前来到第j列
     * @param k 还剩下k步可以走
     * @return 返回骑士在棋盘停止移动后仍留在棋盘上的概率 。
     */
    public static double f1(int n, int i, int j, int k) {

        // base case
        if (k < 0 || i < 0 || i >= n || j < 0 || j >= n) {
            return 0;
        }
        if (k == 0) {
            return 1;
        }

        double ans = 0;

        ans += f1(n, i - 1, j - 2, k - 1) / 8;
        ans += f1(n, i - 2, j - 1, k - 1) / 8;
        ans += f1(n, i - 2, j + 1, k - 1) / 8;
        ans += f1(n, i - 1, j + 2, k - 1) / 8;
        ans += f1(n, i + 1, j - 2, k - 1) / 8;
        ans += f1(n, i + 2, j - 1, k - 1) / 8;
        ans += f1(n, i + 2, j + 1, k - 1) / 8;
        ans += f1(n, i + 1, j + 2, k - 1) / 8;

        return ans;
    }


    /**
     * 骑士在棋盘上的概率 - 记忆化搜索
     *
     * @param n
     * @param k
     * @param row
     * @param column
     * @return
     */
    public static double knightProbability2(int n, int k, int row, int column) {

        double[][][] dp = new double[n][n][k + 1];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                for (int r = 0; r <= k; r++) {
                    dp[i][j][r] = -1;
                }
            }
        }
        return f2(n, row, column, k, dp);
    }


    /**
     * 记忆化搜索
     *
     * @param n
     * @param i
     * @param j
     * @param k
     * @param dp
     * @return
     */
    public static double f2(int n, int i, int j, int k, double[][][] dp) {

        // base case
        if (k < 0 || i < 0 || i >= n || j < 0 || j >= n) {
            return 0;
        }
        if (k == 0) {
            return 1;
        }
        if (dp[i][j][k] != -1) {
            return dp[i][j][k];
        }
        double ans = 0;
        ans += f2(n, i - 1, j - 2, k - 1, dp) / 8;
        ans += f2(n, i - 2, j - 1, k - 1, dp) / 8;
        ans += f2(n, i - 2, j + 1, k - 1, dp) / 8;
        ans += f2(n, i - 1, j + 2, k - 1, dp) / 8;
        ans += f2(n, i + 1, j - 2, k - 1, dp) / 8;
        ans += f2(n, i + 2, j - 1, k - 1, dp) / 8;
        ans += f2(n, i + 2, j + 1, k - 1, dp) / 8;
        ans += f2(n, i + 1, j + 2, k - 1, dp) / 8;
        dp[i][j][k] = ans;
        return ans;
    }


}











