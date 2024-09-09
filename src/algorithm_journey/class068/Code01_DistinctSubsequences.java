package algorithm_journey.class068;

/**
 * 不同的子序列
 *
 * @author: Agony
 * @create: 2024/9/5 10:24
 * @describe: 给你两个字符串 s 和 t ，统计并返回在 s 的 子序列 中 t 出现的个数，结果需要对 109 + 7 取模。
 * @link: <a href="https://leetcode.cn/problems/distinct-subsequences/description/">不同的子序列</a>
 */
public class Code01_DistinctSubsequences {


    // 不同的子序列
    //
    // 能不能自己先做一下暴力递归，然后慢慢改成动态规划❕❕❕
    //
    // 暴力递归：猜想
    // 设计 int f(char[] str, char[] target, int i, int j) -> 返回在str中前i个字符组成的子序列能够严格等于target中前j个字符组成的字符串 的个数
    // base case
    // 这里可能有问题🙋🙋🙋
    // i==0 -> return 0    ||  j==0 -> return 1  ❓❓❓
    // j==0 -> return 0    ||  i==0&&j==0 -> return 1  ❓❓❓
    // ✅✅✅ -> i==0&&j==0 return1; i==0 return 0; j==0 return 1;
    // 可能性
    // 1. str[i]==str[j]
    // -> 调用 f(i-1,j) + f(i-1, j-1)
    // 解释：
    // f(i-1,j)表示不使用当前字符匹配去前面找别的字符匹配
    // f(i-1,j-1)表示使用当前字符匹配，然后去匹配target的前一个字符
    // 2. str[i]!=str[j]
    // -> 调用 f(i-1)
    // .... 先猜想到这里 ✅✅✅
    //
    // 应该也可以和class067的第一题类似，有从(i,j)位置出发到末尾的的总个数
    // 设计 int f(char[] str, char[] target, int i, int j) -> 返回在str中从第i个字符开始到末尾组成的子序列能够严格等于target中从第j个字符开始到末尾组成的字符串 的个数
    //
    //
    // 动态规划
    // 设计 int[][] dp = new int[m][n]
    // m -> str.length
    // n -> target.length
    // dp[i][j]表示 在str中前i个字符组成的子序列能够严格等于target中前j个字符组成的字符串 的个数
    // 即 target前j个字符组成的字符串，在str前i个字符组成的子序列能使其等于前者(target前j个字符组成的字符串)的个数
    // 当来到(i,j)位置时
    // 情况分析
    // 1. 不要str[i]的字符
    // dp[i][j] = dp[i-1][j]
    // 2. 要str[i]的字符
    // 只有当 str[i]==str[j]时
    // dp[i][j] += dp[i-1][j-1]
    // dp[0][0]=1
    // 填表顺序：从左往右，从上往下
    //
    // 动态规划 + 空间压缩
    // 一维代替二维
    // 每一个位置依赖上方和左上方的值
    // dp从右往左填
    // 来到i位置
    // dp[i]表示上方的值
    // dp[i-1]表示左上方的值


    public static void main(String[] args) {

        // 3
        String s = "rabbbit";
        String t = "rabbit";
        System.out.println(numDistinct1(s, t));
        System.out.println(numDistinct2(s, t));
        System.out.println(numDistinct3(s, t));
        System.out.println(numDistinct4(s, t));
        System.out.println(numDistinct5(s, t));


        // 5
        s = "babgbag";
        t = "bag";
        System.out.println(numDistinct1(s, t));
        System.out.println(numDistinct2(s, t));
        System.out.println(numDistinct3(s, t));
        System.out.println(numDistinct4(s, t));
        System.out.println(numDistinct5(s, t));

        s = "eee";
        t = "eee";
        System.out.println(numDistinct4(s, t));
        System.out.println(numDistinct5(s, t));
    }


