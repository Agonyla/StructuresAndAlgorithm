package algorithm_journey.leetcode;

/**
 * @author: Agony
 * @create: 2025/4/4 20:22
 * @describe: Pow(x, n)
 * <a href="https://leetcode.cn/problems/powx-n/description/?envType=company&envId=huawei&favoriteSlug=huawei-three-months">Pow(x, n)</a>
 */
public class LeetCode50 {


    public static void main(String[] args) {

        System.out.println(myPow(2, 2));
        // System.out.println(myPow(2, 10));
        // System.out.println(myPow(2.1, 3));
        // System.out.println(myPow(2, -2));
    }

    public static double myPow(double x, int n) {

        if (x == 0) return 0;
        if (n == 0) return 1;

        double ans = 1;

        if (n < 0) {
            x = 1 / x;
            n = -n;
        }


        return fastPow(x, n);
    }

    public static double fastPow(double x, int n) {

        if (n == 0) return 1;


        double half = fastPow(x, n / 2);

        if (n % 2 == 0) {
            return half * half;
        } else {
            return half * half * x;
        }

    }


}
