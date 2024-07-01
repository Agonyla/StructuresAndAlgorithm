package algorithm_journey.class038;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 子集 II
 *
 * @author: Agony
 * @create: 2024/6/28 11:29
 * @describe: 给你一个整数数组 nums ，其中可能包含重复元素，请你返回该数组所有可能的子集（幂集）。
 * <p>
 * 输入：nums = [1,2,2]
 * 输出：[[],[1],[1,2],[1,2,2],[2],[2,2]]
 * 解集 不能 包含重复的子集。返回的解集中，子集可以按 任意顺序 排列。
 * @link: <a href="https://leetcode.cn/problems/subsets-ii/description/">子集 II</a>
 */
public class Code02_Combinations {

    public static void main(String[] args) {

        int[] nums = new int[]{1, 2, 2};
        subsetsWithDup(nums).forEach(list -> {
            list.forEach(num -> System.out.print(num + " "));
            System.out.println();
        });
    }
    

    // 给你一个整数数组 nums ，其中可能包含重复元素，请你返回该数组所有可能的组合

    // 思路
    // 现将这组数组排序 -> 如 [1, 1, 1, 2, 2, 2, 2, 3, 3]
    // List<List<Integer>> ans -> 记录答案
    // int[] path -> 记录路径中添加的数字
    // 实现函数 f(int[] arr, int i, int size)
    // arr -> 排序之后的整数数组
    // i -> 数组的下标
    // size -> path 中添加的元素个数
    // 首先来到下一个不同的元素， j = 3
    // 然后
    // 0 个 1，不往path中添加，调用 f(arr, j, size) size这时候是 0
    // 1 个 1，往path中添加1个1，调用 f(arr, j, size) size这时候是 1
    // 2 个 1，往path中添加2个1，调用 f(arr, j, size) size这时候是 2
    // 3 个 1，往path中添加3个1，调用 f(arr, j, size) size这时候是 3
    // 时间复杂度: o(n * 2^n)


    /**
     * 给你一个整数数组 nums ，其中可能包含重复元素，请你返回该数组所有可能的组合
     *
     * @param nums
     * @return
     */
    public static List<List<Integer>> subsetsWithDup(int[] nums) {
        ans = new ArrayList<>();
        path = new int[nums.length];
        Arrays.sort(nums);
        f(nums, 0, 0);
        return ans;
    }


    // ans，记录所有组合
    public static List<List<Integer>> ans;


    // 路径，用来记录添加的数字
    public static int[] path;


    /**
     * 数组通过排序之后
     * 相同的元素为一组
     *
     * @param arr   排列之后的数组
     * @param index 数组下标
     * @param size  path 添加元素的数量
     */
    public static void f(int[] arr, int index, int size) {

        // 来到边界，往ans里加添list
        if (index == arr.length) {
            ArrayList<Integer> list = new ArrayList<>();

            // 这里遍历不能 path.iter -> 因为 path 没有值里面全是 0
            // 要遍历 size
            for (int i = 0; i < size; i++) {
                list.add(path[i]);
            }
            ans.add(list);
            return;
        }

        // 找到下一个不同的元素
        // 下一组的第一个元素
        int j = index + 1;
        while (j < arr.length && arr[j] == arr[index]) {
            j++;
        }

        // 什么都不加，直接去下一组
        f(arr, j, size);

        // 加 1 个， 加 2 个...
        while (index < j) {
            path[size++] = arr[index];
            f(arr, j, size);
            index++;
        }
    }


}


























