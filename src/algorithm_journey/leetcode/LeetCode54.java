package algorithm_journey.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: Agony
 * @create: 2025/4/4 20:39
 * @describe: 螺旋矩阵
 * <a href="https://leetcode.cn/problems/spiral-matrix/description/?envType=company&envId=huawei&favoriteSlug=huawei-three-months">螺旋矩阵</a>
 */
public class LeetCode54 {


    public static void main(String[] args) {

        int[][] matrix = {
                {1, 2, 3},
                {4, 5, 6},
                {7, 8, 9},
        };

        List<Integer> list = spiralOrder(matrix);

        for (int i = 0; i < list.size(); i++) {
            System.out.print(list.get(i));
            if (i < list.size() - 1) {
                System.out.print(" ");
            }
        }
        System.out.println();

        matrix = new int[][]{
                {1, 2, 3, 4},
                {5, 6, 7, 8},
                {9, 10, 11, 12},
        };
        list = spiralOrder(matrix);
        for (int i = 0; i < list.size(); i++) {
            System.out.print(list.get(i));
            if (i < list.size() - 1) {
                System.out.print(" ");
            }
        }

    }

    public static List<Integer> spiralOrder(int[][] matrix) {

        ArrayList<Integer> ans = new ArrayList<>();

        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return ans;
        }

        int rows = matrix.length;
        int cols = matrix[0].length;


        int top = 0;
        int bottom = rows - 1;
        int left = 0;
        int right = cols - 1;

        while (top <= bottom && left <= right) {

            //
            for (int i = left; i <= right; i++) {
                ans.add(matrix[top][i]);
            }
            top++;

            for (int i = top; i <= bottom; i++) {
                ans.add(matrix[i][right]);
            }
            right--;

            if (top <= bottom) {
                for (int i = right; i >= left; i--) {
                    ans.add(matrix[bottom][i]);
                }
                bottom--;
            }


            if (left <= right) {
                for (int i = bottom; i >= top; i--) {
                    ans.add(matrix[i][left]);
                }
                left++;
            }

        }
        return ans;

    }
}
