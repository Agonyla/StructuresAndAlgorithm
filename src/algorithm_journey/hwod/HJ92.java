package algorithm_journey.hwod;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * @author: Agony
 * @create: 2025/4/28 12:16
 * @describe: 在字符串中找出连续最长的数字串
 * <a href="https://www.nowcoder.com/practice/2c81f88ecd5a4cc395b5308a99afbbec?tpId=37&rp=1&sourceUrl=%2Fexam%2Foj%2Fta%3FtpId%3D37&difficulty=3&judgeStatus=&tags=&title=&gioEnter=menu">在字符串中找出连续最长的数字串</a>
 */
public class HJ92 {


    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        String s = scanner.nextLine();
        scanner.close();

        String result = process(s);
        System.out.println(result);
    }


    public static String process(String s) {

        if (s == null || s.isEmpty()) {
            return null;
        }

        ArrayList<String> list = new ArrayList<>();

        StringBuilder sb = new StringBuilder();

        int maxLength = 0;

        for (int i = 0; i < s.length(); i++) {

            char c = s.charAt(i);
            if (Character.isDigit(c)) {
                sb.append(c);
            } else {
                if (sb.length() > 0) {
                    if (sb.length() > maxLength) {
                        maxLength = sb.length();
                        sb = new StringBuilder(sb);
                        list.clear();
                        list.add(sb.toString());
                    } else if (sb.length() == maxLength) {
                        list.add(sb.toString());
                    }
                    // sb 清空
                    sb = new StringBuilder();
                }
            }
        }


        // 最后检查，此时sb中可能存储了最长的字符串
        if (sb.length() > maxLength) {
            maxLength = sb.length();
            list.clear();
            list.add(sb.toString());
        } else if (sb.length() == maxLength) {
            list.add(sb.toString());
        }

        StringBuilder result = new StringBuilder();

        for (String string : list) {
            result.append(string);
        }
        result.append(",").append(maxLength);


        return result.toString();
    }

}