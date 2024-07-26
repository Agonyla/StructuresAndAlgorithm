package algorithm_journey.class049;

import java.util.Arrays;

/**
 * K 个不同整数的子数组
 *
 * @author: Agony
 * @create: 2024/7/24 18:27
 * @describe: 给定一个正整数数组 nums和一个整数 k，返回 nums 中 「好子数组」 的数目。
 * <p>
 * 如果 nums 的某个子数组中不同整数的个数恰好为 k，则称 nums 的这个连续、不一定不同的子数组为 「好子数组 」。
 * <p>
 * 例如，[1,2,3,1,2] 中有 3 个不同的整数：1，2，以及 3。
 * 子数组 是数组的 连续 部分。
 * @link: <a href="https://leetcode.cn/problems/subarrays-with-k-different-integers/description/">K 个不同整数的子数组</a>
 */
public class Code06_SubarraysWithKDifferentIntegers {


    // todo
    public static void main(String[] args) {

        int[] arr = {1, 2, 1, 2, 3};
        System.out.println(subarraysWithKDistinct(arr, 2));
    }

    // K个不同整数的子数组

    // 思路：
    // 设计 int f(int[] arr, int k) 函数
    // return -> 返回 arr 数组中 出现的种类 <= k 的子数组个数
    // 如果题目要求 k=4 那么就直接返回 f(arr,4) - f(arr,3)
    //
    // f 函数怎么实现
    // 全局变量，设计一个map -> 数组实现 int[] cnts = new int[MAX_LENGTH]
    // 下标就是数字，值就是该数字出现的词频
    // 局部变量 collect -> 统计出现数字的种类
    // 左窗口从0开始遍历
    // 右窗口向右滑动
    // 如果++cnts[该数字]==1 -> 说明该数字刚出现，collect++
    // 当 collect > k 时，左窗口往外吐(向右移动)，同时cnts相应变化，如果该词频==0，collect--
    //
    // (代码中这段使用while实现的，能不能用if呢？因为每次都是加1，已超过，就立马减1。。)
    // 不能用if代替，因为当collect>k时，需要较少到k中才能满足要求，但是if只会吐出一个，需要左窗口一直吐出到collect=k，才行
    // 如arr=[1,2,1,2,3] , k=2
    // 当l=0，r=4时，
    // 左窗口要一直吐，当l来到3位置时才可以
    //
    // 收集答案 ans+=r-l+1
    // 知道r来到边界

    //

    /**
     * K个不同整数的子数组
     *
     * @param nums
     * @param k
     * @return
     */
    public static int subarraysWithKDistinct(int[] nums, int k) {
        return f(nums, k) - f(nums, k - 1);

    }


    public static int MAX_LENGTH = 20001;

    public static int[] cnts = new int[MAX_LENGTH];


    /**
     * 返回 arr 数组中 出现的种类 <= k 的子数组个数
     *
     * @param arr
     * @param k
     * @return
     */
    public static int f(int[] arr, int k) {

        int n = arr.length;
        // 初始化，arr里的数字是>=1的
        Arrays.fill(cnts, 1, n + 1, 0);

        int ans = 0;

        for (int r = 0, l = 0, collect = 0; r < n; r++) {

            if (++cnts[arr[r]] == 1) {
                collect++;
            }


            while (collect > k) {
                if (--cnts[arr[l++]] == 0) {
                    collect--;
                }
            }

            // 如l=2,r=4，满足
            // 那么一共有2~4、3~4、4~4个
            ans += r - l + 1;
        }

        return ans;
    }

}
