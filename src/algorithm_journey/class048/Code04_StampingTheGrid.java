package algorithm_journey.class048;

/**
 * 用邮票贴满网格图
 *
 * @author: Agony
 * @create: 2024/7/21 16:23
 * @describe: 给你一个 m x n 的二进制矩阵 grid ，每个格子要么为 0 （空）要么为 1 （被占据）。
 * <p>
 * 给你邮票的尺寸为 stampHeight x stampWidth 。我们想将邮票贴进二进制矩阵中，且满足以下 限制 和 要求 ：
 * <p>
 * 覆盖所有 空 格子。
 * 不覆盖任何 被占据 的格子。
 * 我们可以放入任意数目的邮票。
 * 邮票可以相互有 重叠 部分。
 * 邮票不允许 旋转 。
 * 邮票必须完全在矩阵 内 。
 * 如果在满足上述要求的前提下，可以放入邮票，请返回 true ，否则返回 false 。
 * @link: <a href="https://leetcode.cn/problems/stamping-the-grid/description/">用邮票贴满网格图</a>
 */
public class Code04_StampingTheGrid {


    public static void main(String[] args) {

        int[][] grid = {
                {1, 0, 0, 0},
                {1, 0, 0, 0},
                {1, 0, 0, 0},
                {1, 0, 0, 0}
        };

        System.out.println(possibleToStamp(grid, 4, 3));


        grid = new int[][]{
                {1, 0, 0, 0},
                {0, 1, 0, 0},
                {0, 0, 1, 0},
                {0, 0, 0, 1}
        };

        System.out.println(possibleToStamp(grid, 2, 2));
    }

    // 用邮票贴满网格图

    // 注意：
    // 3x2的邮票，不能把它横过来贴

    // 思路：
    // 遍历数组，来到一个数，看它按照邮票的规格能不能贴
    // 判断能不能贴，看这个返回的累加和是不是为0
    // 准备一个差分数组，
    // 在这个差分数组上贴邮票 -> 贴了的位置变1
    // 原始数组某一位置为0，差分数组该位置为1 -> 说明该位置已经被贴了邮票

    // 实现：
    // 1. 准备一个累加和数组，用以计算区域的累加和
    // -> int[] sum = new int[m+1][n+1]
    // 2. 行遍历，列遍历，以给定


    /**
     * 用邮票贴满网格图
     *
     * @param grid        网格
     * @param stampHeight 邮票高度(行)
     * @param stampWidth  邮票宽度(列)
     * @return 能否贴满
     */
    public static boolean possibleToStamp(int[][] grid, int stampHeight, int stampWidth) {


        int row = grid.length;
        int col = grid[0].length;

        // 前缀和数组
        int[][] sum = new int[row + 1][col + 1];

        for (int i = 1; i <= row; i++) {
            for (int j = 1; j <= col; j++) {
                sum[i][j] = grid[i - 1][j - 1];
            }
        }
        build(sum);


        // 差分数组
        // 在差分数组上修改
        int[][] diff = new int[row + 2][col + 2];

        for (int a = 1, c = a + stampHeight - 1; c <= row; a++, c++) {
            for (int b = 1, d = b + stampWidth - 1; d <= col; b++, d++) {

                // 如果该邮票内累加和为0
                // 说明该区域可以贴邮票
                // 那么就差分数组在该区域整体加1
                if (getSum(sum, a, b, c, d) == 0) {
                    add(diff, a, b, c, d);
                }
            }
        }

        build(diff);

        // 检查所有格子
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {

                // 如果原各自是0，且差分数组也是0说明，该位置没有贴到邮票
                if (grid[i][j] == 0 && diff[i + 1][j + 1] == 0) {
                    return false;
                }
            }
        }
        return true;
    }


    /**
     * build 操作
     * 数组构建成前缀和
     *
     * @param nums s
     */
    public static void build(int[][] nums) {
        int row = nums.length;
        int col = nums[0].length;
        for (int i = 1; i < row; i++) {
            for (int j = 1; j < col; j++) {
                nums[i][j] += nums[i - 1][j] + nums[i][j - 1] - nums[i - 1][j - 1];
            }
        }
    }


    /**
     * 得到区域内累加和
     *
     * @param nums 前缀和数组
     * @param a
     * @param b
     * @param c
     * @param d
     * @return 返回区域内累加和
     */
    public static int getSum(int[][] nums, int a, int b, int c, int d) {

        return nums[c][d] - nums[a - 1][d] - nums[c][b - 1] + nums[a - 1][b - 1];
    }


    /**
     * 差分数组 add 操作
     *
     * @param nums
     * @param a
     * @param b
     * @param c
     * @param d
     */
    public static void add(int[][] nums, int a, int b, int c, int d) {
        nums[a][b] += 1;
        nums[a][d + 1] -= 1;
        nums[c + 1][b] -= 1;
        nums[c + 1][d + 1] += 1;
    }
}