    /**
     * 不同的子序列 - 暴力递归
     *
     * @param s
     * @param t
     * @return
     */
    public static int numDistinct1(String s, String t) {


        return f1(s.toCharArray(), t.toCharArray(), s.length(), t.length());
    }


    /**
     * 暴力递归
     *
     * @param s1 字符数组s1
     * @param s2 字符数组s2
     * @param i  s1 前 i 个字符
     * @param j  s2 前 j 个字符
     * @return s1前i个字符组成的子序列等于 s2前j个字符组成字符串的 个数
     */
    public static int f1(char[] s1, char[] s2, int i, int j) {

        if (i == 0 && j == 0) {
            return 1;
        }
        if (i == 0) {
            return 0;
        }
        if (j == 0) {
            return 1;
        }

        int ans = 0;
        // 最后一个字符相同
        if (s1[i - 1] == s2[j - 1]) {
            // s1，s2最后一个字符选择匹配 + s1,s2最后一个字符选择不匹配
            ans = f1(s1, s2, i - 1, j - 1) + f1(s1, s2, i - 1, j);
        } else {
            ans = f1(s1, s2, i - 1, j);
        }

        return ans;

    }


    /**
     * 不同的子序列 - 记忆化搜索
     *
     * @param s
     * @param t
     * @return
     */
    public static int numDistinct2(String s, String t) {


        char[] s1 = s.toCharArray();
        char[] s2 = t.toCharArray();
        int len1 = s.length();
        int len2 = t.length();
        int[][] dp = new int[s1.length + 1][s2.length + 1];
        for (int i = 0; i <= len1; i++) {
            for (int j = 0; j <= len2; j++) {
                dp[i][j] = -1;
            }
        }
        return f2(s1, s2, len1, len2, dp);
    }


    /**
     * 记忆化搜索
     *
     * @param s1
     * @param s2
     * @param i
     * @param j
     * @param dp
     * @return
     */
    public static int f2(char[] s1, char[] s2, int i, int j, int[][] dp) {
        if (i == 0 && j == 0) {
            return 1;
        }
        if (i == 0) {
            return 0;
        }
        if (j == 0) {
            return 1;
        }

        if (dp[i][j] != -1) {
            return dp[i][j];
        }

        int ans = 0;
        // 最后一个字符相同
        if (s1[i - 1] == s2[j - 1]) {
            // s1，s2最后一个字符选择匹配 + s1,s2最后一个字符选择不匹配
            ans = f2(s1, s2, i - 1, j - 1, dp) + f2(s1, s2, i - 1, j, dp);
        } else {
            ans = f2(s1, s2, i - 1, j, dp);
        }
        dp[i][j] = ans;
        return ans;
    }


    /**
     * 不同的子序列 - 动态规划
     *
     * @param s
     * @param t
     * @return
     */
    public static int numDistinct3(String s, String t) {


        char[] s1 = s.toCharArray();
        char[] s2 = t.toCharArray();
        int len1 = s.length();
        int len2 = t.length();

        int[][] dp = new int[len1 + 1][len2 + 1];

        for (int i = 0; i <= len1; i++) {
            dp[i][0] = 1;
        }

        for (int i = 1; i <= len1; i++) {
            for (int j = 1; j <= len2; j++) {
                if (s1[i - 1] == s2[j - 1]) {
                    dp[i][j] = dp[i - 1][j - 1] + dp[i - 1][j];
                } else {
                    dp[i][j] = dp[i - 1][j];
                }
            }
        }
        return dp[len1][len2];
    }


    /**
     * 不同的子序列 - 动态规划 + 空间压缩
     *
     * @param s
     * @param t
     * @return
     */
    public static int numDistinct4(String s, String t) {


        char[] s1 = s.toCharArray();
        char[] s2 = t.toCharArray();
        int len1 = s.length();
        int len2 = t.length();

        int[] dp = new int[len2 + 1];

        dp[0] = 1;
        int leftUp = 0;
        int backup = 0;
        for (int i = 1; i <= len1; i++) {
            leftUp = dp[0];
            for (int j = 1; j <= len2; j++) {
                backup = dp[j];
                if (s1[i - 1] == s2[j - 1]) {
                    dp[j] = leftUp + dp[j];
                }
                leftUp = backup;
            }
        }
        return dp[len2];
    }


