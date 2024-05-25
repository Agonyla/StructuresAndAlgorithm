package algorithm_basic.class04;

/**
 * @Author Agony
 * @Create 2023/8/9 10:01
 * @Version 1.0
 * 从后往前遍历
 */
public class Code03_ReverserPair {
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
        int[] help = new int[R - L + 1];
        int i = help.length - 1;
        int res = 0;
        int P1 = mid;
        int P2 = R;
        while (P1 >= L && P2 >= mid + 1) {
            res += arr[P1] > arr[P2] ? (P2 - mid) : 0;
            help[i--] = arr[P1] > arr[P2] ? arr[P1--] : arr[P2--];
        }
        while (P1 >= L) {
            help[i--] = arr[P1--];
        }
        while (P2 >= mid + 1) {
            help[i--] = arr[P2--];
        }

        for (int j = 0; j < help.length; j++) {
            arr[L + j] = help[j];
        }
        return res;
    }

    public static void main(String[] args) {

        int[] arr = {5, 2, 6, 1};
        int res = reversePair(arr);
        System.out.println(res);
    }

}
