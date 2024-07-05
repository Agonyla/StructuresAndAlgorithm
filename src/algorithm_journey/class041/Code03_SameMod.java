package algorithm_journey.class041;

/**
 * @author: Agony
 * @create: 2024/7/5 15:47
 * @describe:
 * @link:
 */
public class Code03_SameMod {

    public static void main(String[] args) {
        System.out.println(-2 % 7);
    }

    // todo
    //
    // 加法、减法、乘法的同余原理

    // 加法同余原理
    // (a+b) % c = ans
    // ==> a%c=d, b%c=e, -> (d+e)%c = ans
    // (a+b) % c = (a%c + b%c)%c
    // 9 mod 3 = 0；
    // 4 mod 3 = 1, 5 mod 3 = 2, (1 + 2) mod 3 = 0

    // 乘法同加法

    // 减法
    // (a-b)%m = (a%m - b%m + m)%m
    // 为什么要加m -> a%m - b%m 可能会出现负数的情况，为了让结果不为负，就人为让其加上m变成正数
}
