package algorithm_basic.class19;

/**
 * @Author Agony
 * @Create 2023/9/21 20:09
 * @Version 1.0
 * @describe: 给定两个字符串str1和str2，
 * 返回这两个字符串的最长公共子序列长度
 * 比如 ： str1 = “a12b3c456d”,str2 = “1ef23ghi4j56k”
 * 最长公共子序列是“123456”，所以返回长度6
 * <p><a href="https://leetcode.cn/problems/longest-common-subsequence/">最长公共子序列</a></p>
 */
public class Code04_LongestCommonSubsequence {
    public static int longestCommonSubsequence(String text1, String text2) {
        char[] char1 = text1.toCharArray();
        char[] char2 = text2.toCharArray();

        return process(char1, char2, char1.length - 1, char2.length - 1);
    }

    /**
     * @param chars1 字符串1
     * @param chars2 字符串2
     * @param i      开始位置
     * @param j      开始位置
     * @return 最长子序列长度
     * @describe: 递归尝试
     */
    public static int process(char[] chars1, char[] chars2, int i, int j) {
        // base case
        if (i == 0 && j == 0) {
            return chars1[i] == chars2[j] ? 1 : 0;
        } else if (i == 0) {
            if (chars1[i] == chars2[j]) {
                return 1;
            } else {
                return process(chars1, chars2, i, j - 1);
            }
        } else if (j == 0) {
            if (chars1[i] == chars2[j]) {
                return 1;
            } else {
                return process(chars1, chars2, i - 1, j);
            }
        } else {
            int p1 = process(chars1, chars2, i - 1, j - 1);
            int p2 = process(chars1, chars2, i, j - 1);
            int p3 = process(chars1, chars2, i - 1, j);
            int p4 = chars1[i] != chars2[j] ? 0 : (1 + process(chars1, chars2, i - 1, j - 1));
            return Math.max(Math.max(p1, p2), Math.max(p3, p4));
        }
    }

    /**
     * @param text1 字符串1
     * @param text2 字符串2
     * @return 最长数量
     * @describe: 动态规划
     */
    public static int dp(String text1, String text2) {
        char[] ch1 = text1.toCharArray();
        char[] ch2 = text2.toCharArray();
        int N1 = ch1.length;
        int N2 = ch2.length;
        int[][] dp = new int[N1][N2];
        dp[0][0] = ch1[0] == ch2[0] ? 1 : 0;
        // 第一行
        for (int j = 1; j < N2; j++) {
            dp[0][j] = ch1[0] == ch2[j] ? 1 : dp[0][j - 1];
        }
        // 第一列
        for (int i = 1; i < N1; i++) {
            dp[i][0] = ch1[i] == ch2[0] ? 1 : dp[i - 1][0];
        }

        for (int i = 1; i < N1; i++) {
            for (int j = 1; j < N2; j++) {
                int p1 = dp[i - 1][j - 1];
                int p2 = dp[i][j - 1];
                int p3 = dp[i - 1][j];
                int p4 = ch1[i] != ch2[j] ? 0 : (1 + dp[i - 1][j - 1]);
                dp[i][j] = Math.max(Math.max(p1, p2), Math.max(p3, p4));
            }
        }
        return dp[N1 - 1][N2 - 1];
    }

    public static void main(String[] args) {
        // String str1 = "a12b3c456d";
        String str1 = "hofubmnylkra";
        // String str2 = "1ef23ghi4j56k";
        String str2 = "pqhgxgdofcvmr";
        int i = longestCommonSubsequence(str1, str2);
        int dp = dp(str1, str2);
        System.out.println(i);
        System.out.println(dp);

    }
}


