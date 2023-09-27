package class22;

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
        if (arr == null || arr.length == 0) {
            return 0;
        }

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
        // TODO:把剩下的写完
        return 0;
    }

    public static void main(String[] args) {

        int[] arr = {22, 4, 9, 1, 10};
        int aim = 32;
        // res = 2

        System.out.println(minCoins(arr, aim));
    }
}
