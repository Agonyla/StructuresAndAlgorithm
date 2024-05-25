package algorithm_basic.class20;

/**
 * @Author Agony
 * @Create 2023/9/20 20:49
 * @Version 1.0
 * <p>
 * 给定一个字符串str，返回这个字符串的最长回文子序列长度
 * 比如 ： str = "a12b3c43def2ghi1kpm"
 * 最长回文子序列是"1234321"或者"123c321"，返回长度7
 * <p><a href="https://leetcode.cn/problems/longest-palindromic-subsequence/">最长回文子序列</a></p>
 * 方法一：把字符串逆序，用上节课的求两个字符串的最长公共子序列  试一试 ，然后再改成dp试一试
 */
public class Code01_PalindromeSubsequence {

    /**
     * @describe: 把字符串逆序, 然后按照最长公共子序列输出
     */
    public static int longestPalindromeSubseq(String s) {

        String s2 = new StringBuilder(s).reverse().toString();
        char[] ch1 = s.toCharArray();
        char[] ch2 = s2.toCharArray();

        int N = ch1.length;
        int M = ch2.length;
        int[][] dp = new int[N][M];
        dp[0][0] = ch1[0] == ch2[0] ? 1 : 0;
        // 第一行
        for (int j = 1; j < M; j++) {
            dp[0][j] = ch1[0] == ch2[j] ? 1 : dp[0][j - 1];
        }
        // 第一列
        for (int i = 1; i < N; i++) {
            dp[i][0] = ch1[i] == ch2[0] ? 1 : dp[i - 1][0];
        }

        for (int i = 1; i < N; i++) {
            for (int j = 1; j < M; j++) {
                int p1 = dp[i][j - 1];
                int p2 = dp[i - 1][j];
                int p3 = ch1[i] == ch2[j] ? (1 + dp[i - 1][j - 1]) : 0;
                dp[i][j] = Math.max(p1, Math.max(p2, p3));
            }
        }
        return dp[N - 1][M - 1];
    }

    /**
     * @describe: 递归尝试
     */
    public static int longestPalindromeSubseq2(String s) {

        char[] ch = s.toCharArray();
        return process(ch, 0, ch.length - 1);
    }

    public static int process(char[] ch, int i, int j) {
        // base case
        if (i == j) {
            return 1;
        }
        if (i == j - 1) {
            return ch[i] == ch[j] ? 2 : 1;
        }
        // 最长回文子序列即不包含i位置,也不再包含j位置
        int p1 = process(ch, i + 1, j - 1);
        // 最长回文子序列即包含i位置,但不再包含j位置
        int p2 = process(ch, i, j - 1);
        // 最长回文子序列不包含i位置,但包含j位置
        int p3 = process(ch, i + 1, j);
        // 最长回文子序列即包含i位置,也包含j位置
        int p4 = ch[i] == ch[j] ? (2 + process(ch, i + 1, j - 1)) : 0;
        return Math.max(Math.max(p1, p2), Math.max(p3, p4));

    }

    public static int dp(String s) {
        char[] ch = s.toCharArray();
        int N = ch.length;
        int[][] dp = new int[N][N];
        for (int i = 0; i < N; i++) {
            dp[i][i] = 1;
        }
        for (int j = 1; j < N; j++) {
            dp[j - 1][j] = ch[j - 1] == ch[j] ? 2 : 1;
        }
        // 第一种遍历方式
        // for (int startCol = 2; startCol < N; startCol++) {
        //     int i = 0;
        //     int j = startCol;
        //     while (j < N) {
        //         int p2 = dp[i][j - 1];
        //         int p3 = dp[i + 1][j];
        //         int p4 = ch[i] == ch[j] ? (2 + dp[i + 1][j - 1]) : 0;
        //         dp[i][j] = Math.max(p2, Math.max(p3, p4));
        //         i++;
        //         j++;
        //     }
        // }
        // 第二种遍历方式
        for (int i = N - 3; i >= 0; i--) {
            for (int j = i + 2; j < N; j++) {
                int p2 = dp[i][j - 1];
                int p3 = dp[i + 1][j];
                int p4 = ch[i] == ch[j] ? (2 + dp[i + 1][j - 1]) : 0;
                dp[i][j] = Math.max(p2, Math.max(p3, p4));
            }
        }
        return dp[0][N - 1];
    }


    public static void main(String[] args) {
        int i = longestPalindromeSubseq("a12b3c43def2ghi1kpm");
        int i1 = longestPalindromeSubseq2("a12b3c43def2ghi1kpm");
        int i3 = dp("a12b3c43def2ghi1kpm");
        System.out.println(i);
        System.out.println(i1);
        System.out.println(i3);
    }
}
