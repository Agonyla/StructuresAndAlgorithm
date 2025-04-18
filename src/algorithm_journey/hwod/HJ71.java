package algorithm_journey.hwod;

import java.util.Arrays;
import java.util.Scanner;

/**
 * @author: Agony
 * @create: 2025/4/18 14:54
 * @describe: 字符串通配符
 * <a href="https://www.nowcoder.com/practice/43072d50a6eb44d2a6c816a283b02036?tpId=37&tqId=21294&rp=1&sourceUrl=%2Fexam%2Foj%2Fta%3FtpId%3D37&difficulty=3&judgeStatus=undefined&tags=&title=">字符串通配符</a>
 */
public class HJ71 {


    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        while (scanner.hasNext()) {

            String s = scanner.nextLine();
            String target = scanner.nextLine();

            // -1: 未计算
            // 0：不能匹配
            // 1：可以匹配
            int[][] dp = new int[s.length() + 1][target.length() + 1];
            for (int i = 0; i < s.length() + 1; i++) {
                Arrays.fill(dp[i], -1);
            }

            System.out.println(isMatched(s.toCharArray(), target.toCharArray(), 0, 0, dp));
        }


    }


    public static boolean isMatched(char[] s, char[] target, int i, int j, int[][] dp) {
        if (dp[i][j] != -1) {
            return dp[i][j] == 1;
        }

        if (i == s.length && j == target.length) {
            dp[i][j] = 1;
            return true;
        }
        if (i == s.length) {
            dp[i][j] = 0;
            return false;
        }

        if (j == target.length) {
            boolean result = s[i] == '*' && isMatched(s, target, i + 1, j, dp);
            dp[i][j] = result ? 1 : 0;
            return result;
        }

        char c = s[i];
        boolean match = false;
        if (c == '*') {
            // 不匹配
            if (isMatched(s, target, i + 1, j, dp)) {
                match = true;
            } else if (isDigitOrLetter(target[j])) {
                // 匹配1个或多个
                match = isMatched(s, target, i, j + 1, dp);
            }
        } else if (c == '?') {
            if (isDigitOrLetter(target[j])) {
                match = isMatched(s, target, i + 1, j + 1, dp);
            }
        } else {
            if (isLetterMatched(c, target[j])) {
                match = isMatched(s, target, i + 1, j + 1, dp);
            }
        }
        dp[i][j] = match ? 1 : 0;
        return match;
    }

    private static boolean isDigitOrLetter(char c) {

        return Character.isLetter(c) || Character.isDigit(c);
    }


    private static boolean isLetterMatched(char c1, char c2) {
        if (c1 == c2) {
            return true;
        }
        if (!Character.isLetter(c1) || !Character.isLetter(c2)) {
            return false;
        }

        if (Character.toLowerCase(c1) == Character.toLowerCase(c2)) {
            return true;
        }
        return Character.toUpperCase(c1) == Character.toUpperCase(c2);
    }


}
