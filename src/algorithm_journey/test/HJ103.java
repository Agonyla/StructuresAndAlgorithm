package algorithm_journey.test;

import java.util.Arrays;
import java.util.Scanner;

/**
 * @author: Agony
 * @create: 2025/4/28 21:24
 * @describe: Redraiment的走法
 * <a href="https://www.nowcoder.com/practice/24e6243b9f0446b081b1d6d32f2aa3aa?tpId=37&tqId=21326&rp=1&sourceUrl=%2Fexam%2Foj%2Fta%3FtpId%3D37&difficulty=3&judgeStatus=undefined&tags=&title=">Redraiment的走法</a>
 */
public class HJ103 {


    public static void main(String[] args) {


        Scanner scanner = new Scanner(System.in);

        int n = scanner.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = scanner.nextInt();
        }
        scanner.nextLine();

        int[] dp = new int[n];
        Arrays.fill(dp, -1);


        int ans = 0;
        for (int i = 0; i < n; i++) {
            int cur = process(arr, i, dp);
            ans = Math.max(ans, cur);
        }

        System.out.println(ans);

    }


    /**
     * @param arr
     * @param i
     * @return
     */
    public static int process(int[] arr, int i, int[] dp) {
        if (dp[i] != -1) {
            return dp[i];
        }
        int max = 1;

        for (int j = 0; j < i; j++) {
            if (arr[i] > arr[j]) {
                max = Math.max(max, 1 + process(arr, j, dp));
            }
        }
        dp[i] = max;
        return dp[i];
    }
}
