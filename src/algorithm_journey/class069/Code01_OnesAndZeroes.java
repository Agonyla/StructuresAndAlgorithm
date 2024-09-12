package algorithm_journey.class069;


/**
 * 一和零
 *
 * @author: Agony
 * @create: 2024/9/9 20:06
 * @describe: 给你一个二进制字符串数组 strs 和两个整数 m 和 n 。
 * <p>
 * 请你找出并返回 strs 的最大子集的长度，该子集中 最多 有 m 个 0 和 n 个 1 。
 * <p>
 * 如果 x 的所有元素也是 y 的元素，集合 x 是集合 y 的 子集 。
 * @link: <a href="https://leetcode.cn/problems/ones-and-zeroes/description/">一和零</a>
 */
public class Code01_OnesAndZeroes {


    // 一和零(多维费用背包)
    //
    // 暴力递归
    // 设计 int[] f(String[] strs, int i, int zeros, int ones)
    // -> 返回strs从 i 位置开始 希望0的数量不超过zeros、一的数量不超过ones 的最长子集数量
    // strs -> 字符串数组
    // i -> strs[i...]
    // zeros -> 0 的数量不超过 zeros
    // ones -> 1 的数量不超过 ones
    // 可能性分析：
    // 来到i位置
    // 1️⃣. 不选择当前字符串
    // -> 调用 f(strs, i+1, zeros, ones)
    // 2️⃣. 选择当前字符串
    // 需要满足strs[i]字符串的0的数量超过zeros，1的数量不超过ones
    // -> 调用 f(strs, i+1, zeros-z, ones-o)  (z表示当前字符串中0的数量，o表示当前字符串中1的数量)
    // 然后返回两种可能性中的最大值
    //
    // 记忆化搜索
    // 可变参数：i，zeros，ones
    // 设计 int[][][] dp = new int[][][];
    // i取值范围：0~strs.length-1
    // zeros取值范围：0~zeros
    // ones取值范围：0~ones
    // -> dp = new int[strs.length][zeros+1][ones+1]
    // ...
    //
    // 动态规划
    // 位置依赖：
    // 1️⃣. 先建立一个三维坐标轴
    // 2️⃣. 从递归分析依赖，当来到(i,z,o)位置时，都依赖上一层 (i+1, , )
    // 3️⃣. 由于每一层内之间的格子相互之间是不依赖的，所有按照顺序填都可以
    // 填表顺序：
    // 从最上面一层开始，整体顺序从上往下开始填
    // 特殊位置分析：
    // 最上面一层 i==length，dp表都是0
    //
    // 动态规划 + 空间压缩
    // 用二维表代替三维表
    // 因为每一层都依赖上一层，所以可以用二维表示三维
    // 当一层更新完之后，该层都表示上一层的值
    // 此时来到 (i,zeros,ones)位置时
    // 从上面递归可以看出 f(strs, i+1, zeros-z, ones-o)
    // (zeros, ones) 都依赖其所有左边及下边的值 (也就是该位置对应的上一层所有左边及下边的值)
    // 所有二维dp的填表顺序为：
    // 总体方向从右往左，然后再从上往下
    //

    public static void main(String[] args) {

        String[] strs = {"10", "0001", "111001", "1", "0"};
        int m = 5;
        int n = 3;

        // for (int i = 0; i < strs.length; i++) {
        //     getZerosAndOnes(strs[i]);
        //     System.out.println("1= " + ones + " 0= " + zeros);
        // }
        System.out.println(findMaxForm1(strs, m, n));
        System.out.println(findMaxForm2(strs, m, n));
        System.out.println(findMaxForm3(strs, m, n));
        System.out.println(findMaxForm4(strs, m, n));

        strs = new String[]{"10", "0", "1"};
        m = 1;
        n = 1;
        System.out.println(findMaxForm1(strs, m, n));
        System.out.println(findMaxForm2(strs, m, n));
        System.out.println(findMaxForm3(strs, m, n));
        System.out.println(findMaxForm4(strs, m, n));

    }


