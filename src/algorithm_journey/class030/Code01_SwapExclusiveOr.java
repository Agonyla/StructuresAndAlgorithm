package algorithm_journey.class030;

/**
 * 用异或运算交换两数的值
 *
 * @author Agony
 * @create 2024/6/6 09:51
 */
public class Code01_SwapExclusiveOr {


    public static void main(String[] args) {

        int a = 10;
        int b = 20;
        System.out.println("a = " + a);
        System.out.println("b = " + b);
        a = a ^ b;
        b = a ^ b;
        a = a ^ b;
        System.out.println("a = " + a);
        System.out.println("b = " + b);

        int[] arr = {1, 3};
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






