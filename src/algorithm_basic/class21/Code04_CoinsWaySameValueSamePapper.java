package algorithm_basic.class21;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author Agony
 * @Create 2023/9/24 17:01
 * @Version 1.0
 * @describe: arr是货币数组，其中的值都是正数。再给定一个正数aim。
 * 每个值都认为是一张货币，
 * 认为值相同的货币没有任何不同，
 * 返回组成aim的方法数
 * 例如：arr = {1,2,1,1,2,1,2}，aim = 4
 * 方法：1+1+1+1、1+1+2、2+2
 * 一共就3种方法，所以返回3
 */
public class Code04_CoinsWaySameValueSamePapper {

    public static class Info {
        int[] coins;
        int[] numbers;

        public Info(int[] coins, int[] numbers) {
            this.coins = coins;
            this.numbers = numbers;
        }
    }

    /**
     * @param arr 货币数组
     * @return 对象
     * 把货币数组转换成对象，对象包含两个属性
     * coins[] 各种货币的面值
     * numbers[]  每种货币的对应的张数量
     */
    public static Info getInfo(int[] arr) {
        HashMap<Integer, Integer> coinMap = new HashMap<>();
        for (int i : arr) {
            if (!coinMap.containsKey(i)) {
                coinMap.put(i, 1);
            } else {
                coinMap.put(i, coinMap.get(i) + 1);
            }
        }
        int[] coins = new int[coinMap.size()];
        int[] numbers = new int[coinMap.size()];
        int index = 0;
        for (Map.Entry<Integer, Integer> entry : coinMap.entrySet()) {
            coins[index] = entry.getKey();
            numbers[index++] = entry.getValue();
        }
        return new Info(coins, numbers);
    }

    public static int coinWays(int[] arr, int aim) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        Info info = getInfo(arr);
        int[] coins = info.coins;
        int[] numbers = info.numbers;
        return process(coins, numbers, 0, aim);
    }

    /**
     * @param coins   货币面值
     * @param numbers 每种面值的数量
     * @param index   当前位置
     * @param rest    剩余数
     * @return 组成aim的方法数
     * @describe: 递归尝试
     */
    public static int process(int[] coins, int[] numbers, int index, int rest) {
        if (index == coins.length) {
            return rest == 0 ? 1 : 0;
        }
        // 在for循环的判断中去除了rest<0的情况，=0的情况会一直调用process直到index==arr.length
        int ways = 0;
        for (int number = 0; number * coins[index] <= rest && number <= numbers[index]; number++) {
            ways += process(coins, numbers, index + 1, rest - number * coins[index]);
        }
        return ways;
    }

    /**
     * @param arr 货币数组
     * @param aim 目标
     * @return 组成aim的方法数
     * @describe: 动态规划枚举
     */
    public static int dp1(int[] arr, int aim) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        Info info = getInfo(arr);
        int[] coins = info.coins;
        int[] numbers = info.numbers;

        int N = coins.length;
        // index取值范围：0~N
        // rest取值范围：0~aim
        int[][] dp = new int[N + 1][aim + 1];
        dp[N][0] = 1;
        // index 依赖 index + 1 从下往上，从左往右
        for (int index = N - 1; index >= 0; index--) {
            for (int rest = 0; rest <= aim; rest++) {
                int ways = 0;
                for (int number = 0; number * coins[index] <= rest && number <= numbers[index]; number++) {
                    ways += dp[index + 1][rest - number * coins[index]];
                }
                dp[index][rest] = ways;
            }
        }
        return dp[0][aim];
    }

    /**
     * @param arr 货币数组
     * @param aim 目标
     * @return 组成aim的方法数
     * @describe: 枚举动态规划优化
     */
    public static int dp2(int[] arr, int aim) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        Info info = getInfo(arr);
        int[] coins = info.coins;
        int[] numbers = info.numbers;

        int N = coins.length;
        // index取值范围：0~N
        // rest取值范围：0~aim
        int[][] dp = new int[N + 1][aim + 1];
        dp[N][0] = 1;
        // index 依赖 index + 1 从下往上，从左往右
        for (int index = N - 1; index >= 0; index--) {
            for (int rest = 0; rest <= aim; rest++) {
                // 画表去找 index 行与 index+1 依赖以及同一行的关系
                // ==> dp[index][rest] = dp[index + 1][rest] + dp[index][rest - coins[index]] - dp[index + 1][rest - coins[index] * (numbers[index] + 1)]
                // rest - coins[index] 和 rest - coins[index] * (numbers[index] + 1) 可能存在越界
                // ==> 需要单独写if判断一下
                dp[index][rest] = dp[index + 1][rest];
                if (rest - coins[index] >= 0) {
                    dp[index][rest] += dp[index][rest - coins[index]];
                }
                if (rest - coins[index] * (numbers[index] + 1) >= 0) {
                    dp[index][rest] -= dp[index + 1][rest - coins[index] * (numbers[index] + 1)];
                }
            }
        }
        return dp[0][aim];
    }


    public static void main(String[] args) {
        // arr = {10, 5, 4, 4, 12, 3}
        // aim = 22
        // ans = 2
        int[] arr = {10, 5, 4, 4, 12, 3};
        int aim = 22;
        System.out.println(coinWays(arr, aim));
        System.out.println(dp1(arr, aim));
        System.out.println(dp2(arr, aim));
    }
}
