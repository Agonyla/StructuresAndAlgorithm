package algorithm_journey.leetcode;

import algorithm_journey.utils.MathUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: Agony
 * @create: 2025/4/7 10:34
 * @describe: 全排列
 * <a href="https://leetcode.cn/problems/permutations/?envType=company&envId=huawei&favoriteSlug=huawei-three-months">全排列</a>
 */
public class LeetCode46 {

    public static void main(String[] args) {

        int[] nums = {1, 2, 3};
        for (List<Integer> integers : permute(nums)) {
            MathUtils.printList(integers);
        }

    }


    public static List<List<Integer>> permute(int[] nums) {
        ans = new ArrayList<>();
        f(nums, 0);
        return ans;
    }


    public static List<List<Integer>> ans;


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


    public static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }


}


// class Solution {
//   public static List<List<Integer>> permute(int[] nums) {
//         ans = new ArrayList<>();
//         f(nums, 0);
//         return ans;
//     }
//
//
//     public static List<List<Integer>> ans;
//
//
//     public static void f(int[] arr, int index) {
//
//         // 来到边界
//         if (index == arr.length) {
//             ArrayList<Integer> list = new ArrayList<>();
//             for (int i : arr) {
//                 list.add(i);
//             }
//             ans.add(list);
//             return;
//         }
//
//         // index 位置和自己及以后的所有元素交换，去调用下一层递归
//         for (int j = index; j < arr.length; j++) {
//             swap(arr, index, j);
//             f(arr, index + 1);
//             swap(arr, index, j);
//         }
//     }
//
//
//
//     public static void swap(int[] arr, int i, int j) {
//         int temp = arr[i];
//         arr[i] = arr[j];
//         arr[j] = temp;
//     }
// }