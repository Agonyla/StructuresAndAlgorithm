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


    // todo

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
     * 矩阵中的最长递增路径
     *
     * @param matrix
     * @return
     */
    public int longestIncreasingPath(int[][] matrix) {


        return 1;
    }
}
