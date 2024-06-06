package algorithm_journey.class029;

/**
 * @author Agony
 * @create 2024/6/6 09:51
 */
public class SwapExclusiveOr {

    // todo 完成剩余的内容
    public static void main(String[] args) {
        int[] arr = {3, 3};


        System.out.println(arr[0]);
        System.out.println(arr[1]);
    }

    /**
     * 交换两个数的位置
     * 当两个数位置相同时会出现错误
     *
     * @param arr
     * @param i
     * @param j
     */
    public static void swap(int[] arr, int i, int j) {
        if (i == j) {
            return;
        }
        arr[i] = arr[i] ^ arr[j];
        arr[j] = arr[i] ^ arr[j];
        arr[i] = arr[i] ^ arr[j];
    }

}






