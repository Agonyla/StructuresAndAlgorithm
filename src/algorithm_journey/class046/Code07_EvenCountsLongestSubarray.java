package algorithm_journey.class046;

/**
 * @author: Agony
 * @create: 2024/7/17 10:48
 * @describe:
 * @link:
 */
public class Code07_EvenCountsLongestSubarray {

    // todo

    // 每个元音包含偶数次的最长子字符串

    // 思路
    // 用 int status 记录 元音字母的状态
    // status=00100 -> 表示 uoiea 中的 i 出现了奇数次
    // status=10011 -> 表示 uoiea 中的 u、e、a、出现了奇数次
    // 如果0～i位置的状态为 10010
    // 那么就去找status为10010，该状态最早出现的位置j，返回i-j，（利用奇偶性）
    //
    // 实现
    // 准备一个数组 int[] map = new int[32]来记录每个状态最早出现的位置
    // 00000 ～ 11111 一个32个数字
    // map初始化为-2，表示这32个状态之前一次都没出现过
    // 注意：00000 这个状态初始化为-1，表示全都是偶数这个状态最早出现位置为-1
    // 然后遍历字符串，通过 s.charAt(i)，判断该字符并构建 status 状态
    // 如果map中没有这个状态，就往map中加入该状态最招出现的位置 -> map[status]=i
    // 如果重复出现了这个状态，说明之前已经出现过了该状态，那么在之前出现的状态到i位置这段字符串内，aeiou 出现的次数是均是偶数次
    // 就直接返回 i-map[status]
    // 然后比较各个0到当前位置的最大值返回
}
