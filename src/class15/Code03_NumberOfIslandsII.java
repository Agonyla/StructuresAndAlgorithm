package class15;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author Agony
 * @Create 2023/9/8 20:10
 * @Version 1.0
 * <a href="https://leetcode.cn/problems/number-of-islands-ii/">岛屿数量 II</a>
 */
public class Code03_NumberOfIslandsII {

    // 起初size[i] 均为0
    // 初始化之后为 1
    // 如果 size[i] 和 size[j] 比较 size[i] > size[j]
    // size[i] = size[i] + size[j]; size[j] 仍然为 1
    // 之后在判断的时候 当 size[j] == 0的时候，说明还没有被初始化

    public List<Integer> numIslands2(int m, int n, int[][] positions) {
        Union union = new Union(m, n);
        ArrayList<Integer> list = new ArrayList<>();
        for (int[] position : positions) {
            list.add(union.connect(position[0], position[1]));
        }
        return list;
    }

    public static class Union {
        private int[] parents;
        private int[] size;
        private int[] help;
        private int row;
        private int col;
        private int sets;


        public Union(int m, int n) {
            row = m;
            col = n;
            sets = 0;
            int len = row * col;
            parents = new int[len];
            size = new int[len];
            help = new int[len];
        }

        public int index(int r, int c) {
            return r * col + c;
        }

        public int findFather(int cur) {
            int hi = 0;
            while (cur != parents[cur]) {
                help[hi++] = cur;
                cur = parents[cur];
            }

            for (hi--; hi >= 0; hi--) {
                parents[help[hi]] = cur;
            }
            return cur;
        }

        public void union(int r1, int c1, int r2, int c2) {
            // 超出边界
            if (r1 < 0 || r1 == row || c1 < 0 || c1 == col || r2 < 0 || r2 == row || c2 < 0 || c2 == col) {
                return;
            }

            int index1 = index(r1, c1);
            int index2 = index(r2, c2);
            // 不是 1
            if (size[index1] == 0 || size[index2] == 0) {
                return;
            }
            int f1 = findFather(index1);
            int f2 = findFather(index2);
            int size1 = size[f1];
            int size2 = size[f2];

            // 同一个代表节点
            if (f1 == f2) {
                return;
            }
            if (size1 >= size2) {
                size[f1] += size[f2];
                parents[f2] = f1;
            } else {
                size[f2] += size[f1];
                parents[f1] = f2;
            }
            sets--;
        }

        public int connect(int r, int c) {
            int index = index(r, c);
            if (size[index] == 0) {
                parents[index] = index;
                size[index] = 1;
                sets++;
                union(r - 1, c, r, c);
                union(r + 1, c, r, c);
                union(r, c + 1, r, c);
                union(r, c - 1, r, c);
            }
            return sets;
        }
    }

}
