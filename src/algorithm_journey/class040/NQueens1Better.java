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
public class NQueens1Better {


    public static void main(String[] args) {
        List<List<String>> lists = solveNQueens(4);

        lists.forEach(list -> {
            list.forEach(str -> System.out.print(str + " "));
            System.out.println();
        });

        System.out.println(Integer.numberOfTrailingZeros(4));
    }


    /**
     * N皇后
     * 最优解
     *
     * @param n
     * @return
     */
    public static List<List<String>> solveNQueens(int n) {
        ans = new ArrayList<>();
        path = new char[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                path[i][j] = '.';
            }
        }
        int limit = (1 << n) - 1;
        f(limit, 0, 0, 0, 0);
        return ans;
    }

    // 记录答案
    public static List<List<String>> ans;

    // 记录路径，path[1]=0100, 表示1行第2列放了皇后

    public static char[][] path;


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

            for (char[] ch : path) {
                cur.add(String.valueOf(ch));
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
            // Integer.numberOfTrailingZeros()，用于计算整数 i 的二进制表示中最低位连续零的个数
            // 如 4 -> 0100 -> 返回2，正好用来表示第2列
            path[row][Integer.numberOfTrailingZeros(place)] = 'Q';

            // 去下一行尝试
            f(limit, row + 1, place | col, (place | left) >> 1, (place | right) << 1);

            // 当尝试结果不成功时需要把之前放的Q擦除掉
            // 为什么之前的不用擦除呢
            // -> 因为之前使用一维数组来实现的，path[0]=0010，path[0]=0100,每次尝试都会覆盖之前的尝试
            // 尝试不成功会直接覆盖之前的尝试
            // 而现在使用二维数组来实现的，每一列的尝试都会被记录
            // 所以尝试不成功时需要擦除之前的尝试
            // 尝试到最后可以完成摆放时，直接记录每个放置的位置，然后再全部擦除
            path[row][Integer.numberOfTrailingZeros(place)] = '.';
        }
    }
}
