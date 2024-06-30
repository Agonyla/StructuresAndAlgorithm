package algorithm_journey.class038;

/**
 * @author: Agony
 * @create: 2024/6/28 11:29
 * @describe:
 * @link:
 */
public class Code02_Combinations {


    // todo

    // 给你一个整数数组 nums ，其中可能包含重复元素，请你返回该数组所有可能的组合

    // 思路
    // 现将这组数组排序 -> 如 [1, 1, 1, 2, 2, 2, 2, 3, 3]
    // List<List<Integer>> ans -> 记录答案
    // int[] path -> 记录路径中添加的数字
    // 实现函数 f(int[] arr, int i, int size)
    // arr -> 排序之后的整数数组
    // i -> 数组的下标
    // size -> path 中添加的元素大小
    // 首先来到下一个不同的元素， j = 3
    // 然后
    // 0 个 1，不往path中添加，调用 f(arr, j, size) size这时候是 0
    // 1 个 1，往path中添加1个1，调用 f(arr, j, size) size这时候是 1
    // 2 个 1，往path中添加2个1，调用 f(arr, j, size) size这时候是 2
    // 3 个 1，往path中添加3个1，调用 f(arr, j, size) size这时候是 3
    // 时间复杂度: o(n * 2^n)
}
