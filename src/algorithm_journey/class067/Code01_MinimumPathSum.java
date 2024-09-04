package algorithm_journey.class067;

/**
 * 最小路径和
 *
 * @author: Agony
 * @create: 2024/7/28 12:10
 * @describe: 给定一个包含非负整数的 m x n 网格 grid ，请找出一条从左上角到右下角的路径，使得路径上的数字总和为最小。
 * <p>
 * 说明：每次只能向下或者向右移动一步。
 * @link: <a href="https://leetcode.cn/problems/minimum-path-sum/description/">最小路径和</a>
 */
public class Code01_MinimumPathSum {

    
    public static void main(String[] args) {


        int[][] grid = {{1, 3, 1}, {1, 5, 1}, {4, 2, 1}};
        System.out.println(minPathSum1(grid));
        System.out.println(minPathSum2(grid));
        System.out.println(minPathSum3(grid));
        System.out.println(minPathSum4(grid));

        System.out.println("=================");

        System.out.println(minPathSumV1(grid));
        System.out.println(minPathSumV2(grid));
        System.out.println(minPathSumV3(grid));
        System.out.println(minPathSumV4(grid));


        grid = new int[][]{{1, 2, 3}, {4, 5, 6}};
        System.out.println(minPathSum1(grid));
        System.out.println(minPathSum2(grid));
        System.out.println(minPathSum3(grid));
        System.out.println(minPathSum4(grid));

        System.out.println("================");

        System.out.println(minPathSumV1(grid));
        System.out.println(minPathSumV2(grid));
        System.out.println(minPathSumV3(grid));
        System.out.println(minPathSumV4(grid));


    }

    // 最小路径和
    //
    // 暴力递归
    // 每一步只能向右或者向下，所以每一处位置的值取决于从左来的值或者从上来的值
    // 递归，该位置去调用从上来的值，或者从左来的值
    // 返回当前格子的值 ➕ Math.min(从上来的值，从左来的值)
    //
    // 记忆化搜索
    // 在返回之间把值填入到缓存表 dp 中
    // 说明
    // 记忆化搜索通过保存已经计算过的结果，优化了暴力递归的性能，使其更适用于大规模和复杂问题的求解。
    //
    // -> 建议自己画一下递归决策图
    //
    // 定义 minPath(i, j) 表示从 grid[0][0] 到 grid[i][j] 的最小路径和
    // minPath(i, j) = min(minPath(i-1, j), minPath(i, j-1)) + grid[i][j]
    // 例如，我们计算 minPath(2, 2)，它会依赖 minPath(1, 2) 和 minPath(2, 1)。
    // 计算 minPath(1, 2) 会依赖 minPath(0, 2) 和 minPath(1, 1)。
    // 计算 minPath(2, 1) 会依赖 minPath(1, 1) 和 minPath(2, 0)。
    // 这里可以看到 minPath(1, 1) 被重复计算了两次，一次是计算 minPath(1, 2) 时，一次是计算 minPath(2, 1) 时。随着网格规模的增大，重复计算的问题会变得越来越严重。
    //
    // 动态规划
    // 设计int[][] dp = new int[][]
    // dp[i][j] 表示从(0,0)位置到(i,j)位置的最小路径和
    // 因为只能往右或者往下走
    // 填表顺序，从左往右再从上往下
    // 填dp表的时候，先把第一行第一列填好
    // 然后再填剩下的内容
    //
    // 动态规划 + 空间压缩
    // 把二维的dp表改成一维的dp表
    // 第一行独立更新
    // 第一列在整个for循环中独立更新


    /**
     * 最小路径和 - 暴力递归
     *
     * @param grid 网格
     * @return 路径最小和
     */
    public static int minPathSum1(int[][] grid) {

        int m = grid.length;
        int n = grid[0].length;


        return f1(grid, m - 1, n - 1);
    }


    /**
     * 暴力递归
     * 每个位置的值来自于左边和上边的值
     *
     * @param grid 二维网格
     * @param i    第i行
     * @param j    第j列
     * @return 从(0, 0)位置到(i, j)位置的最小路径和
     */
    public static int f1(int[][] grid, int i, int j) {

        // base case
        if (i == 0 && j == 0) {
            return grid[0][0];
        }

        int left = Integer.MAX_VALUE;
        int up = Integer.MAX_VALUE;

        if (i - 1 >= 0) {
            up = f1(grid, i - 1, j);
        }
        if (j - 1 >= 0) {
            left = f1(grid, i, j - 1);
        }
        return Math.min(left, up) + grid[i][j];
    }


