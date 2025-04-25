package algorithm_journey.test;

import java.util.Arrays;
import java.util.Scanner;

/**
 * @author: Agony
 * @create: 2025/4/24 19:17
 * @describe:
 */
public class Main2 {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        int n = scanner.nextInt();

        scanner.nextLine();

        String[] arr = scanner.nextLine().split(" ");

        // 按照长度生序，长度一样则按照字典序生序
        Arrays.sort(arr, (a, b) -> a.length() == b.length() ? a.compareTo(b) : a.length() - b.length());
        System.out.println(Arrays.toString(arr));

    }
}
