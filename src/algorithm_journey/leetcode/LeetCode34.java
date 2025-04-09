package algorithm_journey.leetcode;

/**
 * @author: Agony
 * @create: 2025/4/9 10:46
 * @describe: LCP 34. 二叉树染色
 * <a href="https://leetcode.cn/problems/er-cha-shu-ran-se-UGC/?envType=company&envId=huawei&favoriteSlug=huawei-three-months">LCP 34. 二叉树染色</a>
 */
public class LeetCode34 {


    public static void main(String[] args) {

    }

    public static int maxValue(TreeNode root, int k) {

        int[] result = dfs(root, k);
        int max = 0;
        for (int i = 0; i <= k; i++) {
            max = Math.max(max, result[i]);
        }
        return max;

    }


    public static int[] dfs(TreeNode root, int k) {

        // dp[i]表示以当前节点为根的子树中，蓝色相连部分的节点个数恰好为i时，所能获得的最大价值和
        int[] dp = new int[k + 1];

        if (root == null) {
            return dp;
        }

        int[] leftDP = dfs(root.left, k);
        int[] rightDP = dfs(root.right, k);

        int leftMax = 0;
        int rightMax = 0;


        for (int i = 0; i <= k; i++) {
            leftMax = Math.max(leftMax, leftDP[i]);
            rightMax = Math.max(rightMax, rightDP[i]);
        }

        dp[0] = leftMax + rightMax;


        for (int i = 1; i <= k; i++) {

            for (int leftCount = 0; leftCount < k; leftCount++) {
                int rightCount = i - 1 - leftCount;
                if (rightCount >= 0) {
                    dp[i] = Math.max(dp[i], root.val + leftDP[leftCount] + rightDP[rightCount]);
                }
            }
        }

        return dp;
    }


}


class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode(int x) {
        val = x;
    }
}