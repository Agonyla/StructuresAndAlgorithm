package algorithm_basic.class18;

/**
 * @Author Agony
 * @Create 2023/9/19 19:18
 * @Version 1.0
 * @describe: 机器人走路
 * 假设有排成一行的N个位置记为1~N，N一定大于或等于2
 * 开始时机器人在其中的M位置上(M一定是1~N中的一个)
 * 如果机器人来到1位置，那么下一步只能往右来到2位置；
 * 如果机器人来到N位置，那么下一步只能往左来到N-1位置；
 * 如果机器人来到中间位置，那么下一步可以往左走或者往右走；
 * 规定机器人必须走K步，最终能来到P位置(P也是1~N中的一个)的方法有多少种
 * 给定四个参数 N、M、K、P，返回方法数
 */
public class Code01_RobotWalk {

    /**
     * @param N      一共有N个位置
     * @param target 走到target位置
     * @param M      位于M位置
     * @param K      走K步
     * @return 返回方法数
     * @describe: 递归尝试
     */
    public static int walk(int N, int target, int M, int K) {

        return process(N, target, M, K);

    }

    /**
     * @param N      共有N个位置
     * @param target 走到target位置
     * @param cur    位于cur位置
     * @param rest   还剩下rest步
     * @return 返回方法数
     */
    public static int process(int N, int target, int cur, int rest) {
        // base case
        // 当 rest == 0 时，此时机器人在目标位置就说明有一种方法
        if (rest == 0) {
            return target == cur ? 1 : 0;
        }
        // base case
        if (cur == 1) {
            return process(N, target, cur + 1, rest - 1);
        }
        if (cur == N) {
            return process(N, target, cur - 1, rest - 1);
        }
        return process(N, target, cur - 1, rest - 1) + process(N, target, cur + 1, rest - 1);
    }

    /**
     * @param N      一共有N个位置
     * @param target 走到target位置
     * @param M      位于M位置
     * @param K      走K步
     * @return 返回方法数
     * @describe: 使用缓存表
     */
    public static int walk2(int N, int target, int M, int K) {


        // 有 M 和 K 两个变量
        int[][] dp = new int[N + 1][K + 1];
        // 所有位置都置 -1
        for (int i = 0; i < N + 1; i++) {
            for (int j = 0; j < K + 1; j++) {
                dp[i][j] = -1;
            }
        }

        return process2(N, target, M, K, dp);
    }

    // M 的范围 1~N
    // rest 的范围  0~K
    // dp[cur][rest] == -1 => 表示这个位置没来过
    // dp[cur][rest] != -1 => 表示这个位置来过,直接返回表的值

    public static int process2(int N, int target, int cur, int rest, int[][] dp) {
        // base case
        if (dp[cur][rest] != -1) {
            return dp[cur][rest];
        }

        int ans = 0;
        if (rest == 0) {
            ans = cur == target ? 1 : 0;
        } else if (cur == 1) {
            ans = process2(N, target, cur + 1, rest - 1, dp);
        } else if (cur == N) {
            ans = process2(N, target, cur - 1, rest - 1, dp);
        } else {
            ans = process2(N, target, cur + 1, rest - 1, dp) + process2(N, target, cur - 1, rest - 1, dp);
        }
        dp[cur][rest] = ans;
        return ans;
    }

    /**
     * @param N      一共有N个位置
     * @param target 走到target位置
     * @param M      位于M位置
     * @param K      走K步
     * @return 返回方法数
     * @describe: 动态规划
     */
    public static int walk3(int N, int target, int M, int K) {
        int[][] dp = new int[N + 1][K + 1];
        // rest == 0 时 cur == target时，dp[cur][rest] = 1
        dp[target][0] = 1;

        // 先从左到右，再从上到下
        for (int rest = 1; rest < K + 1; rest++) {
            for (int cur = 1; cur < N + 1; cur++) {
                if (cur == 1) {
                    dp[cur][rest] = dp[cur + 1][rest - 1];
                } else if (cur == N) {
                    dp[cur][rest] = dp[cur - 1][rest - 1];
                } else {
                    dp[cur][rest] = dp[cur - 1][rest - 1] + dp[cur + 1][rest - 1];
                }
            }
        }
        return dp[M][K];

    }


    public static void main(String[] args) {
        // N = 5, target = 4, M = 2, K = 6
        int walk = walk(5, 4, 2, 6);
        int walk2 = walk2(5, 4, 2, 6);
        int walk3 = walk3(5, 4, 2, 6);
        System.out.println(walk);
        System.out.println(walk2);
        System.out.println(walk3);

    }
}
