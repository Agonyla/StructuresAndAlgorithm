package algorithm_journey.hwod;

import java.util.Scanner;


/**
 * @author: Agony
 * @create: 2025/4/28 10:32
 * @describe: 将真分数分解为埃及分数
 * <a href="https://www.nowcoder.com/practice/e0480b2c6aa24bfba0935ffcca3ccb7b?tpId=37&tqId=21305&rp=1&sourceUrl=%2Fexam%2Foj%2Fta%3FtpId%3D37&difficulty=3&judgeStatus=undefined&tags=&title=">将真分数分解为埃及分数</a>
 */
public class HJ82 {


    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        scanner.close();

        // 解析分数
        String[] parts = input.split("/");
        long numerator = Long.parseLong(parts[0]);
        long denominator = Long.parseLong(parts[1]);

        // 化简分数
        long gcd = gcd(numerator, denominator);
        numerator /= gcd;
        denominator /= gcd;

        // 如果已经是埃及分数，直接输出
        if (numerator == 1) {
            System.out.println("1/" + denominator);
            return;
        }

        // 分解为埃及分数
        StringBuilder result = new StringBuilder();
        decomposeToEgyptianFraction(numerator, denominator, result);
        System.out.println(result.toString());
    }

    // 计算最大公约数
    private static long gcd(long a, long b) {
        return b == 0 ? a : gcd(b, a % b);
    }

    private static void decomposeToEgyptianFraction(long numerator, long denominator, StringBuilder result) {
        // 处理特殊情况：分子为0
        if (numerator == 0) {
            return;
        }

        // 如果分子为1，已经是埃及分数
        if (numerator == 1) {
            result.append("1/").append(denominator);
            return;
        }

        // 如果分子整除分母，可以直接分解为多个相同的埃及分数
        if (denominator % numerator == 0) {
            result.append("1/").append(denominator / numerator);
            return;
        }

        // 使用更高效的分解策略
        // 如果分子大于1，先取出最大的埃及分数
        long n = denominator / numerator + 1;
        result.append("1/").append(n);

        // 计算剩余部分的分子和分母
        long newNumerator = numerator * n - denominator;
        long newDenominator = denominator * n;

        // 化简剩余部分的分数
        long g = gcd(newNumerator, newDenominator);
        newNumerator /= g;
        newDenominator /= g;

        // 如果还有剩余部分，添加加号
        if (newNumerator > 0) {
            result.append("+");
            decomposeToEgyptianFraction(newNumerator, newDenominator, result);
        }
    }
}