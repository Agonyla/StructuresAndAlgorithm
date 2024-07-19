package algorithm_journey.class046;

import java.util.HashMap;

/**
 * 表现良好的最长时间段
 *
 * @author: Agony
 * @create: 2024/7/17 09:50
 * @describe: 给你一份工作时间表 hours，上面记录着某一位员工每天的工作小时数。
 * <p>
 * 我们认为当员工一天中的工作小时数大于 8 小时的时候，那么这一天就是「劳累的一天」。
 * <p>
 * 所谓「表现良好的时间段」，意味在这段时间内，「劳累的天数」是严格 大于「不劳累的天数」。
 * <p>
 * 请你返回「表现良好时间段」的最大长度。
 * @link: <a href="https://leetcode.cn/problems/longest-well-performing-interval/description/">表现良好的最长时间段</a>
 */
public class Code05_LongestWellPerformingInterval {


    public static void main(String[] args) {

        int[] arr = {9, 9, 6, 0, 6, 6, 9};
        // MathUtils.printArr(arr);
        System.out.println(longestWPI(arr));
        // MathUtils.printArr(arr);

        arr = new int[]{6, 6, 6};
        System.out.println(longestWPI(arr));
    }

    // 表现良好的最长时间段

    // 思路
    // 和题目2类似
    // 把大于8的转化成1，小于等于8的转化成-1
    // 求累加和>=1的最长子数组长度
    // 特殊情况：
    // 1.如果0～i的累加和>=1时，直接返回i+1 不需要再转化成前缀和了
    // 2.如果0～i的累加和为sum<0，那么就找前缀和为sum-1最早出现的位置j，返回i-j
    // 如：0～i的累加和为-3
    // 那么就找前缀和为-4最早出现的位置
    // 因为转化后的数组值都是1，-1
    // 前缀和是一点一点变化的
    // 如果出现前缀和为-5，那么在其前必有前缀和为-4的位置


    /**
     * 表现良好的最长时间段
     *
     * @param hours 工作时长数组
     * @return 返回表现良好的最长子数组长度
     */
    public static int longestWPI(int[] hours) {

        // 工作时长>8 -> 1; <=8 -> -1
        for (int i = 0; i < hours.length; i++) {
            hours[i] = hours[i] > 8 ? 1 : -1;
        }

        // key:前缀和；value：前缀和最早出现的位置
        HashMap<Integer, Integer> map = new HashMap<>();
        map.put(0, -1);
        int ans = 0;
        int sum = 0;

        for (int i = 0; i < hours.length; i++) {
            sum += hours[i];
            // 0～i这段时间全是表现良好的->直接返回i+1
            if (sum > 0) {
                ans = i + 1;
            } else {

                // 如果包含sum-1 -> j~i位置的累加和是大于0的
                if (map.containsKey(sum - 1)) {
                    ans = Math.max(ans, i - map.get(sum - 1));
                }
            }
            // 第一次出现前缀和就直接加入当前位置
            if (!map.containsKey(sum)) {
                map.put(sum, i);
            }
        }
        return ans;
    }
}















