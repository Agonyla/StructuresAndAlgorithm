package class05;

/**
 * @Author Agony
 * @Create 2023/8/9 20:40
 * @Version 1.0
 * 区间和的个数
 * 给你一个整数数组 nums 以及两个整数 lower 和 upper 。
 * 求数组中，值位于范围 [lower, upper] （包含 lower 和 upper）之内的 区间和的个数
 * <a href="https://leetcode.cn/problems/count-of-range-sum/">...</a>
 */
public class Code01_CountOfRangeSum {
    public static int countRangeSum(int[] nums, int lower, int upper) {
        if (nums == null || nums.length < 1) {
            return 0;
        }
        long[] sums = new long[nums.length];
        sums[0] = nums[0];
        for (int i = 1; i < nums.length; i++) {
            sums[i] = sums[i - 1] + nums[i];
        }

        return process(sums, 0, nums.length - 1, lower, upper);
    }

    public static int process(long[] sums, int L, int R, int lower, int upper) {
        if (L == R) {
            return (sums[L] <= upper && sums[L] >= lower) ? 1 : 0;
        }
        int mid = L + ((R - L) >> 1);
        int leftNums = process(sums, L, mid, lower, upper);
        int rightNums = process(sums, mid + 1, R, lower, upper);
        int mergeNums = merge(sums, L, mid, R, lower, upper);
        return leftNums + rightNums + mergeNums;
    }

    public static int merge(long[] sums, int L, int mid, int R, int lower, int upper) {
        //
        int res = 0;
        int windowL = L;
        int windowR = L;
        for (int i = mid + 1; i <= R; i++) {
            long min = sums[i] - upper;
            long max = sums[i] - lower;
            while (windowL <= mid && sums[windowL] < min) {
                windowL++;
            }

            while (windowR <= mid && sums[windowR] <= max) {
                windowR++;
            }
            res += windowR - windowL;
        }
        // merge
        long[] help = new long[R - L + 1];
        int P1 = L;
        int P2 = mid + 1;
        int i = 0;
        while (P1 <= mid && P2 <= R) {
            help[i++] = sums[P1] <= sums[P2] ? sums[P1++] : sums[P2++];
        }
        while (P1 <= mid) {
            help[i++] = sums[P1++];
        }
        while (P2 <= R) {
            help[i++] = sums[P2++];
        }

        for (i = 0; i < help.length; i++) {
            sums[L + i] = help[i];
        }
        return res;
    }

    public static void main(String[] args) {
        int[] nums = {-2, 5, -1};
        int res = countRangeSum(nums, -2, 2);
        System.out.println(res);
    }
}
