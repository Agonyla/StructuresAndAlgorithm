package class04;

/**
 * @Author Agony
 * @Create 2023/8/7 12:40
 * @Version 1.0
 * 归并排序
 */
public class Code01_MergeSort {
    public static void process(int[] arr, int L, int R) {

        if (L == R) {
            return;
        }
        int mid = (L + R) >> 1;
        process(arr, L, mid);
        process(arr, mid + 1, R);
        merge(arr, L, mid, R);
    }

    public static void merge(int[] arr, int L, int mid, int R) {
        int i = 0;
        int[] help = new int[R - L + 1];
        int p1 = L;
        int p2 = mid + 1;
        while (p1 <= mid && p2 <= R) {
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
    }

    public static void main(String[] args) {
        int[] arr = {2, 4, 1, 7, 4, 2, 5, 9};
        process(arr, 0, arr.length - 1);

        for (int i : arr) {
            System.out.print(i + " ");
        }
        System.out.println(" ");
    }
}
