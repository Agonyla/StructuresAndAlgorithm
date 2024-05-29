package algorithm_journey.class021;

import algorithm_journey.utils.MathUtils;

/**
 * @author Agony
 * @create 2024/5/29 19:24
 * @describe: 给你一个整数数组 nums，请你将该数组升序排列。
 * @link: <a href="https://leetcode.cn/problems/sort-an-array/description/">排序数组</a>
 */

public class MergeSort {

    public static void main(String[] args) {
        int[] arr = {5, 1, 2, 6, 4, 3};
        MathUtils.printArr(arr);
        MathUtils.printArr(sortArray(arr));
        int[] nums = new int[arr.length];
        int a = 0;
        while (a <= 5) {
            nums[a] = arr[a++];
            // nums[a++] = arr[a] 这样写有问题，会 nums[0] = arr[1]
        }
        MathUtils.printArr(nums);

        arr = new int[]{5, 1, 2, 6, 4, 3};
        MathUtils.printArr(arr);
        mergeSort2(arr);
        MathUtils.printArr(arr);


    }

    public static int MAX = 50001;

    public static int[] help = new int[MAX];

    public static int[] sortArray(int[] nums) {
        if (nums == null) {
            return null;
        }
        if (nums.length > 1) {
            mergeSort(nums, 0, nums.length - 1);
        }
        return nums;
    }

    /**
     * 递归归并排序
     *
     * @param arr
     * @param l
     * @param r
     */
    public static void mergeSort(int[] arr, int l, int r) {
        if (l == r) {
            return;
        }
        int m = (l + r) / 2;
        mergeSort(arr, l, m);
        mergeSort(arr, m + 1, r);
        merge(arr, l, m, r);
    }


    /**
     * 非递归归并排序
     *
     * @param arr
     */
    public static void mergeSort2(int[] arr) {
        int n = arr.length;
        for (int l, m, r, step = 1; step < n; step <<= 1) {
            l = 0;
            while (l < n) {
                m = l + step - 1;
                if (m + 1 >= n) {
                    break;
                }
                r = Math.min(l + (step << 1) - 1, n - 1);
                merge(arr, l, m, r);
                l = r + 1;
            }
        }
    }

    /**
     * 左右数组合并
     *
     * @param arr 数组
     * @param l   数组左边界下标
     * @param m   数组中间下标
     * @param r   数组有边界下标
     */
    public static void merge(int[] arr, int l, int m, int r) {
        // 左数组指针
        int a = l;
        // 右数组指针
        int b = m + 1;
        // help数组指针
        int i = l;
        // 左右两个数组谁小先放谁
        while (a <= m && b <= r) {
            help[i++] = arr[a] <= arr[b] ? arr[a++] : arr[b++];
        }
        // 已下两个 while 只会执行一个
        // 左数组还有剩余
        while (a <= m) {
            help[i++] = arr[a++];
        }
        // 右数组还有剩余
        while (b <= r) {
            help[i++] = arr[b++];
        }
        // 把help有序数组拷贝到arr
        while (l <= r) {
            arr[l] = help[l++];
        }
    }
}



