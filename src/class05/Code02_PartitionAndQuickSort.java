package class05;

/**
 * @Author Agony
 * @Create 2023/8/10 14:48
 * @Version 1.0
 * <a href="https://leetcode.cn/problems/sort-colors/">颜色分类</a>
 */
public class Code02_PartitionAndQuickSort {

    public static int[] netherlandsFlag(int[] arr, int L, int R) {
        if (L > R) {
            return new int[]{-1, -1};
        }
        if (L == R) {
            return new int[]{L, R};
        }
        int less = L - 1;
        int more = R;
        int index = L;
        while (index < more) {
            if (arr[index] == arr[R]) {
                index++;
            } else if (arr[index] < arr[R]) {
                swap(arr, index, less + 1);
                less++;
                index++;
            } else {
                swap(arr, index, more - 1);
                more--;
            }
        }
        // 位于 R 的数一直没动过，所以要和more的位置交换，之后more的位置就是target的位置
        swap(arr, more, R);
        return new int[]{less + 1, more};
    }

    public static void swap(int[] arr, int p1, int p2) {
        int temp = arr[p1];
        arr[p1] = arr[p2];
        arr[p2] = temp;
    }


    // leetcode 提交这个代码即可
    public static void sortColor(int[] arr, int L, int R) {
        if (L >= R) {
            return;
        }
        int less = L - 1;
        int more = R + 1;
        int index = L;
        int target = 1;
        while (index < more) {
            if (arr[index] == target) {
                index++;
            } else if (arr[index] < target) {
                swap(arr, index, less + 1);
                less++;
                index++;
            } else {
                swap(arr, index, more - 1);
                more--;
            }
        }
    }

    public static void printArr(int[] arr) {
        for (int i : arr) {
            System.out.print(i + " ");
        }
        System.out.println();
    }

    // 快排2的时间复杂度为O(N^2)
    public static void quickSort2(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        process2(arr, 0, arr.length - 1);
    }

    // arr[L...R] 排有序，快排2.0方式
    public static void process2(int[] arr, int L, int R) {
        if (L >= R) {
            return;
        }
        // [ equalArea[0]  ,  equalArea[0]]
        int[] equalArea = netherlandsFlag(arr, L, R);
        process2(arr, L, equalArea[0] - 1);
        process2(arr, equalArea[1] + 1, R);
    }


    // 快排3的时间复杂度为O(N*longN)
    public static void quickSort3(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        process3(arr, 0, arr.length - 1);
    }

    public static void process3(int[] arr, int L, int R) {
        if (L >= R) {
            return;
        }
        swap(arr, L + (int) (Math.random() * (R - L + 1)), R);
        int[] equalArea = netherlandsFlag(arr, L, R);
        process3(arr, L, equalArea[0] - 1);
        process3(arr, equalArea[1] + 1, R);
    }

    public static void main(String[] args) {
        int[] arr = {2, 0, 2, 1, 1, 0};
        int[] arr1 = {1, 0, 2};
        int[] arr3 = {2, 0, 2, 1, 1, 0};

        // int arr2 = partition(arr3, 0, arr3.length - 1);
        sortColor(arr, 0, arr.length - 1);
        sortColor(arr1, 0, arr1.length - 1);
        sortColor(arr3, 0, arr3.length - 1);
        printArr(arr);
        printArr(arr1);
        printArr(arr3);
    }
}
