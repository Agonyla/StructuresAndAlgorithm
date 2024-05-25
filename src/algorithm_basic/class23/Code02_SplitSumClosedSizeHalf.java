package algorithm_basic.class23;

/**
 * @Author Agony
 * @Create 2023/10/5 19:47
 * @Version 1.0
 * @describe: 给定一个正数数组arr，请把arr中所有的数分成两个集合
 * 如果arr长度为偶数，两个集合包含数的个数要一样多
 * 如果arr长度为奇数，两个集合包含数的个数必须只差一个
 * 请尽量让两个集合的累加和接近
 * 返回最接近的情况下，较小集合的累加和
 */
public class Code02_SplitSumClosedSizeHalf {

    public static int minSum(int[] arr) {
        if (arr == null || arr.length < 2) {
            return 0;
        }

        int sum = 0;
        for (int i : arr) {
            sum += i;
        }
        sum /= 2;
        if (arr.length % 2 == 0) {
            return process(arr, arr.length / 2, 0, sum);
        } else {
            int p1 = process(arr, arr.length / 2, 0, sum);
            int p2 = process(arr, arr.length / 2 + 1, 0, sum);
            return Math.max(p1, p2);
        }
    }

    public static int process(int[] arr, int length, int index, int rest) {
        // base case
        if (index == arr.length) {
            return length == 0 ? 0 : -1;
        }
        int p1 = process(arr, length, index + 1, rest);
        // 注意！！！这个p2初始不能=0，p1可能为-1. 最后会返回0
        int p2 = -1;
        int next = -1;
        if (rest - arr[index] >= 0) {
            next = process(arr, length - 1, index + 1, rest - arr[index]);
        }
        if (next != -1) {
            p2 = arr[index] + next;
        }
        return Math.max(p1, p2);
    }

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
        // length 取值范围：0~N/2+1
        // index 取值范围：0~N
        // rest 取值范围：0~sum
        int[][][] dp = new int[N / 2 + 2][N + 1][sum + 1];
        for (int rest = 0; rest <= sum; rest++) {
            for (int i = 1; i <= N / 2 + 1; i++) {
                dp[i][N][rest] = -1;
            }
        }

        for (int rest = 0; rest <= sum; rest++) {
            for (int index = N - 1; index >= 0; index--) {
                for (int length = 0; length <= N / 2 + 1; length++) {
                    int p1 = dp[length][index + 1][rest];
                    // 注意！！！这个p2初始不能=0，p1可能为-1. 最后会返回0
                    int p2 = -1;
                    int next = -1;
                    // 这里length - 1也可以不加判断，直接length从1开始
                    if (length - 1 >= 0 && rest - arr[index] >= 0) {
                        next = dp[length - 1][index + 1][rest - arr[index]];
                    }
                    if (next != -1) {
                        p2 = arr[index] + next;
                    }
                    dp[length][index][rest] = Math.max(p1, p2);
                }
            }
        }
        if (N % 2 == 0) {
            return dp[N / 2][0][sum];
        } else {
            return Math.max(dp[N / 2][0][sum], dp[N / 2 + 1][0][sum]);
        }
    }

    public static void main(String[] args) {
        // arr = {21, 23, 34, 18, 11, 24};
        // ans = 65

        // arr = {8, 12, 8, 7, 4, 36, 11}
        // ans = 39
        int[] arr = {8, 12, 8, 7, 4, 36, 11};
        int sum = 0;
        for (int i : arr) {
            sum += i;
        }
        System.out.println("sum = " + sum / 2);
        System.out.println(minSum(arr));
        System.out.println(dp(arr));
    }
}
