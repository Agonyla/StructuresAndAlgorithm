package algorithm_journey.class023;

import algorithm_journey.utils.MathUtils;

/**
 * 快速排序
 *
 * @author Agony
 * @create 2024/5/31 10:02
 * @describe: 给你一个整数数组 nums，请你将该数组升序排列。
 * @link: <a href="https://leetcode.cn/problems/sort-an-array/description/">快速排序</a>
 */
public class QuickSort {

    public static void main(String[] args) {
        int[] arr = {4, 1, 5, 6, 4, 3, 8};
        int[] nums = sortArray(arr);
        MathUtils.printArr(nums);
    }

    /**
     * 数组排序
     *
     * @param arr 数组
     * @return 有序数组
     */
    public static int[] sortArray(int[] arr) {
        if (arr.length > 1) {
            quickSort(arr, 0, arr.length - 1);
        }
        return arr;
    }

    public static int first;
    public static int last;

    /**
     * 快排
     * 在数组中找一个随机数，
     * 小于该数的放左边
     * 大于该数的放右边
     * 等于该数的全放在中间
     * 再在左右两边调用递归
     * <p>
     * 时间复杂度 o(n * logn)
     * 空间复杂度 O(logn)
     *
     * @param arr 数组
     * @param l   左边界下标
     * @param r   右边界下标
     */
    public static void quickSort(int[] arr, int l, int r) {
        if (l >= r) {
            return;
        }
        int x = arr[l + (int) (Math.random() * (r + 1 - l))];
        partition(arr, l, r, x);
        int left = first;
        int right = last;
        quickSort(arr, l, left - 1);
        quickSort(arr, right + 1, r);
    }

    /**
     * 把数组按照传入值划分
     * 小于x放左边
     * 大于x放右边
     * 并将等于x的所有值放中间
     * 用两个静态变量记录等于x值的范围
     * <p>
     * 流程:
     * 小于x的边界 a
     * 大于x的边界 b
     * 遍历指针 i
     * arr[i] < x 时 swap(a,i), a++, i++;
     * arr[i] == x 时候 i++;
     * arr[i] > x 时 swap(b,i), b--;
     *
     * @param arr 数组
     * @param l   左边界下标
     * @param r   右边界下标
     * @param x   传入值（数组上的一个随机位置值）
     */
    public static void partition(int[] arr, int l, int r, int x) {
        int a = l;
        int b = r;
        int i = l;
        while (i <= b) {
            if (arr[i] < x) {
                swap(arr, a, i);
                a++;
                i++;
            } else if (arr[i] > x) {
                swap(arr, b, i);
                b--;
            } else {
                i++;
            }
        }
        first = a;
        last = b;
    }


    public static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

}


























