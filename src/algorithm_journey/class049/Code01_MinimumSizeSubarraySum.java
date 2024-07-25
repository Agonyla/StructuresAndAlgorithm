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
 * <p>
 * 示例：
 * 输入：target = 7, nums = [2,3,1,2,4,3]
 * 输出：2
 * 解释：子数组 [4,3] 是该条件下的长度最小的子数组。
 * @link: <a href="https://leetcode.cn/problems/minimum-size-subarray-sum/description/">长度最小的子数组</a>
 */
public class Code01_MinimumSizeSubarraySum {


    public static void main(String[] args) {

        int[] nums = {2, 3, 1, 2, 4, 3};
        System.out.println(minSubArrayLen(7, nums));
    }

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
     * 求子数组在每个位置 结尾 情况下的答案
     *
     * @param target 目标和
     * @param nums   数组
     * @return 返回长度最小的子数组长度
     */
    public static int minSubArrayLen(int target, int[] nums) {


        int ans = Integer.MAX_VALUE;
        int sum = 0;
        // target = 7, nums = [2,3,1,2,4,3]
        // 因为是求以r结尾的情况下的答案，
        // 每次 sum += nums[r];
        // 让左窗口尽量往右缩
        for (int r = 0, l = 0; r < nums.length; r++) {
            sum += nums[r];
            while (sum - nums[l] >= target) {
                sum -= nums[l++];
            }

            if (sum >= target) {
                ans = Math.min(ans, r - l + 1);
            }
        }
        return ans == Integer.MAX_VALUE ? 0 : ans;
    }
}
