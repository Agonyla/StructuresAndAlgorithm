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

    public int numIslands2(char[][] grid) {
        int row = grid.length;
        int col = grid[0].length;
        Union union = new Union(grid);

        // 合并左上

        // 第0行合并
        for (int j = 1; j < col; j++) {
            if (grid[0][j - 1] == '1' && grid[0][j] == '1') {
                union.union(0, j - 1, 0, j);
            }
        }

        // 第0列
        for (int i = 1; i < row; i++) {
            if (grid[i - 1][0] == '1' && grid[i][0] == '1') {
                union.union(i - 1, 0, i, 0);
            }
        }

        // 其他行列
        for (int i = 1; i < row; i++) {
            for (int j = 1; j < col; j++) {
                if (grid[i][j] == '1') {
                    if (grid[i - 1][j] == '1') {
                        union.union(i, j, i - 1, j);
                    }
                    if (grid[i][j - 1] == '1') {
                        union.union(i, j, i, j - 1);
                    }
                }
            }
        }
        return union.getSets();
    }


    public static class Union {
        private int[] parent;
        private int[] size;
        private int[] help;
        private int col;
        private int sets;

        public Union(char[][] grid) {
            int row = grid.length;
            col = grid[0].length;
            int len = row * col;
            parent = new int[len];
            size = new int[len];
            help = new int[len];
            sets = 0;
            for (int i = 0; i < row; i++) {
                for (int j = 0; j < col; j++) {
                    if (grid[i][j] == '1') {
                        int index = index(i, j);
                        parent[index] = index;
                        size[index] = 1;
                        sets++;
                    }
                }
            }
        }

        public int index(int r, int c) {
            return r * col + c;
        }

        /**
         * 找代表节点
         *
         * @param i
         */
        public int find(int i) {
            int hi = 0;
            while (i != parent[i]) {
                help[hi++] = i;
                i = parent[i];
            }

            for (hi--; hi >= 0; hi--) {
                parent[help[hi]] = i;
            }
            return i;
        }

        public void union(int r1, int c1, int r2, int c2) {
            int i1 = index(r1, c1);
            int i2 = index(r2, c2);
            int f1 = find(i1);
            int f2 = find(i2);
            if (f1 != f2) {
                if (size[f1] >= size[f2]) {
                    size[f1] += size[f2];
                    parent[f2] = f1;
                } else {
                    size[f2] += size[f1];
                    parent[f1] = f2;
                }
                sets--;
            }
        }

        public int getSets() {
            return sets;
        }
    }
}
