package algorithm_journey.class030;

/**
 * 不用任何判断语句和比较操作，返回两个数的最大值
 *
 * @author Agony
 * @create 2024/6/8 22:15
 * @describe: 不用任何判断语句和比较操作，返回两个数的最大值
 * @link: <a href="https://www.nowcoder.com/practice/d2707eaf98124f1e8f1d9c18ad487f76">返回最大值</a>
 */
public class Code02_GetMaxWithoutJudge {


    public static void main(String[] args) {

    }


    /**
     * n>=0 返回 1
     * n<0  返回 0
     * n>>>31
     * 负数是1
     * 非负数是0
     *
     * @param n
     * @return
     */
    public static int getSign(int n) {
        return flip(n >>> 31);
    }

    /**
     * 0 -> 1
     * 1 -> 0
     * n 只能是1 or 0
     *
     * @param n
     * @return
     */
    public static int flip(int n) {
        return n ^ 1;
    }

    // todo

}
