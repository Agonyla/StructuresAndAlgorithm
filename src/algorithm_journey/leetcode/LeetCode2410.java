package algorithm_journey.leetcode;

/**
 * @author: Agony
 * @create: 2025/4/5 19:33
 * @describe: 最长优雅子数组
 * <a href="https://leetcode.cn/problems/longest-nice-subarray/description/?envType=company&envId=huawei&favoriteSlug=huawei-three-months">最长优雅子数组</a>
 */
public class LeetCode2410 {


    public static void main(String[] args) {

        int[] nums = new int[]{1, 3, 8, 48, 10};
        System.out.println(longestNiceSubarray(nums));

        nums = new int[]{3, 1, 5, 11, 13};
        System.out.println(longestNiceSubarray(nums));
    }

    public static int longestNiceSubarray(int[] nums) {

        int n = nums.length;

        int ans = 1;
        int left = 0;

        int OR = 0;
        for (int right = 0; right < n; right++) {

            while ((OR & nums[right]) != 0) {
                OR ^= nums[left];
                left++;
            }
            OR |= nums[right];
            ans = Math.max(ans, right - left + 1);

        }

        return ans;
    }


}
