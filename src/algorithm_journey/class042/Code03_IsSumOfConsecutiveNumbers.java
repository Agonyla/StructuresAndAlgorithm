package algorithm_journey.class042;

/**
 * @author: Agony
 * @create: 2024/7/8 11:41
 * @describe:
 * @link:
 */
public class Code03_IsSumOfConsecutiveNumbers {

    public static void main(String[] args) {
        for (int i = 1; i < 500; i++) {
            System.out.println(i + " : " + is(i));
        }


        int times = 10000;
        System.out.println("test begin");
        for (int i = 1; i < times; i++) {
            if (is(i) != isBetter(i)) {
                System.out.println("oops");
            }
        }
        System.out.println("test end");
    }


    // 判断一个数字是否是若干数量(数量>1)的连续正整数的和

    // 12=3+4+5 是连续正整数的和


    /**
     * @param num 数字
     * @return
     */
    public static boolean is(int num) {

        for (int i = 1; i <= num; i++) {
            int sum = i;
            for (int j = i + 1; j <= num; j++) {
                if (sum + j > num) {
                    break;
                }
                if (sum + j == num) {
                    return true;
                }
                sum += j;
            }
        }
        return false;
    }


    /**
     * 规律，2的幂不行
     *
     * @param num
     * @return
     */
    public static boolean isBetter(int num) {
        return (num & (num - 1)) != 0;
    }

}
