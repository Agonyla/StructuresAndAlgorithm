package class15;

/**
 * @Author Agony
 * @Create 2023/9/7 20:37
 * @Version 1.0
 * <a href="https://leetcode.cn/problems/number-of-islands/">岛屿数量</a>
 */
public class Code02_NumberOfIslands {
    public int numIslands1(char[][] grid) {
        int num = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                if (grid[i][j] == '1') {
                    num++;
                    infect(i, j, grid);
                }
            }
        }
        return num;
    }

    public static void infect(int i, int j, char[][] grid) {
        // 边界条件
        if (i < 0 || i == grid.length || j < 0 || j == grid[0].length || grid[i][j] == '0') {
            return;
        }
        grid[i][j] = '0';
        infect(i - 1, j, grid);
        infect(i + 1, j, grid);
        infect(i, j - 1, grid);
        infect(i, j + 1, grid);
    }
}
