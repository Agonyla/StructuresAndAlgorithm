package algorithm_journey.class006;

import algorithm_journey.utils.MathUtils;

import java.util.Arrays;

/**
 * @author Agony
 * @create 2024/5/22 21:02
 * @describe: 二分搜索 (在有序数组中找 <= num 的最右侧位置)
 */
public class Code03_FindRight {

    public static void main(String[] args) {
        int N = 100;
        int V = 200;
        int times = 40000;

        System.out.println("algorithm_basic.test begin");
        for (int i = 0; i < times; i++) {

            int n = (int) (Math.random() * N);
            int[] arr = MathUtils.randomArray(n, V);
            Arrays.sort(arr);
            int num = (int) (Math.random() * V);
            int ans1 = right(arr, num);
            int ans2 = binarySearch(arr, num);
            if (ans1 != ans2) {
                System.out.println("Oops");
                MathUtils.printArr(arr);
                System.out.println("num = " + num);
                System.out.println("ans1 = " + ans1);
                System.out.println("ans2 = " + ans2);
                break;
            }
            // MathUtils.printArr(arr);
            // System.out.println("num = " + num);
            // System.out.println("ans1 = " + ans1);
        }
        System.out.println("algorithm_basic.test end");
    }


    /**
     * 暴力遍历
     *
     * @param arr 有序数组
     * @param num 查找的数字
     * @return <= num 的最右侧位置 找不到返回 -1
     */
    public static int right(int[] arr, int num) {
        if (arr == null || arr.length == 0) {
            return -1;
        }
        for (int i = arr.length - 1; i >= 0; i--) {
            if (arr[i] <= num) {
                return i;
            }
        }
        return -1;
    }


    /**
     * 二分搜索
     *
     * @param arr 有序数组
     * @param num 查找的数字
     * @return <= num 的最右侧位置 找不到返回 -1
     */
    public static int binarySearch(int[] arr, int num) {
        if (arr == null || arr.length == 0) {
            return -1;
        }
        int ans = -1;
        int l = 0;
        int r = arr.length - 1;
        int mid = 0;
        while (l <= r) {
            mid = l + ((r - l) >> 1);
            if (arr[mid] <= num) {
                ans = mid;
                l = mid + 1;
            } else {
                r = mid - 1;
            }
        }
        return ans;
    }
}
