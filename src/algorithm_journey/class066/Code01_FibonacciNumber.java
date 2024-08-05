package algorithm_journey.class066;

import java.util.Arrays;

/**
 * 斐波那契数
 *
 * @author: Agony
 * @create: 2024/7/24 21:37
 * @describe: 斐波那契数 （通常用 F(n) 表示）形成的序列称为 斐波那契数列 。该数列由 0 和 1 开始，后面的每一项数字都是前面两项数字的和。也就是：
 * <p>
 * F(0) = 0，F(1) = 1
 * F(n) = F(n - 1) + F(n - 2)，其中 n > 1
 * 给定 n ，请计算 F(n) 。
 * @link: <a href="https://leetcode.cn/problems/fibonacci-number/description/">斐波那契数</a>
 */
public class Code01_FibonacciNumber {


    public static void main(String[] args) {

        
        System.out.println(fib1(2));
        System.out.println(fib1(3));
        System.out.println(fib1(4));

        System.out.println("===========");

        System.out.println(fib2(2));
        System.out.println(fib2(3));
        System.out.println(fib2(4));

        System.out.println("===========");

        System.out.println(fib3(2));
        System.out.println(fib3(3));
        System.out.println(fib3(4));

        System.out.println("===========");

        System.out.println(fib4(2));
        System.out.println(fib4(3));
        System.out.println(fib4(4));
    }

    // 斐波那契数

    // 实现
    // fib1
    // -> 暴力递归遇到每一项都展开
    // fib2
    // -> 记忆化搜索，从顶到底，遇到每一项先展开，然后将得到的结果记录到数组中
    // 下次再次遇到直接从数组中查询
    // fib3
    // -> 从底到顶，F(i)=F(i-1)+F(i-2)
    // 新建一个长度n+1的数组 int[] dp = new int[n+1]
    // 初始化前两项，for循环，每一项由前两项相加
    // 最后返回 dp[n]
    // fib4
    // -> 在fib3的基础上优化，
    // 不用数组，用两个变量记录，
    // 每次加完之后更新两个变量的值

    /**
     * 斐波那契数
     * 暴力递归实现
     *
     * @param n 第n个数
     * @return 返回第n个数
     */
    public static int fib1(int n) {
        return f1(n);
    }


    /**
     * 暴力递归
     *
     * @param i 第n项
     * @return 第n项的数字
     */
    public static int f1(int i) {
        if (i == 0) {
            return 0;
        }
        if (i == 1) {
            return 1;
        }
        int ans = 0;
        ans = f1(i - 1) + f1(i - 2);
        return ans;
    }


    /**
     * 斐波那契数
     * 记忆化搜索 - 从顶到底
     *
     * @param n
     * @return
     */
    public static int fib2(int n) {
        // 从0开始到n，一共n+1项
        int[] dp = new int[n + 1];
        Arrays.fill(dp, -1);
        return f2(n, dp);
    }

    /**
     * 记忆化搜索
     *
     * @param i  第n项
     * @param dp dp表
     * @return
     */
    public static int f2(int i, int[] dp) {
        if (i == 0) {
            return 0;
        }
        if (i == 1) {
            return 1;
        }
        if (dp[i] != -1) {
            return dp[i];
        }
        int ans = 0;
        ans += f2(i - 1, dp) + f2(i - 2, dp);
        dp[i] = ans;
        return ans;
    }


    /**
     * 斐波那契数
     * 从底到顶
     *
     * @param n
     * @return
     */
    public static int fib3(int n) {
        if (n == 0) {
            return 0;
        }
        if (n == 1) {
            return 1;
        }
        int[] dp = new int[n + 1];
        dp[1] = 1;
        for (int i = 2; i <= n; i++) {
            dp[i] = dp[i - 2] + dp[i - 1];
        }
        return dp[n];
    }

    /**
     * 斐波那契数
     * 从底到顶 - 空间优化
     *
     * @param n
     * @return
     */
    public static int fib4(int n) {
        if (n == 0) {
            return 0;
        }
        if (n == 1) {
            return 1;
        }

        int last = 1;
        int lastLast = 0;
        for (int i = 2, cur; i <= n; i++) {
            cur = lastLast + last;
            lastLast = last;
            last = cur;
        }
        return last;
    }
}
