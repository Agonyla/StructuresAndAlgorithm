package algorithm_journey.class066;

import java.util.Arrays;

/**
 * 解码方法 II
 *
 * @author: Agony
 * @create: 2024/7/26 11:51
 * @describe: 一条包含字母 A-Z 的消息通过以下的方式进行了 编码 ：
 * <p>
 * 'A' -> "1"
 * 'B' -> "2"
 * ...
 * 'Z' -> "26"
 * 要 解码 一条已编码的消息，所有的数字都必须分组，然后按原来的编码方案反向映射回字母（可能存在多种方式）。例如，"11106" 可以映射为：
 * <p>
 * "AAJF" 对应分组 (1 1 10 6)
 * "KJF" 对应分组 (11 10 6)
 * 注意，像 (1 11 06) 这样的分组是无效的，因为 "06" 不可以映射为 'F' ，因为 "6" 与 "06" 不同。
 * <p>
 * 除了 上面描述的数字字母映射方案，编码消息中可能包含 '*' 字符，可以表示从 '1' 到 '9' 的任一数字（不包括 '0'）。例如，编码字符串 "1*" 可以表示 "11"、"12"、"13"、"14"、"15"、"16"、"17"、"18" 或 "19" 中的任意一条消息。对 "1*" 进行解码，相当于解码该字符串可以表示的任何编码消息。
 * <p>
 * 给你一个字符串 s ，由数字和 '*' 字符组成，返回 解码 该字符串的方法 数目 。
 * <p>
 * 由于答案数目可能非常大，返回 109 + 7 的 模 。
 * @link: <a href="https://leetcode.cn/problems/decode-ways-ii/description/">解码方法 II</a>
 */
public class Code04_DecodeWaysII {


    // todo
    public static void main(String[] args) {


        String s = "*";
        System.out.println(numDecodings1(s));
        System.out.println(numDecodings2(s));

        s = "1*";
        System.out.println(numDecodings1(s));
        System.out.println(numDecodings2(s));

        s = "2*";
        System.out.println(numDecodings1(s));
        System.out.println(numDecodings2(s));
    }

    // 解码方法 II

    // 思路：
    // 流程和上一题一致，需要讨论
    // 1. i位置为0，直接返回0
    //
    // 2. i位置单独解码，从i+1位置开始
    // 2.1 如果i位置为数字，-> 只有一种结果，
    // 2.2 如果i位置为*，-> 有9种结果
    //
    // 3. i位置和i+1位置一起解码，从i+2位置开始
    // 3.1 i位置为数字，i+1位置为数字，-> 只有和<=26时，有1种结果
    // 3.2 i位置为数字，i+1位置为*，-> 1) i位置为1，有9种; 2) i位置为2，有6种；3) i位置>2,没有
    // 3.3 i位置为*，i+1位置为数字，-> 1) i+1位置<=6，有2种；2) i+1位置>6，有1种
    // 3.4 i位置为*，i+1位置为*，-> 有15种


    public static long mod = 1000000007;

    /**
     * 解码方法 II
     * 暴力递归
     *
     * @param s
     * @return
     */
    public static int numDecodings1(String s) {
        return f1(s.toCharArray(), 0);
    }


    /**
     * 暴力递归
     *
     * @param s
     * @param i
     * @return
     */
    public static int f1(char[] s, int i) {

        if (i == s.length) {
            return 1;
        }

        int ans;
        if (s[i] == '0') {
            ans = 0;
        } else {

            // 自己解码
            ans = s[i] == '*' ? 9 * f1(s, i + 1) : f1(s, i + 1);

            // 和下一个字符一起解码
            if (i + 1 < s.length) {

                // 第一个字符是*
                if (s[i] == '*') {
                    // 第二个字符是*
                    if (s[i + 1] == '*') {
                        ans += 15 * f1(s, i + 2);
                    } else {
                        ans += (s[i + 1] - '0' > 6) ? f1(s, i + 2) : 2 * f1(s, i + 2);
                    }
                } else {
                    // 第二个字符是*
                    if (s[i + 1] == '*') {
                        if (s[i] == '1') {
                            ans += 9 * f1(s, i + 2);
                        }
                        if (s[i] == '2') {
                            ans += 6 * f1(s, i + 2);
                        }
                    } else {
                        if ((s[i] - '0') * 10 + s[i + 1] - '0' <= 26) {
                            ans += f1(s, i + 2);
                        }
                    }
                }
            }

        }

        return ans %= mod;
    }


    /**
     * 解码方法 II
     * 记忆化搜索
     *
     * @param s
     * @return
     */
    public static int numDecodings2(String s) {

        int n = s.length();
        long[] dp = new long[n + 1];
        Arrays.fill(dp, -1);
        return (int) f2(s.toCharArray(), 0, dp);
    }


    /**
     * 记忆化搜索
     *
     * @param s
     * @param i
     * @param dp dp表
     * @return
     */
    public static long f2(char[] s, int i, long[] dp) {

        if (i == s.length) {
            return 1;
        }
        if (dp[i] != -1) {
            return dp[i];
        }
        long ans;
        if (s[i] == '0') {
            ans = 0;
        } else {
            // 自己解码
            ans = s[i] == '*' ? 9 * f2(s, i + 1, dp) : f2(s, i + 1, dp);

            // 和下一个字符一起解码
            if (i + 1 < s.length) {

                // 第一个字符是*
                if (s[i] == '*') {
                    // 第二个字符是*
                    if (s[i + 1] == '*') {
                        ans += 15 * f2(s, i + 2, dp);
                    } else {
                        ans += (s[i + 1] - '0' > 6) ? f2(s, i + 2, dp) : 2 * f2(s, i + 2, dp);
                    }
                } else {
                    // 第二个字符是*
                    if (s[i + 1] == '*') {
                        if (s[i] == '1') {
                            ans += 9 * f2(s, i + 2, dp);
                        }
                        if (s[i] == '2') {
                            ans += 6 * f2(s, i + 2, dp);
                        }
                    } else {
                        if ((s[i] - '0') * 10 + s[i + 1] - '0' <= 26) {
                            ans += f2(s, i + 2, dp);
                        }
                    }
                }
            }
        }
        ans %= mod;
        dp[i] = ans;
        return ans;
    }


    // todo 从底到顶
    // todo 空间压缩
}



















