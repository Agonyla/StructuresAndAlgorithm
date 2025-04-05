package algorithm_journey.leetcode;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @author: Agony
 * @create: 2025/4/5 19:01
 * @describe: 最短的桥
 * <a href="https://leetcode.cn/problems/shortest-bridge/description/?envType=company&envId=huawei&favoriteSlug=huawei-three-months">最短的桥</a>
 */
public class LeetCode934 {


    public static void main(String[] args) {

        //     示例 1：
        //
        // 输入：grid = [[0,1],[1,0]]
        // 输出：1
        // 示例 2：
        //
        // 输入：grid = [[0,1,0],[0,0,0],[0,0,1]]
        // 输出：2
        // 示例 3：
        //
        // 输入：grid = [[1,1,1,1,1],[1,0,0,0,1],[1,0,1,0,1],[1,0,0,0,1],[1,1,1,1,1]]
        // 输出：1


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

    // 上下左右四个方向
    static int[][] directions = new int[][]{{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

    public static int shortestBridge(int[][] grid) {


        int n = grid.length;
        LinkedList<int[]> queue = new LinkedList<>();
        boolean[][] visited = new boolean[n][n];

        boolean found = false;


        // 找到第一个岛屿
        for (int i = 0; i < n && !found; i++) {
            for (int j = 0; j < n && !found; j++) {
                if (grid[i][j] == 1) {
                    bfs(grid, n, i, j, visited, queue);
                    found = true;
                }
            }
        }

        int distance = 0;

        while (!queue.isEmpty()) {

            int size = queue.size();
            for (int i = 0; i < size; i++) {

                int[] position = queue.poll();
                int x = position[0];
                int y = position[1];

                for (int[] direction : directions) {
                    int nx = x + direction[0];
                    int ny = y + direction[1];

                    if (nx >= 0 && nx < n && ny >= 0 && ny < n && !visited[nx][ny]) {

                        if (grid[nx][ny] == 1) {
                            return distance;
                        }
                        visited[nx][ny] = true;
                        queue.offer(new int[]{nx, ny});
                    }

                }
            }
            distance++;
        }
        return -1;
    }

    public static void bfs(int[][] grid, int n, int i, int j, boolean[][] visited, Queue<int[]> queue) {

        if (i < 0 || j < 0 || i >= n || j >= n || visited[i][j] || grid[i][j] == 0) {
            return;
        }
        visited[i][j] = true;
        queue.offer(new int[]{i, j});

        for (int[] direction : directions) {
            bfs(grid, n, i + direction[0], j + direction[1], visited, queue);
        }
    }

}
