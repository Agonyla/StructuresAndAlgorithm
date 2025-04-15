package algorithm_journey.hwod;

import java.util.Arrays;
import java.util.Scanner;

/**
 * @author: Agony
 * @create: 2025/4/15 10:01
 * @describe: 计算字符串的编辑距离
 * <a href="https://www.nowcoder.com/practice/3959837097c7413a961a135d7104c314?tpId=37&tqId=21275&rp=1&sourceUrl=%2Fexam%2Foj%2Fta%3FtpId%3D37&difficulty=3&judgeStatus=undefined&tags=&title=">计算字符串的编辑距离</a>
 */
public class HJ52 {

    public static void main(String[] args) {


        Scanner scanner = new Scanner(System.in);
        String s1 = scanner.nextLine();
        String s2 = scanner.nextLine();

        int[][] dp = new int[s1.length() + 1][s2.length() + 1];

        for (int[] ints : dp) {
            Arrays.fill(ints, -1);
        }
        System.out.println(process(s1.toCharArray(), s2.toCharArray(), s1.length(), s2.length(), dp));

    }


    public static int process(char[] s1, char[] s2, int i, int j, int[][] dp) {

        if (i == 0) {
            return j;
        }
        if (j == 0) {
            return i;
        }

        if (dp[i][j] != -1) {
            return dp[i][j];
        }
        int p1 = Integer.MAX_VALUE;
        int p2 = Integer.MAX_VALUE;

        if (s1[i - 1] == s2[j - 1]) {
            p1 = process(s1, s2, i - 1, j - 1, dp);
        } else {
            p2 = process(s1, s2, i - 1, j - 1, dp) + 1;
        }
        int p3 = process(s1, s2, i - 1, j, dp) + 1;
        int p4 = process(s1, s2, i, j - 1, dp) + 1;

        dp[i][j] = Math.min(Math.min(p1, p2), Math.min(p3, p4));

        return Math.min(Math.min(p1, p2), Math.min(p3, p4));
    }
}
