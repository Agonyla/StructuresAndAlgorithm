package algorithm_journey.hwod;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

/**
 * @author: Agony
 * @create: 2025/4/11 15:58
 * @describe: 字符串排序
 * <a href="https://www.nowcoder.com/practice/5190a1db6f4f4ddb92fd9c365c944584?tpId=37&rp=1&sourceUrl=%2Fexam%2Foj%2Fta%3FtpId%3D37&difficulty=3&judgeStatus=&tags=&title=&gioEnter=menu">字符串排序</a>
 */
public class HJ26 {


    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);


        String s = scanner.nextLine();

        String string = sortArr(s);
        System.out.println(string);

    }


    public static String sortArr(String s) {


        ArrayList<Character> list = new ArrayList<>();

        char[] result = new char[s.length()];
        Arrays.fill(result, '\0');

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (Character.isLetter(c)) {
                list.add(s.charAt(i));
            } else {
                result[i] = c;
            }
        }

        list.sort((o1, o2) -> {
            char c1 = Character.toLowerCase(o1);
            char c2 = Character.toLowerCase(o2);
            return c1 - c2;
        });

        for (int i = 0; i < s.length(); i++) {
            if (result[i] == '\0') {
                result[i] = list.remove(0);
            }
        }

        return new String(result);
    }
}
