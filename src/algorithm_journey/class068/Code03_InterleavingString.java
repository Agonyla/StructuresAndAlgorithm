package algorithm_journey.class068;

/**
 * 交错字符串
 *
 * @author: Agony
 * @create: 2024/9/6 11:22
 * @describe: 给定三个字符串 s1、s2、s3，请你帮忙验证 s3 是否是由 s1 和 s2 交错 组成的。
 * <p>
 * 两个字符串 s 和 t 交错 的定义与过程如下，其中每个字符串都会被分割成若干 非空
 * 子字符串：
 * <p>
 * s = s1 + s2 + ... + sn
 * t = t1 + t2 + ... + tm
 * |n - m| <= 1
 * 交错 是 s1 + t1 + s2 + t2 + s3 + t3 + ... 或者 t1 + s1 + t2 + s2 + t3 + s3 + ...
 * 注意：a + b 意味着字符串 a 和 b 连接。
 * @link: <a href="https://leetcode.cn/problems/interleaving-string/">交错字符串</a>
 */
public class Code03_InterleavingString {


    // 交错字符串
    //
    // 动态规划
    // 首先判断三个字符串的长度能不能对的上
    // 设计 boolean[][] dp = new boolean[][]
    // dp[i][j] 表示 s1[0...i-1] 和 s2[0...j-1] 能不能交错组合成 s3[0...i+j-1]个
    // 情况分析：
    // 1. s1[i-1]==s3[i+j-1] (s3最后一个字符来自于s1)
    // -> dp[i-1][j] (用s1[0...i-2] 和 s2[0...j-1] 能不能交错组合成 s3[0...i+j-2])
    // 2. s2[j-1]==s3[i+j-1] (s3最后一个字符来自于s2)
    // -> dp[i][j-1] (用s1[0...i-1] 和 s2[0...j-2] 能不能交错组合成 s3[0...i+j-2])
    // 两种情况有一种成立就是true，都不成立就是false
    // 位置依赖分析：
    // (i,j) 依赖 (i-1, j) (i, j-1) -> 每一个位置都依赖上边和左边的位置
    // 填表顺序：
    // 从左往下，从上往下
    // 特殊位置分析：
    // 第0行，s1字符为空，s3全来自s2 -> 直接判断s2，s3每一个位置是否相等
    // 第0列，s2字符为空，s3全来自s1 -> 直接判断s1，s3每一个位置是否相等
    //
    // 动态规划 + 空间压缩
    // 一维代替二维
    // 简单，之前有涉及到


    public static void main(String[] args) {


        // true
        String s1 = "aabcc";
        String s2 = "dbbca";
        String s3 = "aadbbcbcac";
        System.out.println(isInterleave1(s1, s2, s3));
        System.out.println(isInterleave2(s1, s2, s3));
        System.out.println(isInterleave3(s1, s2, s3));
        System.out.println(isInterleave4(s1, s2, s3));


        // false
        s1 = "aabcc";
        s2 = "dbbca";
        s3 = "aadbbbaccc";
        System.out.println(isInterleave1(s1, s2, s3));
        System.out.println(isInterleave2(s1, s2, s3));
        System.out.println(isInterleave3(s1, s2, s3));
        System.out.println(isInterleave4(s1, s2, s3));


        // true
        s1 = "";
        s2 = "";
        s3 = "";
        System.out.println(isInterleave1(s1, s2, s3));
        System.out.println(isInterleave2(s1, s2, s3));
        System.out.println(isInterleave3(s1, s2, s3));
        System.out.println(isInterleave4(s1, s2, s3));
    }


    /**
     * 交错字符串 - 暴力递归
     *
     * @param s1
     * @param s2
     * @param s3
     * @return
     */
    public static boolean isInterleave1(String s1, String s2, String s3) {


        if (s1.length() + s2.length() != s3.length()) {
            return false;
        }
        return f1(s1.toCharArray(), s2.toCharArray(), s3.toCharArray(), s1.length(), s2.length());
    }


    /**
     * 暴力递归
     *
     * @param s1 字符数组 s1
     * @param s2 字符数组 s2
     * @param s3 字符数组 s3
     * @param i  s1的前i个字符
     * @param j  s2的前j个字符
     * @return s1的前i个字符 和 s2的前j个字符 能否拼成s3的前i+j个字符
     */
    public static boolean f1(char[] s1, char[] s2, char[] s3, int i, int j) {

        // i==0，判断s2和s3每个字符是否相等
        if (i == 0) {
            for (int n = 1; n <= j; n++) {
                if (s2[n - 1] != s3[n - 1]) {
                    return false;
                }
            }
            return true;
        }

        // j==0，判断s1和s3每个字符是否相等
        if (j == 0) {
            for (int n = 1; n <= i; n++) {
                if (s1[n - 1] != s3[n - 1]) {
                    return false;
                }
            }
            return true;
        }

        // s3最后一个字符和谁的是一样的
        if (s1[i - 1] == s3[i + j - 1]) {
            return f1(s1, s2, s3, i - 1, j);
        }
        if (s2[j - 1] == s3[i + j - 1]) {
            return f1(s1, s2, s3, i, j - 1);
        }

        // 两个都不相等
        return false;
    }


