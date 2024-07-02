package algorithm_journey.class038;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

/**
 * 全排列 II
 *
 * @author: Agony
 * @create: 2024/7/1 19:56
 * @describe: 给定一个可包含重复数字的序列 nums ，按任意顺序返回所有不重复的全排列。输入：nums = [1,1,2]
 * 输出：
 * [[1,1,2], [1,2,1], [2,1,1]]
 * @link: <a href="https://leetcode.cn/problems/permutations-ii/description/">全排列 II</a>
 */
public class Code04_PermutationWithoutRepetition {


    public static void main(String[] args) {
        int[] arr = new int[]{1, 1, 3};
        permuteUnique(arr).forEach(list -> {
            list.forEach(num -> System.out.print(num + " "));
            System.out.println();
        });
    }


    // 有重复项数组的去重全排列

    // 实现和上一题一样
    // 多准备一个 hashset 用来去重


    /**
     * 有重复项数组的去重全排列
     *
     * @param nums
     * @return
     */
    public static List<List<Integer>> permuteUnique(int[] nums) {
        ans = new ArrayList<>();
        f(nums, 0);
        return ans;
    }


    public static List<List<Integer>> ans;


    /**
     * @param arr
     * @param i
     */
    public static void f(int[] arr, int i) {

        if (i == arr.length) {
            ArrayList<Integer> cur = new ArrayList<>();
            for (int num : arr) {
                cur.add(num);
            }
            ans.add(cur);
            return;
        }

        HashSet<Integer> set = new HashSet<>();
        for (int j = i; j < arr.length; j++) {
            // 如果包含了就直接提过
            // 相当于遇到了相同的数字就不交换，直接和下一个数字交换
            if (set.contains(arr[j])) {
                continue;
            }
            set.add(arr[j]);
            swap(arr, i, j);
            f(arr, i + 1);
            swap(arr, i, j);
        }
    }


    public static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

}



















