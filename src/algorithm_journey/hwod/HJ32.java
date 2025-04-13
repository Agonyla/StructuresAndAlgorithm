package algorithm_journey.hwod;

import java.util.Scanner;

/**
 * @author: Agony
 * @create: 2025/4/13 15:36
 * @describe: 密码截取
 * <a href="https://www.nowcoder.com/practice/3cd4621963e8454594f00199f4536bb1?tpId=37&rp=1&sourceUrl=%2Fexam%2Foj%2Fta%3FtpId%3D37&difficulty=3&judgeStatus=&tags=&title=&gioEnter=menu">密码截取</a>
 */
public class HJ32 {

    public static void main(String[] args) {


        Scanner scanner = new Scanner(System.in);

        String s = scanner.next();


        System.out.println(findLongestPalindrome(s));

    }

    public static int findLongestPalindrome(String s) {

        int start = 0;
        int end = 0;

        for (int i = 0; i < s.length(); i++) {
            int l1 = maxLength(s, i, i);
            int l2 = maxLength(s, i, i + 1);
            int len = Math.max(l1, l2);

            if (len > end - start) {
                end = i + len / 2;
                start = i - (len - 1) / 2;
            }
        }

        return end - start + 1;
    }


    public static int maxLength(String s, int left, int right) {

        while (left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)) {
            left--;
            right++;
        }

        return right - left - 1;
    }
}



