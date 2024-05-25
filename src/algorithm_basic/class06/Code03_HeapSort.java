package algorithm_basic.class06;

import java.util.Arrays;

/**
 * @Author Agony
 * @Create 2023/8/13 17:37
 * @Version 1.0
 */
public class Code03_HeapSort {

    public static void heapSort(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }

        // 首先让数组组成大根堆
        for (int i = 0; i < arr.length; i++) {
            heapInsert(arr, i);
        }
        //
        int heapSize = arr.length;
        // 让最大值和最后的值交换
        // 交换之后在heapfiy
        while (heapSize > 1) {
            swap(arr, 0, --heapSize);
            heapify(arr, 0, heapSize);
        }

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

    public static void printArr(int[] arr) {

        for (int i : arr) {
            System.out.print(i + " ");
        }
        System.out.println();
    }


    public static int[] generateArr(int maxSize, int maxValue) {
        int[] arr = new int[(int) ((maxSize + 1) * Math.random())];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) ((maxValue + 1) * Math.random()) - (int) ((maxValue) * Math.random());
        }
        return arr;
    }

    public static int[] copyArray(int[] arr) {
        if (arr == null) {
            return null;
        }
        int[] res = new int[arr.length];
        for (int i = 0; i < arr.length; i++) {
            res[i] = arr[i];
        }
        return res;
    }

    public static boolean isEqual(int[] arr1, int[] arr2) {
        if ((arr1 == null && arr2 != null) || (arr1 != null && arr2 == null)) {
            return false;
        }
        if (arr1 == null && arr2 == null) {
            return true;
        }
        if (arr1.length != arr2.length) {
            return false;
        }
        for (int i = 0; i < arr1.length; i++) {
            if (arr1[i] != arr2[i]) {
                return false;
            }
        }
        return true;
    }


    public static void main(String[] args) {
        // int[] ints = {5, 7, 3, 5, 8, 4};
        // // int[] ints = {7, 5, 8};
        // printArr(ints);
        // heapSort(ints);
        // printArr(ints);

        int maxSize = 10;
        int maxValue = 50;
        int testTimes = 1000;

        for (int i = 0; i < testTimes; i++) {
            int[] arr = generateArr(maxSize, maxValue);
            // printArr(arr);
            int[] copyArray = copyArray(arr);
            Arrays.sort(copyArray);
            // printArr(copyArray);
            heapSort(arr);
            // printArr(arr);
            // System.out.println("===================");
            if (!isEqual(copyArray, arr)) {
                System.out.println("Oops");
            }
        }
        System.out.println("finish");
    }
}
