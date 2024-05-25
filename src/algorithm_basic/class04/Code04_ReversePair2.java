package algorithm_basic.class04;

/**
 * @Author Agony
 * @Create 2023/8/9 10:40
 * @Version 1.0
 * 从前往后遍历
 */
public class Code04_ReversePair2 {
    public static int reversePair(int[] arr) {

        if (arr == null || arr.length < 2) {
            return 0;
        }

        return mergeSort(arr, 0, arr.length - 1);
    }

    public static int mergeSort(int[] arr, int L, int R) {

        if (L == R) {
            return 0;
        }
        int mid = (L + R) >> 1;
        return mergeSort(arr, L, mid)
                + mergeSort(arr, mid + 1, R)
                + merge(arr, L, mid, R);
    }

    public static int merge(int[] arr, int L, int mid, int R) {
        int i = 0;
        int[] help = new int[R - L + 1];
        int p1 = L;
        int p2 = mid + 1;
        int res = 0;
        while (p1 <= mid && p2 <= R) {
            res += arr[p1] > arr[p2] ? (mid - p1 + 1) : 0;
            help[i++] = arr[p1] <= arr[p2] ? arr[p1++] : arr[p2++];
        }
        while (p1 <= mid) {
            help[i++] = arr[p1++];
        }

        while (p2 <= R) {
            help[i++] = arr[p2++];
        }
        for (int j = 0; j < help.length; j++) {
            arr[L + j] = help[j];
        }
        return res;
    }
}
