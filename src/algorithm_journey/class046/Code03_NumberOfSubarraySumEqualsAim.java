package algorithm_journey.class046;

import java.util.HashMap;

/**
 * 和为 K 的子数组
 *
 * @author: Agony
 * @create: 2024/7/16 21:30
 * @describe: 给你一个整数数组 nums 和一个整数 k ，请你统计并返回 该数组中和为 k 的子数组的个数 。
 * <p>
 * 子数组是数组中元素的连续非空序列。
 * @link: <a href="https://leetcode.cn/problems/subarray-sum-equals-k/description/">和为 K 的子数组</a>
 */
public class Code03_NumberOfSubarraySumEqualsAim {


    public static void main(String[] args) {

        int[] nums = {1, 1, 1};
        int aim = 2;
        System.out.println(subarraySum(nums, aim));


        nums = new int[]{1, 2, 3};
        aim = 3;
        System.out.println(subarraySum(nums, aim));


        nums = new int[]{1};
        aim = 0;
        System.out.println(subarraySum(nums, aim));
    }

    // 返回无序数组中累加和为给定值的子数组个数

    // 和上一题类似
    // 给定aim，知道0～i的累加和为sum
    // 求 sum-aim 前缀和的次数


    /**
     * 返回无序数组中累加和为给定值的子数组个数
     *
     * @param nums 数组
     * @param aim  累加和
     * @return 返回累加和为aim子数组个数
     */
    public static int subarraySum(int[] nums, int aim) {

        int ans = 0;
        // map
        // key: 前缀和
        // value：出现的次数
        HashMap<Integer, Integer> map = new HashMap<>();

        map.put(0, 1);

        int sum = 0;

        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
            // 要先判断前缀和出现的次数
            ans += map.getOrDefault(sum - aim, 0);

            // 每次出现前缀和都要更新map的值
            map.put(sum, map.getOrDefault(sum, 0) + 1);
        }
        return ans;
    }


}
