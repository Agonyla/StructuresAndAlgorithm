package algorithm_journey.class070;

/**
 * @author: Agony
 * @create: 2024/9/24 10:54
 * @describe:
 * @link:
 */
public class Code01_MaximumSubarray {


    // todo

    // 子数组最大累加和
    //
    // 总体思路：求出子数组以每个位置结尾的情况下得到的最大累加和
    //
    // 动态规划
    // 设计 int[] dp = new int[nums.length]
    // dp[i]表示以i位置结尾，能向左延伸的最大累加和
    // 情况分析：
    // 1️⃣不向左延伸 (左边的累加和是负数) -> dp[i]=nums[i]
    // 2️⃣向左延伸 (左边的累加和是正数) -> dp[i]=nums[i]+dp[i-1]
    // 特殊位置分析：
    // dp[0]=nums[0] -> 以0位置结尾的最大累加和就是其本身
    //
    // 空间压缩
    // 用一个变量pre代替dp数组
    // 来到i位置 pre 表示dp[i-1]的值
    // 然后更新


    // 附加问题
    // 子数组中找到拥有最大累加和的子数组，并返回如下三个信息:
    // 1) 最大累加和子数组的开头left
    // 2) 最大累加和子数组的结尾right
    // 3) 最大累加和子数组的累加和sum
    // 如果不止一个子数组拥有最大累加和，那么找到哪一个都可以
    //
    // 设计变量
    // left -> 最长累加和的左边界
    // right -> 最长累加和的右边界
    // sum -> 最长累加和的值
    // l -> 上一个累加的左边界
    // r -> 上一个累加和的右边界
    // pre -> 上一个累加和的值
    // 可能性分析：
    // 1️⃣ pre>=0 -> pre+=nums[i]
    // 2️⃣ pre<0  -> 换开头 pre=nums[i], l=r
    // 当pre大于sum时 更新答案
    // left=l, r=right, sum=pre
    

}
