package algorithm_journey.hwod;

import java.util.Scanner;

/**
 * @author: Agony
 * @create: 2025/4/30 16:16
 * @describe: 进制转换
 * <a href="https://www.nowcoder.com/practice/8f3df50d2b9043208c5eed283d1d4da6?tpId=37&tqId=21228&rp=1&sourceUrl=%2Fexam%2Foj%2Fta%3FtpId%3D37&difficulty=undefined&judgeStatus=undefined&tags=&title=">进制转换</a>
 */
public class HJ5 {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String s = in.nextLine();
        s = s.substring(2);
        int result = process(s);
        System.out.println(result);

    }

    public static int process(String s) {

        int len = s.length();
        int num = 0;
        for (int i = 0; i < len; i++) {
            int cur = 0;
            char c = s.charAt(i);
            if (c == 'A') {
                cur = 10;
            } else if (c == 'B') {
                cur = 11;
            } else if (c == 'C') {
                cur = 12;
            } else if (c == 'D') {
                cur = 13;
            } else if (c == 'E') {
                cur = 14;
            } else if (c == 'F') {
                cur = 15;
            } else {
                cur = c - '0';
            }
            num += (int) (cur * Math.pow(16, len - i - 1));
        }

        return num;
    }
}
