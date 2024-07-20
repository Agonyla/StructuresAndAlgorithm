package algorithm_journey.class046;

/**
 * 区域和检索 - 数组不可变
 *
 * @author: Agony
 * @create: 2024/7/16 16:10
 * @describe: 给定一个整数数组  nums，处理以下类型的多个查询:
 * <p>
 * 计算索引 left 和 right （包含 left 和 right）之间的 nums 元素的 和 ，其中 left <= right
 * 实现 NumArray 类：
 * <p>
 * NumArray(int[] nums) 使用数组 nums 初始化对象
 * int sumRange(int i, int j) 返回数组 nums 中索引 left 和 right 之间的元素的 总和 ，包含 left 和 right 两点（也就是 nums[left] + nums[left + 1] + ... + nums[right] )
 * @link: <a href="https://leetcode.cn/problems/range-sum-query-immutable/description/"> 区域和检索 - 数组不可变</a>
 */
public class Code01_PrefixSumArray {


    public static void main(String[] args) {
        NumArray numArray = new NumArray(new int[]{-2, 0, 3, -5, 2, -1});
        System.out.println(numArray.sumRange(0, 2));
        System.out.println(numArray.sumRange(2, 5));
        System.out.println(numArray.sumRange(0, 5));
    }

    // 利用前缀和快速得到区域累加和

    // 思路
    // 用数组记录前i个位置的累加和
    // public static int[] sum
    // sum = new int[nums.length+1]
    // sum[0] 表示前0个位置的累加和
    // sum[1] 表示前1个位置的累加和
    // 所以求 l 到 r 范围的累加和
    // -> sum[r+1] - sum[l]
    // nums = {1,2,3,5,6}
    // sum = {0,1,3,6,11,17}
    // l=2, r=4 -> 14
    // sum[5]-sum[2] = 14

    // 如果直接用sum={1,3,6,11,17}表示的话
    // sums 表示0～i位置的累加和
    // 累加和 = sum[r] - sum[l-1] = 14
    // 但是当l=0时还要多加一个if判断比较麻烦

    static class NumArray {

        private final int[] sum;

        public NumArray(int[] nums) {
            sum = new int[nums.length + 1];
            for (int i = 1; i <= nums.length; i++) {
                sum[i] = sum[i - 1] + nums[i - 1];
            }
        }

        public int sumRange(int left, int right) {
            return sum[right + 1] - sum[left];
        }
    }

}



















