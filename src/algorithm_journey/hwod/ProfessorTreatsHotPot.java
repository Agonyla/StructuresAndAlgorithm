package algorithm_journey.hwod;

import java.util.Arrays;
import java.util.Scanner;

/**
 * @author: Agony
 * @create: 2025/4/3 21:42
 * @describe: 导师请吃火锅
 */
public class ProfessorTreatsHotPot {

    public static void main(String[] args) {


        Scanner scanner = new Scanner(System.in);

        int n = scanner.nextInt();

        int cd = scanner.nextInt();

        int[] dish = new int[n];

        for (int i = 0; i < n; i++) {
            int start = scanner.nextInt();
            int end = scanner.nextInt();
            dish[i] = start + end;
        }


        Arrays.sort(dish);


        int ans = 1;
        int lastTime = dish[0];

        for (int i = 1; i < n; i++) {

            if (dish[i] - lastTime >= cd) {
                ans++;
                lastTime = dish[i];
            }
        }

        System.out.println(ans);
    }
}
