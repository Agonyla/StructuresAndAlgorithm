package algorithm_journey.class024;

/**
 * @author Agony
 * @create 2024/5/31 16:17
 * @describe: 之前 leetcode 上的代码 用堆完成
 */
public class RandomizedSelect2 {
    public static int findKthLargest(int[] nums, int k) {
        if (nums == null || nums.length < 1) {
            return Integer.MIN_VALUE;
        }
        for (int i = nums.length - 1; i >= 0; i--) {
            heapify(nums, i, nums.length);
        }
        int res = 0;
        int heapSize = nums.length;
        for (int i = 0; i < k; i++) {
            res = nums[0];
            swap(nums, 0, --heapSize);
            heapify(nums, 0, heapSize);
        }
        return res;
    }


    public static void heapInsert(int[] arr, int index) {
        while (arr[index] > arr[(index - 1) / 2]) {
            swap(arr, index, (index - 1) / 2);
            index = (index - 1) / 2;
        }
    }

    public static void heapify(int[] arr, int index, int heapSize) {
        int left = index * 2 + 1;
        while (left < heapSize) {
            int largest = left + 1 < heapSize && arr[left + 1] > arr[left] ? left + 1 : left;
            largest = arr[largest] > arr[index] ? largest : index;
            if (largest == index) {
                break;
            }
            swap(arr, index, largest);
            index = largest;
            left = index * 2 + 1;
        }
    }

    public static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}
