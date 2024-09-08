package algorithm_journey.class067;

/**
 * 矩阵中的最长递增路径
 *
 * @author: Agony
 * @create: 2024/9/4 13:20
 * @describe: 给定一个 m x n 整数矩阵 matrix ，找出其中 最长递增路径 的长度。
 * <p>
 * 对于每个单元格，你可以往上，下，左，右四个方向移动。 你 不能 在 对角线 方向上移动或移动到 边界外（即不允许环绕）。
 * @link: <a href="https://leetcode.cn/problems/longest-increasing-path-in-a-matrix/description/">矩阵中的最长递增路径</a>
 */
public class Code06_LongestIncreasingPath {


    public static void main(String[] args) {


        int[][] matrix = {{9, 9, 4}, {6, 6, 8}, {2, 1, 1}};     // 4
        System.out.println(longestIncreasingPath1(matrix));
        System.out.println(longestIncreasingPath2(matrix));


        matrix = new int[][]{{3, 4, 5}, {3, 2, 6}, {2, 2, 1}};  // 4
        System.out.println(longestIncreasingPath1(matrix));
        System.out.println(longestIncreasingPath2(matrix));


        matrix = new int[][]{{1}};                              // 1
        System.out.println(longestIncreasingPath1(matrix));
        System.out.println(longestIncreasingPath2(matrix));

    }
    // 矩阵中的最长递增路径
    //
    // 暴力递归
    // 设计 int f(int[][] grid, int i, int j) -> 返回当前来到(i, j)位置能走出最长的路径
    // 来到每一个位置，分别向上下左右四个方向走(不越界且要走方向的值大于(i, j)位置的值)
    // 然后返回能走的最大值+1 (1表示我现在所在的位置)
    //
    // 记忆化搜索
    // ...
    //
    // 动态规划
    // 由于每个位置依赖都比较复杂(只依赖比自己大的位置)
    // 位置依赖整理起来比较复杂，所以只需要记忆化搜索的版本即可
    //


    /**
     * 矩阵中的最长递增路径 - 暴力递归
     *
     * @param matrix
     * @return
     */
    public static int longestIncreasingPath1(int[][] matrix) {

        int ans = 0;
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                ans = Math.max(f1(matrix, i, j), ans);
            }
        }

        return ans;
    }


    /**
     * 暴力递归
     *
     * @param matrix 矩阵
     * @param i      当前来到i行
     * @param j      当前来到j列
     * @return 返回当前来到(i, j)位置能走出最长的路径
     */
    public static int f1(int[][] matrix, int i, int j) {


        // up
        int next = 0;
        if (i - 1 >= 0 && matrix[i - 1][j] > matrix[i][j]) {
            next = Math.max(next, f1(matrix, i - 1, j));
        }

        // down
        if (i + 1 < matrix.length && matrix[i + 1][j] > matrix[i][j]) {
            next = Math.max(next, f1(matrix, i + 1, j));
        }

        // left
        if (j - 1 >= 0 && matrix[i][j - 1] > matrix[i][j]) {
            next = Math.max(next, f1(matrix, i, j - 1));
        }

        // right
        if (j + 1 < matrix[0].length && matrix[i][j + 1] > matrix[i][j]) {
            next = Math.max(next, f1(matrix, i, j + 1));
        }
        return next + 1;
    }


    /**
     * 矩阵中的最长递增路径 - 记忆化搜索
     *
     * @param matrix
     * @return
     */
    public static int longestIncreasingPath2(int[][] matrix) {

        int n = matrix.length;
        int m = matrix[0].length;
        int[][] dp = new int[n][m];
        int ans = 0;
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                ans = Math.max(f2(matrix, i, j, dp), ans);
            }
        }
        return ans;
    }

    public static int f2(int[][] matrix, int i, int j, int[][] dp) {

        if (dp[i][j] != 0) {
            return dp[i][j];
        }
        // up
        int next = 0;
        if (i - 1 >= 0 && matrix[i - 1][j] > matrix[i][j]) {
            next = Math.max(next, f2(matrix, i - 1, j, dp));
        }

        // down
        if (i + 1 < matrix.length && matrix[i + 1][j] > matrix[i][j]) {
            next = Math.max(next, f2(matrix, i + 1, j, dp));
        }

        // left
        if (j - 1 >= 0 && matrix[i][j - 1] > matrix[i][j]) {
            next = Math.max(next, f2(matrix, i, j - 1, dp));
        }

        // right
        if (j + 1 < matrix[0].length && matrix[i][j + 1] > matrix[i][j]) {
            next = Math.max(next, f2(matrix, i, j + 1, dp));
        }
        dp[i][j] = next + 1;
        return next + 1;
    }


}





