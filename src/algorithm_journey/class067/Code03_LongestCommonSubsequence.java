package algorithm_journey.class067;

/**
 * @author: Agony
 * @create: 2024/9/1 14:55
 * @describe:
 * @link:
 */
public class Code03_LongestCommonSubsequence {

    // todo

    // 最长公共子序列
    //
    // 暴力递归
    // 设计 int f(char[] s1, char[] s2, int i1, int i2)
    // f函数实现，s1字符串从0出发到i1位置 s1[0...s1] 与 s2从0位置出发到i2 s2[0...s2]，这两个字符串的最长公共子序列
    // 四种可能性 （真是一点都想不到啊❕❕❕）
    // 1. s1从0位置出发到i1-1 与 s2从0位置出发到i2-1的最长公共子序列一样(最长公共子序列不包含最后一个)
    // 2. s1从0位置出发到i1-1 与 s2从0位置出发到i2的最长公共子序列一样
    // 3. s1从0位置出发到i1 与 s2从0位置出发到i2-1的最长公共子序列一样
    // 4. s1从0位置出发到i1 与 s2从0位置出发到i2的最长公共子序列一样，当s1[i1]==s2[i2]时，在第一种可能性的情况下加1
    // 但是该f函数涉及到下标 i1和i2 < 0的情况
    // 在设计dp表的时候会比较麻烦，需要预留一个-1的位置
    // 但是在预留-1的位置会可能发发生越界
    // 所以采用设计f2函数实现
    //
    // 改进1: 设计 int f2(char[] s1, char[] s2, int len1, int len2)
    // f2函数实现，s1字符串前缀长度为len1 与 s2字符串前缀长度为len2，这两个字符串的最长公共子序列
    // len1=6 -> s1[0...5]
    // 这样边界条件就只有 len1==0 || len2==0
    // 改进2: 将上面那四种可能性改进
    // 如果 s1[len1-1]==s2[len2-1] -> 可能性就是 = f2(s1, s2, len1-1, len2-1) + 1，不再去考虑别的可能性
    // 如果 s1[len1-1]!=s2[len2-1] -> 可能性就是 = max(f2(s1, s2, len1, len2-1), f2(s1, s2, len1-1, len2))
    // 因为当最后两个字符不相等时，天然范围更大的情况公共子序列越长
    //
    // 记忆化搜索 (从顶到底)
    // 通过改进之后的改
    // 设计 int[][] dp = new int[len1+1][len2+1]
    //
    // 动态规划 (从底到顶)
    // 每一个位置 (len1, len2) 从上面递归改进版本中可以看出 都依赖 (len1-1, len2-1) (len1-1, len2) (len1, len2-1)
    // 所以填表顺序为从左往右，从上往下
    // 设计 int[][] dp = new int[len1+1][len2+1]
    // 从 len1=1，len2=1，开始 当len1==0||len2==0，显然最长子序列不存在
    // 之后就是两个for循环开始填dp
    // 递归怎么调，dp怎么填
    //
    // 空间压缩
    // 一维数组代替dp表
    // s1，s2中s1表示长的，长的作行，短的作列，一维数组选取较短的那个
    // 因为每一个位置依赖三个位置，如来到 i 位置， dp[i-1]表示左边已经更新的位置，dp[i]表示上边还未更新的位置
    // 因此需要单独一个变量来表示左上的位置，
    // 在每次跟新前先用该变脸记录原来的值，表示左上角的位置
    // 在更新dp[i]时，先记录该值，当来到i-1位置时候，左上角的值就是之前记录的dp[i]
    // 显然这种更新操作需要由两个变量来完成 -> 类似于交换两个数的值需要额外一个变量
    // leftUp -> 记录左上角的值
    // backup -> 记录更新前的值
    // backup = dp[i]
    // dp[i] = ... leftUp
    // leftUp = backup


    /**
     * 之前在leetcode上提交的方法
     *
     * @param text1
     * @param text2
     * @return
     */
    public static int longestCommonSubsequenceAtFirstTime(String text1, String text2) {
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
}













