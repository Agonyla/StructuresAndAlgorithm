package class21;

/**
 * @Author Agony
 * @Create 2023/9/24 20:24
 * @Version 1.0
 * @describe: 给定5个参数，N，M，row，col，k
 * 表示在N*M的区域上，醉汉Bob初始在(row,col)位置
 * Bob一共要迈出k步，且每步都会等概率向上下左右四个方向走一个单位
 * 任何时候Bob只要离开N*M的区域，就直接死亡
 * 返回k步之后，Bob还在N*M的区域的概率
 */
public class Code05_BobDie {


    public static double bobIsAlive(int N, int M, int row, int col, int K) {
        if (row == N || row < 0 || col == M || col < 0) {
            return 0;
        }
        double allSteps = Math.pow(4, K);
        long liveSteps = process(N, M, row, col, K);
        return (double) liveSteps / allSteps;
    }

    /**
     * @param N   N行
     * @param M   M列
     * @param row 当前位于row行
     * @param col 当前位于col列
     * @param K   剩余行走步数
     * @return 返回存活的所有可能情况总数
     * @describe: 递归尝试
     */
    public static long process(int N, int M, int row, int col, int K) {
        if (row == N || row < 0 || col == M || col < 0) {
            return 0;
        }
        if (K == 0) {
            return 1;
        }
        long up = process(N, M, row - 1, col, K - 1);
        long down = process(N, M, row + 1, col, K - 1);
        long left = process(N, M, row, col - 1, K - 1);
        long right = process(N, M, row, col + 1, K - 1);
        return up + down + left + right;
    }

    /**
     * @param N   N行
     * @param M   M列
     * @param row 当前位于row行
     * @param col 当前位于col列
     * @param K   剩余行走步数
     * @return 返回存活的所有可能情况总数
     * @describe: 递归尝试
     */
    public static double dp(int N, int M, int row, int col, int K) {
        if (row == N || row < 0 || col == M || col < 0) {
            return 0;
        }
        double allSteps = Math.pow(4, K);
        long[][][] dp = new long[N][M][K + 1];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                dp[i][j][0] = 1;
            }
        }
        for (int k = 1; k <= K; k++) {
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < M; j++) {
                    long up = pick(dp, N, M, i - 1, j, k - 1);
                    long down = pick(dp, N, M, i + 1, j, k - 1);
                    long left = pick(dp, N, M, i, j - 1, k - 1);
                    long right = pick(dp, N, M, i, j + 1, k - 1);
                    dp[i][j][k] = up + down + left + right;
                }
            }
        }
        return (double) dp[row][col][K] / allSteps;
    }

    public static long pick(long[][][] dp, int N, int M, int row, int col, int K) {
        if (row == N || row < 0 || col == M || col < 0) {
            return 0;
        }
        return dp[row][col][K];
    }

    public static void main(String[] args) {

        // System.out.println(Math.pow(2, 3));
        // ans = 0.9970207214355469
        System.out.println(bobIsAlive(50, 50, 6, 6, 10));
        System.out.println(dp(50, 50, 6, 6, 10));
    }


}
