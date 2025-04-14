package algorithm_journey.hwod;

import java.util.Scanner;

/**
 * @author: Agony
 * @create: 2025/4/14 11:16
 * @describe: 求小球落地5次后所经历的路程和第5次反弹的高度
 * <a href="https://www.nowcoder.com/practice/2f6f9339d151410583459847ecc98446?tpId=37&rp=1&sourceUrl=%2Fexam%2Foj%2Fta%3FtpId%3D37&difficulty=3&judgeStatus=&tags=&title=&gioEnter=menu">求小球落地5次后所经历的路程和第5次反弹的高度</a>
 */
public class HJ38 {


    public static void main(String[] args) {


        Scanner scanner = new Scanner(System.in);


        double h = scanner.nextDouble();
        double travel = h;

        double[] travels = new double[5];

        for (int i = 0; i < 5; i++) {
            travel /= 2;
            travels[i] = travel;
        }

        double ans = 0;

        for (int i = 0; i < 4; i++) {
            ans += travels[i];
        }
        System.out.println(h + 2 * ans);
        System.out.println(travels[4]);

    }
}
