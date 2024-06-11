package algorithm_journey.class030;

/**
 * 丢失的数字
 *
 * @author: Agony
 * @create: 2024/6/11 15:51
 * @describe: 给定一个包含 [0, n] 中 n 个数的数组 nums ，找出 [0, n] 这个范围内没有出现在数组中的那个数。
 * @link: <a href="https://leetcode.cn/problems/missing-number/description/">丢失的数字</a>
 */
public class Code03_MissingNumber {


    /**
     * nums=[a,b], 确实了c
     * x=a^b
     * y=a^b^c
     * x^y=a^b^a^b^c=c
     *
     * @param nums
     * @return
     */
    public static int missingNumber(int[] nums) {
        int eorAll = 0;
        int eorHas = 0;
        for (int i = 0; i < nums.length; i++) {
            eorHas ^= nums[i];
            eorAll ^= i;
        }
        eorAll ^= nums.length;
        return eorAll ^ eorHas;
    }
}
