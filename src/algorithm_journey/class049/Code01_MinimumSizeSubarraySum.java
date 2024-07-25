package algorithm_journey.class049;

/**
 * 长度最小的子数组
 *
 * @author: Agony
 * @create: 2024/7/22 17:48
 * @describe: 给定一个含有 n 个正整数的数组和一个正整数 target 。
 * <p>
 * 找出该数组中满足其总和大于等于 target 的长度最小的
 * 子数组
 * [nums_l, nums_l+1, ..., nums_r-1, nums_r] ，并返回其长度。如果不存在符合条件的子数组，返回 0 。
 * @link: <a href="https://leetcode.cn/problems/minimum-size-subarray-sum/description/">长度最小的子数组</a>
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


    /**
     * 长度最小的子数组
     *
     * @param target 目标和
     * @param nums   数组
     * @return 返回长度最小的子数组长度
     */
    public static int minSubArrayLen(int target, int[] nums) {


        return 0;
    }
}
