package algorithm_journey.class069;

/**
 * 盈利计划
 *
 * @author: Agony
 * @create: 2024/9/10 10:44
 * @describe: 集团里有 n 名员工，他们可以完成各种各样的工作创造利润。
 * <p>
 * 第 i 种工作会产生 profit[i] 的利润，它要求 group[i] 名成员共同参与。如果成员参与了其中一项工作，就不能参与另一项工作。
 * <p>
 * 工作的任何至少产生 minProfit 利润的子集称为 盈利计划 。并且工作的成员总数最多为 n 。
 * <p>
 * 有多少种计划可以选择？因为答案很大，所以 返回结果模 10^9 + 7 的值。
 * @link: <a href="https://leetcode.cn/problems/profitable-schemes/description/">盈利计划</a>
 */
public class Code02_ProfitableSchemes {


    // 盈利计划(多维费用背包)
    //
    // 暴力递归
    // 设计 int f(int[] group, int[] profit, int i, int r, int s)
    // -> 返回有多少种方案
    // group -> 员工数组
    // profit -> 利润数组
    // i -> 当前来到员工数组(利润数组)的第i位
    // r -> 员工额度还剩下多少人，如果r<=0说明没法再选择项目了
    // s -> 利润还要s才达标，如果s<=0说明之前的选择已经让利润达标了
    // 边界条件：
    // r<=0 -> return s<=0?1:0;
    // i==group.length -> return s<=0?1:0;
    // 可能性分析：
    // 1️⃣不要当前工作
    // 2️⃣要当前工作  (只有在group[i]<=r的情况下才能选择当前工作)
    //
    // 记忆化搜索
    // 设计 int[][][] dp = new int[g.length][n+1][minProfit+1] ❓❓❓➡️ ✅✅✅
    // ...
    // 注意点⚠️
    // 在上面调用递归的过程中 -> f(g,p,i,r-g[i],s-p[i])是这样调的，没问题
    // 但是在记忆化搜索中，这样调用，其中 s-p[i]会出现负数，dp表会越界(dp[i][r][s], 这时候s会<0, i和r通过边界条件判断是不会出现问题的)
    // 当s<0，这种方案是可以继续下去的(本质上和s==0没区别)，所以，为了让dp不越界，在调用递归的时候，用 max(0,s-p[i])
    // 这样就可以解决dp越界问题
    //
    // 动态规划
    // 设计 dp = new int[g.length+1][n+1][minProfit+1]  因为最上面一层是 i==g.length, 所以是不是初始长度要g.length+1 ❓❓❓ ➡️ ✅✅✅
    // 位置依赖：
    // 从递归分析依赖，当来到(i,r,s)位置时，都依赖上一层 (i+1, , )
    // 每一层内的位置是不相互依赖的
    // 填表顺序：
    // 从上往下，层内随便填
    // 特殊位置分析：
    // i==g.length -> s==0?1:0
    //
    // 空间压缩：
    // 二维代替三维
    // 因为每一层都依赖上一层，所以可以用二维表示三维
    // 当一层更新完之后，该层都表示上一层的值
    // (r, i) 都依赖其所有左边及下边的值 (也就是该位置对应的上一层所有左边及下边的值)
    // 内容与上一类似
    // 所有二维dp的填表顺序为：
    // 总体方向从右往左，然后再从上往下
    // ⚠️⚠️⚠️：这个跳表还是要去画一下图，和单纯的二维动态规划不一样，二维：从上往下变大，从左往右变大
    // 三维：类似xyz坐标轴，空间压缩后的二维：从左往右变大，从下往上变大


    public static void main(String[] args) {

        int n = 5;
        int minProfit = 3;
        int[] group = {2, 2};
        int[] profit = {2, 3};
        System.out.println(profitableSchemes1(n, minProfit, group, profit));
        System.out.println(profitableSchemes2(n, minProfit, group, profit));
        System.out.println(profitableSchemes3(n, minProfit, group, profit));
        System.out.println(profitableSchemes4(n, minProfit, group, profit));

        n = 10;
        minProfit = 5;
        group = new int[]{2, 3, 5};
        profit = new int[]{6, 7, 8};
        System.out.println(profitableSchemes1(n, minProfit, group, profit));
        System.out.println(profitableSchemes2(n, minProfit, group, profit));
        System.out.println(profitableSchemes3(n, minProfit, group, profit));
        System.out.println(profitableSchemes4(n, minProfit, group, profit));


        n = 1;
        minProfit = 1;
        group = new int[]{2, 2, 2, 2, 2};
        profit = new int[]{1, 2, 1, 1, 0};
        System.out.println(profitableSchemes1(n, minProfit, group, profit));
        System.out.println(profitableSchemes2(n, minProfit, group, profit));
        System.out.println(profitableSchemes3(n, minProfit, group, profit));
        System.out.println(profitableSchemes4(n, minProfit, group, profit));

    }

