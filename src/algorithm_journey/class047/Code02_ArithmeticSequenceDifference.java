package algorithm_journey.class047;

/**
 * @author: Agony
 * @create: 2024/7/19 09:58
 * @describe:
 * @link:
 */
public class Code02_ArithmeticSequenceDifference {


    // todo

    // 等差数列差分模版

    // 思路
    // 等差数列差分
    // void set(int l, int r, int s, int e, int d)
    // l -> 左区间
    // r -> 右区间
    // s -> 首项
    // e -> 尾项
    // d -> 公差
    // arr[l] += s;
    // arr[l + 1] += (d - s);
    // arr[r + 1] -= (d + e);
    // arr[r + 2] += e;
    // 相当于 l 位置加首项，然后 l+1 ，减去首项，把加首项抵消，再加公差
    // 在 r+1 位置减去尾项，再减去公差，把加上公差的抵消，再r+2位置加上尾项，把减去尾项抵消
    // 都执行后相当于全部抵消
    // 然后做两次前缀和

}
