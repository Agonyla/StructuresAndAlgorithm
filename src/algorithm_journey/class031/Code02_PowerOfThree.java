package algorithm_journey.class031;

/**
 * 3 的幂
 *
 * @author: Agony
 * @create: 2024/6/12 15:03
 * @describe: 给定一个整数，写一个函数来判断它是否是 3 的幂次方。如果是，返回 true ；否则，返回 false 。
 * <p>
 * 整数 n 是 3 的幂次方需满足：存在整数 x 使得 n == 3^x
 * @link: <a href="https://leetcode.cn/problems/power-of-three/description/">3 的幂</a>
 */
public class Code02_PowerOfThree {

    public static void main(String[] args) {
        System.out.println(isPowerOfThree(27));
        System.out.println(isPowerOfThree(0));
        System.out.println(isPowerOfThree(9));
        System.out.println(isPowerOfThree(45));
    }

    /**
     * 如果一个数字是3的某次幂，那么这个数一定只含有3这个质数因子
     * 1162261467是int型范围内，最大的3的幂，它是3的19次方
     * 这个1162261467只含有3这个质数因子，如果n也是只含有3这个质数因子，那么
     * 1162261467 % n == 0
     * 反之如果1162261467 % n != 0 说明n一定含有其他因子
     *
     * @param n
     * @return
     */
    public static boolean isPowerOfThree(int n) {

        return n > 0 && 1162261467 % n == 0;
    }
}
