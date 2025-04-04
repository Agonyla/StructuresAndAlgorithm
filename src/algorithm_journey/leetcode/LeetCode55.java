package algorithm_journey.leetcode;

/**
 * @author: Agony
 * @create: 2025/4/4 20:58
 * @describe: 跳跃游戏
 * <a href="https://leetcode.cn/problems/jump-game/description/?envType=company&envId=huawei&favoriteSlug=huawei-three-months">跳跃游戏</a>
 */
public class LeetCode55 {


    public static void main(String[] args) {


        int[] nums = {2, 3, 1, 1, 4};
        System.out.println(canJump(nums));


        nums = new int[]{3, 2, 1, 0, 4};
        System.out.println(canJump(nums));
    }

    public static boolean canJump(int[] nums) {


        int maxReach = 0;

        for (int i = 0; i < nums.length; i++) {

            if (i > maxReach) {
                return false;
            }
            maxReach = Math.max(maxReach, i + nums[i]);
            if (maxReach >= nums.length - 1) {
                return true;
            }
        }
        return false;

    }


}