    private static int mod = 1000000007;


    /**
     * 盈利计划 - 暴力递归
     *
     * @param n         最多工作成员
     * @param minProfit 最少利润
     * @param group     每种工作的人员数组
     * @param profit    每种工作能产生的利润
     * @return 返回有多少种选择
     */
    public static int profitableSchemes1(int n, int minProfit, int[] group, int[] profit) {

        return f1(group, profit, n, minProfit, 0);
    }

    /**
     * 暴力递归
     *
     * @param group
     * @param profit
     * @param r      目前还剩多少员工可以使用
     * @param p      还剩多少利润需要完成
     * @param i      当前来到第i份工作
     * @return 返回多少种选择
     */
    public static int f1(int[] group, int[] profit, int r, int p, int i) {

        if (r <= 0) {
            return p > 0 ? 0 : 1;
        }

        if (i == group.length) {
            return p > 0 ? 0 : 1;
        }

        // 不选择当前工作
        int p1 = f1(group, profit, r, p, i + 1);
        // 选择当前工作
        int p2 = 0;
        if (r - group[i] >= 0) {
            p2 = f1(group, profit, r - group[i], p - profit[i], i + 1);
        }

        return p1 + p2;
    }


    /**
     * 盈利计划 - 记忆化搜索
     *
     * @param n
     * @param minProfit
     * @param group
     * @param profit
     * @return
     */
    public static int profitableSchemes2(int n, int minProfit, int[] group, int[] profit) {

        int len = group.length;
        int[][][] dp = new int[n + 1][minProfit + 1][len];

        for (int r = 0; r <= n; r++) {
            for (int p = 0; p <= minProfit; p++) {
                for (int i = 0; i < len; i++) {
                    dp[r][p][i] = -1;
                }
            }
        }

        return f2(group, profit, n, minProfit, 0, dp);
    }


    /**
     * 记忆化搜索
     *
     * @param group
     * @param profit
     * @param r
     * @param p
     * @param i
     * @param dp
     * @return
     */
    public static int f2(int[] group, int[] profit, int r, int p, int i, int[][][] dp) {


        if (r <= 0) {
            return p > 0 ? 0 : 1;
        }

        if (i == group.length) {
            return p > 0 ? 0 : 1;
        }

        if (dp[r][p][i] != -1) {
            return dp[r][p][i];
        }

        // 不选择当前工作
        int p1 = f2(group, profit, r, p, i + 1, dp);
        // 选择当前工作
        int p2 = 0;
        if (r - group[i] >= 0) {
            p2 = f2(group, profit, r - group[i], Math.max(p - profit[i], 0), i + 1, dp);
        }

        dp[r][p][i] = (p1 + p2) % mod;
        return dp[r][p][i];
    }


    /**
     * 盈利计划 - 动态规划
     *
     * @param n
     * @param minProfit
     * @param group
     * @param profit
     * @return
     */
    public static int profitableSchemes3(int n, int minProfit, int[] group, int[] profit) {

        int len = group.length;
        int[][][] dp = new int[n + 1][minProfit + 1][len + 1];

        for (int r = 0; r <= n; r++) {
            dp[r][0][len] = 1;
        }

        for (int i = len - 1; i >= 0; i--) {
            for (int r = 0; r <= n; r++) {
                for (int p = 0; p <= minProfit; p++) {
                    int p1 = dp[r][p][i + 1];
                    int p2 = 0;
                    if (r - group[i] >= 0) {
                        p2 = dp[r - group[i]][Math.max(p - profit[i], 0)][i + 1];
                    }
                    dp[r][p][i] = (p1 + p2) % mod;
                }
            }
        }

        return dp[n][minProfit][0];
    }


    /**
     * 盈利计划 - 空间压缩
     *
     * @param n
     * @param minProfit
     * @param group
     * @param profit
     * @return
     */
    public static int profitableSchemes4(int n, int minProfit, int[] group, int[] profit) {

        int len = group.length;
        int[][] dp = new int[n + 1][minProfit + 1];
        for (int r = 0; r <= n; r++) {
            dp[r][0] = 1;
        }
        for (int i = len - 1; i >= 0; i--) {
            for (int r = n; r >= 0; r--) {
                for (int p = minProfit; p >= 0; p--) {

                    int p1 = dp[r][p];
                    int p2 = r - group[i] >= 0 ? dp[r - group[i]][Math.max(p - profit[i], 0)] : 0;
                    dp[r][p] = (p1 + p2) % mod;
                }
            }

        }
        return dp[n][minProfit];
    }

}

















