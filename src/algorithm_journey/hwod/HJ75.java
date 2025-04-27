package algorithm_journey.hwod;

import java.util.Scanner;

/**
 * @author: Agony
 * @create: 2025/4/27 22:07
 * @describe: 公共子串计算
 * <a href="https://www.nowcoder.com/practice/98dc82c094e043ccb7e0570e5342dd1b?tpId=37&rp=1&sourceUrl=%2Fexam%2Foj%2Fta%3FtpId%3D37&difficulty=3&judgeStatus=&tags=&title=&gioEnter=menu">公共子串计算</a>
 */
public class HJ75 {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        String s1 = scanner.nextLine();
        String s2 = scanner.nextLine();

        int max = 0;

        for (int i = 0; i < s1.length(); i++) {
            for (int j = 0; j < s2.length(); j++) {
                max = Math.max(max, process(s1, s2, i, j));
            }
        }
        System.out.println(max);
    }


    /**
     * 找到s1和s2的最长公共子串
     *
     * @param s1
     * @param s2
     * @param i
     * @param j
     * @return
     */
    public static int process(String s1, String s2, int i, int j) {
        if (i == s1.length() || j == s2.length()) {
            return 0;
        }
        if (s1.charAt(i) == s2.charAt(j)) {
            return process(s1, s2, i + 1, j + 1) + 1;
        } else {
            return 0;
        }
    }
}


// public class Main {
//     public static void main(String[] args) {
//         Scanner scanner = new Scanner(System.in);
//
//         // 读取输入的两个字符串
//         String str1 = scanner.nextLine();
//         String str2 = scanner.nextLine();
//         scanner.close();
//
//         // 确保str1是较短的字符串，优化性能
//         if (str1.length() > str2.length()) {
//             String temp = str1;
//             str1 = str2;
//             str2 = temp;
//         }
//
//         // 计算并输出最长公共子串的长度
//         System.out.println(findLongestCommonSubstring(str1, str2));
//     }
//
//     public static int findLongestCommonSubstring(String str1, String str2) {
//         int maxLength = 0;
//         int[][] dp = new int[str1.length() + 1][str2.length() + 1];
//
//         // 动态规划求解最长公共子串
//         for (int i = 1; i <= str1.length(); i++) {
//             for (int j = 1; j <= str2.length(); j++) {
//                 if (str1.charAt(i - 1) == str2.charAt(j - 1)) {
//                     dp[i][j] = dp[i - 1][j - 1] + 1;
//                     maxLength = Math.max(maxLength, dp[i][j]);
//                 }
//             }
//         }
//
//         return maxLength;
//     }
// }