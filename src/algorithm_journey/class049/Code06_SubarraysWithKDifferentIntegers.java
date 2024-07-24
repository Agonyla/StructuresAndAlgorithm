package algorithm_journey.class049;

/**
 * @author: Agony
 * @create: 2024/7/24 18:27
 * @describe:
 * @link:
 */
public class Code06_SubarraysWithKDifferentIntegers {


    // todo

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
    // 当 collect > k 时，左窗口往外吐(向右移动)，同时cnts相应变化，如果该词频==0，collect--  (代码中这段使用while实现的，能不能用if呢？因为每次都是加1，已超过，就立马减1。。)
    // 收集答案 ans+=r-l+1
    // 知道r来到边界

    //
}
