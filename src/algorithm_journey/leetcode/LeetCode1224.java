package algorithm_journey.leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * @author: Agony
 * @create: 2025/4/5 14:52
 * @describe: 最大相等频率
 * <a href="https://leetcode.cn/problems/maximum-equal-frequency/?envType=company&envId=huawei&favoriteSlug=huawei-three-months">最大相等频率</a>
 */
public class LeetCode1224 {


    public static void main(String[] args) {

        int[] nums = {2, 2, 1, 1, 5, 3, 3, 5};
        System.out.println(maxEqualFreq(nums));

        System.out.println(maxEqualFreq(new int[]{1, 1, 1, 2, 2, 2, 3, 3, 3, 4, 4, 4, 5}));
    }

    public static int maxEqualFreq(int[] nums) {
        // 记录每个数字出现的次数
        Map<Integer, Integer> numCount = new HashMap<>();

        // 记录有多少个不同的数字出现了特定的频率
        Map<Integer, Integer> freqCount = new HashMap<>();

        int maxLength = 0;

        for (int i = 0; i < nums.length; i++) {
            int num = nums[i];

            // 获取当前数字之前的出现次数
            int oldFreq = numCount.getOrDefault(num, 0);

            // 更新当前数字的出现次数
            int newFreq = oldFreq + 1;
            numCount.put(num, newFreq);

            // 更新频率统计
            // 1. 减少旧频率的计数
            if (oldFreq > 0) {
                freqCount.put(oldFreq, freqCount.get(oldFreq) - 1);
                if (freqCount.get(oldFreq) == 0) {
                    freqCount.remove(oldFreq);
                }
            }

            // 2. 增加新频率的计数
            freqCount.put(newFreq, freqCount.getOrDefault(newFreq, 0) + 1);

            // 判断当前前缀是否满足条件

            // 情况1：所有数字都只出现一次，可以删除任意一个数字
            if (freqCount.size() == 1 && freqCount.containsKey(1)) {
                maxLength = i + 1;
            }

            // 情况2：所有数字都出现相同次数k，且只有一个数字，可以删除该数字的一次出现
            else if (freqCount.size() == 1 && freqCount.values().iterator().next() == 1) {
                maxLength = i + 1;
            }

            // 情况3：除了一个数字出现一次外，其他所有数字都出现相同次数，可以删除那个出现一次的数字
            else if (freqCount.size() == 2 && freqCount.containsKey(1) && freqCount.get(1) == 1) {
                maxLength = i + 1;
            }

            // 情况4：有两种频率，且频率差为1，并且较高频率只有一个数字拥有，可以删除该数字的一次出现
            else if (freqCount.size() == 2) {
                // 获取两种频率
                int freq1 = 0, freq2 = 0;
                for (int freq : freqCount.keySet()) {
                    if (freq1 == 0) {
                        freq1 = freq;
                    } else {
                        freq2 = freq;
                    }
                }

                // 确保freq1 < freq2
                if (freq1 > freq2) {
                    int temp = freq1;
                    freq1 = freq2;
                    freq2 = temp;
                }

                // 频率差为1且较高频率只有一个数字
                if (freq2 - freq1 == 1 && freqCount.get(freq2) == 1) {
                    maxLength = i + 1;
                }
            }
        }

        return maxLength;
    }

}
