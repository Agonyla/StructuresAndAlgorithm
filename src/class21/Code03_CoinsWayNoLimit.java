package class21;

/**
 * @Author Agony
 * @Create 2023/9/24 15:54
 * @Version 1.0
 * @describe: arr是面值数组，其中的值都是正数且没有重复。再给定一个正数aim。
 * 每个值都认为是一种面值，且认为张数是无限的。
 * 返回组成aim的方法数
 * 例如：arr = {1,2}，aim = 4
 * 方法如下：1+1+1+1、1+1+2、2+2
 * 一共就3种方法，所以返回3
 */
public class Code03_CoinsWayNoLimit {

    public static int myCoinWays(int[] arr, int aim) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        return myProcess(arr, 0, aim);
    }

    // 递归尝试  ==> 自己瞎几把尝试了一下
    public static int myProcess(int[] arr, int index, int rest) {
        if (index == arr.length) {
            return rest == 0 ? 1 : 0;
        }
        if (rest == 0) {
            return 1;
        }
        if (rest < 0) {
            return 0;
        }
        // 继续使用当前的arr[index]
        int p1 = myProcess(arr, index, rest - arr[index]);
        // 不用当前张
        int p3 = myProcess(arr, index + 1, rest);
        return p1 + p3;
    }

    public static int coinWays(int[] arr, int aim) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        return process(arr, 0, aim);
    }


    // 枚举递归尝试 (在递归中有for循环，每种情况都去尝试)
    public static int process(int[] arr, int index, int rest) {
        if (index == arr.length) {
            return rest == 0 ? 1 : 0;
        }
        int ways = 0;
        // 在for循环的判断中去除了rest<0的情况，=0的情况会一直调用process直到index==arr.length
        for (int num = 0; num * arr[index] <= rest; num++) {
            ways += process(arr, index + 1, rest - num * arr[index]);
        }
        return ways;
    }

    // 枚举动态规划
    public static int dp(int[] arr, int aim) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        int N = arr.length;
        // index 取值范围：0~N
        // rest 取值范围：0~aim
        int[][] dp = new int[N + 1][aim + 1];
        dp[N][0] = 1;
        // 从下到上  从左到右
        for (int index = N - 1; index >= 0; index--) {
            for (int rest = 0; rest <= aim; rest++) {
                int ways = 0;
                for (int num = 0; num * arr[index] <= rest; num++) {
                    ways += dp[index + 1][rest - num * arr[index]];
                }
                dp[index][rest] = ways;
            }
        }
        return dp[0][aim];
    }

    // 枚举动态规划优化
    public static int dp2(int[] arr, int aim) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        int N = arr.length;
        // index 取值范围：0~N
        // rest 取值范围：0~aim
        int[][] dp = new int[N + 1][aim + 1];
        dp[N][0] = 1;
        // 从下到上  从左到右
        for (int index = N - 1; index >= 0; index--) {
            for (int rest = 0; rest <= aim; rest++) {
                dp[index][rest] = dp[index + 1][rest];
                if (rest - arr[index] >= 0) {
                    dp[index][rest] += dp[index][rest - arr[index]];
                }
            }
        }
        return dp[0][aim];
    }


    // 动态规划  ==> 根据瞎几把尝试改的
    public static int myDP(int[] arr, int aim) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        int N = arr.length;
        // index 取值范围：0~N
        // rest 取值范围：0~aim
        int[][] dp = new int[N + 1][aim + 1];
        dp[N][0] = 1;
        for (int i = 0; i < N; i++) {
            dp[i][0] = 1;
        }
        // 从下到上  从左到右
        for (int index = N - 1; index >= 0; index--) {
            for (int rest = 1; rest <= aim; rest++) {
                int ways = dp[index + 1][rest];
                if (rest - arr[index] >= 0) {
                    ways += dp[index][rest - arr[index]];
                }
                dp[index][rest] = ways;
            }
        }
        return dp[0][aim];
    }


    public static void main(String[] args) {
        //  14 8 10 21 16 1
        // aim = 30
        // 21
        int[] arr = {14, 8, 10, 21, 16, 1};
        int aim = 30;
        System.out.println(myCoinWays(arr, aim));
        System.out.println(myDP(arr, aim));
        System.out.println(coinWays(arr, aim));
        System.out.println(dp(arr, aim));
        System.out.println(dp2(arr, aim));
    }
}
