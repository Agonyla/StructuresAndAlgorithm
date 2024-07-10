package algorithm_journey.class042;

/**
 * @author: Agony
 * @create: 2024/7/8 12:59
 * @describe:
 * @link:
 */
public class Code04_RedPalindromeGoodStrings {

    // todo

    // 要求只有一个长度>=2的回文子串，求所有长度为n的red字符串中好串的数量
    // 可以用r、e、d三种字符拼接字符串，如果拼出来的字符串中
    // 有且仅有1个长度>=2的回文子串，那么这个字符串定义为"好串"
    // 返回长度为n的所有可能的字符串中，好串有多少个
    // 结果对1000000007取模， 1 <= n <= 10^9
    // 示例：
    // n = 1, 输出0
    // n = 2, 输出3
    // n = 3, 输出18


    // 思路
    // 先用暴力方法拼出所有字符串
    // 再遍历所有的字串去验证是否是好串


    /**
     * 返回 1～n中到好串数量
     *
     * @param n
     * @return
     */
    public static int goodString(int n) {
        return 0;
    }
}
