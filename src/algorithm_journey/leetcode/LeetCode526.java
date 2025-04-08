package algorithm_journey.leetcode;

/**
 * @author: Agony
 * @create: 2025/4/8 21:24
 * @describe: 优美的排列
 * <a href="https://leetcode.cn/problems/beautiful-arrangement/description/?envType=company&envId=huawei&favoriteSlug=huawei-three-months">优美的排列</a>
 */
public class LeetCode526 {


    public static void main(String[] args) {

        System.out.println(countArrangement(2));
        System.out.println(countArrangement(3));
        System.out.println(countArrangement(1));

    }

    public static int countArrangement(int n) {


        boolean[] used = new boolean[n + 1];
        backtrack(n, 1, used);

        int ans = count;
        count = 0;
        return ans;
    }

    public static int count = 0;

    public static void backtrack(int n, int index, boolean[] used) {
        if (index > n) {
            count++;
            return;
        }

        for (int i = 1; i <= n; i++) {

            if (!used[i] && (index % i == 0 || i % index == 0)) {
                used[i] = true;
                backtrack(n, index + 1, used);
                used[i] = false;
            }
        }

    }


}
