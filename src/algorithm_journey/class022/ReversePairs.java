package algorithm_journey.class022;

/**
 * 翻转对
 *
 * @author Agony
 * @create 2024/5/30 11:22
 * @describe: 给定一个数组 nums ，如果 i < j 且 nums[i] > 2*nums[j] 我们就将 (i, j) 称作一个重要翻转对。
 * <p>
 * 你需要返回给定数组中的重要翻转对的数量。
 * 给定数组的长度不会超过50000。
 * 输入数组中的所有数字都在32位整数的表示范围内。
 * @link: <a href="https://leetcode.cn/problems/reverse-pairs/description/">翻转对</a>
 */
public class ReversePairs {

    public static void main(String[] args) {
        // int[] arr = {1, 3, 2, 3, 1};
        int[] arr = {2147483647, 2147483647, 2147483647, 2147483647, 2147483647, 2147483647};
        System.out.println(reversePairs(arr));
        System.out.println(arr[1]);
        System.out.println(Integer.MAX_VALUE);


    }


    public static int MAXN = 50001;


    public static int[] help = new int[MAXN];

    public static int reversePairs(int[] nums) {
        return reverseCounts(nums, 0, nums.length - 1);
    }

    public static int reverseCounts(int[] arr, int l, int r) {
        if (l == r) {
            return 0;
        }
        int m = (l + r) / 2;
        return reverseCounts(arr, l, m) + reverseCounts(arr, m + 1, r) + merge(arr, l, m, r);
    }

    public static int merge(int[] arr, int l, int m, int r) {
        // 统计部分
        int ans = 0;
        int sum = 0;
        for (int i = l, j = m + 1; i <= m; i++) {
            // 这里要先判断 j<=r 防止数组越界
            // 当 j == r+1 时，先执行arr[i] > 2 * arr[j]就越界
            // 2 * arr[j] 还要考虑越界
            while (j <= r && (long) arr[i] > 2L * arr[j]) {
                j++;
                sum++;
            }
            ans += sum;
        }
        // 归并排序
        int i = l;
        int a = l;
        int b = m + 1;
        while (a <= m && b <= r) {
            help[i++] = arr[a] <= arr[b] ? arr[a++] : arr[b++];
        }
        while (a <= m) {
            help[i++] = arr[a++];
        }
        while (b <= r) {
            help[i++] = arr[b++];
        }
        while (l <= r) {
            arr[l] = help[l++];
        }

        return ans;
    }
}




