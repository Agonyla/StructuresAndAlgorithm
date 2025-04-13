package algorithm_journey.hwod;

import java.util.Scanner;

/**
 * @author: Agony
 * @create: 2025/4/13 23:04
 * @describe: 整数与IP地址间的转换
 * <a href="https://www.nowcoder.com/practice/66ca0e28f90c42a196afd78cc9c496ea?tpId=37&tqId=21256&rp=1&sourceUrl=%2Fexam%2Foj%2Fta%3FtpId%3D37&difficulty=3&judgeStatus=undefined&tags=&title=">整数与IP地址间的转换</a>
 */
public class HJ33 {

    public static void main(String[] args) {


        Scanner scanner = new Scanner(System.in);

        String[] IPs = scanner.nextLine().split("\\.");
        String num = scanner.nextLine();
        //
        System.out.println(IPToNum(IPs));
        System.out.println(NumToIP(Long.parseLong(num)));


    }

    public static long IPToNum(String[] IPs) {

        int[] arr = new int[4];

        for (int i = 0; i < 4; i++) {
            arr[i] = Integer.parseInt(IPs[i]);
        }

        long sum = 0;
        for (int i = 0; i < IPs.length; i++) {
            long num = (long) arr[i] * (1L << (8 * (3 - i)));
            sum += num;
        }
        return sum;
    }


    public static String NumToIP(long num) {

        StringBuilder sb = new StringBuilder();

        int[] arr = new int[4];

        for (int i = 0; i < 4; i++) {
            int ip = (int) (num % 256);
            num /= 256;
            arr[3 - i] = ip;
        }

        for (int i = 0; i < 4; i++) {
            if (i == 3) {
                sb.append(arr[i]);
            } else {
                sb.append(arr[i]).append(".");
            }
        }

        return sb.toString();

    }


    public static String intToString(int n) {

        if (n == 0) {
            return "00000000";
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 7; i >= 0; i--) {
            int c = 1 & (n >>> i);
            sb.append(c);
        }


        System.out.println();
        return sb.toString();

    }
}
