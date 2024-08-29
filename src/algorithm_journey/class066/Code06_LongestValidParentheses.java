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


    public static void main(String[] args) {


        String str = "(()";
        System.out.println(longestValidParentheses(str));

        str = ")()())";
        System.out.println(longestValidParentheses(str));

        str = "";
        System.out.println(longestValidParentheses(str));
    }

    // 最长有效括号

    // 思路：
    // 设计一张dp表 int[] dp = new int[str.length]
    // dp[i]表示以i位置作结尾的最长有效括号的长度
    // dp[0]=0 -> 默认就是0。当str=""时，该语句有问题，所以不能加 ⚠️⚠️⚠️
    // 如果 str[i]=='('， 那么dp[i]=0
    // 如果 str[i]==')'， 根据 dp[i-1] 的长度往前跳到匹配的位置 p，p=i-dp[i-1]-1   dp[i-1]： 前一个位置的最长有效长度
    // 如果 str[p]=='('， 那么 dp[i]= dp[i-1]+2+dp[p-1]，+2就是因为有连个位置肯定可以配对，然后再加dp[i-1]的有效长度
    // 如果 str[p]==')'， 那么dp[i]=0
    // dp[p-1]：之前位置位置的最长有效长度
    //
    // 当然以上需要判断 p和p-1是否越界
    //
    // 为什么只加一个dp[p-1]就行，不用再往左判断
    // 因为dp[p-1]已经是其结尾的最长长度，它已经往左判断过了
    //
    // 然后返回 dp 中的最大值
    //
    // (()()())


    /**
     * 最长有效括号
     *
     * @param s
     * @return
     */
    public static int longestValidParentheses(String s) {


        int n = s.length();
        char[] str = s.toCharArray();

        int[] dp = new int[n];

        int ans = 0;
        for (int i = 1; i < n; i++) {

            if (str[i] == '(') {
                dp[i] = 0;
            } else {
                int p = i - dp[i - 1] - 1;
                if (p >= 0 && str[p] == '(') {
                    dp[i] = dp[i - 1] + 2 + (p - 1 >= 0 ? dp[p - 1] : 0);
                }
            }
            ans = Math.max(ans, dp[i]);
        }

        return ans;
    }
}









