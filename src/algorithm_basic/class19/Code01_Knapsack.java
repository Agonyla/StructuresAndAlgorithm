package algorithm_basic.class19;

/**
 * @Author Agony
 * @Create 2023/9/19 15:19
 * @Version 1.0
 * @describe: 给定两个长度都为N的数组weights和values，weights[i]和values[i]分别代表 i号物品的重量和价值
 * 给定一个正数bag，表示一个载重bag的袋子，装的物品不能超过这个重量
 * 返回能装下的最大价值
 */
public class Code01_Knapsack {

    /**
     * @param weights 重量
     * @param values  价值
     * @param bag     袋子承重
     * @return 最大价值
     * @describe: 递归尝试
     */
    public static int maxValue(int[] weights, int[] values, int bag) {
        // base case
        if (weights == null || values == null || weights.length != values.length || weights.length == 0 || bag < 0) {
            return -1;
        }

        return process(weights, values, 0, bag);

    }

    /**
     * @param weights 重量
     * @param values  价值
     * @param bag     袋子承重
     * @param index   当前所处位置
     * @return 最大价值
     * @describe: 递归尝试
     */
    public static int process(int[] weights, int[] values, int index, int bag) {
        // base case
        if (bag < 0) {
            return -1;
        }
        // base case
        if (index == weights.length) {
            return 0;
        }
        // 要当前的货物 => 考虑此时bag - weights[index]是否小于0
        int p1 = bag - weights[index] < 0 ? 0 : values[index] + process(weights, values, index + 1, bag - weights[index]);
        // int p1 = 0;
        // int next = process(weights,values,index+1,bag - weights[index]);
        // if (next != -1){
        //     p1 = values[index] + next;
        // }
        // 不要当前的货物
        int p2 = process(weights, values, index + 1, bag);

        return Math.max(p1, p2);
    }

    /**
     * @param weights 重量
     * @param values  价值
     * @param bag     袋子承重
     * @return 最大价值
     * @describe: 动态规划
     */
    public static int maxValue2(int[] weights, int[] values, int bag) {
        // base case
        if (weights == null || values == null || weights.length != values.length || weights.length == 0 || bag < 0) {
            return -1;
        }

        // index 范围：0~N (在递归中，index是可以取到N的)
        // rest 范围：0~bag

        int N = values.length;
        int[][] dp = new int[N + 1][bag + 1];
        // 返回的是dp[0][bag] index依赖index+1，所以总的趋势是要从下到上遍历(外层从下到上)，内层都可以
        for (int index = N - 1; index >= 0; index--) {
            for (int rest = 0; rest <= bag; rest++) {
                // int p1 = 0;
                // int next = rest - weights[index] < 0 ? -1 : dp[index + 1][rest - weights[index]];
                // if (next != -1) {
                //     p1 = values[index] + next;
                // }
                int p1 = rest - weights[index] < 0 ? 0 : values[index] + dp[index + 1][rest - weights[index]];
                int p2 = dp[index + 1][rest];
                dp[index][rest] = Math.max(p1, p2);
            }
        }
        return dp[0][bag];
    }

    public static void main(String[] args) {
        int[] weights = {3, 2, 4, 7, 3, 1, 7};
        int[] values = {5, 6, 3, 19, 12, 4, 2};
        int bag = 15;
        System.out.println(maxValue(weights, values, bag));
        System.out.println(maxValue2(weights, values, bag));
    }

}
