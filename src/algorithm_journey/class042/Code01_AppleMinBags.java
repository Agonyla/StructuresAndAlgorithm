package algorithm_journey.class042;

/**
 * @author: Agony
 * @create: 2024/7/8 11:15
 * @describe:
 * @link:
 */
public class Code01_AppleMinBags {


    public static void main(String[] args) {


        for (int i = 0; i < 100; i++) {
            System.out.println(i + " : " + bags(i));
        }

        int times = 200;

        System.out.println("test begin");
        for (int i = 0; i < times; i++) {

            if (i % 10 == 0) {
                System.out.println(i);
            }

            if (bags(i) != bagsBetter(i)) {
                System.out.println("oops");
                System.out.println(i);
                System.out.println("bags: " + bags(i));
                System.out.println("bagsBetter: " + bagsBetter(i));
                break;
            }
        }
        System.out.println("test end");
    }

    // 有装下8个苹果的袋子、装下6个苹果的袋子，一定要保证买苹果时所有使用的袋子都装满
    // 对于无法装满所有袋子的方案不予考虑，给定n个苹果，返回至少要多少个袋子
    // 如果不存在每个袋子都装满的方案返回-1


    /**
     * 求解苹果需要多少个袋子
     *
     * @param apple 苹果数量
     * @return 袋子数量
     */
    public static int bags(int apple) {
        int ans = f(apple);

        return ans == Integer.MAX_VALUE ? -1 : ans;
    }


    /**
     * 递归处理苹果
     *
     * @param rest 剩下苹果数量
     * @return 袋子数量
     */
    public static int f(int rest) {
        if (rest < 0) {
            return Integer.MAX_VALUE;
        }
        if (rest == 0) {
            return 0;
        }
        int ans = 0;
        int p1 = f(rest - 6);
        int p2 = f(rest - 8);

        p1 += p1 != Integer.MAX_VALUE ? 1 : 0;
        p2 += p2 != Integer.MAX_VALUE ? 1 : 0;
        return Math.min(p1, p2);
    }


    public static int bagsBetter(int apple) {
        // 奇数返回-1
        if ((apple & 1) != 0) {
            return -1;
        }
        // 小于18无规律
        if (apple < 18) {
            if (apple == 0) {
                return 0;
            }
            if (apple == 6 || apple == 8) {
                return 1;
            }
            if (apple == 12 || apple == 14 || apple == 16) {
                return 2;
            }
            return -1;
        }
        // 大于18 8个一组
        return (apple - 18) / 8 + 3;
    }

}
