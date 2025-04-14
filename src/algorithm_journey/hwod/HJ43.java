package algorithm_journey.hwod;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * @author: Agony
 * @create: 2025/4/14 12:35
 * @describe: 迷宫问题
 * <a href="https://www.nowcoder.com/practice/cf24906056f4488c9ddb132f317e03bc?tpId=37&rp=1&sourceUrl=%2Fexam%2Foj%2Fta%3FtpId%3D37&difficulty=3&judgeStatus=&tags=&title=&gioEnter=menu">迷宫问题</a>
 */
public class HJ43 {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        int row = scanner.nextInt();
        int col = scanner.nextInt();

        int[][] grid = new int[row][col];

        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                grid[i][j] = scanner.nextInt();
            }
        }

        ArrayList<Node> list = new ArrayList<>();

        boolean[][] visited = new boolean[row][col];

        process(list, 0, 0, grid, visited);

        for (Node node : list) {
            System.out.println(node);
        }

        scanner.close();
    }

    public static boolean found = false;

    public static void process(List<Node> list, int x, int y, int[][] grid, boolean[][] visited) {

        if (x < 0 || x >= grid.length || y < 0 || y >= grid[0].length || grid[x][y] == 1 || visited[x][y] || found) {
            return;
        }

        list.add(new Node(x, y));
        visited[x][y] = true;

        if (x == grid.length - 1 && y == grid[0].length - 1) {
            found = true;
            return;
        }
        if (!found) {
            process(list, x + 1, y, grid, visited);
        }
        if (!found) {
            process(list, x - 1, y, grid, visited);
        }
        if (!found) {
            process(list, x, y + 1, grid, visited);
        }
        if (!found) {
            process(list, x, y - 1, grid, visited);
        }
        if (!found) {
            visited[x][y] = false;
            list.remove(list.size() - 1);
        }


    }

    static class Node {
        int x;
        int y;

        public Node(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public String toString() {
            return "(" + x + "," + y + ")";
        }
    }

}
