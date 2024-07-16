package algorithm_journey.class045;

import java.util.HashSet;

/**
 * 数组中两个数的最大异或值
 *
 * @author: Agony
 * @create: 2024/7/16 09:56
 * @describe: 给你一个整数数组 nums ，返回 nums[i] XOR nums[j] 的最大运算结果，其中 0 ≤ i ≤ j < n 。
 * <p>
 * 输入：nums = [3,10,5,25,2,8]
 * 输出：28
 * 解释：最大运算结果是 5 XOR 25 = 28
 * <p>
 * 输入：nums = [14,70,53,83,49,91,36,80,92,51,66,70]
 * 输出：127
 * @link: <a href="https://leetcode.cn/problems/maximum-xor-of-two-numbers-in-an-array/description/">数组中两个数的最大异或值</a>
 */
public class Code02_TwoNumbersMaximumXorHashSet {


    public static void main(String[] args) {
        int[] nums = {3, 10, 5, 25, 2, 8};
        System.out.println(findMaximumXOR(nums));
        System.out.println(right(nums));

        nums = new int[]{14, 70, 53, 83, 49, 91, 36, 80, 92, 51, 66, 70};
        System.out.println(findMaximumXOR(nums));
        System.out.println(right(nums));
    }


    // hashset实现
    // 先取出最大值，从最大值的最高位开始构建
    // want -> 想要的状态位
    // num -> 实际的状态位，只保留最高位到i位置的状态，i一下的位置都置0
    // 如果 num 和 set 中的某个数 x 异或 能达成 want，就让 ans = want
    // 即 want = num ^ x
    // 那么 只要去set中查询是否存在 x ，能达成want
    // -> set.contains(x) -> x = num * want

    /**
     * 求数组中两个数的最大异或值
     * hashset 实现
     *
     * @param nums
     * @return
     */
    public static int findMaximumXOR(int[] nums) {

        int max = Integer.MIN_VALUE;

        for (int num : nums) {
            max = Math.max(max, num);
        }

        // 最大值的最高位1所在的位
        int ans = 0;
        int high = 31 - Integer.numberOfLeadingZeros(max);
        HashSet<Integer> set = new HashSet<>();
        for (int i = high; i >= 0; i--) {

            // 想要达成的状态
            int want = ans | (1 << i);
            set.clear();
            for (int num : nums) {

                // num第i位以下的都置0
                num = num >> i << i;
                set.add(num);
                if (set.contains(want ^ num)) {
                    ans = want;
                    break;
                }
            }
        }
        return ans;
    }

    public static int right(int[] nums) {
        int max = 0;
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                max = Math.max(max, nums[i] ^ nums[j]);
            }
        }
        return max;
    }
}
