package algorithm_journey.hwod;

import java.util.Scanner;

/**
 * @author: Agony
 * @create: 2025/4/11 15:19
 * @describe: 合唱队
 * <a href="https://www.nowcoder.com/practice/6d9d69e3898f45169a441632b325c7b4?tpId=37&rp=1&sourceUrl=%2Fexam%2Foj%2Fta%3FtpId%3D37&difficulty=3&judgeStatus=&tags=&title=&gioEnter=menu">合唱队</a>
 */
public class HJ24 {

    public static void main(String[] args) {


        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();

        int[] students = new int[n];

        for (int i = 0; i < n; i++) {
            students[i] = scanner.nextInt();
        }

        System.out.println(process(students, n));

    }


    /**
     * @param students
     * @param n        学生数量
     * @return
     */
    public static int process(int[] students, int n) {

        if (n <= 0) {
            return 0;
        }
        int maxLength = 0;

        int[] dpUp = new int[n];
        int[] dpDown = new int[n];


        for (int i = 0; i < n; i++) {

            int upLength = up(students, i, dpUp);
            int downLength = down(students, i, n, dpDown);
            maxLength = Math.max(maxLength, upLength + downLength - 1);

        }


        return n - maxLength;

    }


    /**
     * 返回从 0 到 end 位置students是递增的最大长度
     *
     * @param students
     * @param end
     * @return
     */
    private static int up(int[] students, int end, int[] dp) {

        if (end == 0) {
            return 1;
        }
        if (dp[end] != 0) {
            return dp[end];
        }
        int maxLength = 1;
        for (int i = 0; i < end; i++) {
            if (students[i] < students[end]) {
                int length = 1 + up(students, i, dp);
                maxLength = Math.max(maxLength, length);
            }
        }
        dp[end] = maxLength;
        return dp[end];
    }

    /**
     * 返回从 start 到 n 位置 students 是递减的最大长度
     *
     * @param students
     * @param start
     * @param n
     * @return
     */
    private static int down(int[] students, int start, int n, int[] dp) {

        if (start == n - 1) {
            return 1;
        }
        if (dp[start] != 0) {
            return dp[start];
        }
        int maxLength = 1;
        for (int i = start + 1; i < n; i++) {
            if (students[start] > students[i]) {
                int length = 1 + down(students, i, n, dp);
                maxLength = Math.max(maxLength, length);
            }
        }
        dp[start] = maxLength;
        return dp[start];
    }

}