    /**
     * 一和零 - 暴力递归
     *
     * @param strs
     * @param m
     * @param n
     * @return
     */
    public static int findMaxForm1(String[] strs, int m, int n) {

        return f1(strs, 0, m, n);
    }


    /**
     * 暴力递归
     *
     * @param strs 字符串数组
     * @param i    当前来到第i个位置
     * @param m    0的最大个数
     * @param n    1的最多个数
     * @return 返回strs的最大子集的长度，该子集中最多有m个0和n个1
     */
    public static int f1(String[] strs, int i, int m, int n) {


        if (i == strs.length) {
            return 0;
        }
        // 选择当前字符串
        int p1 = 0;
        getZerosAndOnes(strs[i]);
        if (zeros <= m && ones <= n) {
            p1 = f1(strs, i + 1, m - zeros, n - ones) + 1;
        }
        // 不选择当前字符串
        int p2 = f1(strs, i + 1, m, n);
        return Math.max(p1, p2);
    }


    /**
     * 一和零 - 记忆化搜索
     *
     * @param strs
     * @param m
     * @param n
     * @return
     */
    public static int findMaxForm2(String[] strs, int m, int n) {


        int[][][] dp = new int[strs.length][m + 1][n + 1];
        for (int i = 0; i < strs.length; i++) {
            for (int j = 0; j <= m; j++) {
                for (int k = 0; k <= n; k++) {
                    dp[i][j][k] = -1;
                }
            }
        }
        return f2(strs, 0, m, n, dp);
    }


    /**
     * 记忆化搜索
     *
     * @param strs
     * @param i
     * @param m
     * @param n
     * @param dp
     * @return
     */
    public static int f2(String[] strs, int i, int m, int n, int[][][] dp) {

        if (i == strs.length) {
            return 0;
        }

        if (dp[i][m][n] != -1) {
            return dp[i][m][n];
        }
        // 选择当前字符串
        int p1 = 0;
        getZerosAndOnes(strs[i]);
        if (zeros <= m && ones <= n) {
            p1 = f2(strs, i + 1, m - zeros, n - ones, dp) + 1;
        }
        int p2 = f2(strs, i + 1, m, n, dp);
        dp[i][m][n] = Math.max(p1, p2);
        return dp[i][m][n];
    }


    /**
     * 一和零 - 动态规划
     *
     * @param strs
     * @param m
     * @param n
     * @return
     */
    public static int findMaxForm3(String[] strs, int m, int n) {


        int[][][] dp = new int[strs.length + 1][m + 1][n + 1];

        for (int i = strs.length - 1; i >= 0; i--) {
            getZerosAndOnes(strs[i]);
            for (int z = 0; z <= m; z++) {
                for (int o = 0; o <= n; o++) {
                    int p1 = 0;
                    if (zeros <= z && ones <= o) {
                        p1 = dp[i + 1][z - zeros][o - ones] + 1;
                    }
                    int p2 = dp[i + 1][z][o];
                    dp[i][z][o] = Math.max(p1, p2);
                }
            }

        }
        return dp[0][m][n];
    }


    /**
     * 一和零 - 动态规划 + 空间压缩
     *
     * @param strs
     * @param m
     * @param n
     * @return
     */
    public static int findMaxForm4(String[] strs, int m, int n) {


        int[][] dp = new int[m + 1][n + 1];
        for (String str : strs) {
            getZerosAndOnes(str);
            for (int z = m; z >= zeros; z--) {
                for (int o = n; o >= ones; o--) {
                    dp[z][o] = Math.max(dp[z][o], dp[z - zeros][o - ones] + 1);
                }
            }
        }
        return dp[m][n];
    }


    //=============================
    //=============================
    //=============================


    public static int zeros;
    public static int ones;

    /**
     * 获取字符串中0和1的数量
     *
     * @param str
     */
    public static void getZerosAndOnes(String str) {
        zeros = 0;
        ones = 0;

        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) == '0') {
                zeros++;
            } else {
                ones++;
            }
        }
    }
}
















