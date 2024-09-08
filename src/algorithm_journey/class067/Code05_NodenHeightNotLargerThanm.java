package algorithm_journey.class067;

import java.io.*;

/**
 * 节点数为n高度不大于m的二叉树个数
 *
 * @author: Agony
 * @create: 2024/9/3 21:26
 * @describe: 小强现在有
 * n个节点,他想请你帮他计算出有多少种不同的二叉树满足节点个数为
 * n且树的高度不超过m的方案.因为答案很大,所以答案需要模上1e9+7后输出.
 * 树的高度: 定义为所有叶子到根路径上节点个数的最大值.
 * @link: <a href="https://www.nowcoder.com/practice/aaefe5896cce4204b276e213e725f3ea">节点数为n高度不大于m的二叉树个数</a>
 */
public class Code05_NodenHeightNotLargerThanm {


    // 节点数为n高度不大于m的二叉树个数

    // 暴力递归
    // 设计 int f(int n, int m) -> 返回节点数为n高度不大于m的二叉树个数
    // n -> 节点总数
    // m -> 高度
    // base case: n==0 -> return 1; 当节点数为0时候，总有一种结果
    // m==0 -> return 0; 当高度为0时，没有结果
    // 假设总共有5个节点，高度为3
    // 那么在头节点占有一个之后，就有如下5种情况
    // 左:0; 右:4，且高度<=2
    // 左:1,且高度<=2 ; 右:3，且高度<=2
    // 左:2,且高度<=2 ; 右:2，且高度<=2
    // 左:3,且高度<=2 ; 右:1，且高度<=2
    // 左:4,且高度<=2; 右:0，
    // 在每一种情况下的答案即为左右两种情况的乘积
    // 然后再所有情况结果相加
    // 注意⚠️：结果要用同余原理！！！
    //
    // 记忆化搜索
    // ...
    //
    // 动态规划
    //
    // 可变参数： n，m
    // 位置分析，来到(n, m)都依赖于(0~n-1, m-1)这些格子。  自己画图看一下❕❕❕
    // 填表顺序：从左往右，从上到下
    // ⚠️：一些特殊的位置，需要通过另外的for循环，或者在填表过程中另外执行
    //
    // 动态规划 + 空间压缩
    // 用一维代替二维dp
    // 之前题目的一维dp是横着的(记录的是每一列的数字)，然后按照行开始向下滚动更新
    // 该题设计的一维dp是竖着的(记录的是每一行的数字)，然后按照列开始向右滚动更新
    // dp更新的时候是从下往上更新，来到i位置，0~i-1位置表示是上一列还未更新的值


    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StreamTokenizer in = new StreamTokenizer(br);
        PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));

        // 节点数
        in.nextToken();
        int n = (int) in.nval;

        // 高度
        in.nextToken();
        int m = (int) in.nval;

        out.println(f4(n, m));

        out.flush();
        out.close();
        br.close();
    }

    public static int MAX_LENGTH = 51;

    public static int MOD = 1000000007;

    public static long[][] dp = new long[MAX_LENGTH][MAX_LENGTH];

    static {
        for (int i = 0; i < MAX_LENGTH; i++) {
            for (int j = 0; j < MAX_LENGTH; j++) {
                dp[i][j] = -1;
            }
        }
    }

    /**
     * 节点数为n高度不大于m的二叉树个数 - 暴力递归
     *
     * @param n
     * @param m
     * @return
     */
    public static int f1(int n, int m) {

        // 没节点了说明这种方案可行
        if (n == 0) {
            return 1;
        }
        if (m == 0) {
            return 0;
        }

        long ans = 0;
        for (int i = 0; i < n; i++) {

            // 一共 左树 * 右树种可能
            // 头节点1个
            // 左树1个，右树 n-1-1
            ans = (ans + (long) f1(i, m - 1) * f1(n - i - 1, m - 1) % MOD) % MOD;

        }
        return (int) ans;
    }


    /**
     * 节点数为n高度不大于m的二叉树个数 - 记忆化搜索
     *
     * @param n
     * @param m
     * @return
     */
    public static int f2(int n, int m) {
        // 没节点了说明这种方案可行
        if (n == 0) {
            return 1;
        }
        if (m == 0) {
            return 0;
        }
        if (dp[n][m] != -1) {
            return (int) dp[n][m];
        }

        long ans = 0;
        for (int i = 0; i < n; i++) {

            // 一共 左树 * 右树种可能
            // 头节点1个
            // 左树1个，右树 n-1-1
            ans = (ans + (long) f2(i, m - 1) * f2(n - i - 1, m - 1) % MOD) % MOD;
        }
        dp[n][m] = ans;
        return (int) dp[n][m];
    }


    public static long[][] dp3 = new long[MAX_LENGTH][MAX_LENGTH];

    /**
     * 节点数为n高度不大于m的二叉树个数 - 动态规划
     *
     * @param n
     * @param m
     * @return
     */
    public static int f3(int n, int m) {

        for (int j = 0; j <= m; j++) {
            dp3[0][j] = 1;
        }

        for (int i = 1; i <= n; i++) {

            for (int j = 1; j <= m; j++) {
                dp3[i][j] = 0;

                for (int k = 0; k < i; k++) {
                    dp3[i][j] = (dp3[i][j] + dp3[k][j - 1] * dp3[i - k - 1][j - 1] % MOD) % MOD;
                }
            }
        }
        return (int) dp3[n][m];
    }


    public static long[] dp4 = new long[MAX_LENGTH];

    /**
     * 节点数为n高度不大于m的二叉树个数 - 动态规划 + 空间压缩
     *
     * @param n
     * @param m
     * @return
     */
    public static int f4(int n, int m) {
        dp4[0] = 1;
        for (int i = 1; i <= n; i++) {
            dp4[i] = 0;
        }
        for (int j = 1; j <= m; j++) {
            // 根据依赖，一定要先枚举列
            for (int i = n; i >= 1; i--) {
                // 再枚举行，而且i不需要到达0，i>=1即可
                dp4[i] = 0;
                for (int k = 0; k < i; k++) {
                    // 枚举
                    dp4[i] = (dp4[i] + dp4[k] * dp4[i - k - 1] % MOD) % MOD;
                }
            }
        }
        return (int) dp4[n];
    }


}













