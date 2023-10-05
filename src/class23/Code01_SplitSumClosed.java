package class23;

/**
 * @Author Agony
 * @Create 2023/9/25 15:50
 * @Version 1.0
 * @describe: 给定一个正数数组arr，
 * 请把arr中所有的数分成两个集合，尽量让两个集合的累加和接近
 * 返回最接近的情况下，较小集合的累加和
 */
public class Code01_SplitSumClosed {

    public static int minSum(int[] arr) {
        if (arr == null || arr.length < 2) {
            return 0;
        }
        int sum = 0;
        for (int j : arr) {
            sum += j;
        }
        int length = arr.length / 2;
        return process(arr, 0, sum / 2);
    }

    /**
     * @param arr   数组
     * @param index 当前位于index
     * @param rest  剩余累加和
     * @return 较小集合的累加和
     * @describe: 递归尝试
     */
    public static int process(int[] arr, int index, int rest) {
        // base case
        if (index == arr.length) {
            return 0;
        }
        int p1 = process(arr, index + 1, rest);
        int p2 = 0;
        if (rest - arr[index] >= 0) {
            p2 = arr[index] + process(arr, index + 1, rest - arr[index]);
        }
        return Math.max(p1, p2);
    }

    /**
     * @param arr 数组
     * @return 较小集合的累加和
     * @describe: 动态规划
     */
    public static int dp(int[] arr) {
        if (arr == null || arr.length < 2) {
            return 0;
        }
        int N = arr.length;
        int sum = 0;
        for (int i : arr) {
            sum += i;
        }
        sum /= 2;
        // index 取值范围：0~N
        // rest 取值范围：0~sum/2
        int[][] dp = new int[N + 1][sum + 1];
        for (int index = N - 1; index >= 0; index--) {
            for (int rest = 0; rest <= sum; rest++) {
                int p1 = dp[index + 1][rest];
                int p2 = 0;
                if (rest - arr[index] >= 0) {
                    p2 = arr[index] + dp[index + 1][rest - arr[index]];
                }
                dp[index][rest] = Math.max(p1, p2);
            }
        }
        return dp[0][sum];
    }

    public static void main(String[] args) {


        // arr = {48, 9, 17, 9, 27}
        // ans = 53
        int[] arr = {48, 9, 17, 9, 27};
        int sum = 0;
        for (int i : arr) {
            sum += i;
        }
        System.out.println(sum / 2);
        System.out.println(minSum(arr));
        System.out.println(dp(arr));
    }
}
