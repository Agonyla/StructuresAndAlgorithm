package algorithm_basic.class06;

import java.util.Arrays;
import java.util.Comparator;

/**
 * @Author Agony
 * @Create 2023/8/13 18:50
 * @Version 1.0
 * 降序排序
 */
public class Code04_HeapSort {


    public static void heapSort(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        // 形成小根堆
        for (int i = 0; i < arr.length; i++) {
            heapInsert(arr, i);
        }

        int heapSize = arr.length;
        while (heapSize > 1) {
            swap(arr, 0, --heapSize);
            heapify(arr, 0, heapSize);
        }
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

    public static Integer[] intToIntegerArray(int[] intArray) {
        Integer[] integerArray = new Integer[intArray.length];

        for (int i = 0; i < intArray.length; i++) {
            integerArray[i] = Integer.valueOf(intArray[i]);
        }

        return integerArray;
    }

    public static void main(String[] args) {

        // int[] arr = {7, 5, 8, 4, 9, 2, 1};
        // printArr(arr);
        // heapSort(arr);
        // printArr(arr);

        int maxSize = 10;
        int maxValue = 50;
        int testTimes = 10;

        for (int i = 0; i < testTimes; i++) {
            int[] arr = generateArr(maxSize, maxValue);
            printArr(arr);
            int[] copyArray = copyArray(arr);
            Integer[] copyArr = intToIntegerArray(copyArray);
            Arrays.sort(copyArr, new Comparator<Integer>() {
                @Override
                public int compare(Integer o1, Integer o2) {
                    return o2 - o1;
                }
            });

            for (int j = 0; j < copyArr.length; j++) {
                System.out.print(copyArr[j] + " ");
            }
            System.out.println();

            heapSort(arr);
            printArr(arr);
            System.out.println("==============");

        }
    }
}
