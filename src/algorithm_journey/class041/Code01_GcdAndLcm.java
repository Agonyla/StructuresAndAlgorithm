package algorithm_journey.class041;

/**
 * @author: Agony
 * @create: 2024/7/5 15:00
 * @describe:
 * @link:
 */
public class Code01_GcdAndLcm {


    public static void main(String[] args) {
        long a = 12;
        long b = 16;
        System.out.println(gcd(a, b));

    }

    // 求最大公约数、最小公倍数
    // 辗转相除法

    // 最大公约数
    // 思路
    // 两个数 a,b
    // b==0, return a
    // a = b, b = a % b
    // 直到 b == 0


    // 证明辗转相除法就是证明如下关系：
    // gcd(a, b) = gcd(b, a % b)
    // 假设a % b = r，即需要证明的关系为：gcd(a, b) = gcd(b, r)
    // 证明过程：
    // 因为a % b = r，所以如下两个等式必然成立
    // 1) a = b * q + r，q为0、1、2、3....中的一个整数
    // 2) r = a − b * q，q为0、1、2、3....中的一个整数
    // 假设u是a和b的公因子，则有: a = s * u, b = t * u
    // 把a和b带入2)得到，r = s * u - t * u * q = (s - t * q) * u
    // 这说明 : u如果是a和b的公因子，那么u也是r的因子
    // 假设v是b和r的公因子，则有: b = x * v, r = y * v
    // 把b和r带入1)得到，a = x * v * q + y * v = (x * q + y) * v
    // 这说明 : v如果是b和r的公因子，那么v也是a的公因子
    // 综上，a和b的每一个公因子 也是 b和r的一个公因子，反之亦然
    // 所以，a和b的全体公因子集合 = b和r的全体公因子集合
    // 即gcd(a, b) = gcd(b, r)
    // 证明结束


    // 最小公倍数
    // 思路
    // 在求得的最大公约数，
    // 一个数除以最大公约数再乘另一个数就是最小公倍数


    /**
     * 最大公约数
     * Greatest common divisor
     *
     * @param a
     * @param b
     * @return
     */
    public static long gcd(long a, long b) {
        return b == 0 ? a : gcd(b, a % b);
    }


    /**
     * 最小公倍数
     * Least common multiple
     *
     * @param a
     * @param b
     * @return
     */
    public static long lcm(long a, long b) {
        return a / gcd(a, b) * b;
    }
}
