package algorithm_journey.class027;

/**
 * 将数组和减半的最少操作次数
 *
 * @author Agony
 * @create 2024/6/5 14:40
 * @describe: 给你一个正整数数组 nums 。每一次操作中，你可以从 nums 中选择 任意 一个数并将它减小到 恰好 一半。
 * （注意，在后续操作中你可以对减半过的数继续执行操作）
 * <p>
 * 请你返回将 nums 数组和 至少 减少一半的 最少 操作数。
 * @link: <a href="https://leetcode.cn/problems/minimum-operations-to-halve-array-sum/description/">将数组和减半的最少操作次数</a>
 */
public class MinimumOperationsToHalveArraySum2 {

    public static void main(String[] args) {

        // int[] arr = {5, 19, 8, 1};
        int[] arr = {3, 8, 20};
        System.out.println(halveArray(arr));

    }

    public static int length = 100001;
    public static long[] heap = new long[length];
    public static int size;

    public static int halveArray(int[] arr) {
        size = arr.length;
        long sum = 0;
        for (int i = arr.length - 1; i >= 0; i--) {
            heap[i] = (long) arr[i] << 20;
            sum += heap[i];
            heapify(i);
        }
        sum /= 2;
        int ans = 0;
        long minus = 0;
        while (minus < sum) {
            heap[0] /= 2;
            minus += heap[0];
            heapify(0);
            ans++;
        }
        return ans;
    }


    public static void heapify(int i) {
        int l = 2 * i + 1;
        while (l < size) {
            int r = l + 1;
            int bigger = r < size && heap[l] < heap[r] ? r : l;
            if (heap[i] >= heap[bigger]) {
                break;
            } else {
                swap(i, bigger);
                i = bigger;
                l = 2 * i + 1;
            }
        }
    }

    public static void swap(int i, int j) {
        long temp = heap[i];
        heap[i] = heap[j];
        heap[j] = temp;
    }

}
