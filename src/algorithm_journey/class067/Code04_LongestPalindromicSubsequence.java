package algorithm_journey.class067;

/**
 * 最长回文子序列
 *
 * @author: Agony
 * @create: 2024/9/3 20:58
 * @describe: 给你一个字符串 s ，找出其中最长的回文子序列，并返回该序列的长度。
 * <p>
 * 子序列定义为：不改变剩余字符顺序的情况下，删除某些字符或者不删除任何字符形成的一个序列。
 * @link: <a href="https://leetcode.cn/problems/longest-palindromic-subsequence/description/">最长回文子序列</a>
 */
public class Code04_LongestPalindromicSubsequence {


    public static void main(String[] args) {

        String str = "bbbab";  // 4
        System.out.println(longestPalindromeSubseq1(str));
        System.out.println(longestPalindromeSubseq2(str));
        System.out.println(longestPalindromeSubseq3(str));
        System.out.println(longestPalindromeSubseq4(str));


        str = "cbbd";  // 2
        System.out.println(longestPalindromeSubseq1(str));
        System.out.println(longestPalindromeSubseq2(str));
        System.out.println(longestPalindromeSubseq3(str));
        System.out.println(longestPalindromeSubseq4(str));
    }

    // 最长回文子序列
    //
    // 将字符串逆序，然后求连个字符串的最长公共子序列
    //
    // 新的尝试方法
    // 暴力递归
    // 设计 int f(char[] s, int l, int r) -> 从l到r这个区间最长回文子序列的长度
    // 情况分析
    // s[l]!=s[r]
    // 1. s[l+1...r-1]
    // 2. s[l...r-1]
    // 3. s[l+1...r]
    // 类似上一题，2，3两种情况结果肯定是>=1的
    // s[l]==s[r]
    // -> s[l+1...r-1] + 2
    //
    // 记忆化搜索
    // ...
    //
    // 动态规划
    // 显然可变参数只有l (0~s.length), r(0~s.length)
    // 且必然 l<=r -> 所以在二维dp表上只有右上角一半的区域
    // l==r这篇区域 dp[...] = 1
    // 从依赖位置分析，每一个格子依赖左边、左下、下边的位置
    // 所以填表顺序 -> 从左到右，从下到上
    //
    // 动态规划 + 空间压缩
    // 用一维数组代替二维dp表
    // 同样需要用变量来记录左下角的值
    // 类似于上一题


    /**
     * 最长回文子序列 - 暴力递归
     *
     * @param s
     * @return
     */
    public static int longestPalindromeSubseq1(String s) {


        return f1(s.toCharArray(), 0, s.length() - 1);
    }


    /**
     * 暴力递归
     *
     * @param s 字符数组
     * @param l 左区间
     * @param r 右区间
     * @return 返回字符数组在[l, r]之前的最长回文子序列长度
     */
    public static int f1(char[] s, int l, int r) {

        // base case
        if (l == r) {
            return 1;
        }
        if (l + 1 == r) {
            return s[l] == s[r] ? 2 : 1;
        }
        if (s[l] == s[r]) {
            return f1(s, l + 1, r - 1) + 2;
        } else {
            return Math.max(f1(s, l + 1, r), f1(s, l, r - 1));
        }
    }


    /**
     * 最长回文子序列 - 记忆化搜索
     *
     * @param s
     * @return
     */
    public static int longestPalindromeSubseq2(String s) {
        int n = s.length();
        int[][] dp = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                dp[i][j] = -1;
            }
        }

        return f2(s.toCharArray(), 0, n - 1, dp);
    }


    /**
     * 记忆化搜索
     *
     * @param s
     * @param l
     * @param r
     * @param dp
     * @return
     */
    public static int f2(char[] s, int l, int r, int[][] dp) {

        if (l == r) {
            return 1;
        }
        if (l + 1 == r) {
            return s[l] == s[r] ? 2 : 1;
        }
        if (dp[l][r] != -1) {
            return dp[l][r];
        }
        int ans = 0;
        if (s[l] == s[r]) {
            ans = f2(s, l + 1, r - 1, dp) + 2;
        } else {
            ans = Math.max(f2(s, l + 1, r, dp), f2(s, l, r - 1, dp));
        }
        dp[l][r] = ans;
        return ans;
    }


    /**
     * 最长回文子序列 - 动态规划
     *
     * @param str
     * @return
     */
    public static int longestPalindromeSubseq3(String str) {

        int n = str.length();
        char[] s = str.toCharArray();

        int[][] dp = new int[n][n];
        for (int l = n - 1; l >= 0; l--) {

            // 对角线格子都是1
            dp[l][l] = 1;

            // l+1==r的格子，相等是2，不等是1
            if (l + 1 < n) {
                dp[l][l + 1] = s[l] == s[l + 1] ? 2 : 1;
            }
            // 这里直接从倒数第3行开始填
            for (int r = l + 2; r < n; r++) {
                if (s[l] == s[r]) {
                    dp[l][r] = dp[l + 1][r - 1] + 2;
                } else {
                    dp[l][r] = Math.max(dp[l + 1][r], dp[l][r - 1]);
                }
            }
        }
        return dp[0][n - 1];
    }


    /**
     * 最长回文子序列 - 动态规划 + 空间压缩
     *
     * @param str
     * @return
     */
    public static int longestPalindromeSubseq4(String str) {

        int n = str.length();
        char[] s = str.toCharArray();

        int[] dp = new int[n];

        for (int l = n - 1, leftDown = 0, backup; l >= 0; l--) {

            dp[l] = 1;
            if (l + 1 < n) {
                // 左下角的值就是更新之前的值
                leftDown = dp[l + 1];
                dp[l + 1] = s[l] == s[l + 1] ? 2 : 1;
            }
            for (int r = l + 2; r < n; r++) {
                // 更新之前的值先备份一下，作为左下角的值
                backup = dp[r];
                if (s[l] == s[r]) {
                    dp[r] = leftDown + 2;
                } else {
                    dp[r] = Math.max(dp[r - 1], dp[r]);
                }
                leftDown = backup;
            }
        }
        return dp[n - 1];
    }


    // ====================================================
    // ====================================================
    // ====================================================


    /**
     * 之前leetcode提交的代码
     *
     * @param s
     * @return
     */
    public static int longestPalindromeSubseqAtFirstTime(String s) {
        char[] ch = s.toCharArray();
        int N = ch.length;
        int[][] dp = new int[N][N];
        for (int i = 0; i < N; i++) {
            dp[i][i] = 1;
        }
        for (int j = 1; j < N; j++) {
            dp[j - 1][j] = ch[j - 1] == ch[j] ? 2 : 1;
        }
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
}