    /**
     * 最小路径和 - 记忆化搜索
     *
     * @param grid
     * @return
     */
    public static int minPathSum2(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        int[][] dp = new int[m][n];

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                dp[i][j] = -1;
            }
        }

        return f2(grid, m - 1, n - 1, dp);
    }


    /**
     * 记忆化搜索
     *
     * @param grid
     * @param i
     * @param j
     * @param dp
     * @return
     */
    public static int f2(int[][] grid, int i, int j, int[][] dp) {
        // base case

        if (i == 0 && j == 0) {
            return grid[0][0];
        }
        if (dp[i][j] != -1) {
            return dp[i][j];
        }

        int left = Integer.MAX_VALUE;
        int up = Integer.MAX_VALUE;

        if (i - 1 >= 0) {
            up = f2(grid, i - 1, j, dp);
        }
        if (j - 1 >= 0) {
            left = f2(grid, i, j - 1, dp);
        }
        dp[i][j] = Math.min(left, up) + grid[i][j];
        return dp[i][j];


    }


    /**
     * 最小路径和 - 动态规划
     * 可变参数：i，j
     * 位置依赖：从递归中可以看出依赖上边和左边的位置
     * 填表顺序：从左到右，从上到下
     *
     * @param grid
     * @return
     */
    public static int minPathSum3(int[][] grid) {

        int m = grid.length;
        int n = grid[0].length;
        int[][] dp = new int[m][n];


        dp[0][0] = grid[0][0];
        // 第一行
        for (int j = 1; j < n; j++) {
            dp[0][j] = grid[0][j] + dp[0][j - 1];
        }
        // 第一列
        for (int i = 1; i < m; i++) {
            dp[i][0] = grid[i][0] + dp[i - 1][0];
        }

        // 剩下内容填写
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                dp[i][j] = Math.min(dp[i - 1][j], dp[i][j - 1]) + grid[i][j];
            }
        }
        return dp[m - 1][n - 1];
    }


    /**
     * 最下路径和 - 空间压缩
     *
     * @param grid
     * @return
     */
    public static int minPathSum4(int[][] grid) {

        int m = grid.length;
        int n = grid[0].length;

        int[] dp = new int[n];

        dp[0] = grid[0][0];
        // 第一行
        for (int j = 1; j < n; j++) {
            dp[j] = dp[j - 1] + grid[0][j];
        }

        for (int i = 1; i < m; i++) {
            dp[0] += grid[i][0];
            for (int j = 1; j < n; j++) {

                dp[j] = Math.min(dp[j - 1], dp[j]) + grid[i][j];
            }
        }

        return dp[n - 1];
    }

    // ==============================================
    // ==============================================
    // 另一个递归版本的实现
    // ==============================================
    // ==============================================


    /**
     * 最小路径和
     *
     * @param grid
     * @return
     */
    public static int minPathSumV1(int[][] grid) {

        int m = grid.length;
        int n = grid[0].length;


        return v1(grid, 0, 0);
    }


    /**
     * 暴力递归
     * 每一步只能往右或者往下走
     *
     * @param grid
     * @param i
     * @param j
     * @return 从(i, j)位置到右下角位置的最小路径和
     */
    public static int v1(int[][] grid, int i, int j) {

        int m = grid.length;
        int n = grid[0].length;

        if (i == m - 1 && j == n - 1) {
            return grid[i][j];
        }

        int right = Integer.MAX_VALUE;
        int down = Integer.MAX_VALUE;

        if (i + 1 <= m - 1) {
            down = v1(grid, i + 1, j);
        }
        if (j + 1 <= n - 1) {
            right = v1(grid, i, j + 1);
        }

        return grid[i][j] + Math.min(right, down);
    }


    /**
     * 最小路径和 - 记忆化搜索
     *
     * @param grid
     * @return
     */
    public static int minPathSumV2(int[][] grid) {

        int m = grid.length;
        int n = grid[0].length;
        int[][] dp = new int[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                dp[i][j] = -1;
            }

        }

        return v2(grid, 0, 0, dp);
    }


    /**
     * 记忆化搜索
     *
     * @param grid
     * @param i
     * @param j
     * @param dp
     * @return
     */
    public static int v2(int[][] grid, int i, int j, int[][] dp) {

        int m = grid.length;
        int n = grid[0].length;

        if (i == m - 1 && j == n - 1) {
            return grid[i][j];
        }
        if (dp[i][j] != -1) {
            return dp[i][j];
        }

        int right = Integer.MAX_VALUE;
        int down = Integer.MAX_VALUE;

        if (i + 1 <= m - 1) {
            down = v2(grid, i + 1, j, dp);
        }
        if (j + 1 <= n - 1) {
            right = v2(grid, i, j + 1, dp);
        }
        dp[i][j] = grid[i][j] + Math.min(right, down);

        return dp[i][j];
    }


    /**
     * 动态规划
     * 位置依赖：每一个位置都依赖其右边和下面的位置
     * 填表顺序：从右往左，从下往上
     *
     * @param grid
     * @return
     */
    public static int minPathSumV3(int[][] grid) {


        int m = grid.length;
        int n = grid[0].length;
        int[][] dp = new int[m][n];

        dp[m - 1][n - 1] = grid[m - 1][n - 1];

        // 最后一行
        for (int j = n - 2; j >= 0; j--) {
            dp[m - 1][j] = dp[m - 1][j + 1] + grid[m - 1][j];
        }

        // 最后一列
        for (int i = m - 2; i >= 0; i--) {
            dp[i][n - 1] = dp[i + 1][n - 1] + grid[i][n - 1];
        }

        for (int i = m - 2; i >= 0; i--) {
            for (int j = n - 2; j >= 0; j--) {
                dp[i][j] = Math.min(dp[i + 1][j], dp[i][j + 1]) + grid[i][j];
            }
        }

        return dp[0][0];
    }


    /**
     * 动态规划 + 空间压缩
     *
     * @param grid
     * @return
     */
    public static int minPathSumV4(int[][] grid) {

        int m = grid.length;
        int n = grid[0].length;
        int[] dp = new int[n];

        dp[n - 1] = grid[m - 1][n - 1];

        // 最后一行
        for (int j = n - 2; j >= 0; j--) {
            dp[j] = dp[j + 1] + grid[m - 1][j];
        }

        for (int i = m - 2; i >= 0; i--) {

            // 最后一个单独更新
            dp[n - 1] += grid[i][n - 1];

            for (int j = n - 2; j >= 0; j--) {
                dp[j] = Math.min(dp[j], dp[j + 1]) + grid[i][j];
            }
        }

        return dp[0];
    }


}












