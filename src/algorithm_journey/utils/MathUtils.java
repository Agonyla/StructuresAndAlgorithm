package algorithm_journey.utils;

import java.util.Arrays;

/**
 * 工具类
 *
 * @author Agony
 * @create 2024/5/22 10:58
 */
public class MathUtils {


    /**
     * 交换数组两个位置的数
     *
     * @param arr
     * @param i
     * @param j
     */
    public static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    /**
     * 打印数组
     *
     * @param arr
     */
    public static void printArr(int[] arr) {
        Arrays.stream(arr).forEach(num -> System.out.print(num + " "));
        System.out.println();
    }
}
