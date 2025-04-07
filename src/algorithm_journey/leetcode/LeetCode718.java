package algorithm_journey.leetcode;

import java.util.Arrays;

/**
 * @author: Agony
 * @create: 2025/4/7 11:22
 * @describe: 最长重复子数组
 * <a href="https://leetcode.cn/problems/maximum-length-of-repeated-subarray/description/?envType=company&envId=huawei&favoriteSlug=huawei-three-months">最长重复子数组</a>
 */
public class LeetCode718 {

    public static void main(String[] args) {

        int[] nums1 = {1, 2, 3, 2, 1};
        int[] nums2 = {3, 2, 1, 4, 7};
        System.out.println(findLength(nums1, nums2));

    }

    public static int findLength(int[] nums1, int[] nums2) {

        int max = 0;

        int n = nums1.length;
        int m = nums2.length;
        int[][] dp = new int[n][m];

        for (int i = 0; i < n; i++) {
            Arrays.fill(dp[i], -1);
        }


        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                max = Math.max(max, process(i, j, nums1, nums2, dp));
            }
        }

        return max;
    }


    public static int process(int i, int j, int[] nums1, int[] nums2, int[][] dp) {

        if (i >= nums1.length || j >= nums2.length) {
            return 0;
        }

        if (dp[i][j] != -1) {
            return dp[i][j];
        }

        int max = 0;
        if (nums1[i] == nums2[j]) {
            max = 1 + process(i + 1, j + 1, nums1, nums2, dp);
        }
        dp[i][j] = max;
        return dp[i][j];
    }
}
