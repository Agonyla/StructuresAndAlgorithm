package algorithm_journey.leetcode;

import algorithm_journey.utils.MathUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: Agony
 * @create: 2025/4/8 11:06
 * @describe: <a href="https://leetcode.cn/problems/palindrome-partitioning/description/?envType=company&envId=huawei&favoriteSlug=huawei-three-months">分割回文串</a>
 */
public class LeetCode131 {


    // todo 再看一遍

    public static void main(String[] args) {

        for (List<String> list : partition("aab")) {
            MathUtils.printList(list);
        }


    }

    /**
     * 回溯法解决分割回文串问题
     * 时间复杂度：O(n * 2^n)，其中n是字符串长度
     * 空间复杂度：O(n^2)，用于存储动态规划表和回溯递归栈
     */
    public static List<List<String>> partition(String s) {
        int len = s.length();
        List<List<String>> result = new ArrayList<>();
        // 特殊情况处理
        if (len == 0) {
            return result;
        }

        // 预处理：使用动态规划判断字符串的子串是否为回文
        // dp[i][j]表示s[i...j]是否为回文串
        boolean[][] dp = new boolean[len][len];

        // 预处理所有子串是否为回文
        for (int right = 0; right < len; right++) {
            for (int left = 0; left <= right; left++) {
                // 当子串长度为1时，一定是回文
                // 当子串长度为2时，两个字符相同则为回文
                // 当子串长度大于2时，首尾字符相同且中间子串为回文，则为回文
                if (s.charAt(left) == s.charAt(right) && (right - left <= 2 || dp[left + 1][right - 1])) {
                    dp[left][right] = true;
                }
            }
        }

        // 回溯算法，从字符串开头开始切割
        backtrack(s, 0, dp, new ArrayList<>(), result);

        return result;
    }

    /**
     * 回溯方法，尝试从start位置开始切割字符串
     *
     * @param s      原始字符串
     * @param start  当前切割起始位置
     * @param dp     动态规划表，用于判断子串是否为回文
     * @param path   当前路径（已切割的回文子串列表）
     * @param result 所有可能的分割方案
     */
    private static void backtrack(String s, int start, boolean[][] dp, List<String> path, List<List<String>> result) {
        // 如果已经到达字符串末尾，说明找到了一种分割方案
        if (start == s.length()) {
            result.add(new ArrayList<>(path));
            return;
        }

        // 尝试从start开始的所有可能切割点
        for (int i = start; i < s.length(); i++) {
            // 如果从start到i的子串是回文，则可以切割
            if (dp[start][i]) {
                // 添加这个回文子串到当前路径
                path.add(s.substring(start, i + 1));
                // 从i+1位置继续切割剩余部分
                backtrack(s, i + 1, dp, path, result);
                // 回溯，移除最后添加的子串
                path.remove(path.size() - 1);
            }
        }
    }
}
