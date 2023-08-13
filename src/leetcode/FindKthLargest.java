package leetcode;

/**
 * @Author Agony
 * @Create 2023/8/13 19:46
 * @Version 1.0
 */
public class FindKthLargest {
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

    public static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
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

    public static void main(String[] args) {
        int[] arr = {3, 2, 3, 1, 2, 4, 5, 5, 6};
        int k = 4;
        int res = findKthLargest(arr, k);
        System.out.println(res);
    }
}
