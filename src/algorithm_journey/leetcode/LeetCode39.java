package algorithm_journey.leetcode;

import algorithm_journey.utils.MathUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: Agony
 * @create: 2025/4/9 11:10
 * @describe: 39. 组合总和
 * <a href="https://leetcode.cn/problems/combination-sum/?envType=company&envId=huawei&favoriteSlug=huawei-three-months">39. 组合总和</a>
 */
public class LeetCode39 {


    public static void main(String[] args) {


        int[] candidates = new int[]{2, 3, 6, 7};
        List<List<Integer>> lists = combinationSum(candidates, 7);

        for (List<Integer> list : lists) {
            MathUtils.printList(list);
        }
    }


    public static List<List<Integer>> combinationSum(int[] candidates, int target) {
        ans = new ArrayList<>();
        backtrack(candidates, 0, target, new ArrayList<>());
        return ans;
    }


    private static List<List<Integer>> ans;

    public static void backtrack(int[] candidates, int index, int target, List<Integer> current) {


        if (target == 0) {
            // 注意这里需要 new
            ans.add(new ArrayList<>(current));
            return;
        }
        if (target < 0) {
            return;
        }

        for (int i = index; i < candidates.length; i++) {

            current.add(candidates[i]);

            backtrack(candidates, i, target - candidates[i], current);

            current.remove(current.size() - 1);

        }


    }
}
