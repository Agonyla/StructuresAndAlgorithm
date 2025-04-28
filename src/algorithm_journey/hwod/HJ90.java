package algorithm_journey.hwod;

import java.util.Scanner;

/**
 * @author: Agony
 * @create: 2025/4/28 12:07
 * @describe: 合法IP
 * <a href="https://www.nowcoder.com/practice/995b8a548827494699dc38c3e2a54ee9?tpId=37&rp=1&sourceUrl=%2Fexam%2Foj%2Fta%3FtpId%3D37&difficulty=3&judgeStatus=&tags=&title=&gioEnter=menu">合法IP</a>
 */
public class HJ90 {


    public static void main(String[] args) {


        Scanner scanner = new Scanner(System.in);
        String[] ips = scanner.nextLine().split("\\.");
        scanner.close();

        if (ips.length != 4) {
            System.out.println("NO");
            return;
        }

        for (int i = 0; i < 4; i++) {
            boolean result = isLegal(ips[i]);
            if (!result) {
                System.out.println("NO");
                return;
            }
        }
        System.out.println("YES");


    }

    public static boolean isLegal(String ip) {
        if (ip.equals("0")) {
            return true;
        }
        if (ip.startsWith("0") || ip.startsWith("+")) {
            return false;
        }
        int num = 0;

        try {
            num = Integer.parseInt(ip);
        } catch (NumberFormatException e) {
            return false;
        }

        return num <= 255 && num >= 0;

    }
}
