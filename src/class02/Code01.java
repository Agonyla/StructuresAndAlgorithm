package class02;

/**
 * @Author Agony
 * @Create 2023/7/24 19:36
 * @Version 1.0
 * 两种数出现奇数次，另外的数出现偶数次，找出那两种数
 */
public class Code01 {

    public static void getNum(int[] arr) {


        if (arr == null || arr.length == 0) {
            return;
        }

        int eor = 0;
        for (int j : arr) {
            eor ^= j;
        }
        // right为最右边是1的数
        int rightOne = eor & (-eor);
        int onlyOne = 0;
        for (int j : arr) {
            if ((j & rightOne) == 0) {
                onlyOne ^= j;
            }
        }

        int a = eor ^ onlyOne;
        int b = a ^ eor;
        System.out.println(a);
        System.out.println(b);

    }

    public static void main(String[] args) {
        int[] arr = {6, 10, 6, 6, 4, 4, 12, 12, 12, 12, 3, 3};

        getNum(arr);

    }
}
