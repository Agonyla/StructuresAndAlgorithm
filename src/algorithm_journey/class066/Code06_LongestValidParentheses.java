package algorithm_journey.class066;

/**
 * 最长有效括号
 *
 * @author: Agony
 * @create: 2024/7/27 11:52
 * @describe: 给你一个只包含 '(' 和 ')' 的字符串，找出最长有效（格式正确且连续）括号子串的长度。
 * <p>
 * 示例 1：
 * <p>
 * 输入：s = "(()"
 * 输出：2
 * 解释：最长有效括号子串是 "()"
 * 示例 2：
 * <p>
 * 输入：s = ")()())"
 * 输出：4
 * 解释：最长有效括号子串是 "()()"
 * @link: <a href="https://leetcode.cn/problems/longest-valid-parentheses/description/">最长有效括号</a>
 */
public class Code06_LongestValidParentheses {

    // todo

    public static void main(String[] args) {

    }

    // 最长有效括号

    // 思路：
    // 设计一张dp表 int[] dp = new int[str.length]
    // dp[i]表示以i位置作结尾的最长有效括号的长度
    // dp[0]=0
    // 如果str[i]=='('，那么dp[i]=0
    // 如果str[i]=')'，那么变量 p 来到 i-dp[i-1]-1 位置
    // 如果 dp[p]='('，那么 dp[i]= dp[i-1]+2+dp[p-1]，+2就是因为有连个位置肯定可以配对，然后再加dp[i-1]的有效长度
    // 如果 dp[p]=')'，那么dp[i]=0
    //
    // 为什么只加一个dp[p-1]就行，不用再往左判断
    // 因为dp[p-1]已经是其结尾的最长长度，它已经往左判断过了
    //
    // 然后返回 dp 中的最大值


    /**
     * 最长有效括号
     *
     * @param s
     * @return
     */
    public static int longestValidParentheses(String s) {

        return 0;
    }
}









