package algorithm_journey.hwod;

import java.util.Scanner;

/**
 * @author: Agony
 * @create: 2025/4/13 15:21
 * @describe: 字符串加解密
 * <a href="https://www.nowcoder.com/practice/2aa32b378a024755a3f251e75cbf233a?tpId=37&rp=1&sourceUrl=%2Fexam%2Foj%2Fta%3FtpId%3D37&difficulty=3&judgeStatus=&tags=&title=&gioEnter=menu">字符串加解密</a>
 */
public class HJ29 {

    public static void main(String[] args) {


        Scanner scanner = new Scanner(System.in);

        String s1 = scanner.nextLine();
        String s2 = scanner.nextLine();


        // char a = 'a';
        // System.out.println((char) (a + 1));

        char[] s1CharArray = s1.toCharArray();
        encrypt(s1CharArray);
        System.out.println(new String(s1CharArray));

        char[] s2CharArray = s2.toCharArray();
        decrypt(s2CharArray);
        System.out.println(new String(s2CharArray));

    }


    public static void encrypt(char[] s) {

        for (int i = 0; i < s.length; i++) {
            if (Character.isDigit(s[i])) {
                if (s[i] == '9') {
                    s[i] = '0';
                } else {
                    s[i] = (char) (s[i] + 1);
                }
            } else if (Character.isLowerCase(s[i])) {
                if (s[i] == 'z') {
                    s[i] = 'A';
                } else {
                    s[i] = (char) Character.toUpperCase(s[i] + 1);
                }
            } else {
                if (s[i] == 'Z') {
                    s[i] = 'a';
                } else {
                    s[i] = (char) Character.toLowerCase(s[i] + 1);
                }
            }
        }
    }


    public static void decrypt(char[] s) {

        for (int i = 0; i < s.length; i++) {
            if (Character.isDigit(s[i])) {
                if (s[i] == '0') {
                    s[i] = '9';
                } else {
                    s[i] = (char) (s[i] - 1);
                }
            } else if (Character.isLowerCase(s[i])) {
                if (s[i] == 'a') {
                    s[i] = 'Z';
                } else {
                    s[i] = (char) Character.toUpperCase(s[i] - 1);
                }
            } else {
                if (s[i] == 'A') {
                    s[i] = 'z';
                } else {
                    s[i] = (char) Character.toLowerCase(s[i] - 1);
                }
            }
        }
    }


}
