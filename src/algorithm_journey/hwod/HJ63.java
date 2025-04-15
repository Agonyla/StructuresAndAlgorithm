package algorithm_journey.hwod;

import java.util.Scanner;

/**
 * @author: Agony
 * @create: 2025/4/15 11:26
 * @describe: DNA序列
 * <a href="https://www.nowcoder.com/practice/e8480ed7501640709354db1cc4ffd42a?tpId=37&rp=1&sourceUrl=%2Fexam%2Foj%2Fta%3FtpId%3D37&difficulty=3&judgeStatus=&tags=&title=&gioEnter=menu"> DNA序列</a>
 */
public class HJ63 {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        String s = scanner.nextLine();
        int n = scanner.nextInt();
        System.out.println(process(s, n));
    }


    public static String process(String s, int n) {

        String maxGC = "";
        double maxGCRatio = -1;


        for (int i = 0; i <= s.length() - n; i++) {

            String substring = s.substring(i, i + n);
            double result = calculate(substring, n);
            if (result > maxGCRatio) {
                maxGCRatio = result;
                maxGC = substring;
            }
        }


        return maxGC;


    }

    public static double calculate(String s, int n) {

        int count = 0;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == 'C' || c == 'G') {
                count++;
            }
        }


        return (double) count / n;
    }
}
