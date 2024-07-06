package algorithm_journey.class040;

import java.util.ArrayList;
import java.util.List;

/**
 * N 皇后
 *
 * @author: Agony
 * @create: 2024/7/6 19:29
 * @describe: 按照国际象棋的规则，皇后可以攻击与之处在同一行或同一列或同一斜线上的棋子。
 * <p>
 * n 皇后问题 研究的是如何将 n 个皇后放置在 n×n 的棋盘上，并且使皇后彼此之间不能相互攻击。
 * <p>
 * 给你一个整数 n ，返回所有不同的 n 皇后问题 的解决方案。
 * <p>
 * 每一种解法包含一个不同的 n 皇后问题 的棋子放置方案，该方案中 'Q' 和 '.' 分别代表了皇后和空位。
 * @link: <a href="https://leetcode.cn/problems/n-queens/description/">N 皇后</a>
 */
public class NQueens1 {


    public static void main(String[] args) {
        List<List<String>> lists = solveNQueens(4);

        lists.forEach(list -> {
            list.forEach(str -> System.out.print(str + " "));
            System.out.println();
        });
        
    }


    /**
     * N皇后
     *
     * @param n
     * @return
     */
    public static List<List<String>> solveNQueens(int n) {
        ans = new ArrayList<>();
        path = new int[n];
        int limit = (1 << n) - 1;
        f(limit, 0, 0, 0, 0);
        return ans;
    }

    // 记录答案
    public static List<List<String>> ans;

    // 记录路径，path[1]=0100, 表示1行第2列放了皇后
    public static int[] path;


    /**
     * 记录皇后放置的位置
     *
     * @param limit 位数限制 如 5皇后， limit = 00-011111
     * @param row   表示来到了第几行
     * @param col   列限制，如 col=00100，表示在第二列摆放过
     * @param left  左下角限制，如 left=00100，那么在下一层left=00010，在第二列就不能放
     * @param right 右下角限制，如 right=00100，那么在下一层right=01000，在第三列就不能放
     */
    public static void f(int limit, int row, int col, int left, int right) {

        if (col == limit) {
            ArrayList<String> cur = new ArrayList<>();

            // 行循环
            for (int i = 0; i < path.length; i++) {
                StringBuilder sb = new StringBuilder();

                // 列循环
                for (int j = 0; j < path.length; j++) {

                    // 找path的第i行第j列是否为1
                    sb.append(path[i] == (1 << j) ? "Q" : ".");
                }
                cur.add(sb.toString());
            }
            ans.add(cur);
            return;
        }

        // 这些位置已经不能放了，只有0位置可以放
        int ban = col | left | right;

        // 转换为只有1位置可以放，limit限制高位的都为0
        // 为什么要转换成只有1位置可以放呢
        // 这样就可以通过取最右边的1 -> Brian Kernighan算法，来尝试该位置能不能放
        int candidate = limit & (~ban);

        // 当candidate!=0，说明仍然有位置可以放，继续尝试
        while (candidate != 0) {

            // 取最右边的1，放置，开始尝试
            int place = candidate & (-candidate);

            // 尝试完把尝试的那位置0
            candidate = candidate ^ place;

            // 在path中把place信息加入
            path[row] = place;

            // 去下一行尝试
            f(limit, row + 1, place | col, (place | left) >> 1, (place | right) << 1);
        }
    }
}
