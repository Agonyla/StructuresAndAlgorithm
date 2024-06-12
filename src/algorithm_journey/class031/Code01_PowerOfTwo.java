package algorithm_journey.class031;

/**
 * 2 的幂
 *
 * @author Agony
 * @create 2024/6/7 13:32
 * @describe: 给你一个整数 n，请你判断该整数是否是 2 的幂次方。如果是，返回 true ；否则，返回 false 。
 * <p>
 * 如果存在一个整数 x 使得 n == 2^x ，则认为 n 是 2 的幂次方
 * @link: <a href="https://leetcode.cn/problems/power-of-two/description/">2 的幂</a>
 */
public class Code01_PowerOfTwo {


    //todo
    public static void main(String[] args) {
        System.out.println(isPowerOfTwo(1));
        System.out.println(isPowerOfTwo(16));
        System.out.println(isPowerOfTwo(3));
    }

    /**
     * 2 的幂二进制中只有一位是 1
     *
     * @param n
     * @return
     */
    public static boolean isPowerOfTwo(int n) {

        return n > 0 && n == (n & -n);
    }
}
