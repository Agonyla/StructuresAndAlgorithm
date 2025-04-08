package algorithm_journey.leetcode;

import algorithm_journey.utils.MathUtils;

import java.util.Comparator;
import java.util.HashMap;
import java.util.PriorityQueue;

/**
 * @author: Agony
 * @create: 2025/4/8 11:36
 * @describe: 前 K 个高频元素
 * <a href="https://leetcode.cn/problems/top-k-frequent-elements/description/">前 K 个高频元素</a>
 */
public class LeetCode347 {


    public static void main(String[] args) {
        int[] nums = {1, 1, 1, 2, 2, 3};
        int[] ans = topKFrequent(nums, 2);
        MathUtils.printArr(ans);

        nums = new int[]{1};
        ans = topKFrequent(nums, 1);
        MathUtils.printArr(ans);
    }

    public static int[] topKFrequent(int[] nums, int k) {

        HashMap<Integer, Integer> map = new HashMap<>();

        for (int i = 0; i < nums.length; i++) {
            map.put(nums[i], map.getOrDefault(nums[i], 0) + 1);
        }


        // 按照出现频率从低到高
        PriorityQueue<Integer> heap = new PriorityQueue<>(Comparator.comparingInt(map::get));

        for (Integer key : map.keySet()) {
            heap.offer(key);
            if (heap.size() > k) {
                heap.poll();
            }
        }
        int[] ans = new int[k];

        for (int i = ans.length - 1; i >= 0; i--) {
            ans[i] = heap.poll();
        }
        return ans;
    }
}
