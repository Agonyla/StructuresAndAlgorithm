package algorithm_journey.class023;

import algorithm_journey.utils.MathUtils;

/**
 * @author Agony
 * @create 2024/5/31 11:32
 */
public class QuickSort2 {
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
            quickSort2(arr, 0, arr.length - 1);
        }
        return arr;
    }

    /**
     * 快排
     * 在数组中找一个随机数，
     * 小于该数的放左边
     * 大于该数的放右边
     * 并且返回该数的下标
     * 再在该下标的左右两边调用递归
     * <p>
     * 时间复杂度 o(n^2)
     * 空间复杂度 o(n)
     *
     * @param arr 数组
     * @param l   左边界下标
     * @param r   右边界下标
     */
    public static void quickSort2(int[] arr, int l, int r) {
        // 这里不能仅仅是等于
        // 当随机数的下标来到数组边界的时候
        // 会出现 l > r 的情况
        if (l >= r) {
            return;
        }
        int x = arr[l + (int) (Math.random() * (r + 1 - l))];
        int m = partition2(arr, l, r, x);
        quickSort2(arr, l, m - 1);
        quickSort2(arr, m + 1, r);
    }

    /**
     * 把数组按照传入值划分
     * 小于x放左边
     * 大于x放右边
     * 并返回x坐在下标
     * <p>
     * 流程:
     * 小于x的边界 a
     * 遍历指针 i
     * arr[i] <= x 时 swap(a,i), a++, i++;
     * 记录一个 arr[a] == x 时的值，最后交换一下
     * arr[i] > x 时 a, i++;
     *
     * @param arr 数组
     * @param l   左边界下标
     * @param r   右边界下标
     * @param x   传入值（数组上的一个随机位置值）
     * @return 返回该值的下标
     */
    public static int partition2(int[] arr, int l, int r, int x) {

        int a = l;
        int xi = 0;
        for (int i = l; i <= r; i++) {
            if (arr[i] <= x) {
                swap(arr, a, i);
                // 这里是 arr[a]
                // arr[i] 可能来到 > x 的数
                if (arr[a] == x) {
                    xi = a;
                }
                a++;
            }
        }
        swap(arr, xi, a - 1);
        return a - 1;
    }

    public static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}
