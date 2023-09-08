package class15;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Stack;

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


    public int numIslands3(char[][] grid) {

        int row = grid.length;
        int col = grid[0].length;
        Dot[][] dots = new Dot[row][col];
        ArrayList<Dot> list = new ArrayList<>();
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (grid[i][j] == '1') {
                    dots[i][j] = new Dot();
                    list.add(dots[i][j]);
                }
            }
        }

        Union2<Dot> union2 = new Union2<>(list);
        for (int i = 1; i < row; i++) {
            if (grid[i - 1][0] == '1' && grid[i][0] == '1') {
                union2.union(dots[i - 1][0], dots[i][0]);
            }
        }

        for (int j = 1; j < col; j++) {
            if (grid[0][j - 1] == '1' && grid[0][j] == '1') {
                union2.union(dots[0][j - 1], dots[0][j]);
            }
        }
        for (int i = 1; i < row; i++) {
            for (int j = 1; j < col; j++) {
                if (grid[i][j] == '1') {
                    if (grid[i - 1][j] == '1') {
                        union2.union(dots[i][j], dots[i - 1][j]);
                    }
                    if (grid[i][j - 1] == '1') {
                        union2.union(dots[i][j], dots[i][j - 1]);
                    }
                }
            }
        }
        return union2.getSets();
    }

    public static class Node<V> {
        V value;

        public Node(V v) {
            value = v;
        }
    }

    public static class Dot {
    }

    public static class Union2<V> {
        private HashMap<V, Node<V>> nodes;
        private HashMap<Node<V>, Node<V>> parents;
        private HashMap<Node<V>, Integer> mapSize;

        public Union2(List<V> values) {
            nodes = new HashMap<>();
            parents = new HashMap<>();
            mapSize = new HashMap<>();
            for (V value : values) {
                Node<V> node = new Node<>(value);
                nodes.put(value, node);
                parents.put(node, node);
                mapSize.put(node, 1);
            }
        }

        public Node<V> findFather(Node<V> cur) {
            Stack<Node<V>> stack = new Stack<>();
            while (cur != parents.get(cur)) {
                stack.push(cur);
                cur = parents.get(cur);
            }

            while (!stack.isEmpty()) {
                parents.put(stack.pop(), cur);
            }
            return cur;
        }

        public void union(V a, V b) {
            if (findFather(nodes.get(a)) == findFather(nodes.get(b))) {
                return;
            }
            Node<V> fa = parents.get(nodes.get(a));
            Node<V> fb = parents.get(nodes.get(b));
            int sizeA = mapSize.get(fa);
            int sizeB = mapSize.get(fb);
            Node<V> big = sizeA >= sizeB ? fa : fb;
            Node<V> small = big == fa ? fb : fa;
            parents.put(small, big);
            mapSize.put(big, sizeA + sizeB);
            mapSize.remove(small);
        }

        public int getSets() {
            return mapSize.size();
        }
    }

}
