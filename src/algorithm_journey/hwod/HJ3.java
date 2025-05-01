package algorithm_journey.hwod;

import java.util.HashSet;
import java.util.Scanner;

/**
 * @author: Agony
 * @create: 2025/5/1 16:12
 * @describe:
 */
public class HJ3 {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        int n = scanner.nextInt();
        HashSet<Integer> set = new HashSet<>();
        for (int i = 0; i < n; i++) {
            set.add(scanner.nextInt());
        }
    }

    // todo

}
