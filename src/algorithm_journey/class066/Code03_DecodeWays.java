package algorithm_journey.class066;

import java.util.Arrays;

/**
 * 解码方法
 *
 * @author: Agony
 * @create: 2024/7/26 11:23
 * @describe: 一条包含字母 A-Z 的消息通过以下映射进行了 编码 ：
 * <p>
 * "1" -> 'A'
 * <p>
 * "2" -> 'B'
 * <p>
 * ...
 * <p>
 * "25" -> 'Y'
 * <p>
 * "26" -> 'Z'
 * <p>
 * 然而，在 解码 已编码的消息时，你意识到有许多不同的方式来解码，因为有些编码被包含在其它编码当中（"2" 和 "5" 与 "25"）。
 * <p>
 * 例如，"11106" 可以映射为：
 * <p>
 * "AAJF" ，将消息分组为 (1, 1, 10, 6)
 * "KJF" ，将消息分组为 (11, 10, 6)
 * 消息不能分组为  (1, 11, 06) ，因为 "06" 不是一个合法编码（只有 "6" 是合法的）。
 * 注意，可能存在无法解码的字符串。
 * <p>
 * 给你一个只含数字的 非空 字符串 s ，请计算并返回 解码 方法的 总数 。如果没有合法的方式解码整个字符串，返回 0。
 * <p>
 * 题目数据保证答案肯定是一个 32 位 的整数。
 * @link: <a href="https://leetcode.cn/problems/decode-ways/description/">解码方法</a>
 */
public class Code03_DecodeWays {


    public static void main(String[] args) {


        String s = "12";
        System.out.println(numDecodings1(s));
        System.out.println(numDecodings2(s));
        System.out.println(numDecodings3(s));
        System.out.println(numDecodings4(s));
        System.out.println("==============");

        s = "226";
        System.out.println(numDecodings1(s));
        System.out.println(numDecodings2(s));
        System.out.println(numDecodings3(s));
        System.out.println(numDecodings4(s));
        System.out.println("==============");

        s = "06";
        System.out.println(numDecodings1(s));
        System.out.println(numDecodings2(s));
        System.out.println(numDecodings3(s));
        System.out.println(numDecodings4(s));
    }

    // 解码方法

    // 思路：

    // numDecoding1
    // -> 暴力递归
    // 设计函数 int f(char[] s, int i)
    // return -> 从i位置出发一共有几种解码方法
    // s -> 字符串转成的数组
    // i -> 当前来到i位置
    // 当i来到边界时，返回1，-> 表示之前的尝试有一种解码方法
    // 如果i位置为0，-> 无法解码，直接返回0
    // 从i+1位置尝试，
    // 从i+2位置尝试，(i+2越界，且i位置和i+1位置的和<=26，才能尝试)
    // 两种答案累加
    //
    // numDecoding2
    // -> 改记忆化搜索
    // 设计 int[] dp = new int[s.length]，初始化都是-1
    // 如果dp[i]!=-1，说明之前来过该位置，直接返回
    // 如果i位置为0，-> 无法解码，直接返回0
    // 依然是两种尝试
    // 在返回之前把当前位置的值记录到dp中
    // 返回ans
    //
    // numDecoding3
    // -> 严格位置依赖的动态规划
    // i位置依赖i+1位置和i+2位置
    // int[] dp = new int[s.length+1]
    // 所以从右往左填，最后返回dp[0]
    // dp[n]=1
    // 如果i位置为0，dp[i]=0
    // 否则 dp[i]=dp[i+1]
    // 如果可以 dp[i]+=dp[i+2]
    // 最后返回 dp[0]
    //
    // numDecoding4
    // 压缩空间
    // 显然i位置只依赖i+1和i+2
    // 那么就不需要这几一个n长度的数组
    // 直接用连个变量代替
    // 在每次使用完之后更新


    /**
     * 解码方法
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
     * @param s 字符数组
     * @param i 当前来到i位置
     * @return 解码方法总数
     */
    public static int f1(char[] s, int i) {

        // i来到末尾，说明有一种解码的方法
        if (i == s.length) {
            return 1;
        }

        int ans;
        if (s[i] - '0' == 0) {
            ans = 0;
        } else {
            // 一个字符转换
            ans = f1(s, i + 1);
            // 两个字符转换
            if (i + 1 < s.length && ((s[i] - '0') * 10 + s[i + 1] - '0' <= 26)) {
                ans += f1(s, i + 2);
            }
        }

        return ans;
    }


    /**
     * 解码方法
     * 记忆化搜索
     *
     * @param s
     * @return
     */
    public static int numDecodings2(String s) {

        int n = s.length();
        int[] dp = new int[n + 1];
        Arrays.fill(dp, -1);
        dp[n] = 1;
        return f2(s.toCharArray(), 0, dp);
    }

    /**
     * 记忆化搜索
     *
     * @param s
     * @param i
     * @param dp dp表
     * @return
     */
    public static int f2(char[] s, int i, int[] dp) {
        if (i == s.length) {
            return 1;
        }

        if (dp[i] != -1) {
            return dp[i];
        }
        int ans;
        if (s[i] - '0' == 0) {
            ans = 0;
        } else {
            // 一个字符转换
            ans = f2(s, i + 1, dp);
            // 两个字符转换
            if (i + 1 < s.length && ((s[i] - '0') * 10 + s[i + 1] - '0' <= 26)) {
                ans += f2(s, i + 2, dp);
            }
        }

        dp[i] = ans;
        return ans;
    }


    /**
     * 解码方法
     * 从底到顶的动态规划
     *
     * @param str
     * @return
     */
    public static int numDecodings3(String str) {

        char[] s = str.toCharArray();
        int n = str.length();
        int[] dp = new int[n + 1];
        dp[n] = 1;

        for (int i = n - 1, ans; i >= 0; i--) {
            if (s[i] - '0' == 0) {
                ans = 0;
            } else {
                ans = dp[i + 1];
                if (i + 1 < n && ((s[i] - '0') * 10 + s[i + 1] - '0' <= 26)) {
                    ans += dp[i + 2];
                }
            }
            dp[i] = ans;
        }
        return dp[0];
    }


    /**
     * 解码方法
     * 从底到顶的动态规划 + 空间压缩
     *
     * @param str
     * @return
     */
    public static int numDecodings4(String str) {
        char[] s = str.toCharArray();
        int n = str.length();

        // i+1
        int next = 1;
        // i+2
        int nextNext = 0;

        for (int i = n - 1, cur; i >= 0; i--) {
            if (s[i] - '0' == 0) {
                cur = 0;
            } else {
                cur = next;
                if (i + 1 < n && ((s[i] - '0') * 10 + s[i + 1] - '0' <= 26)) {
                    cur += nextNext;
                }
            }
            nextNext = next;
            next = cur;
        }
        return next;
    }

}
















