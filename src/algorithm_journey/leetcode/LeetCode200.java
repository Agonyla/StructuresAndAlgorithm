package algorithm_journey.leetcode;

/**
 * @author: Agony
 * @create: 2025/4/7 11:10
 * @describe: 岛屿数量
 * <a href="https://leetcode.cn/problems/number-of-islands/?envType=company&envId=huawei&favoriteSlug=huawei-three-months">岛屿数量</a>
 */
public class LeetCode200 {


    public static void main(String[] args) {


        char[][] grid = {
                {'1', '1', '1', '1', '0'},
                {'1', '1', '0', '1', '0'},
                {'1', '1', '0', '0', '0'},
                {'0', '0', '0', '0', '0'},
        };
        System.out.println(numIslands(grid));
    }


    public static int numIslands(char[][] grid) {

        int row = grid.length;
        int col = grid[0].length;
        int count = 0;

        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (grid[i][j] == '1') {
                    count++;
                    bfs(i, j, row, col, grid);
                }
            }
        }

        return count;
    }


    public static void bfs(int i, int j, int row, int col, char[][] grid) {

        if (i < 0 || i >= row || j < 0 || j >= col || grid[i][j] == '0') {
            return;
        }

        grid[i][j] = '0';
        bfs(i + 1, j, row, col, grid);
        bfs(i - 1, j, row, col, grid);
        bfs(i, j + 1, row, col, grid);
        bfs(i, j - 1, row, col, grid);
    }
}
