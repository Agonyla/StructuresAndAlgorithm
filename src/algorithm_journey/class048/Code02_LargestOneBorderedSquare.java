package algorithm_journey.class048;

/**
 * 最大的以 1 为边界的正方形
 *
 * @author: Agony
 * @create: 2024/7/20 15:19
 * @describe: 给你一个由若干 0 和 1 组成的二维网格 grid，请你找出边界全部由 1 组成的最大 正方形 子网格，并返回该子网格中的元素数量。如果不存在，则返回 0。
 * @link: <a href="https://leetcode.cn/problems/largest-1-bordered-square/description/">最大的以 1 为边界的正方形</a>
 */
public class Code02_LargestOneBorderedSquare {

    // todo 还没做完，验证一下

    // 边框为1的最大正方形

    // 思路
    // 行遍历、列遍历、最后再边长为1、2...直到行<n,列<m，去找最长边长的正方形
    // 怎么判断是合法的正方形
    // 整个面积的累加和 - 里面面积的累加和 = 正方形的边长
    //
    // 这次在构建前缀和数组的时候没有用 0 包裹


    /**
     * 求边框为1的最大正方形
     *
     * @param grid 网格
     * @return 返回子网格内的元素数量（面积）
     */
    public static int largest1BorderedSquare(int[][] grid) {

        int row = grid.length;
        int col = grid[0].length;
        build(row, col, grid);

        // 如果右下角的前缀和为0，说明数组里没有1，直接返回0
        if (grid[row - 1][col - 1] == 0) {
            return 0;
        }

        int ans = 1;
        for (int a = 0; a < row; a++) {
            for (int b = 0; b < col; b++) {

                // 右下角 c,d
                // side：边长
                for (int c = a + ans, d = b + ans, side = ans + 1; c < row && d < col; c++, d++, side++) {
                    if (getSum(a, b, c, d, grid) - getSum(a + 1, b + 1, c - 1, d - 1, grid) == (side - 1) << 2) {
                        ans = side;
                    }
                }
            }
        }

        return ans * ans;
    }


    /**
     * 获得(a,b) 到 (c,d) 的累加和
     *
     * @param a
     * @param b
     * @param c
     * @param d
     * @param grid 前缀和数组
     * @return
     */
    public static int getSum(int a, int b, int c, int d, int[][] grid) {
        return grid[c][d] - grid[a - 1][d] - grid[c][b - 1] + grid[a - 1][b - 1];
    }


    /**
     * 把数组构建成前缀和数组
     *
     * @param row  行数
     * @param col  列数
     * @param grid 原始数组
     */
    public static void build(int row, int col, int[][] grid) {

        for (int i = 1; i < row; i++) {
            for (int j = 1; j < col; j++) {
                grid[i][j] += get(i, j - 1, grid) + get(i - 1, j, grid) - get(i - 1, j - 1, grid);
            }
        }
    }

    /**
     * 返回该位置的前缀和
     *
     * @param i    第i行
     * @param j    第j列
     * @param grid 数组
     * @return 该位置的前缀和
     */
    public static int get(int i, int j, int[][] grid) {
        return (i == 0 || j == 0) ? 0 : grid[i][j];
    }


}






