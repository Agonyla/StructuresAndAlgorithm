package leetcode;

/**
 * @Author Agony
 * @Create 2023/9/22 9:56
 * @Version 1.0
 * @describe: 按照国际象棋的规则，皇后可以攻击与之处在同一行或同一列或同一斜线上的棋子。
 * n 皇后问题 研究的是如何将 n 个皇后放置在 n × n 的棋盘上，并且使皇后彼此之间不能相互攻击。
 * 给你一个整数 n ，返回 n 皇后问题 不同的解决方案的数量。
 * <p><a href="https://leetcode.cn/problems/n-queens-ii/">N 皇后 II</a></p>
 */
public class TotalNQueens {
    
    public static int totalNQueens(int n) {
        if (n < 0) {
            return 0;
        }
        int[] record = new int[n];
        return process(n, record, 0);
    }

    public static int process(int n, int[] record, int index) {
        if (index == n) {
            return 1;
        }
        int ans = 0;
        for (int j = 0; j < n; j++) {
            if (isValid(record, index, j)) {
                record[index] = j;
                ans += process(n, record, index + 1);
            }
        }
        return ans;
    }

    public static boolean isValid(int[] record, int index, int j) {
        for (int i = 0; i < index; i++) {
            if (record[i] == j || Math.abs(i - index) == Math.abs(record[i] - j)) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        System.out.println(totalNQueens(8));
    }

}
