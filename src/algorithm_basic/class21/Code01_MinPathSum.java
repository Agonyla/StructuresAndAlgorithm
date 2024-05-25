package algorithm_basic.class21;

/**
 * @Author Agony
 * @Create 2023/9/24 13:47
 * @Version 1.0
 * @describe: 给定一个二维数组matrix，一个人必须从左上角出发，最后到达右下角
 * 沿途只可以向下或者向右走，沿途的数字都累加就是距离累加和
 * 返回最小距离累加和
 */
public class Code01_MinPathSum {

    public static int minPath(int[][] arr) {
        if (arr == null || arr.length == 0) {
            return -1;
        }
        int N = arr.length;
        int M = arr[0].length;
        return process(arr, N, M, 0, 0);
    }

    /**
     * @describe: 递归尝试
     */
    public static int process(int[][] arr, int N, int M, int row, int col) {
        // 位于右下角
        if (row == N - 1 && col == M - 1) {
            return arr[row][col];
        }
        // 位于最后一行，只能往右走
        if (row == N - 1) {
            return arr[row][col] + process(arr, N, M, row, col + 1);
        }
        // 位于最右列，只能往下走
        if (col == M - 1) {
            return arr[row][col] + process(arr, N, M, row + 1, col);
        }
        // 向右走
        int p1 = process(arr, N, M, row, col + 1);
        // 向下走
        int p2 = process(arr, N, M, row + 1, col);
        return arr[row][col] + Math.min(p1, p2);
    }

    /**
     * @describe: 动态规划
     */
    public static int dp1(int[][] arr) {
        if (arr == null || arr[0] == null || arr[0].length == 0) {
            return -1;
        }
        int N = arr.length;
        int M = arr[0].length;
        // row的取值范围：0~N  col的取值范围：0~N
        int[][] dp = new int[N][M];

        // 按照递归改动态 从下往上，从右往左遍历
        dp[N - 1][M - 1] = arr[N - 1][M - 1];
        // 最右列
        for (int i = N - 2; i >= 0; i--) {
            dp[i][M - 1] = dp[i + 1][M - 1] + arr[i][M - 1];
        }
        // 最后一行
        for (int j = M - 2; j >= 0; j--) {
            dp[N - 1][j] = dp[N - 1][j + 1] + arr[N - 1][j];
        }
        for (int i = N - 2; i >= 0; i--) {
            for (int j = M - 2; j >= 0; j--) {
                // 向右走
                int p1 = dp[i][j + 1];
                // 向下走
                int p2 = dp[i + 1][j];
                dp[i][j] = arr[i][j] + Math.min(p1, p2);
            }
        }
        return dp[0][0];


        // 按照题意从上往下来，选择左边或上面最小值 ==> minPathSum1
        // dp[0][0] = arr[0][0];
        // for (int i = 1; i < N; i++) {
        //     dp[i][0] = dp[i - 1][0] + arr[i][0];
        // }
        // for (int j = 1; j < M; j++) {
        //     dp[0][j] = dp[0][j - 1] + arr[0][j];
        // }
        // for (int row = 1; row < N; row++) {
        //     for (int col = 1; col < M; col++) {
        //         // 前一列的值
        //         int p1 = dp[row][col - 1];
        //         // 上一行的值
        //         int p2 = dp[row - 1][col];
        //         dp[row][col] = arr[row][col] + Math.min(p1, p2);
        //     }
        // }
        // return dp[N - 1][M - 1];
    }

    /**
     * @describe: 动态规划 -> 压缩空间
     */
    public static int dp2(int[][] arr) {
        if (arr == null || arr[0] == null || arr[0].length == 0) {
            return -1;
        }
        int N = arr.length;
        int M = arr[0].length;
        int[] dp = new int[M];
        dp[0] = arr[0][0];
        for (int j = 1; j < M; j++) {
            dp[j] = dp[j - 1] + arr[0][j];
        }
        for (int row = 1; row < N; row++) {
            dp[0] += arr[row][0];
            for (int col = 1; col < M; col++) {
                dp[col] = Math.min(dp[col], dp[col - 1]) + arr[row][col];
            }
        }
        return dp[M - 1];
    }


    // for algorithm_basic.test
    public static int[][] generateRandomMatrix(int rowSize, int colSize) {
        if (rowSize < 0 || colSize < 0) {
            return null;
        }
        int[][] result = new int[rowSize][colSize];
        for (int i = 0; i != result.length; i++) {
            for (int j = 0; j != result[0].length; j++) {
                result[i][j] = (int) (Math.random() * 100);
            }
        }
        return result;
    }

    // for algorithm_basic.test
    public static void printMatrix(int[][] matrix) {
        for (int i = 0; i != matrix.length; i++) {
            for (int j = 0; j != matrix[0].length; j++) {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }
    }

    public static int minPathSum1(int[][] m) {
        if (m == null || m.length == 0 || m[0] == null || m[0].length == 0) {
            return 0;
        }
        int row = m.length;
        int col = m[0].length;
        int[][] dp = new int[row][col];
        dp[0][0] = m[0][0];
        for (int i = 1; i < row; i++) {
            dp[i][0] = dp[i - 1][0] + m[i][0];
        }
        for (int j = 1; j < col; j++) {
            dp[0][j] = dp[0][j - 1] + m[0][j];
        }
        for (int i = 1; i < row; i++) {
            for (int j = 1; j < col; j++) {
                dp[i][j] = Math.min(dp[i - 1][j], dp[i][j - 1]) + m[i][j];
            }
        }
        return dp[row - 1][col - 1];
    }

    public static int minPathSum2(int[][] m) {
        if (m == null || m.length == 0 || m[0] == null || m[0].length == 0) {
            return 0;
        }
        int row = m.length;
        int col = m[0].length;
        int[] dp = new int[col];
        dp[0] = m[0][0];
        for (int j = 1; j < col; j++) {
            dp[j] = dp[j - 1] + m[0][j];
        }
        for (int i = 1; i < row; i++) {
            dp[0] += m[i][0];
            for (int j = 1; j < col; j++) {
                dp[j] = Math.min(dp[j - 1], dp[j]) + m[i][j];
            }
        }
        return dp[col - 1];
    }

    public static void main(String[] args) {
        int rowSize = 3;
        int colSize = 3;
        int[][] m = generateRandomMatrix(rowSize, colSize);
        printMatrix(m);
        System.out.println(minPathSum1(m));
        System.out.println(minPathSum2(m));
        System.out.println(minPath(m));
        System.out.println(dp1(m));
        System.out.println(dp2(m));

    }
}
