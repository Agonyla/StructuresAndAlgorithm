package algorithm_journey.class049;

/**
 * @author: Agony
 * @create: 2024/7/22 17:48
 * @describe:
 * @link:
 */
public class Code01_MinimumSizeSubarraySum {

    // todo

    // 累加和大于等于target的最短子数组长度

    // 思路：
    // 从以i位置结尾开始
    // 验证当前窗口左边界 到 窗口右边界的累加和能否满足 >= target
    // 满足更新 ans
    // 同时左边界++，缩小窗口看能否满足
    // 满足更新 ans
    // 不满足右边界++，扩大窗口
    // 。。。
    // 求最小值返回
}