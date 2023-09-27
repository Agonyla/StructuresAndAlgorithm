package class22;

/**
 * @Author Agony
 * @Create 2023/9/27 16:25
 * @Version 1.0
 * @describe: 给定3个参数，N，M，K
 * 怪兽有N滴血，等着英雄来砍自己
 * 英雄每一次打击，都会让怪兽流失[0~M]的血量
 * 到底流失多少？每一次在[0~M]上等概率的获得一个值
 * 求K次打击之后，英雄把怪兽砍死的概率
 */
public class Code01_KillMonster {
    public static double isDead(int N, int M, int K) {
        if (N < 1 || M < 1 || K < 1) {
            return 0;
        }

        double allWays = Math.pow(M + 1, K);
        return (double) process(M, N, K) / allWays;
    }

    /**
     * @param M     扣血量[0~M]
     * @param HP    剩余血量
     * @param times 剩余次数
     * @return 返回死亡的次数
     * @describe: 递归尝试
     */
    public static long process(int M, int HP, int times) {
        // base case
        if (times == 0) {
            return HP <= 0 ? 1 : 0;
        }
        if (HP <= 0) {
            return (long) Math.pow(M + 1, times);
        }
        long ways = 0;
        for (int i = 0; i <= M; i++) {
            ways += process(M, HP - i, times - 1);
        }
        return ways;
    }

    /**
     * 动态规划
     */
    public static double dp(int N, int M, int K) {
        if (N < 1 || M < 1 || K < 1) {
            return 0;
        }
        double allWays = Math.pow(M + 1, K);
        // HP 取值范围：0~N
        // times 取值范围：0~K
        long[][] dp = new long[N + 1][K + 1];
        dp[0][0] = 1;
        for (int times = 1; times <= K; times++) {
            dp[0][times] = (long) Math.pow(M + 1, times);
        }
        //  从上到下 从左到右
        for (int HP = 1; HP <= N; HP++) {
            for (int times = 1; times <= K; times++) {
                long ways = 0;
                for (int i = 0; i <= M; i++) {
                    if (HP - i <= 0) {
                        ways += dp[0][times - 1];
                    } else {
                        ways += dp[HP - i][times - 1];
                    }
                }
                dp[HP][times] = ways;
            }
        }
        return (double) dp[N][K] / allWays;
    }

    /**
     * 动态规划优化
     */
    public static double dp2(int N, int M, int K) {
        if (N < 1 || M < 1 || K < 1) {
            return 0;
        }
        double allWays = Math.pow(M + 1, K);
        // HP 取值范围：0~N
        // times 取值范围：0~K
        long[][] dp = new long[N + 1][K + 1];
        dp[0][0] = 1;
        for (int times = 1; times <= K; times++) {
            dp[0][times] = (long) Math.pow(M + 1, times);
        }
        //  从上到下 从左到右
        for (int HP = 1; HP <= N; HP++) {
            for (int times = 1; times <= K; times++) {
                // 依赖关系 ==> 画图
                // dp[HP][times] = dp[HP][times - 1] + dp[HP - 1][times] - dp[HP - M - 1][times - 1];
                // 讨论HP - M - 1是否越界
                dp[HP][times] = dp[HP][times - 1] + dp[HP - 1][times];
                if (HP - M - 1 >= 0) {
                    dp[HP][times] -= dp[HP - M - 1][times - 1];
                } else {
                    dp[HP][times] -= dp[0][times - 1];
                }
            }
        }
        return (double) dp[N][K] / allWays;
    }

    public static void main(String[] args) {
        //  N = 15 M = 3 K = 11
        // ans1 = 0.7030892372131348

        System.out.println(isDead(15, 3, 11));
        System.out.println(dp(15, 3, 11));
        System.out.println(dp2(15, 3, 11));

    }
}
