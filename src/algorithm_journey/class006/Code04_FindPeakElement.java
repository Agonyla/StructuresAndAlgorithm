package algorithm_journey.class006;

/**
 * @author Agony
 * @create 2024/5/22 21:17
 * 峰值元素是指其值严格大于左右相邻值的元素
 * 给你一个整数数组 nums，已知任何两个相邻的值都不相等
 * 找到峰值元素并返回其索引
 * 数组可能包含多个峰值，在这种情况下，返回 任何一个峰值 所在位置即可。
 * 你可以假设 nums[-1] = nums[n] = 无穷小
 * 你必须实现时间复杂度为 O(log n) 的算法来解决此问题。
 * <a href="https://leetcode.cn/problems/find-peak-element/">测试链接</a>
 */
public class Code04_FindPeakElement {


    /**
     * 查找数组峰值
     *
     * @param nums 已知任何两个相邻的值都不相等的数组
     * @return 返回任意峰值所在位置
     */
    public static int findPeekElement(int[] nums) {
        if (nums == null || nums.length == 0) {
            return -1;
        }
        int n = nums.length;
        if (nums.length == 1) {
            return 0;
        }
        if (nums[0] > nums[1]) {
            return 0;
        }
        if (nums[n - 2] < nums[n - 1]) {
            return n - 1;
        }
        int ans = -1;
        int l = 1;
        int r = n - 2;
        int mid = 0;
        while (l <= r) {
            mid = l + ((r - l) >> 1);
            if (nums[mid - 1] > nums[mid]) {
                r = mid - 1;
            } else if (nums[mid] < nums[mid + 1]) {
                l = mid + 1;
            } else {
                ans = mid;
                break;
            }
        }
        return ans;

    }
}