    /**
     * 小修改
     *
     * @param s
     * @param t
     * @return
     */
    public static int numDistinct5(String s, String t) {


        char[] s1 = s.toCharArray();
        char[] s2 = t.toCharArray();
        int len1 = s.length();
        int len2 = t.length();

        int[] dp = new int[len2 + 1];

        dp[0] = 1;
        for (int i = 1; i <= len1; i++) {

            // 这里可以从右往左遍历
            // 因为从递归中可以看出，每个位置依赖左上位置和上边位置
            // 从右往左遍历时，来到j位置，dp[j]表示上边的值，dp[j-1]就是左上的值
            // 不需要申请额外变量来表示左上的值了
            for (int j = len2; j >= 1; j--) {

                if (s1[i - 1] == s2[j - 1]) {
                    dp[j] = dp[j] + dp[j - 1];
                }
            }
        }
        return dp[len2];
    }


    // ==============================================
    // ==============================================
    // ==============================================


    // gpt答案
    public static class DistinctSubsequences {


        public static void main(String[] args) {
            DistinctSubsequences ds = new DistinctSubsequences();
            String s = "rabbbit";
            String t = "rabbit";
            System.out.println(ds.numDistinct1(s, t)); // 输出 3
        }

        /**
         * 暴力递归
         *
         * @param s
         * @param t
         * @return
         */
        public int numDistinct1(String s, String t) {
            return countSubsequences(s, t, 0, 0);
        }


        private int countSubsequences(String s, String t, int i, int j) {
            // 如果 t 已经匹配完，说明找到一个匹配的子序列
            if (j == t.length()) {
                return 1;
            }
            // 如果 s 已经匹配完，但 t 还没有匹配完，说明没有匹配成功
            if (i == s.length()) {
                return 0;
            }

            // 如果当前字符匹配上
            if (s.charAt(i) == t.charAt(j)) {
                // 两种选择：匹配这个字符，或者跳过这个字符
                return countSubsequences(s, t, i + 1, j + 1) + countSubsequences(s, t, i + 1, j);
            } else {
                // 当前字符不匹配，只能跳过 s 的当前字符
                return countSubsequences(s, t, i + 1, j);
            }
        }


        /**
         * 动态规划
         *
         * @param s
         * @param t
         * @return
         */
        public int numDistinct2(String s, String t) {
            int m = s.length();
            int n = t.length();

            // 定义 dp 数组，dp[i][j] 表示 s[i..] 中的子序列中等于 t[j..] 的子序列个数
            int[][] dp = new int[m + 1][n + 1];

            // 初始化边界条件
            // 当 t 匹配完了，dp[i][n] = 1，因为空字符串 t 可以从 s 的任何子序列中匹配出来
            for (int i = 0; i <= m; i++) {
                dp[i][n] = 1;
            }

            // 当 s 匹配完了，但 t 没有匹配完，dp[m][j] = 0 (除了 dp[m][n] = 1)
            // 其实 dp[m][j] 的初始值已经是 0，因此不需要特别初始化

            // 从后向前填表
            for (int i = m - 1; i >= 0; i--) {
                for (int j = n - 1; j >= 0; j--) {
                    if (s.charAt(i) == t.charAt(j)) {
                        // 匹配 s[i] 和 t[j]，有两种选择：匹配或不匹配
                        dp[i][j] = dp[i + 1][j + 1] + dp[i + 1][j];
                    } else {
                        // 不匹配，只能跳过 s[i]
                        dp[i][j] = dp[i + 1][j];
                    }
                }
            }

            // dp[0][0] 表示从 s 的开头开始匹配 t 的开头，结果就是答案
            return dp[0][0];
        }


    }

}
