package algorithm_journey.hwod;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

/**
 * @author: Agony
 * @create: 2025/4/13 14:40
 * @describe: 查找兄弟单词
 * <a href="https://www.nowcoder.com/practice/03ba8aeeef73400ca7a37a5f3370fe68?tpId=37&rp=1&sourceUrl=%2Fexam%2Foj%2Fta%3FtpId%3D37&difficulty=3&judgeStatus=&tags=&title=&gioEnter=menu">查找兄弟单词</a>
 */
public class HJ27 {

    public static void main(String[] args) {


        Scanner scanner = new Scanner(System.in);
        int num = scanner.nextInt();

        String[] strs = new String[num];

        for (int i = 0; i < num; i++) {
            strs[i] = scanner.next();
        }

        String target = scanner.next();

        int k = scanner.nextInt();

        ArrayList<String> list = new ArrayList<>();

        for (int i = 0; i < strs.length; i++) {

            if (isBrother(strs[i], target)) {
                list.add(strs[i]);
            }
        }

        list.sort(String::compareTo);

        System.out.println(list.size());

        if (!list.isEmpty() && list.size() >= k) {
            System.out.println(list.get(k - 1));
        }

    }


    public static boolean isBrother(String s, String target) {

        if (s.length() != target.length()) {
            return false;
        }
        if (s.equals(target)) {
            return false;
        }
        char[] targetCharArray = target.toCharArray();
        Arrays.sort(targetCharArray);
        target = new String(targetCharArray);


        char[] sCharArray = s.toCharArray();
        Arrays.sort(sCharArray);
        s = new String(sCharArray);

        return s.equals(target);
    }
}
