package algorithm_journey.leetcode;

/**
 * @author: Agony
 * @create: 2025/4/8 11:10
 * @describe: <a href="https://leetcode.cn/problems/longest-palindromic-substring/?envType=company&envId=huawei&favoriteSlug=huawei-three-months">最长回文子串</a>
 */
public class LeetCode5 {

    public static void main(String[] args) {

        System.out.println(longestPalindrome("babad"));
        System.out.println(longestPalindrome("cbbd"));
    }


    public static String longestPalindrome(String s) {
        if (s == null || s.isEmpty()) {
            return "";
        }

        int start = 0, end = 0;

        for (int i = 0; i < s.length(); i++) {
            // 考虑以i为中心的奇数长度回文串
            int len1 = expandAroundCenter(s, i, i);
            // 考虑以i和i+1为中心的偶数长度回文串
            int len2 = expandAroundCenter(s, i, i + 1);
            // 取两种情况的最大值
            int len = Math.max(len1, len2);

            // 如果找到更长的回文子串，更新起止位置
            if (len > end - start) {
                start = i - (len - 1) / 2;
                end = i + len / 2;
            }
        }

        return s.substring(start, end + 1);
    }

    /**
     * 从给定的左右位置向两边扩展，返回能够形成的最长回文子串长度
     */
    private static int expandAroundCenter(String s, int left, int right) {
        while (left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)) {
            left--;
            right++;
        }
        // 返回回文串的长度
        return right - left - 1;
    }
}
