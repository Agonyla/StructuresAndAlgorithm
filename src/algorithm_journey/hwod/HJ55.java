package algorithm_journey.hwod;

import java.util.Scanner;

/**
 * @author: Agony
 * @create: 2025/4/15 10:27
 * @describe: 挑7
 * <a href="https://www.nowcoder.com/practice/ba241b85371c409ea01ac0aa1a8d957b?tpId=37&rp=1&sourceUrl=%2Fexam%2Foj%2Fta%3FtpId%3D37&difficulty=3&judgeStatus=&tags=&title=&gioEnter=menu">挑7</a>
 */
public class HJ55 {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        int num = scanner.nextInt();

        int count = 0;

        for (int i = 1; i <= num; i++) {
            if (include7(i)) {
                count++;
                // System.out.println(i);
            }
        }
        System.out.println(count);
    }


    public static boolean include7(int num) {


        if (num % 7 == 0) {
            return true;
        }

        while (num != 0) {
            int rest = num % 10;
            num /= 10;
            if (rest == 7) {
                return true;
            }
            if (num == 7) {
                return true;
            }
        }

        return false;
    }


}
