package algorithm_journey.hwod;

import java.util.Scanner;


/**
 * @author: Agony
 * @create: 2025/4/29 10:22
 * @describe: 气球谜题
 * <a href="https://www.nowcoder.com/practice/3b5ebe9b5f944ccda84517bb748a6c0f?tpId=37&rp=1&sourceUrl=%2Fexam%2Foj%2Fta%3FtpId%3D37&difficulty=3&judgeStatus=&tags=&title=&gioEnter=menu">气球谜题</a>
 */
public class HJ111 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        String s = in.next();
        int[] costs = new int[n];
        for (int i = 0; i < n; i++) {
            costs[i] = in.nextInt();
        }
        // 0 :全红、形如000
        // 1 :全黄、形如111
        // 2 :全蓝、形如222
        // 3 :黄红、形如10
        // 4 :蓝黄、形如21
        // 5 :红蓝、形如02
        // 6 :蓝红、形如20
        // 7 :红黄、形如01
        // 8 :黄蓝、形如12
        // 9 :黄蓝红、形如120
        // 10 :红蓝黄、形如021
        // 11 :黄红蓝、形如102
        // 12 :蓝黄红、形如210
        // 13 :蓝红黄、形如201
        // 14 :红黄蓝、形如012
        int[] t = new int[3]; // t[0]、t[1]、t[2]分别表示更改为红黄蓝颜色需要花费的时间
        long[] dp = new long[15];  // 表示更改为对应的颜色类型需要花费的最少时间
        for (int i = 0; i < s.length(); i++) {
            t[0] = t[1] = t[2] = costs[i];
            t[s.charAt(i) - '0'] = 0; // 改为当前颜色的花费为0
            dp[14] = Math.min(dp[14], dp[7]) + t[2];  // 红黄蓝只能由红黄蓝本身以及红黄变过来
            dp[13] = Math.min(dp[13], dp[6]) + t[1];
            dp[12] = Math.min(dp[12], dp[4]) + t[0];
            dp[11] = Math.min(dp[11], dp[3]) + t[2];
            dp[10] = Math.min(dp[10], dp[5]) + t[1];
            dp[9] = Math.min(dp[9], dp[8]) + t[0];
            dp[8] = Math.min(dp[8], dp[1]) + t[2];
            dp[7] = Math.min(dp[7], dp[0]) + t[1];
            dp[6] = Math.min(dp[6], dp[2]) + t[0];
            dp[5] = Math.min(dp[5], dp[0]) + t[2];
            dp[4] = Math.min(dp[4], dp[2]) + t[1];
            dp[3] = Math.min(dp[3], dp[1]) + t[0];
            dp[2] += t[2];
            dp[1] += t[1];
            dp[0] += t[0];
        }

        long minV = Long.MAX_VALUE;
        for (int i = 0; i < 15; i++) {
            minV = Math.min(minV, dp[i]);
        }
        System.out.println(minV);
    }
}

