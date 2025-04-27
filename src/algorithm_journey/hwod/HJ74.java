package algorithm_journey.hwod;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * @author: Agony
 * @create: 2025/4/27 21:42
 * @describe: 参数解析
 * <a href="https://www.nowcoder.com/practice/668603dc307e4ef4bb07bcd0615ea677?tpId=37&tqId=21297&rp=1&sourceUrl=%2Fexam%2Foj%2Fta%3FtpId%3D37&difficulty=3&judgeStatus=undefined&tags=&title=">HJ74 参数解析</a>
 */
public class HJ74 {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        String s = scanner.nextLine();
        ArrayList<String> list = process(s);
        System.out.println(list.size());
        for (String string : list) {
            System.out.println(string);
        }
    }


    public static ArrayList<String> process(String s) {

        ArrayList<String> list = new ArrayList<>();

        boolean isQuote = false;
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < s.length(); i++) {

            char c = s.charAt(i);
            if (c == '\"') {
                isQuote = !isQuote;
                continue;
            }

            if (c == ' ' && !isQuote) {
                if (sb.length() > 0) {
                    list.add(sb.toString());
                    sb = new StringBuilder();
                }
            } else {
                sb.append(c);
            }
        }

        if (sb.length() > 0) {
            list.add(sb.toString());
        }

        return list;
    }


}
