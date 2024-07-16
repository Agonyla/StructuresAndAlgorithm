package algorithm_journey.class046;

/**
 * @author: Agony
 * @create: 2024/7/16 16:10
 * @describe:
 * @link:
 */
public class Code01_PrefixSumArray {


    // todo

    // 利用前缀和快速得到区域累加和

    // 思路
    // 用数组记录前i个位置的累加和
    // public static int[] sum
    // sum = new int[nums.length+1]
    // sum[0] 表示前0个位置的累加和
    // sum[1] 表示前1个位置的累加和
    // 所以求 l 到 r 范围的累加和
    // -> sum[r+1] - sum[l]
    // nums = {1,2,3,5,6}
    // sum = {0,1,3,6,11,17}
    // l=2, r=4 -> 14
    // sum[5]-sum[2] = 14

    // 如果直接用sum={1,3,6,11,17}表示的话
    // sums 表示0～i位置的累加和
    // 累加和 = sum[r] - sum[l-1] = 14
    // 但是当l=0时还要多加一个if判断比较麻烦
}
