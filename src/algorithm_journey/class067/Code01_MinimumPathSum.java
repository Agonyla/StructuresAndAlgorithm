package algorithm_journey.class067;

/**
 * @author: Agony
 * @create: 2024/7/28 12:10
 * @describe:
 * @link:
 */
public class Code01_MinimumPathSum {

    // todo

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


}
