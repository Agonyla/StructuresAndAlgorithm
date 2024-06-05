package algorithm_journey.class027;

import java.util.PriorityQueue;

/**
 * 将数组和减半的最少操作次数
 *
 * @author Agony
 * @create 2024/6/5 14:22
 * @describe: 给你一个正整数数组 nums 。每一次操作中，你可以从 nums 中选择 任意 一个数并将它减小到 恰好 一半。
 * （注意，在后续操作中你可以对减半过的数继续执行操作）
 * <p>
 * 请你返回将 nums 数组和 至少 减少一半的 最少 操作数。
 * @link: <a href="https://leetcode.cn/problems/minimum-operations-to-halve-array-sum/description/">将数组和减半的最少操作次数</a>
 */
public class MinimumOperationsToHalveArraySum {

    public static void main(String[] args) {

        // int[] arr = {5, 19, 8, 1};
        int[] arr = {3, 8, 20};
        System.out.println(halveArray(arr));

    }

    /**
     * 把数组每个元素加入大根堆
     * cur = 弹出堆顶元素 /= 2
     * minus += cur
     * cur再重新加入堆
     * ans++
     *
     * @param arr
     * @return
     */
    public static int halveArray(int[] arr) {
        PriorityQueue<Double> heap = new PriorityQueue<>((a, b) -> b.compareTo(a));
        int ans = 0;
        double sum = 0;
        for (int i : arr) {
            heap.add((double) i);
            sum += i;
        }
        sum /= 2;
        double minus = 0;
        while (minus < sum) {
            double cur = heap.poll() / 2;
            heap.add(cur);
            minus += cur;
            ans++;
        }
        return ans;
    }
}
