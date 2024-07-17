package algorithm_journey.class046;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;

/**
 * 未排序数组中累加和为给定值的最长子数组长度
 *
 * @author: Agony
 * @create: 2024/7/16 20:59
 * @describe: 描述
 * 给定一个无序数组arr, 其中元素可正、可负、可0。给定一个整数k，求arr所有子数组中累加和为k的最长子数组长度
 * 输入描述：
 * 第一行两个整数N, k。N表示数组长度，k的定义已在题目描述中给出
 * 第二行N个整数表示数组内的数
 * 输出描述：
 * 输出一个整数表示答案
 * @link: <a href="https://www.nowcoder.com/practice/36fb0fd3c656480c92b569258a1223d5">未排序数组中累加和为给定值的最长子数组长度</a>
 */
public class Code02_LongestSubarraySumEqualsAim {


    // todo
    public static void main(String[] args) {

        // todo 把获取输入完成
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));


    }


    // 返回无序数组中累加和为给定值的最长子数组长度

    // 思路
    // 求最长子数组长度，给定值位aim
    // -> 以i位置作为结尾，往左延伸多长累加值可以达到aim
    // 每个位置都最为结尾遍历一边，然后求最大值就可以求出最长子数组长度
    // 怎么以i位置作为结尾的最长子数组长度
    // -> 假设0～i位置的累加和为 sum，那么就去找累加和为 sum-aim 最早出现的位置
    // -> 该位置向右到i位置就是最长长度
    // 实现
    // 用hashmap记录每个累加和出现的最早位置
    // key：某个前缀和
    // value：该前缀和最早出现的位置
    // 如 num = {1,4,-5,2,3}
    // map={ 1:0, 5:1, 0:2, 2:3 } 这里的 0:2 这样是不对的，知识为了解释才这么写，看下面的解释
    // 当put操作发现出现位置大于当前要更新的值时，不更新

    // 注意：
    // 需要首先在map中插入 {0:-1}
    // 0最早出现的位置为-1
    // num = {1,4,-5,2,3} aim=5
    // 如果没有 0:-1
    // 从map中查询找到最早出现0的位置是2
    // 然后就导致累加和达到aim的最长为 [3,4] 长度为2  当然也可以直接 4-2 来计算

    // 数组长度
    public static int N;

    public static int[] arr;


    // 累加和
    public static int aim;


    /**
     * 返回无序数组中累加和为给定值的最长子数组长度
     *
     * @return
     */
    public static int compute() {

        // 0~i位置的累加和
        int ans = 0;
        int sum = 0;
        HashMap<Integer, Integer> map = new HashMap<>();
        map.put(0, -1);
        for (int i = 0; i < N; i++) {
            sum += arr[i];

            // 如果sum-aim还没出现过，就把最早出现的位置放入
            // 如果出现过了，出现的位置为j，那么就说明，j～i这段累加和为aim
            // 直接比较最大值返回
            if (!map.containsKey(sum - aim)) {
                map.put(sum - aim, i);
            } else {
                ans = Math.max(ans, i - map.get(sum - aim));
            }
        }
        return ans;

    }


}
