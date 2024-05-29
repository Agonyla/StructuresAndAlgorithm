package algorithm_journey.class006;

import algorithm_journey.utils.MathUtils;

import java.util.Arrays;

/**
 * @author Agony
 * @create 2024/5/22 20:13
 * @describe: 二分搜索 (在有序数组确定num存在还是不存在)
 */
public class Code01_FindNumber {

    public static void main(String[] args) {

        int N = 100;
        int V = 1000;
        int times = 10000;

        System.out.println("test begin");
        for (int i = 0; i < times; i++) {

            int n = (int) (Math.random() * N);
            int[] arr = MathUtils.randomArray(n, V);
            Arrays.sort(arr);
            int num = (int) (Math.random() * N);
            boolean ans1 = binarySearch(arr, num);
            boolean ans2 = right(arr, num);
            if (ans1 != ans2) {
                System.out.println("Oops");
                break;
            }
        }
        System.out.println("test end");
    }


    /**
     * 二分搜索
     *
     * @param arr 数组 （要求有序）
     * @param num 查找数字
     * @return 返回查找数字所在下标
     */
    public static boolean binarySearch(int[] arr, int num) {

        if (arr == null || arr.length == 0) {
            return false;
        }
        int L = 0;
        int R = arr.length - 1;
        int mid = 0;
        while (L <= R) {
            // mid = (L + R) >> 1  L + R 可能会越界
            // mid = L + (R - L) >> 1 不能写成这样⚠️ 运算先后顺序有误
            mid = L + ((R - L) >> 1);
            if (num < arr[mid]) {
                R = mid - 1;
            } else if (arr[mid] < num) {
                L = mid + 1;
            } else {
                return true;
            }
        }
        return false;
    }

    /**
     * 暴力遍历
     *
     * @param arr
     * @param num
     * @return
     */
    public static boolean right(int[] arr, int num) {

        if (arr == null || arr.length == 0) {
            return false;
        }
        int ans = -1;
        for (int j : arr) {
            if (j == num) {
                return true;
            }
        }
        return false;
    }

}
