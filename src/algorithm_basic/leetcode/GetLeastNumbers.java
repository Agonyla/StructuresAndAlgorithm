package algorithm_basic.leetcode;

/**
 * @Author Agony
 * @Create 2023/8/13 19:30
 * @Version 1.0
 * <a href="https://leetcode.cn/problems/zui-xiao-de-kge-shu-lcof/">最小的k个数</a>
 */
public class GetLeastNumbers {

    public static int[] getLeastNumbers(int[] arr, int k) {
        if (arr.length == k) {
            return arr;
        }
        if (arr == null || arr.length < 1 || k == 0) {
            return new int[0];
        }

        int[] res = new int[k];
        int i = 0;

        for (int j = arr.length - 1; j >= 0; j--) {
            heapify(arr, j, arr.length);
        }
        int heapSize = arr.length;
        while (heapSize > 1) {
            res[i++] = arr[0];
            swap(arr, 0, --heapSize);
            heapify(arr, 0, heapSize);
            if (i == k) {
                break;
            }
        }
        return res;
    }

    public static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public static void heapInsert(int[] arr, int index) {
        while (arr[index] < arr[(index - 1) / 2]) {
            swap(arr, index, (index - 1) / 2);
            index = (index - 1) / 2;
        }
    }

    public static void heapify(int[] arr, int index, int heapSize) {
        int left = 2 * index + 1;
        while (left < heapSize) {
            int smallest = left + 1 < heapSize && arr[left + 1] < arr[left] ? left + 1 : left;
            smallest = arr[smallest] < arr[index] ? smallest : index;
            if (smallest == index) {
                break;
            }
            swap(arr, index, smallest);
            index = smallest;
            left = 2 * index + 1;
        }
    }

    public static void main(String[] args) {
        //     arr = [3,2,1], k = 2

        // int[] arr = {3, 2, 1};
        // int k = 2;
        int[] arr = {0, 1, 2, 1};
        int k = 1;
        int[] res = getLeastNumbers(arr, k);
        for (int re : res) {
            System.out.print(re + " ");
        }
    }
}
