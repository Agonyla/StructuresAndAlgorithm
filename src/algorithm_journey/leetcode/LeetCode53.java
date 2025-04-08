package algorithm_journey.leetcode;

/**
 * @author: Agony
 * @create: 2025/4/8 10:10
 * @describe: 最大子数组和
 * <a href="https://leetcode.cn/problems/maximum-subarray/description/?envType=company&envId=huawei&favoriteSlug=huawei-three-months">最大子数组和</a>
 */
public class LeetCode53 {


    public static void main(String[] args) {

        int[] nums = {-2, 1, -3, 4, -1, 2, 1, -5, 4};
        System.out.println(maxSubArray(nums));
    }

    public static int maxSubArray(int[] nums) {

        if (nums == null || nums.length == 0) {
            return 0;
        }

        int sum = nums[0];
        int max = sum;

        for (int i = 1; i < nums.length; i++) {

            sum = Math.max(nums[i], sum + nums[i]);
            max = Math.max(sum, max);
        }


        return max;
    }

}
