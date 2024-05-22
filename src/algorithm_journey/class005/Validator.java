package algorithm_journey.class005;

import static algorithm_journey.class004.SelectBubbleInsert.*;
import static algorithm_journey.utils.MathUtils.*;

/**
 * 对数器
 *
 * @author Agony
 * @create 2024/5/22 15:42
 */
public class Validator {


    public static void main(String[] args) {

        // 数组长度
        int N = 100;
        // 数组最大值
        int V = 1000;
        // 循环次数
        int times = 10000;

        System.out.println("测试开始");
        for (int i = 0; i < times; i++) {
            // 得到一个 长度在 0 - N-1 的长度
            int n = (int) (Math.random() * N);
            int[] arr = randomArray(n, V);
            int[] arr1 = copyArray(arr);
            int[] arr2 = copyArray(arr);
            int[] arr3 = copyArray(arr);
            insertionSort(arr1);
            bubbleSort(arr2);
            selectionSort(arr3);
            if (!sameArray(arr1, arr2) || !sameArray(arr2, arr3) || !sameArray(arr1, arr3)) {
                System.out.println("出错了");
                printArr(arr1);
                printArr(arr2);
                printArr(arr3);
                break;
            }
        }
        System.out.println("测试结束");

    }
}
