package algorithm_basic.class21;

/**
 * @Author Agony
 * @Create 2023/9/24 15:24
 * @Version 1.0
 * @describe: arr是货币数组，其中的值都是正数。再给定一个正数aim。
 * 每个值都认为是一张货币，
 * 即便是值相同的货币也认为每一张都是不同的，
 * 返回组成aim的方法数
 * 例如：arr = {1,1,1}，aim = 2
 * 第0个和第1个能组成2，第1个和第2个能组成2，第0个和第2个能组成2
 * 一共就3种方法，所以返回3
 */
public class Code02_CoinsWayEveryPaperDifferent {

    public static int coinWays(int[] arr, int aim) {
        if (arr == null || arr.length == 0) {
            return 0;
        }

        return process(arr, 0, aim);
    }

    /**
     * @param arr   给定数组
     * @param index 当前位置
     * @param rest  剩余结果
     * @return 返回可能性
     * @describe: 递归尝试
     */

    public static int process(int[] arr, int index, int rest) {
        // base case
        if (index == arr.length) {
            return rest == 0 ? 1 : 0;
        }
        if (rest == 0) {
            return 1;
        }
        if (rest < 0) {
            return 0;
        }
        // 要当前数字
        int p1 = process(arr, index + 1, rest - arr[index]);
        // 不要当前数字
        int p2 = process(arr, index + 1, rest);
        return p1 + p2;
    }

    /**
     * @param arr 给定数组
     * @param aim 目标值
     * @return 返回可能性
     * @describe: 动态规划
     */
    public static int dp(int[] arr, int aim) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        int N = arr.length;
        // index取值范围：0~N
        // rest 取值范围：0~aim
        int[][] dp = new int[N + 1][aim + 1];
        dp[N][0] = 1;
        // 第一列
        for (int i = 0; i < N; i++) {
            dp[i][0] = 1;
        }
        //
        for (int index = N - 1; index >= 0; index--) {
            for (int rest = 1; rest <= aim; rest++) {
                int ways = dp[index + 1][rest];
                if (rest - arr[index] >= 0) {
                    ways += dp[index + 1][rest - arr[index]];
                }
                dp[index][rest] = ways;
            }
        }
        return dp[0][aim];
    }

    public static void main(String[] args) {
        int[] arr = {7, 30, 11, 5, 11, 18, 12, 14, 9, 20, 26};
        int aim = 25;
        System.out.println(coinWays(arr, aim));
        System.out.println(dp(arr, aim));

        //7 30 11 5 11 18 12 14 9 20 26
        // aim = 25
        // res = 6
    }

}
