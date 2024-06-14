package algorithm_journey.class033;

/**
 * 不用任何算术运算，只用位运算实现加减乘除
 * 代码实现中你找不到任何一个算术运算符
 *
 * @author: Agony
 * @create: 2024/6/14 14:54
 * @describe: 给你两个整数，被除数 dividend 和除数 divisor。将两数相除，要求 不使用 乘法、除法和取余运算。
 * <p>
 * 整数除法应该向零截断，也就是截去（truncate）其小数部分。例如，8.345 将被截断为 8 ，-2.7335 将被截断至 -2 。
 * <p>
 * 返回被除数 dividend 除以除数 divisor 得到的 商 。
 * <p>
 * 注意：假设我们的环境只能存储 32 位 有符号整数，其数值范围是 [−231,  231 − 1] 。本题中，如果商 严格大于 231 − 1 ，则返回 231 − 1 ；如果商 严格小于 -231 ，则返回 -231 。
 * @link: <a href="https://leetcode.cn/problems/divide-two-integers/description/">两数相除</a>
 */
public class BitOperationAddMinusMultiplyDivide {


    public static void main(String[] args) {

        System.out.println(add(3, 5));
        System.out.println(minus(3, 5));
        System.out.println(multiply(3, 5));
        System.out.println(div(7, -3));
        System.out.println(divide(7, -3));
    }

    /**
     * 加法
     * a = 0101 0011
     * b = 0011 0010
     * a+b = a^b + 进位信息
     * a+b = 1000 0101
     * a^b = 0110 0001
     * a&b = 0001 0010   a&b << 1 表示有进位信息 -> 0100 0100
     * x = a^b
     * y = a+b << 1
     * x+y = x^y + 进位信息
     * ...
     * 直到进位信息为0
     *
     * @param a
     * @param b
     * @return
     */
    public static int add(int a, int b) {
        // 0101 0011
        // 0011 0010
        //
        int ans = a;
        while (b != 0) {
            ans = a ^ b;
            b = (a & b) << 1;
            a = ans;
        }
        return ans;
    }

    /**
     * a-b = a+(-b)
     * -b=~b+1 = add(~b,1)
     *
     * @param a
     * @param b
     * @return
     */
    public static int minus(int a, int b) {

        return add(a, neg(b));
    }

    /**
     * 返回一个数的相反数
     *
     * @param a
     * @return
     */
    public static int neg(int a) {
        return add(~a, 1);
    }

    /**
     * 乘法
     * b&1!=0
     * ans = a+b
     * b>>>1 , a = a<<1
     *
     * @param a
     * @param b
     * @return
     */
    public static int multiply(int a, int b) {
        int ans = 0;
        while (b != 0) {
            if ((b & 1) != 0) {
                ans = add(ans, a);
            }
            a <<= 1;
            b >>>= 1;
        }

        return ans;
    }


    /**
     * 两数相除
     *
     * @param a
     * @param b
     * @return
     */
    public static int divide(int a, int b) {
        if (a == Integer.MIN_VALUE && b == Integer.MIN_VALUE) {
            return 1;
        }
        if (a != Integer.MIN_VALUE && b != Integer.MIN_VALUE) {
            return div(a, b);
        }
        if (b == Integer.MIN_VALUE) {
            return 0;
        }
        // 以下是 a== Integer.MIN_VALUE

        if (b == -1) {
            return Integer.MAX_VALUE;
        }
        // b<0 -> a/b = (a-b)/b+1
        // b>0 -> a/b = (a+b)/b-1
        a = add(a, b > 0 ? b : -b);
        int ans = div(a, b);
        int offset = b > 0 ? -1 : 1;
        return add(ans, offset);
    }


    /**
     * 除法
     * 必须保证a和b都不是整数最小值，返回a除以b的结果
     * 最小值的相反数越界了
     * a=27 b=2  a/b=13
     * a = b*2^3 + b*2^2 + b*2^0 -> 1101
     *
     * @param a
     * @param b
     * @return
     */
    public static int div(int a, int b) {
        // 先把连个数转为正数
        int x = a >= 0 ? a : neg(a);
        int y = b >= 0 ? b : neg(b);
        int ans = 0;
        // i-- 就不用 i=minus(i, 1)了
        for (int i = 30; i >= 0; i--) {
            // 不能用 x>=(y<<i) y<<i 会越界
            if ((x >> i) >= y) {
                ans |= (1 << i);
                // x -= 1 << i 就不用 x=minus(x, y<<i)了
                x -= y << i;
            }
        }
        // a, b异号就返回相反值
        return a < 0 ^ b < 0 ? neg(ans) : ans;
    }
}
