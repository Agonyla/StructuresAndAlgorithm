package algorithm_journey.class031;

/**
 * 汉明距离
 *
 * @author: Agony
 * @create: 2024/6/12 16:06
 * @describe: 两个整数之间的 汉明距离 指的是这两个数字对应二进制位不同的位置的数目。
 * 如 a=01100110 , b=10100110 他们有 2 个位置状态不同
 * <p>
 * 给你两个整数 x 和 y，计算并返回它们之间的汉明距离。
 * @link: <a href="https://leetcode.cn/problems/hamming-distance/description/">汉明距离</a>
 */
public class Code06_CountOnesBinarySystem {


    public static void main(String[] args) {

        System.out.println(hammingDistance(1, 4));
        System.out.println(hammingDistance(3, 1));

    }

    public static int hammingDistance(int x, int y) {
        return cntOnes(x ^ y);
    }

    /**
     * 统计 n 的二进制表达中有几个1
     * <a href="https://www.bilibili.com/list/8888480?sid=3509640&oid=659640517&bvid=BV1ch4y1Q7vd">视频讲解</a>
     *
     * @param n
     * @return 返回1的个数
     */
    public static int cntOnes(int n) {
        n = (n & 0x55555555) + ((n >>> 1) & 0x55555555);
        n = (n & 0x33333333) + ((n >>> 2) & 0x33333333);
        n = (n & 0x0f0f0f0f) + ((n >>> 4) & 0x0f0f0f0f);
        n = (n & 0x00ff00ff) + ((n >>> 8) & 0x00ff00ff);
        n = (n & 0x0000ffff) + ((n >>> 16) & 0x0000ffff);
        return n;
    }
}
