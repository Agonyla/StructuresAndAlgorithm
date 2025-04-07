package algorithm_journey.hwod;

import algorithm_journey.utils.MathUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * @author: Agony
 * @create: 2025/4/3 22:37
 * @describe: 第k个排列
 */
public class KthPermutation_1 {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);


        int n = scanner.nextInt();
        // int k = scanner.nextInt();

        int[] nums = new int[n];
        for (int i = 1; i <= n; i++) {
            nums[i - 1] = i;
        }

        MathUtils.printArr(nums);

        backtrack(nums, 0);

        for (String an : ans) {
            System.out.println(an);
        }

    }

    public static List<String> ans = new ArrayList<>();

    public static void backtrack(int[] nums, int index) {

        if (index == nums.length) {
            StringBuilder sb = new StringBuilder();
            for (int num : nums) {
                sb.append(num);
            }
            ans.add(sb.toString());
            return;
        }
        for (int i = index; i < nums.length; i++) {
            swap(nums, index, i);
            backtrack(nums, index + 1);
            swap(nums, index, i);
        }

    }

    public static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}
