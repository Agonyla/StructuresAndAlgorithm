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

    /**
     * 产生一个长度为 n 最大值为 v 的随机数组
     *
     * @param n 数组长度
     * @param v 数组最大值
     * @return 随机数组
     */
    public static int[] randomArray(int n, int v) {
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = (int) (Math.random() * v) + 1;
        }
        return arr;
    }

    /**
     * 拷贝数组
     *
     * @param arr
     * @return
     */
    public static int[] copyArray(int[] arr) {
        int length = arr.length;
        int[] ans = new int[length];
        System.arraycopy(arr, 0, ans, 0, length);
        return ans;
    }


    /**
     * 验证两个数组是否相同
     *
     * @param arr1
     * @param arr2
     * @return
     */
    public static boolean sameArray(int[] arr1, int[] arr2) {
        int length = arr1.length;
        for (int i = 0; i < length; i++) {
            if (arr1[i] != arr2[i]) {
                return false;
            }
        }
        return true;
    }
}
