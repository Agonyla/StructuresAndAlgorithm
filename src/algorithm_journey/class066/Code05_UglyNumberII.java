package algorithm_journey.class066;

/**
 * 丑数 II
 *
 * @author: Agony
 * @create: 2024/7/27 11:20
 * @describe: 给你一个整数 n ，请你找出并返回第 n 个 丑数 。
 * <p>
 * 丑数 就是质因子只包含 2、3 和 5 的正整数。
 * @link: <a href="https://leetcode.cn/problems/ugly-number-ii/description/">丑数 II</a>
 */
public class Code05_UglyNumberII {


    public static void main(String[] args) {

        
        // for (int i = 0; i < 20; i++) {
        //     if (isUgly(i)) {
        //         System.out.println(i + " 是丑数");
        //     }
        // }

        System.out.println(nthUglyNumber(10));

    }

    // 丑数 II

    // 思路：
    // 暴力递归
    // 每个数都去验证是否是丑数，直到n
    // 验证 -> 让该数去除2、3、5 直到模不等于0
    // int[] factors = {2, 3, 5};
    //     for (int factor : factors) {
    //     while (num % factor == 0) {
    //         num /= factor;
    //     }
    // }

    // 动态规划
    // 实现思路：摒弃掉去验证每一个是否是丑数，而是直接把下一个丑数写出来
    // 因为丑数是质因子只有2、3、5的数字，所以后面的丑数必然是前面的丑数乘2或3或5得到的
    // 故下一个丑数就是前面丑数乘 2或3或5 中的最小值
    // 最初的实现
    // 起始位置是1，开始往后填数字
    // 每个数字必然会是前一个数字的2、3或5倍数
    // 第2个是2，第3个是前2个数字2、3、5倍的最小值
    // 第3个是3，第4个是前3个数字2、3、5倍的最小值
    // 。。。
    //
    // 优化后的实现
    // 设置3个指针，每个指针表示该指针的下标，如i2在第3个数，表示该数要乘2
    // 比较当前指针所在位置的数乘该指针，得到最小值
    // 当前指针使用过之后，该指针来到下一个数 -> 因为该数已经乘过该指针了，后面的数肯定比刚刚得到的数要大
    // （如果两个指针和所在位置的数的乘积一样，都来到下一个数）-> 因为下一个丑数必然比现在这个大，停在原来的位置没意义
    // 只要比较三个数的最小值，而优化前需要比较所有丑数乘三个数的最小值


    /**
     * 丑数 II
     *
     * @param n
     * @return
     */
    public static int nthUglyNumber(int n) {

        int[] dp = new int[n + 1];
        dp[1] = 1;

        for (int i = 2, i2 = 1, i3 = 1, i5 = 1, a, b, c, cur; i <= n; i++) {
            a = dp[i2] * 2;
            b = dp[i3] * 3;
            c = dp[i5] * 5;
            cur = Math.min(a, Math.min(b, c));
            if (a == cur) {
                i2++;
            }
            if (b == cur) {
                i3++;
            }
            if (c == cur) {
                i5++;
            }
            dp[i] = cur;
        }
        return dp[n];
    }


    /**
     * 验证是否是丑数
     *
     * @param num
     * @return
     */
    public static boolean isUgly(int num) {

        if (num <= 0) {
            return false;
        }
        // 该数去除 2、3、5
        int[] factors = {2, 3, 5};
        for (int factor : factors) {
            while (num % factor == 0) {
                num /= factor;
            }
        }
        // 如果除尽之后该数为1，那么该数就是丑数
        // 这个判断很重要
        return num == 1;
    }
}
