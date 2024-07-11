package algorithm_journey.class043;

/**
 * 回文数
 *
 * @author: Agony
 * @create: 2024/7/11 21:52
 * @describe: 给你一个整数 x ，如果 x 是一个回文整数，返回 true ；否则，返回 false 。
 * <p>
 * 回文数
 * 是指正序（从左向右）和倒序（从右向左）读都是一样的整数。
 * @link: <a href="https://leetcode.cn/problems/palindrome-number/description/">回文数</a>
 */
public class Code03_IsPalindrome {


    public static void main(String[] args) {
        System.out.println(isPalindrome(1));
        System.out.println(isPalindrome(22));
        System.out.println(isPalindrome(121));
        System.out.println(isPalindrome(1221));
    }

    /**
     * 判断x是否是回文数
     *
     * @param x
     * @return
     */
    public static boolean isPalindrome(int x) {
        if (x < 0) {
            return false;
        }
        // 获得长度和x相等的10的幂
        int offset = 1;
        while (x / offset >= 10) {
            offset *= 10;
        }
        // 为什么是>=10
        // 正常写法
        // while (x >= offset) {
        //     offset *= 10;
        // }
        // offset /= 10;
        // 这样如果x很大，offset会溢出
        while (x != 0) {
            if (x / offset != x % 10) {
                return false;
            }
            x = x % offset / 10;
            offset /= 100;
        }
        return true;
    }


    /**
     * 最优解
     * 把x逆序再和自己比较是否一样
     *
     * @param x
     * @return
     */
    public static boolean isPalindromeBetter(int x) {
        if (x < 0) {
            return false;
        }
        int reverse = 0;
        int cur = x;
        while (cur != 0) {
            reverse = reverse * 10 + cur % 10;
            cur /= 10;
        }
        return reverse == x;
    }
}
