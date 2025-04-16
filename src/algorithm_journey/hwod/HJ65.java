package algorithm_journey.hwod;

import java.util.Scanner;

/**
 * @author: Agony
 * @create: 2025/4/16 10:23
 * @describe: 查找两个字符串a, b中的最长公共子串
 * <a href="https://www.nowcoder.com/practice/181a1a71c7574266ad07f9739f791506?tpId=37&tqId=21288&rp=1&sourceUrl=%2Fexam%2Foj%2Fta%3FtpId%3D37&difficulty=3&judgeStatus=undefined&tags=&title=">查找两个字符串a,b中的最长公共子串</a>
 */
public class HJ65 {

    public static void main(String[] args) {


        Scanner scanner = new Scanner(System.in);
        String str1 = scanner.nextLine();
        String str2 = scanner.nextLine();
        String s1 = str1.length() > str2.length() ? str1 : str2;
        String s2 = s1.equals(str1) ? str2 : str1;


        // System.out.println(s1);
        // System.out.println(s2);

        System.out.println(getLongestString(s1, s2));


    }


    /**
     * s1 是较长的
     *
     * @param s1
     * @param s2
     * @return
     */
    public static String getLongestString(String s1, String s2) {

        String ans = "";
        int maxLength = 0;

        for (int j = 0; j < s2.length(); j++) {
            for (int i = 0; i < s1.length(); i++) {
                int length = getLongestLength(s1, s2, i, j, 0);
                if (length > maxLength) {
                    maxLength = length;
                    ans = s2.substring(j, maxLength + j);
                }
            }
        }


        return ans;
    }


    public static int getLongestLength(String s1, String s2, int i, int j, int count) {

        if (i >= s1.length() || j >= s2.length()) {
            return count;
        }

        if (s1.charAt(i) == s2.charAt(j)) {
            count = getLongestLength(s1, s2, i + 1, j + 1, count + 1);
        }

        return count;
    }

}
