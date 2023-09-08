package class15;

/**
 * @Author Agony
 * @Create 2023/9/7 20:06
 * @Version 1.0
 * <a href="https://leetcode.cn/problems/number-of-provinces/">省份数量</a>
 * 关于对角线对称
 * 1 1 0 0
 * 1 1 0 0
 * 0 0 1 1
 * 0 0 1 1
 */
public class Code01_FriendCircles {

    public int findCircleNum(int[][] isConnected) {
        int row = isConnected.length;
        int col = isConnected[0].length;
        Union union = new Union(row);
        for (int i = 0; i < row; i++) {
            for (int j = i + 1; j < col; j++) {
                if (isConnected[i][j] == 1) {
                    union.union(i, j);
                }
            }
        }
        return union.getSets();
    }

    public static class Union {
        private int[] parent;
        private int[] size;
        private int[] help;
        private int sets;

        public Union(int N) {
            parent = new int[N];
            size = new int[N];
            help = new int[N];
            sets = N;
            for (int i = 0; i < N; i++) {
                parent[i] = i;
                size[i] = 1;
            }
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

        public void union(int a, int b) {
            int fa = find(a);
            int fb = find(b);
            if (fa == fb) {
                return;
            }
            int sizeA = size[fa];
            int sizeB = size[fb];
            if (sizeA >= sizeB) {
                size[fa] = sizeA + sizeB;
                size[fb] = 0;
                parent[fb] = fa;
            } else {
                size[fb] = sizeA + sizeB;
                size[fa] = 0;
                parent[fa] = fb;
            }
            sets--;
        }

        public int getSets() {
            return sets;
        }
    }

}
