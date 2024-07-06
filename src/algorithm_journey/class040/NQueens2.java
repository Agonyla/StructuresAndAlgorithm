package algorithm_journey.class040;

/**
 * N 皇后 II
 *
 * @author: Agony
 * @create: 2024/7/3 15:07
 * @describe: n 皇后问题 研究的是如何将 n 个皇后放置在 n × n 的棋盘上，并且使皇后彼此之间不能相互攻击。
 * <p>
 * 给你一个整数 n ，返回 n 皇后问题 不同的解决方案的数量。
 * @link: <a href="https://leetcode.cn/problems/n-queens-ii/description/">N 皇后 II</a>
 */
public class NQueens2 {


    public static void main(String[] args) {
        System.out.println(totalNQueens(1));    // 1
        System.out.println(totalNQueens(2));    // 0
        System.out.println(totalNQueens(3));    // 2
        System.out.println(totalNQueens(4));    // 2
        System.out.println(totalNQueens(5));    // 10
        System.out.println(totalNQueens(6));    // 4
        System.out.println(totalNQueens(7));    // 40
        System.out.println(totalNQueens(8));    // 92
    }

    // N皇后问题
    //
    // 思路
    // 设计函数 int f(int[] path, int i, int n)
    // return -> 一共有几种摆法
    // path -> 路径用来记录一行摆放的位置，如 path = {1,3,0,2}
    // 表示第 0 行摆在第 1 列；第 1 行摆在第 3 列；第 2 行摆在第0列；第 3 行摆在第 2 列
    // i -> 表示当前来到第几行
    // n -> 表示 n 皇后问题
    // 到第 i 行了
    // 从 0 到 n 每一列都去尝试能不能放

    // 验证能不能摆放
    // 不能摆放在同一列 或者 对角线
    // 当前想在 i 行，j 列摆放
    // k 从 0 到 i for循环
    // 如果 path[k]==j || Math.abs(k-i) == Math.abs(path[k]-j) 那么就摆不了
    // 位于同一列：path[k]==j
    // 位于同一对角线：Math.abs(k-i) == Math.abs(path[k]-j) -> 斜率一样


    /**
     * N皇后
     *
     * @param n n行n列
     * @return 摆放总数
     */
    public static int totalNQueens(int n) {

        return f(new int[n], 0, n);
    }


    /**
     * 求解摆法
     *
     * @param path 路径，记录每一放放在第几列
     * @param i    第i行
     * @param n    n皇后问题
     * @return 求解数
     */
    public static int f(int[] path, int i, int n) {

        // 来到边界，表示之前的摆法没问题
        if (i == path.length) {
            return 1;
        }
        int ans = 0;
        // 当前行的每一列尝试
        for (int j = 0; j < n; j++) {
            if (check(path, i, j)) {
                path[i] = j;
                ans += f(path, i + 1, n);
            }
        }
        return ans;
    }


    /**
     * 检查能否在 i 行，j 列摆放
     *
     * @param path 路径
     * @param i    第i行
     * @param j    第j列
     * @return 能否摆放
     */
    public static boolean check(int[] path, int i, int j) {

        for (int k = 0; k < i; k++) {
            // 摆放的位置与之前放的位置在同一列或者在对角线
            if (path[k] == j || Math.abs(k - i) == Math.abs(path[k] - j)) {
                return false;
            }
        }
        return true;
    }


}





