    /**
     * 交错字符串 - 记忆化搜索
     *
     * @param s1
     * @param s2
     * @param s3
     * @return
     */
    public static boolean isInterleave2(String s1, String s2, String s3) {

        int len1 = s1.length();
        int len2 = s2.length();
        int len3 = s3.length();

        if (len1 + len2 != len3) {
            return false;
        }
        boolean[][] dp = new boolean[len1 + 1][len2 + 1];
        for (int i = 0; i <= len1; i++) {
            for (int j = 0; j <= len2; j++) {
                dp[i][j] = false;
            }
        }
        return f2(s1.toCharArray(), s2.toCharArray(), s3.toCharArray(), len1, len2, dp);
    }


    /**
     * 记忆化搜索
     *
     * @param s1
     * @param s2
     * @param s3
     * @param i
     * @param j
     * @param dp
     * @return
     */
    public static boolean f2(char[] s1, char[] s2, char[] s3, int i, int j, boolean[][] dp) {

        if (dp[i][j]) {
            return dp[i][j];
        }

        // i==0，判断s2和s3每个字符是否相等
        if (i == 0) {
            for (int n = 1; n <= j; n++) {
                if (s2[n - 1] != s3[n - 1]) {
                    return false;
                }
            }
            return true;
        }

        // j==0，判断s1和s3每个字符是否相等
        if (j == 0) {
            for (int n = 1; n <= i; n++) {
                if (s1[n - 1] != s3[n - 1]) {
                    return false;
                }
            }
            return true;
        }

        boolean p1 = false;
        boolean p2 = false;
        // s3最后一个字符和谁的是一样的
        if (s1[i - 1] == s3[i + j - 1]) {
            p1 = f2(s1, s2, s3, i - 1, j, dp);
        }
        if (s2[j - 1] == s3[i + j - 1]) {
            p2 = f2(s1, s2, s3, i, j - 1, dp);
        }
        dp[i][j] = p1 || p2;

        // 两个都不相等
        return dp[i][j];
    }


    /**
     * 交错字符串 - 动态规划
     *
     * @param str1
     * @param str2
     * @param str3
     * @return
     */
    public static boolean isInterleave3(String str1, String str2, String str3) {


        if (str1.length() + str2.length() != str3.length()) {
            return false;
        }

        char[] s1 = str1.toCharArray();
        char[] s2 = str2.toCharArray();
        char[] s3 = str3.toCharArray();

        int len1 = str1.length();
        int len2 = str2.length();

        boolean[][] dp = new boolean[len1 + 1][len2 + 1];

        dp[0][0] = true;
        for (int j = 1; j <= len2; j++) {
            if (s2[j - 1] != s3[j - 1]) {
                // 这里不能像上面一样直接返回，要把结果记录在表里
                // 下面的数据都会依赖上面的内容
                break;
            }
            dp[0][j] = true;
        }

        for (int i = 1; i <= len1; i++) {
            if (s1[i - 1] != s3[i - 1]) {
                break;
            }
            dp[i][0] = true;
        }

        for (int i = 1; i <= len1; i++) {

            for (int j = 1; j <= len2; j++) {

                // boolean p1 = false;
                // boolean p2 = false;
                // if (s1[i - 1] == s3[i + j - 1]) {
                //     p1 = dp[i - 1][j];
                // }
                // if (s2[j - 1] == s3[i + j - 1]) {
                //     p2 = dp[i][j - 1];
                // }
                // dp[i][j] = p1 || p2;

                // 上面这些可以优化一下
                dp[i][j] = (s1[i - 1] == s3[i + j - 1] && dp[i - 1][j]) || (s2[j - 1] == s3[i + j - 1] && dp[i][j - 1]);
            }
        }
        return dp[len1][len2];
    }


    /**
     * 交错字符串 - 动态规划 + 空间压缩
     *
     * @param str1
     * @param str2
     * @param str3
     * @return
     */
    public static boolean isInterleave4(String str1, String str2, String str3) {


        if (str1.length() + str2.length() != str3.length()) {
            return false;
        }

        char[] s1 = str1.toCharArray();
        char[] s2 = str2.toCharArray();
        char[] s3 = str3.toCharArray();

        int len1 = str1.length();
        int len2 = str2.length();

        boolean[] dp = new boolean[len2 + 1];

        dp[0] = true;
        for (int j = 1; j <= len2; j++) {
            // if (s2[j - 1] != s3[j - 1]) {
            //     break;
            // }
            // 这里也能优化 ❓❓❓(不知道算不算优化)
            dp[j] = s2[j - 1] == s3[j - 1] && dp[j - 1];
        }

        for (int i = 1; i <= len1; i++) {
            // 二维表中，j=0这里列每一行都要满足
            // 所有从上往下遍历时，只有上一行满足且自己这行满足才行
            dp[0] = s1[i - 1] == s3[i - 1] && dp[0];
            for (int j = 1; j <= len2; j++) {
                dp[j] = (s1[i - 1] == s3[i + j - 1] && dp[j]) || (s2[j - 1] == s3[i + j - 1] && dp[j - 1]);
            }
        }
        return dp[len2];
    }

}
























