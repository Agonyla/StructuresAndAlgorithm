package algorithm_journey.class030;

/**
 * 只出现一次的数字
 *
 * @author: Agony
 * @create: 2024/6/11 16:00
 * @describe: 给你一个 非空 整数数组 nums ，除了某个元素只出现一次以外，其余每个元素均出现两次。找出那个只出现了一次的元素。
 * <p>
 * 你必须设计并实现线性时间复杂度的算法来解决此问题，且该算法只使用常量额外空间。
 * @link: <a href="https://leetcode.cn/problems/single-number/description/">只出现一次的数字</a>
 */
public class Code04_SingleNumber {

    /**
     * 一个数偶数次异或操作之后=0
     * 一个数奇数次异或操作之后=1
     *
     * @param nums
     * @return
     */
    public static int singleNumber(int[] nums) {
        int ans = 0;
        for (int num : nums) {
            ans ^= num;
        }
        return ans;
    }
}
