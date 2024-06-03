package algorithm_journey.class025;

import java.util.Arrays;

/**
 * 堆排序
 *
 * @author Agony
 * @create 2024/6/3 09:41
 * @describe: 给你一个整数数组 nums，请你将该数组升序排列。
 * @link: <a href="https://leetcode.cn/problems/sort-an-array/description/">堆排序</a>
 */
public class HeapSort {

    public static void main(String[] args) {
        // int[] arr = {5, 1, 1, 2, 0, 0};
        // int[] arr = {5, 2, 3, 1};
        int[] arr = {-74, 48, -20, 2, 10, -84, -5, -9, 11, -24, -91, 2, -71, 64, 63, 80, 28, -30, -58, -11, -44, -87, -22, 54, -74, -10, -55, -28, -46, 29, 10, 50, -72, 34, 26, 25, 8, 51, 13, 30, 35, -8, 50, 65, -6, 16, -2, 21, -78, 35, -13, 14, 23, -3, 26, -90, 86, 25, -56, 91, -13, 92, -25, 37, 57, -20, -69, 98, 95, 45, 47, 29, 86, -28, 73, -44, -46, 65, -84, -96, -24, -12, 72, -68, 93, 57, 92, 52, -45, -2, 85, -63, 56, 55, 12, -85, 77, -39};
        int[] sortArray = sortArray(arr);
        Arrays.stream(sortArray).forEach(num -> System.out.print(num + " "));

    }


    public static int[] sortArray(int[] arr) {
        if (arr == null || arr.length < 2) {
            return arr;
        }
        heapSort(arr);
        return arr;
    }


    /**
     * 堆排序
     * 从头到顶，每个数都 heapInsert 一下
     * -> 头节点是最大值
     * 头节点和尾节点交换，size--
     * -> 尾节点是最大值
     * 头节点位置 heapify 一下
     * -> 头节点又是最大值
     * 往复做，直到size==1
     *
     * @param arr
     */
    public static void heapSort(int[] arr) {
        int size = arr.length;
        for (int i = 0; i < size; i++) {
            heapInsert(arr, i);
        }
        while (size > 1) {
            swap(arr, 0, --size);
            heapify(arr, 0, size);
        }
    }

    /**
     * i 位置的数，向上调整大根堆
     * i 位置的数，父节点: (i - 1) / 2
     * 如果当前节点大于父节点就交换
     *
     * @param arr 数组
     * @param i   i 位置下标
     */
    public static void heapInsert(int[] arr, int i) {
        while (arr[i] > arr[(i - 1) / 2]) {
            swap(arr, i, (i - 1) / 2);
            i = (i - 1) / 2;
        }
    }

    /**
     * i 位置的数，向下调整大根堆
     * i 位置的数，左节点: i * 2 + 1，右节点: i * 2 + 2
     * 如果当前节点小于子节点就交换
     *
     * @param arr  数组
     * @param i    i 位置下标
     * @param size 数组长度
     */
    public static void heapify(int[] arr, int i, int size) {
        int left = i * 2 + 1;
        while (left < size) {
            int right = left + 1;
            // 找到左右节点中较大的那个节点
            int bigger = right < size && arr[left] < arr[right] ? right : left;
            if (arr[i] >= arr[bigger]) {
                break;
            } else {
                swap(arr, i, bigger);
                i = bigger;
                left = i * 2 + 1;
            }
        }
    }


    public static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

}



















