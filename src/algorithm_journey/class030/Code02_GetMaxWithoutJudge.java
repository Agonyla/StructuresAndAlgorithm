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
        int a = -3;
        int b = Integer.MAX_VALUE;
        int c = a - b;

        System.out.println("a = " + a);
        System.out.println("b = " + b);
        System.out.println("c = " + c);


        int max = getMax(a, b);
        int max2 = getMax2(a, b);
        System.out.println("max = " + max);
        System.out.println("max2 = " + max2);
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
    

    /**
     * c = a - b
     * c>=0 returnA=1
     * c<0 returnB=1
     * 可能有溢出的风险 (a很小，b很大)
     * 通过直接判断signC的符号不稳定
     *
     * @param a
     * @param b
     * @return 较大值
     */
    public static int getMax(int a, int b) {
        // write code here
        int c = a - b;
        int returnA = getSign(c);
        int returnB = flip(returnA);
        // returnA 和 returnB 一定有一个是1，一个是0;
        return returnA * a + returnB * b;
    }

    /**
     * @param a
     * @param b
     * @return
     */
    public static int getMax2(int a, int b) {

        int c = a - b;
        int signA = getSign(a);
        int signB = getSign(b);
        int signC = getSign(c);

        // a,b符号不同 -> diffAB=1
        // a,b符号相同 -> diffAB=0
        // a>=0,b>=0
        // a<0 , b<0
        // -> diffAB=0;
        int diffAB = signA ^ signB;

        // a,b符号相同 -> sameAB=1
        // a,b符号不同 -> sameAB=0
        int sameAB = flip(diffAB);

        // 1. diffAB=1 (a,b符号不同)
        // 1) a>=0,b<0  signA=1, signB=0 -> returnA=1
        // 2) a<0,b>=0  signA=0, signB=1 -> returnA=0
        //
        // 2. sameAB=1 (a,b符号相同)
        // 1) a>=b>=0 -> signC=1 -> returnA=1
        // 2) 0>=b>=a -> signC=0 -> returnA=0
        int returnA = diffAB * signA + sameAB * signC;
        int returnB = flip(returnA);
        return returnA * a + returnB * b;
    }
}











