package algorithm_journey.leetcode;

import java.util.HashMap;

/**
 * @author: Agony
 * @create: 2025/3/16 21:25
 * @describe: 两数之和
 * @link: <a href="https://leetcode.cn/problems/two-sum/description/">两数之和</a>
 */
public class LeetCode1 {


    /**
     * 两数之和
     *
     * @param nums
     * @param target
     * @return
     */
    public int[] twoSum(int[] nums, int target) {

        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            if (map.containsKey(target - nums[i])) {
                return new int[]{map.get(target - nums[i]), i};
            } else {
                map.put(nums[i], i);
            }
        }
        return new int[0];
    }
}
