package algorithm_journey.class038;

import java.util.ArrayList;
import java.util.List;

/**
 * 全排列
 *
 * @author: Agony
 * @create: 2024/6/30 21:45
 * @describe: 给定一个不含重复数字的数组 nums ，返回其所有可能的全排列 。你可以 按任意顺序 返回答案。
 * 输入：nums = [1,2,3]
 * 输出：[[1,2,3],[1,3,2],[2,1,3],[2,3,1],[3,1,2],[3,2,1]]
 * @link: <a href="https://leetcode.cn/problems/permutations/description/">全排列</a>
 */
public class Code03_Permutations {


    public static void main(String[] args) {
        int[] arr = new int[]{1, 2, 3};
        permute(arr).forEach(list -> {
            list.forEach(num -> System.out.print(num + " "));
            System.out.println();
        });

    }
    

    // 没有重复项数字的全排列
    // 思路
    // 设计一个函数 f(int[] num, int index)
    // num -> 提供的数组
    // index -> 数组的下标
    // 来到 i 位置
    // i 位置数与自己交换，调用递归
    // i 位置数与 i+1 交换，调用递归
    // i 位置数与 i+2 交换，调用递归
    // 。。。
    // i 位置数与 num.length - 1 交换，调用递归
    // 注意！！！ 交换玩之后还要换回来
    // 保证换之前的数组状态

    // 为什么要先和自己交换呢？
    // 因为当 i 来到 最终位置之后是要收集的
    // 如果不和自己交换，那么数组的初始顺序是收集不到的
    // 如 [1, 2] 要实现全排列
    // 如果 1 直接 2 交换，那么 1 直接和 2 交换然后收集就只有 [2,1] 这一项
    // 如 [1,2,3] 要是实现全排列
    // 如果不和自己交换的话，那么就收集不到 [1,2,3,] 和 [1,3,2] 两项了
    // 时间复杂度: o(n! * n)

    // 画一下递归调用图！！！


    /**
     * 没有重复项数字的全排列
     *
     * @param nums
     * @return
     */
    public static List<List<Integer>> permute(int[] nums) {
        ans = new ArrayList<>();
        f(nums, 0);
        return ans;
    }


    public static List<List<Integer>> ans;

    /**
     * 实现全排列
     *
     * @param arr   数组
     * @param index 下标
     */
    public static void f(int[] arr, int index) {

        // 来到边界
        if (index == arr.length) {
            ArrayList<Integer> list = new ArrayList<>();
            for (int i : arr) {
                list.add(i);
            }
            ans.add(list);
            return;
        }

        // index 位置和自己及以后的所有元素交换，去调用下一层递归
        for (int j = index; j < arr.length; j++) {
            swap(arr, index, j);
            f(arr, index + 1);
            swap(arr, index, j);
        }
    }


    /**
     * 数组交换两个值
     *
     * @param arr
     * @param i
     * @param j
     */
    public static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}






















