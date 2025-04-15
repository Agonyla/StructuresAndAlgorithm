package algorithm_journey.hwod;

import java.util.Scanner;

/**
 * @author: Agony
 * @create: 2025/4/15 10:39
 * @describe: 高精度整数加法
 * <a href="https://www.nowcoder.com/practice/49e772ab08994a96980f9618892e55b6?tpId=37&rp=1&sourceUrl=%2Fexam%2Foj%2Fta%3FtpId%3D37&difficulty=3&judgeStatus=&tags=&title=&gioEnter=menu">高精度整数加法</a>
 */
public class HJ57 {


    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        String s1 = scanner.nextLine();
        String s2 = scanner.nextLine();

        System.out.println(add(s1, s2));
    }


    public static String add(String s1, String s2) {

        // 1920
        // 0080
        int carry = 0;
        int i = s1.length() - 1;
        int j = s2.length() - 1;
        StringBuilder sb = new StringBuilder();

        while (i >= 0 && j >= 0) {

            int num1 = s1.charAt(i) - '0';
            int num2 = s2.charAt(j) - '0';
            int sum = num1 + num2;
            if (carry == 1) {
                sum += 1;
                carry = 0;
            }
            if (sum >= 10) {
                carry = 1;
                sum %= 10;
            }
            sb.append(sum);
            i--;
            j--;
        }


        while (i >= 0) {
            int sum = s1.charAt(i) - '0';
            if (carry == 1) {
                sum += 1;
                carry = 0;
            }
            if (sum >= 10) {
                carry = 1;
                sum %= 10;
            }
            sb.append(sum);
            i--;
        }

        while (j >= 0) {
            int sum = s2.charAt(j) - '0';
            if (carry == 1) {
                sum += 1;
                carry = 0;
            }
            if (sum >= 10) {
                carry = 1;
                sum %= 10;
            }
            sb.append(sum);
            j--;
        }
        if (carry == 1) {
            sb.append(1);
        }

        sb.reverse();
        return sb.toString();
    }
}
