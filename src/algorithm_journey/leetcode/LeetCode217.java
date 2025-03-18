package algorithm_journey.leetcode;

import java.util.HashSet;

/**
 * @author: Agony
 * @create: 2025/3/18 16:53
 * @describe: 存在重复元素
 * @link: <a href="https://leetcode.cn/problems/contains-duplicate/">存在重复元素</a>
 */
public class LeetCode217 {


    public boolean containsDuplicate(int[] nums) {

        HashSet<Integer> set = new HashSet<>();
        for (int i = 0; i < nums.length; i++) {
            if (set.contains(nums[i])) {
                return true;
            } else {
                set.add(nums[i]);
            }
        }
        return false;
    }
}
