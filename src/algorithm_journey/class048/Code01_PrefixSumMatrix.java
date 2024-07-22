package algorithm_journey.class048;

/**
 * 二维区域和检索 - 矩阵不可变
 *
 * @author: Agony
 * @create: 2024/7/20 11:42
 * @describe: 给定一个二维矩阵 matrix，以下类型的多个请求：
 * <p>
 * 计算其子矩形范围内元素的总和，该子矩阵的 左上角 为 (row1, col1) ，右下角 为 (row2, col2) 。
 * 实现 NumMatrix 类：
 * <p>
 * NumMatrix(int[][] matrix) 给定整数矩阵 matrix 进行初始化
 * int sumRegion(int row1, int col1, int row2, int col2) 返回 左上角 (row1, col1) 、右下角 (row2, col2) 所描述的子矩阵的元素 总和 。
 * <p>
 * 输入:
 * ["NumMatrix","sumRegion","sumRegion","sumRegion"]
 * [[[[3,0,1,4,2],[5,6,3,2,1],[1,2,0,1,5],[4,1,0,1,7],[1,0,3,0,5]]],[2,1,4,3],[1,1,2,2],[1,2,2,4]]
 * 输出:
 * [null, 8, 11, 12]
 * @link: <a href="https://leetcode.cn/problems/range-sum-query-2d-immutable/description/">二维区域和检索 - 矩阵不可变</a>
 */
public class Code01_PrefixSumMatrix {


    public static void main(String[] args) {


        int[][] matrix = {
                {3, 0, 1, 4, 2},
                {5, 6, 3, 2, 1},
                {1, 2, 0, 1, 5},
                {4, 1, 0, 1, 7},
                {1, 0, 3, 0, 5}
        };
        NumMatrix numMatrix = new NumMatrix(matrix);
        System.out.println(numMatrix.sumRegion(2, 1, 4, 3));
        System.out.println(numMatrix.sumRegion(1, 1, 2, 2));
        System.out.println(numMatrix.sumRegion(1, 2, 2, 4));
    }

    // 利用二维前缀和信息迅速得到二维区域和

    // 思路
    // 实现数组 int[][] sum
    // sum[i][j] 表示从 nums[0][0]~nums[i][j]的累加和
    // sum 从左往右，从上往下开始加工
    // sum[i][j] = 左边 + 上边 - 左上 + 自己
    // -> sum[i][j] += sum[i][j - 1] + sum[i - 1][j] - sum[i - 1][j - 1]
    // 左边 -> nums[0][0]~nums[i][j-1]的累加和
    // 上边 -> nums[0][0]~nums[i-1][j]的累加和
    // 左上 -> nums[0][0]~nums[i-1][j-1]的累加和
    // 左上是左边+上边重复累加的部分，需要减去，再加上自己就是nums[0][0]~nums[i][j]的累加和


    // 怎么计算(1,1)到(2,2)的累加和
    // -> 通过画图，有整体的累加和，需要把哪部分减掉，然后把重复减的部分加上
    // 如求 (a,b) 到 (c,d) 的累加和
    // ans = sum[c][d] - sum[c][b-1] - sum[a-1][d] + sum[a-1][b-1]

    // 注意：实际处理在处理sum的第0行、第0列的时候会有很多条件判断
    // 可以额外加第0行，第0列把sum包裹起来，再处理
    // 0   0   0   0   0
    // 0   10  12  4   1
    // 0   20  20  54  14
    // 0   15  3   5   8
    //


    static class NumMatrix {

        public int[][] sum;


        public NumMatrix(int[][] matrix) {
            int row = matrix.length;
            int col = matrix[0].length;

            // 第一行、第一列用 0 包裹
            sum = new int[row + 1][col + 1];
            for (int i = 1; i <= row; i++) {
                for (int j = 1; j <= col; j++) {
                    sum[i][j] = matrix[i - 1][j - 1];
                }
            }

            // 前缀和
            for (int i = 1; i <= row; i++) {
                for (int j = 1; j <= col; j++) {
                    sum[i][j] += sum[i - 1][j] + sum[i][j - 1] - sum[i - 1][j - 1];
                }
            }
        }

        public int sumRegion(int a, int b, int c, int d) {
            a++;
            b++;
            c++;
            d++;
            return sum[c][d] - sum[a - 1][d] - sum[c][b - 1] + sum[a - 1][b - 1];
        }
    }
}
