package algorithm_journey.class041;

/**
 * 第 N 个神奇数字
 *
 * @author: Agony
 * @create: 2024/7/5 15:19
 * @describe: 一个正整数如果能被 a 或 b 整除，那么它是神奇的。
 * <p>
 * 给定三个整数 n , a , b ，返回第 n 个神奇的数字。因为答案可能很大，所以返回答案 对 109 + 7 取模 后的值
 * <p>
 * 输入：n = 1, a = 2, b = 3
 * 输出：2
 * <p>
 * 输入：n = 4, a = 2, b = 3
 * 输出：6
 * @link: <a href="https://leetcode.cn/problems/nth-magical-number/description/">第 N 个神奇数字</a>
 */
public class Code02_NthMagicalNumber {


    public static void main(String[] args) {
        System.out.println(nthMagicalNumber(1, 2, 3));
        System.out.println(nthMagicalNumber(4, 2, 3));
    }


    // 一个正整数如果能被 a 或 b 整除，那么它是神奇的。
    // 给定三个整数 n , a , b ，返回第 n 个神奇的数字。

    // 思路
    // 第 n 个神奇的数字一定在[0,  min(a,b)*n] 这个范围内
    // [1, x] 范围内一共有多少个神奇的数字
    // 个数 =  x/a+ x/b - x/(a和b的最小公倍数)
    //
    //
    // 左边界 0，右边界 min(a,b)*n
    // 如果多了往左二分，如果少了往右二分


    /**
     * 返回第n个神奇的数字
     *
     * @param n
     * @param a
     * @param b
     * @return
     */
    public static int nthMagicalNumber(int n, int a, int b) {

        long lcm = lcm(a, b);
        long ans = 0;

        long l = 0;
        long r = (long) Math.min(a, b) * n;
        long mid = 0;
        while (l <= r) {
            mid = (l + r) / 2;
            if (mid / a + mid / b - mid / lcm >= n) {
                ans = mid;
                r = mid - 1;
            } else {
                l = mid + 1;
            }
        }
        return (int) (ans % 1000000007);
    }

    public static long gcd(long a, long b) {
        return b == 0 ? a : gcd(b, a % b);
    }

    public static long lcm(long a, long b) {
        return a / gcd(a, b) * b;
    }


}




