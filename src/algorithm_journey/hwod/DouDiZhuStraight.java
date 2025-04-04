package algorithm_journey.hwod;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * @author: Agony
 * @create: 2025/4/4 19:42
 * @describe: 斗地主之顺子
 */
public class DouDiZhuStraight {


    public static String[] arr = {"3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K", "A"};


    public static int getPlace(String str) {

        for (int i = 0; i < arr.length; i++) {
            if (arr[i].equals(str)) {
                return i;
            }
        }
        return -1;
    }


    public static void main(String[] args) {


        Scanner scanner = new Scanner(System.in);
        String[] pokers = scanner.nextLine().split(" ");


        // 统计频率
        int[] counts = new int[arr.length];


        for (String poker : pokers) {
            int index = getPlace(poker);
            if (index != -1) {
                counts[index]++;
            }
        }

        // 是否有顺子
        boolean flag = false;


        for (int i = 0; i < 8; i++) {

            // i 到 i+j 是否有五张连续的
            boolean yes = true;

            ArrayList<String> list = new ArrayList<>();
            // 判断是否存在连续五张牌
            for (int j = 0; j < 5; j++) {
                if (counts[i + j] == 0) {
                    yes = false;
                    break;
                }
            }

            if (yes) {
                for (int j = 0; j < 13; j++) {
                    if (i + j >= arr.length || counts[i + j] == 0) {
                        break;
                    }
                    counts[i + j]--;
                    list.add(arr[i + j]);
                }
                System.out.println(String.join(" ", list));
                flag = true;
            }
        }

        if (!flag) {
            System.out.println("No");
        }

    }
}
