package algorithm_journey.class046;

import java.util.HashMap;

/**
 * @author: Agony
 * @create: 2024/7/16 20:59
 * @describe:
 * @link:
 */
public class Code02_LongestSubarraySumEqualsAim {


    // todo
    public static void main(String[] args) {

        HashMap<Integer, Integer> map = new HashMap<>();
        Integer i = map.get(0);
        System.out.println(i);
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
}
