package algorithm_journey.class049;

/**
 * 最小覆盖子串
 *
 * @author: Agony
 * @create: 2024/7/24 9:13
 * @describe: 给你一个字符串 s 、一个字符串 t 。返回 s 中涵盖 t 所有字符的最小子串。如果 s 中不存在涵盖 t 所有字符的子串，则返回空字符串 "" 。
 * <p>
 * 输入：s = "ADOBECODEBANC", t = "ABC"
 * 输出："BANC"
 * 解释：最小覆盖子串 "BANC" 包含来自字符串 t 的 'A'、'B' 和 'C'。
 * @link: <a href="https://leetcode.cn/problems/minimum-window-substring/description/">最小覆盖子串</a>
 */
public class Code03_MinimumWindowSubstring {


    public static void main(String[] args) {
        String s = "ADOBECODEBANC";
        String t = "ABC";
        System.out.println(minWindow(s, t));
    }

    // 最小覆盖子串

    // 思路：
    // 设计一个 int[] cnts = new int[]
    // 下标为字符的ASCII码，所有的字符可以转成数组对应下标。'a' -> 97
    // 值为其欠的个数
    // target="aa"
    // cnts['a']=-2
    // 一个变量 debt，表示总欠债

    // 流程：
    // 串口从左边界开始，右窗口遇到一个字符，执行 cnts[字符]++
    // 如果遇到欠款字符，debt-=1
    // 当debt==0时候，更新答案
    // 左窗口向右滑动，遇到字符执行 cnts[字符]--
    // 如果更新了会让debt!=0时，那就在此停止
    // 更新答案
    // 。。。
    // 循环往复向右滑动，知道右窗口来到字符串边界


    /**
     * 最小覆盖子串
     *
     * @param str
     * @param target
     * @return
     */
    public static String minWindow(String str, String target) {

        if (target.length() > str.length()) {
            return "";
        }

        char[] s = str.toCharArray();

        char[] t = target.toCharArray();

        // 记录每个字符出现的个数
        int[] cnts = new int[256];

        for (char c : t) {
            cnts[c]--;
        }

        // 最小字串的长度
        int length = Integer.MAX_VALUE;

        // 字串开始的位置
        int start = 0;

        for (int r = 0, l = 0, debt = target.length(); r < str.length(); r++) {

            // 如果当前字符欠款，那么就加入窗口，欠款--
            if (cnts[s[r]] < 0) {
                debt--;
            }
            cnts[s[r]]++;

            if (debt == 0) {

                // 左窗口尽可能往右缩
                while (cnts[s[l]] > 0) {
                    cnts[s[l++]]--;
                }
                if (r - l + 1 < length) {
                    length = r - l + 1;
                    start = l;
                }
            }
        }
        return length == Integer.MAX_VALUE ? "" : str.substring(start, start + length);
    }
}
