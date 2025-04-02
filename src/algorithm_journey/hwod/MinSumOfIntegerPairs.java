package algorithm_journey.hwod;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

/**
 * @author: Agony
 * @create: 2025/4/2 20:34
 * @describe: 整数对最小和
 * 给定两个整数数组array1、array2，数组元素按升序排列。
 * 假设从array1、array2中分别取出一个元素可构成一对元素，现在需要取出k对元素，
 * 并对取出的所有元素求和，计算和的最小值。
 * 注意：
 * 两对元素如果对应于array1、array2中的两个下标均相同，则视为同一对元素。
 * 输入描述
 * 输入两行数组array1、array2，每行首个数字为数组大小size(0 < size <= 100);
 * 0 < array1[i] <= 1000
 * 0 < array2[i] <= 1000
 * 接下来一行为正整数k
 * 0 < k <= array1.size() * array2.size()
 * 输出描述
 * 满足要求的最小和
 * 用例1
 * 输入：
 * <p>
 * 3 1 1 2
 * 3 1 2 3
 * 2
 * <p>
 * 输出：
 * <p>
 * 4
 */
public class MinSumOfIntegerPairs {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        // 第一个数组
        int m = scanner.nextInt();
        ArrayList<Integer> list1 = new ArrayList<>();
        for (int i = 0; i < m; i++) {
            list1.add(scanner.nextInt());
        }

        // 第二个数组
        int n = scanner.nextInt();
        ArrayList<Integer> list2 = new ArrayList<>();
        for (int i = 0; i < m; i++) {
            list2.add(scanner.nextInt());
        }

        // k对
        int k = scanner.nextInt();

        ArrayList<Integer> list = new ArrayList<>();

        for (Integer i : list1) {
            for (Integer j : list2) {
                list.add(i + j);
            }
        }

        Collections.sort(list);

        int ans = 0;
        for (int i = 0; i < k; i++) {
            ans += list.get(i);
        }
        System.out.println(ans);
    }

}




























