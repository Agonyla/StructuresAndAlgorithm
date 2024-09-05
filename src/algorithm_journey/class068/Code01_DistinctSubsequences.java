package algorithm_journey.class068;

/**
 * @author: Agony
 * @create: 2024/9/5 10:24
 * @describe:
 * @link:
 */
public class Code01_DistinctSubsequences {

    // todo
    //
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
    // 可能性
    // 1. str[i]==str[j]
    // -> 调用 f(i-1,j) + f(i-1, j-1)
    // 解释：
    // f(i-1,j)表示不使用当前字符匹配去前面找别的字符匹配
    // f(i-1,j-1)表示使用当前字符匹配，然后去匹配target的前一个字符
    // 2. str[i]!=str[j]
    // -> 调用 f(i-1)
    // .... 先猜想到这里
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
