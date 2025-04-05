package algorithm_journey.leetcode;

/**
 * @author: Agony
 * @create: 2025/4/5 20:09
 * @describe: 旋转图像
 * <a href="https://leetcode.cn/problems/rotate-image/description/">旋转图像</a>
 * <a href="https://leetcode.cn/problems/rotate-matrix-lcci/description/?envType=company&envId=huawei&favoriteSlug=huawei-three-months">面试题 01.07. 旋转矩阵</a>
 */
public class LeetCode48 {

    public void rotate(int[][] matrix) {
        int n = matrix.length;

        // 第一步：沿对角线翻转（从左上到右下的对角线）
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                // 交换 matrix[i][j] 和 matrix[j][i]
                int temp = matrix[i][j];
                matrix[i][j] = matrix[j][i];
                matrix[j][i] = temp;
            }
        }

        // 第二步：水平翻转（左右翻转）
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n / 2; j++) {
                // 交换 matrix[i][j] 和 matrix[i][n-1-j]
                int temp = matrix[i][j];
                matrix[i][j] = matrix[i][n - 1 - j];
                matrix[i][n - 1 - j] = temp;
            }
        }
    }

}
