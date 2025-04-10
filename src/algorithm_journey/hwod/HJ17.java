package algorithm_journey.hwod;

import java.util.Scanner;

/**
 * @author: Agony
 * @create: 2025/4/10 21:30
 * @describe: 坐标移动
 * <a href="https://www.nowcoder.com/practice/119bcca3befb405fbe58abe9c532eb29?tpId=37&tqId=21240&rp=1&sourceUrl=%2Fexam%2Foj%2Fta%3FtpId%3D37&difficulty=3&judgeStatus=undefined&tags=&title=">坐标移动</a>
 */
public class HJ17 {

    public static void main(String[] args) {


        Scanner scanner = new Scanner(System.in);

        String[] strs = scanner.nextLine().split(";");

        int x = 0;
        int y = 0;

        for (String str : strs) {
            if (!isValid(str)) {
                continue;
            }
            char direction = str.charAt(0);
            String number = str.substring(1);
            int num = Integer.parseInt(number);

            if ('W' == direction) {
                y += num;
            } else if ('S' == direction) {
                y -= num;
            } else if ('A' == direction) {
                x -= num;
            } else if ('D' == direction) {
                x += num;
            }
        }
        System.out.println(x + "," + y);

    }


    public static boolean isValid(String s) {

        if (s.length() < 2) {
            return false;
        }
        char direction = s.charAt(0);

        if (direction != 'W' && direction != 'A' && direction != 'S' && direction != 'D') {
            return false;
        }

        String number = s.substring(1);
        try {
            Integer.parseInt(number);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}

