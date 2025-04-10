package algorithm_journey.hwod;

import java.util.Scanner;

/**
 * @author: Agony
 * @create: 2025/4/10 22:20
 * @describe: 密码验证合格程序
 * <a href="https://www.nowcoder.com/practice/184edec193864f0985ad2684fbc86841?tpId=37&tqId=21243&rp=1&sourceUrl=%2Fexam%2Foj%2Fta%3FtpId%3D37&difficulty=3&judgeStatus=undefined&tags=&title=">密码验证合格程序</a>
 */
public class HJ20 {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        while (scanner.hasNextLine()) {

            String s = scanner.nextLine();
            if (!isValid(s)) {
                System.out.println("NG");
            } else {
                System.out.println("OK");
            }
        }

    }


    public static boolean isValid(String s) {
        if (s.length() < 8) {
            return false;
        }

        boolean hasUpChar = false;
        boolean hasLowChar = false;
        boolean hasNumber = false;
        boolean hasOthers = false;
        int count = 0;
        char[] chars = s.toCharArray();

        for (int i = 0; i < chars.length; i++) {
            if (!hasUpChar && Character.isUpperCase(chars[i])) {
                hasUpChar = true;
                count++;
            } else if (!hasLowChar && Character.isLowerCase(chars[i])) {
                hasLowChar = true;
                count++;
            } else if (!hasNumber && Character.isDigit(chars[i])) {
                hasNumber = true;
                count++;
            } else if (!hasOthers && !Character.isUpperCase(chars[i]) && !Character.isLowerCase(chars[i]) && !Character.isDigit(chars[i])) {
                hasOthers = true;
                count++;
            }
        }
        if (count < 3) {
            return false;
        }


        return !isRepeated(s);
    }


    private static boolean isRepeated(String password) {

        for (int i = 0; i < password.length() - 2; i++) {
            String subStr = password.substring(i, i + 3);
            // 从i+3位置开始查找子串，检查是否有重复
            int foundIndex = password.indexOf(subStr, i + 3);
            if (foundIndex != -1) {
                return true;
            }
        }
        return false;
    }

}
