package algorithm_basic.class22;

/**
 * @Author Agony
 * @Create 2023/9/24 21:08
 * @Version 1.0
 * arr是面值数组，其中的值都是正数且没有重复。再给定一个正数aim。
 * 每个值都认为是一种面值，且认为张数是无限的。
 * 返回组成aim的最少货币数
 */
public class Code02_MinCoinsNoLimit {


    // 用上节课自己瞎几把尝试的方法试一试 ==> 尝试失败
    // 之前那个CoinsWayNoLimit 收集的是组成方法
    // 本题是组成的最少货币数量

    public static int minCoins(int[] arr, int aim) {
        if (aim == 0) {
            return 0;
        }

        // int res = process(arr, 0, aim);
        // return res == Integer.MAX_VALUE ? 0 : res;
        return process(arr, 0, aim);
    }

    /**
     * @param arr   面值数组
     * @param index 当前所处位置
     * @param rest  剩下的目标
     * @return 返回最少货币数
     * @describe: 递归尝试
     */
    public static int process(int[] arr, int index, int rest) {
        // base case
        if (index == arr.length) {
            return rest == 0 ? 0 : Integer.MAX_VALUE;
        }

        int res = Integer.MAX_VALUE;
        for (int num = 0; arr[index] * num <= rest; num++) {
            int next = process(arr, index + 1, rest - arr[index] * num);
            if (next != Integer.MAX_VALUE) {
                res = Math.min(res, num + next);
            }
        }
        return res;
    }

    /**
     * @param arr 面值数组
     * @param aim 目标数字
     * @return 返回最少货币数
     * @describe: 动态规划
     */
    public static int dp1(int[] arr, int aim) {
        // base case
        if (aim == 0) {
            return 0;
        }
        int N = arr.length;
        // index 取值范围：0~N
        // rest 取值范围：0~aim
        int[][] dp = new int[N + 1][aim + 1];

        for (int j = 1; j <= aim; j++) {
            dp[N][j] = Integer.MAX_VALUE;
        }
        for (int index = N - 1; index >= 0; index--) {
            for (int rest = 0; rest <= aim; rest++) {
                int res = Integer.MAX_VALUE;
                for (int num = 0; arr[index] * num <= rest; num++) {
                    int next = dp[index + 1][rest - arr[index] * num];
                    if (next != Integer.MAX_VALUE) {
                        res = Math.min(res, num + next);
                    }
                }
                dp[index][rest] = res;
            }
        }
        return dp[0][aim];
    }

    /**
     * @param arr 面值数组
     * @param aim 目标数字
     * @return 返回最少货币数
     * @describe: 动态规划 枚举优化
     */
    public static int dp2(int[] arr, int aim) {
        // base case
        if (aim == 0) {
            return 0;
        }
        int N = arr.length;
        // index 取值范围：0~N
        // rest 取值范围：0~aim
        int[][] dp = new int[N + 1][aim + 1];

        for (int j = 1; j <= aim; j++) {
            dp[N][j] = Integer.MAX_VALUE;
        }
        for (int index = N - 1; index >= 0; index--) {
            for (int rest = 0; rest <= aim; rest++) {
                dp[index][rest] = dp[index + 1][rest];
                if (rest - arr[index] >= 0 && dp[index][rest - arr[index]] != Integer.MAX_VALUE) {
                    dp[index][rest] = Math.min(dp[index][rest], dp[index][rest - arr[index]] + 1);

                    // dp[index][rest - arr[index]] + 1 为什么要加1
                    //          X  T
                    //    d  c  b  a
                    // T 位置依赖 a+0, b+1, c+2, d+3的最小值
                    // X 位置依赖 b+0, c+1, d+2 的最小值
                    // 所以 T 位置依赖 X+1, a+0 的最小值
                }
            }
        }
        return dp[0][aim];
    }


    public static void main(String[] args) {

        int[] arr = {22, 4, 9, 1, 10};
        int aim = 32;
        // res = 2

        System.out.println(minCoins(arr, aim));
        System.out.println(dp1(arr, aim));
        System.out.println(dp2(arr, aim));
    }
}
