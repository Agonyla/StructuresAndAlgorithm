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

    // todo

    // 环绕字符串中唯一的子字符串

    // 思路：
    // 类似滑动窗口，以某个位置结尾去探索它的最大长度
    // 但是本题稍微有点不同，是去探索以某一个字符结尾探索它的最大长度
    // dp表含义：int[] dp = new int[26]。
    // dp[0]表示以'a'结尾的字符串，它的最大延伸长度是多少
    // dp[25]表示以'z'结尾的字符串，它的最大延伸长度是多少
    //
    // 实现：
    // 先把字符串转成 int[] s 数组 "abc" -> {0,1,2}
    // dp[s[0]]=1 -> 表示第一个数只能延伸1位
    // 什么情况下能延伸
    // cur=s[i], pre=s[i-1]
    // pre==25 && cur==0  || pre+1==cur
    // 或者  (cur-pre+25)%26==0 ??? 试一试行不行
    // 更新最大长度
    // 返回dp中的所有值累加和


    /**
     * 环绕字符串中唯一的子字符串
     *
     * @param s
     * @return
     */
    public static int findSubstringInWraproundString(String s) {
        return 0;
    }
}
