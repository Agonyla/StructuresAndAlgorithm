package algorithm_basic.class22;

/**
 * @Author Agony
 * @Create 2023/10/5 15:27
 * @Version 1.0
 * @describe: 给定一个正数n，求n的裂开方法数，
 * 规定：后面的数不能比前面的数小
 * 比如4的裂开方法有：
 * 1+1+1+1、1+1+2、1+3、2+2、4
 * 5种，所以返回5
 */
public class Code03_SplitNumber {


    public static int ways(int n) {
        return process(1, n);
    }

    /**
     * @param start 裂开的数字从start开始
     * @param rest  剩余的数字
     * @return 返回裂开方法数
     * @describe: 递归尝试
     */
    public static int process(int start, int rest) {
        // base case
        if (rest == 0) {
            return 1;
        }
        // 有 num <= rest 保证不会 可以省略
        // 但是省略的话改动态规划会忽略这个初始化条件
        if (start > rest) {
            return 0;
        }
        // int res = process(start, rest - start);
        // res += process(start + 1, rest - start - 1);
        // res += process(start + 2, rest - start - 2);
        // res += process(start + 3, rest - start - 3);
        // res += process(start + 4, rest - start - 4);

        int res = 0;
        for (int num = start; num <= rest; num++) {
            res += process(num, rest - num);
        }
        return res;
    }

    /**
     * @param n 目标数字
     * @return 返回分裂的方法数
     * @describe: 动态规划枚举
     */
    public static int dp1(int n) {
        // start 取值范围：1~n
        // rest 取值范围：0~n
        int[][] dp = new int[n + 1][n + 1];

        // base case 中可以看出
        for (int i = 0; i <= n; i++) {
            dp[i][i] = 1;
            dp[i][0] = 1;
        }

        for (int start = n - 1; start >= 1; start--) {
            for (int rest = start + 1; rest <= n; rest++) {
                int res = 0;
                for (int num = start; num <= rest; num++) {
                    res += dp[num][rest - num];
                }
                dp[start][rest] = res;
            }
        }
        return dp[1][n];
    }

    /**
     * @param n 目标数字
     * @return 返回分裂的方法数
     * @describe: 动态规划枚举优化
     */
    public static int dp2(int n) {
        // start 取值范围：1~n
        // rest 取值范围：0~n
        int[][] dp = new int[n + 1][n + 1];

        // base case 中可以看出
        for (int i = 0; i <= n; i++) {
            dp[i][i] = 1;
            dp[i][0] = 1;
        }
        for (int start = n - 1; start >= 1; start--) {
            for (int rest = start + 1; rest <= n; rest++) {
                dp[start][rest] = dp[start][rest - start] + dp[start + 1][rest];
                // res += dp[num][rest - num];
                // start=2, rest=16
                // 依赖 2,14  3,13  4,12...
                // start=3, rest=16
                // 依赖 3,13  4,12...
                // 所以start=2, rest=16依赖start=2, rest - start 和 start+1, rest
            }
        }
        return dp[1][n];
    }

    public static void main(String[] args) {
        System.out.println(ways(39));
        System.out.println(dp1(39));
        System.out.println(dp2(39));
    }
}
