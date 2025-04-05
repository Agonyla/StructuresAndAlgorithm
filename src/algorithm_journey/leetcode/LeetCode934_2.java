package algorithm_journey.leetcode;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @author: Agony
 * @create: 2025/4/5 19:29
 * @describe:
 */
public class LeetCode934_2 {


    public static void main(String[] args) {


        int[][] grid = new int[][]{
                {0, 1},
                {1, 0}
        };
        System.out.println(shortestBridge(grid));

        grid = new int[][]{
                {0, 1, 0},
                {0, 0, 0},
                {0, 0, 1},
        };
        System.out.println(shortestBridge(grid));

        grid = new int[][]{
                {1, 1, 1, 1, 1},
                {1, 0, 0, 0, 1},
                {1, 0, 1, 0, 1},
                {1, 0, 0, 0, 1},
                {1, 1, 1, 1, 1},
        };
        System.out.println(shortestBridge(grid));
    }

    // 四个方向：上、右、下、左
    private static final int[][] directions = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};

    public static int shortestBridge(int[][] grid) {
        int n = grid.length;
        Queue<int[]> queue = new LinkedList<>();
        boolean[][] visited = new boolean[n][n];

        // 第一步：找到第一个岛屿，并将其所有点标记为已访问，并加入队列
        boolean found = false;
        for (int i = 0; i < n && !found; i++) {
            for (int j = 0; j < n && !found; j++) {
                if (grid[i][j] == 1) {
                    // 使用DFS找到第一个岛屿的所有点
                    dfs(grid, i, j, visited, queue);
                    found = true;
                }
            }
        }

        // 第二步：使用BFS扩展第一个岛屿，直到触及第二个岛屿
        int distance = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            // 处理当前层的所有点
            for (int i = 0; i < size; i++) {
                int[] cell = queue.poll();
                int x = cell[0];
                int y = cell[1];

                // 检查四个方向
                for (int[] dir : directions) {
                    int nx = x + dir[0];
                    int ny = y + dir[1];

                    // 确保新坐标在网格内且未访问过
                    if (nx >= 0 && nx < n && ny >= 0 && ny < n && !visited[nx][ny]) {
                        // 如果遇到第二个岛屿，返回距离
                        if (grid[nx][ny] == 1) {
                            return distance;
                        }

                        // 否则，标记为已访问并加入队列
                        visited[nx][ny] = true;
                        queue.offer(new int[]{nx, ny});
                    }
                }
            }
            // 增加距离
            distance++;
        }

        return -1; // 如果没有找到桥，返回-1（题目保证有解，所以实际上不会到达这里）
    }

    // 使用DFS找到第一个岛屿的所有点
    private static void dfs(int[][] grid, int i, int j, boolean[][] visited, Queue<int[]> queue) {
        int n = grid.length;
        // 如果越界、已访问或不是陆地，则返回
        if (i < 0 || i >= n || j < 0 || j >= n || visited[i][j] || grid[i][j] == 0) {
            return;
        }

        // 标记为已访问，并加入队列
        visited[i][j] = true;
        queue.offer(new int[]{i, j});

        // 递归访问四个方向
        for (int[] dir : directions) {
            dfs(grid, i + dir[0], j + dir[1], visited, queue);
        }
    }
}
