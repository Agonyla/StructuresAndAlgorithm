package algorithm_journey.class031;

/**
 * @author Agony
 * @create 2024/6/7 09:25
 * @describe: 已知n是非负数
 * 返回大于等于n的最小的2某次方
 * 如果int范围内不存在这样的数，返回整数最小值
 */
public class Code03_Near2power {

    public static void main(String[] args) {


        System.out.println(near2power(23));
        System.out.println(near2power(223));
        System.out.println(near2power(100));

    }

    /**
     * 让n的左边第一个1后面的所有位变1
     * begin -> n=0010 1011
     * n--   -> n=0010 1010
     * n>>>1 -> n=0001 0101
     * n>>>2 -> n=0000 0101
     * n>>>4 -> n=0000 0001
     * all | -> n=0011 1111
     * n+1   -> n=0100 0000
     * <p>
     * begin -> n=00100000
     * n--   -> n=00011111
     * n>>>1 -> n=00001111
     * n>>>2 -> n=00000011
     * all | -> n=00011111
     * n+1   -> n=00100000
     *
     * @param n
     * @return
     */
    public static int near2power(int n) {
        if (n <= 0) {
            return 1;
        }
        n--;
        n |= n >>> 1;
        n |= n >>> 2;
        n |= n >>> 4;
        n |= n >>> 8;
        n |= n >>> 16;
        return n + 1;
    }
}
