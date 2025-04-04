package algorithm_journey.hwod;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * @author: Agony
 * @create: 2025/4/3 22:37
 * @describe: 第k个排列
 */
public class KthPermutation {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);


        int n = scanner.nextInt();
        int k = scanner.nextInt();

        boolean[] used = new boolean[n];

        ArrayList<String> list = new ArrayList<>();

        process(list, new StringBuilder(), used);

        System.out.println(list.get(k - 1));

    }


    public static void process(ArrayList<String> list, StringBuilder sb, boolean[] used) {

        if (sb.toString().length() == used.length) {
            list.add(sb.toString());
            return;
        }

        for (int i = 1; i <= used.length; i++) {
            if (!used[i - 1]) {
                sb.append(i);
                used[i - 1] = true;
                process(list, sb, used);
                used[i - 1] = false;
                // 移除最后一个 （刚刚添加的）
                sb.deleteCharAt(sb.length() - 1);
            }
        }


    }
}
