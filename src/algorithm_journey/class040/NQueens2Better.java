package algorithm_journey.class040;

/**
 * N 皇后 II
 *
 * @author: Agony
 * @create: 2024/7/5 10:58
 * @describe: n 皇后问题 研究的是如何将 n 个皇后放置在 n × n 的棋盘上，并且使皇后彼此之间不能相互攻击。
 * <p>
 * 给你一个整数 n ，返回 n 皇后问题 不同的解决方案的数量。
 * @link: <a href="https://leetcode.cn/problems/n-queens-ii/description/">N 皇后 II</a>
 */
public class NQueens2Better {


    public static void main(String[] args) {

        System.out.println((1 << 4) - 1);

        System.out.println(totalNQueens(1));
        System.out.println(totalNQueens(2));
        System.out.println(totalNQueens(3));
        System.out.println(totalNQueens(4));
        System.out.println(totalNQueens(5));
        System.out.println(totalNQueens(6));
        System.out.println(totalNQueens(7));
        System.out.println(totalNQueens(8));
        System.out.println("=============");
        System.out.println();


        int n = 14;
        long start, end;
        System.out.println("test start");
        System.out.println(n + "皇后问题");
        start = System.currentTimeMillis();
        System.out.println("位运算解决答案: " + totalNQueens(n));
        end = System.currentTimeMillis();
        System.out.println("位运算解决时间: " + (end - start) + " 毫秒");
        System.out.println("==============");


        start = System.currentTimeMillis();
        System.out.println("传统运算解决答案: " + NQueens2.totalNQueens(n));
        end = System.currentTimeMillis();
        System.out.println("传统运算解决时间: " + (end - start) + " 毫秒");
        System.out.println("test end");
        System.out.println("==============");


        System.out.println("位运算解决 16 皇后问题");
        start = System.currentTimeMillis();
        System.out.println("位运算解决答案: " + totalNQueens(16));
        end = System.currentTimeMillis();
        System.out.println("位运算解决时间: " + (end - start) + " 毫秒");

    }

    // N皇后问题
    // 位运算解决

    // 思路
    // 设计函数 int f(int limit, int col, int left, int right)
    // return -> 一共有几种摆法
    // limit -> 表示几皇后问题 limit = 00111111, 表示这是6皇后问题（有几个1就表示几皇后）
    // col   ->  哪些列已经放过了，如我在第0行的第2列放了1，那么在下一行中 col = 0000100，在下一行中，第1列就不能放置了
    // left -> 表示左下角不能放了，如我在第0行的第2列放了1，那么在下一行中 left = 0000010，在下一行中，第1列就不能放置了
    // right -> 表示右下角不能放了，如我在第0行的第2列放了1，那么在下一行中 right = 0001000，在下一行中，第3列就不能放置了
    // 那么在第1行 ban = 0001110，在有1的位置都不能放
    // 然后需要反转一下
    // candidate = limit & (~ban) = 0110001
    // 只有在 1 位置 上可以放
    // 然后通过 while循环直到candidate都为0
    // 取出 candidate最右侧的1依次尝试
    // place=candidate&(-candidate)
    // 把最右侧的1置0
    // candidate ^= place
    // 到下一层去尝试
    // 直到 col == limit 表示所有的列的放满了，说明这是一种可行的方式


    /**
     * N皇后
     *
     * @param n
     * @return
     */
    public static int totalNQueens(int n) {

        // 如 n=4，-> limit=1111
        int limit = (1 << n) - 1;
        return f(limit, 0, 0, 0);
    }


    /**
     * 位运算实现N皇后
     *
     * @param limit 位数限制 如 5皇后， limit = 00-011111
     * @param col   列限制，如 col=00100，表示在第二列摆放过
     * @param left  左下角限制，如 left=00100，那么在下一层left=00010，在第二列就不能放
     * @param right 右下角限制，如 right=00100，那么在下一层right=01000，在第三列就不能放
     * @return 返回摆放总数
     */
    public static int f(int limit, int col, int left, int right) {

        // col == limit 说明已经所有的列都放满了，有一种可行的摆放方法
        if (col == limit) {
            return 1;
        }

        int ans = 0;

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

            // 尝试完需要把candidate尝试的那位置0
            candidate = candidate ^ place;

            ans += f(limit, place | col, (place | left) >> 1, (place | right) << 1);

        }

        return ans;

    }


}



















