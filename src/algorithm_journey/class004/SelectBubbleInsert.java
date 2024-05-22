package algorithm_journey.class004;

import static algorithm_journey.utils.MathUtils.printArr;
import static algorithm_journey.utils.MathUtils.swap;

/**
 * @author Agony
 * @create 2024/5/22 10:39
 */
public class SelectBubbleInsert {


    public static void main(String[] args) {
        int[] arr = {5, 3, 4, 1, 2};
        insertionSort(arr);
        // bubbleSort(arr);
        // selectionSort(arr);
        printArr(arr);
    }

    /**
     * 选择排序
     * 把最小值放到最右边
     * 0 - n-1 上找到最小值放到最左边
     * 1 - n-1 上找到最小值放到最左边
     * ...
     *
     * @param arr
     */
    public static void selectionSort(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        for (int i = 0; i < arr.length - 1; i++) {
            // 先认为最小值在 i 位置
            int minIndex = i;
            // 将 i+1 到 n-1 上的数都和 minIndex 位置比较，找到最小值
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[minIndex] > arr[j]) {
                    minIndex = j;
                }
            }
            // 将最小值和 i 位置交换
            swap(arr, i, minIndex);
        }

    }

    /**
     * 冒泡排序
     * 相邻两数字比较，最大值逐渐比较到最右边
     * 0 - n-1 上相邻两数比较最大值向右冒泡
     * 0 - n-2 上相邻两数比较最大值向右冒泡
     * ...
     *
     * @param arr
     */
    public static void bubbleSort(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        for (int i = 0; i < arr.length - 1; i++) {
            for (int j = 0; j < arr.length - 1 - i; j++) {
                if (arr[j] > arr[j + 1]) {
                    swap(arr, j, j + 1);
                }
            }
        }
    }

    /**
     * 插入排序 (类似扑克牌，从左到右递增，把牌从右到左插入到一个合适的位置)
     * 让数组从 0 - n -1 范围上有序
     * 0 - 0 天然有序
     * 0 - 1 范围有序  拿 1 位置和 0 位置比较，1 位置小于 0 位置则交换
     * 0 - 2 范围有序  拿 2 位置和 1 位置比较，2 位置小于 1 位置则交换，再拿 1 位置和 0 位置比较
     * 0 - n-1...
     * 直到比较位来到 0
     *
     * @param arr
     */
    public static void insertionSort(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        // 直接从 1 开始
        for (int i = 1; i < arr.length; i++) {

            // 0 - i 位置有序，j 和前一位比较
            for (int j = i; j > 0 && arr[j] < arr[j - 1]; j--) {
                swap(arr, j, j - 1);
            }
        }

        // 外循环控制循环次数
        // 循环次数
        // for (int i = 0; i < arr.length - 1; i++) {
        //     // 从 0 - 1 有序开始，j 和 前一位置比较
        //     for (int j = i + 1; j > 0 && arr[j] < arr[j - 1]; j--) {
        //         swap(arr, j, j - 1);
        //     }
        // }
    }


}
