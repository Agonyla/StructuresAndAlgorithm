package algorithm_journey.leetcode;

/**
 * @author: Agony
 * @create: 2025/4/8 22:47
 * @describe: 1004. 最大连续1的个数 III
 * <a href="https://leetcode.cn/problems/max-consecutive-ones-iii/?envType=company&envId=huawei&favoriteSlug=huawei-three-months">1004. 最大连续1的个数 III</a>
 */
public class LeetCode1004 {

    public static void main(String[] args) {


        int[] nums = {1, 1, 1, 0, 0, 0, 1, 1, 1, 1, 0};
        System.out.println(longestOnes(nums, 2));

        // nums = new int[]{0, 0, 1, 1, 0, 0, 1, 1, 1, 0, 1, 1, 0, 0, 0, 1, 1, 1, 1};
        // System.out.println(longestOnes(nums, 3));
    }


    public static int longestOnes(int[] nums, int k) {

        int left = 0;
        int max = 0;
        int zeroNum = 0;

        for (int right = 0; right < nums.length; right++) {

            if (nums[right] == 0) {
                zeroNum++;
            }

            while (zeroNum > k) {
                if (nums[left] == 0) {
                    zeroNum--;
                }
                left++;
            }
            max = Math.max(max, right - left + 1);

        }

        return max;
    }


}
