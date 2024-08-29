package algorithm_journey.class066;

/**
 * 环绕字符串中唯一的子字符串
 *
 * @author: Agony
 * @create: 2024/7/27 16:21
 * @describe: 定义字符串 base 为一个 "abcdefghijklmnopqrstuvwxyz" 无限环绕的字符串，所以 base 看起来是这样的：
 * <p>
 * "...zabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcd....".
 * 给你一个字符串 s ，请你统计并返回 s 中有多少 不同非空子串 也在 base 中出现。
 * @link: <a href="https://leetcode.cn/problems/unique-substrings-in-wraparound-string/description/">环绕字符串中唯一的子字符串</a>
 */
public class Code07_UniqueSubstringsWraparoundString {


    public static void main(String[] args) {

        System.out.println(findSubstringInWraproundString("a"));
        System.out.println(findSubstringInWraproundString("cac"));
        System.out.println(findSubstringInWraproundString("zab"));
    }

    // 环绕字符串中唯一的子字符串

    // 思路：
    // 类似滑动窗口，以某个位置结尾去探索它的最大长度
    // 但是本题稍微有点不同，是去探索以某一个字符结尾探索它的最大长度
    // dp表含义：int[] dp = new int[26]。
    // dp[0]表示以'a'结尾的字符串，它的最大延伸长度是多少
    // dp[25]表示以'z'结尾的字符串，它的最大延伸长度是多少
    // 把dp表所有值累加就是答案
    //
    // 实现：
    // 先把字符串转成 int[] s 数组。如 "abc" -> {0,1,2}  关键 ‼️
    // dp[s[0]]=1 -> 表示s字符串中以第1个字符结尾的子串只能延伸1位
    // dp[s[4]]=5 -> 表示s字符串中以第5个字符结尾的子串只能延伸5位
    // 什么情况下能延伸
    // cur=s[i], pre=s[i-1]
    // if(pre==25 && cur==0  || pre+1==cur)
    // 或者  (cur-pre+25)%26==0 ??? 试一试行不行
    // length++
    // 否则 length=1
    // 更新最大长度 -> dp[s[i]]=length
    // 返回dp中的所有值累加和


    /**
     * 环绕字符串中唯一的子字符串
     *
     * @param str
     * @return
     */
    public static int findSubstringInWraproundString(String str) {

        int n = str.length();
        int[] s = new int[n];
        int[] dp = new int[26];

        // abad -> 0103
        for (int i = 0; i < n; i++) {
            s[i] = str.charAt(i) - 'a';
        }

        dp[s[0]] = 1;

        for (int i = 1, cur, pre, length = 1; i < n; i++) {
            cur = s[i];
            pre = s[i - 1];
            // (cur - pre + 25) % 26 == 0 这个也可以
            if ((cur == 0 && pre == 25) || (cur == pre + 1)) {
                length++;
            } else {
                length = 1;
            }
            dp[cur] = Math.max(dp[cur], length);
        }

        int ans = 0;
        for (int i = 0; i < 26; i++) {
            ans += dp[i];
        }

        return ans;
    }
}


















