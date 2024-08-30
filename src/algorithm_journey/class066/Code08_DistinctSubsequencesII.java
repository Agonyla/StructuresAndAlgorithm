package algorithm_journey.class066;

/**
 * 不同的子序列 II
 *
 * @author: Agony
 * @create: 2024/7/27 16:52
 * @describe: 给定一个字符串 s，计算 s 的 不同非空子序列 的个数。因为结果可能很大，所以返回答案需要对 10^9 + 7 取余 。
 * <p>
 * 字符串的 子序列 是经由原字符串删除一些（也可能不删除）字符但不改变剩余字符相对位置的一个新字符串。
 * <p>
 * 例如，"ace" 是 "abcde" 的一个子序列，但 "aec" 不是。
 * @link: <a href="https://leetcode.cn/problems/distinct-subsequences-ii/description/">不同的子序列 II</a>
 */
public class Code08_DistinctSubsequencesII {

    // todo

    public static void main(String[] args) {

        System.out.println((int) Math.pow(2, 5));

        System.out.println(distinctSubseqII("abc"));
        System.out.println(distinctSubseqII("aba"));
        System.out.println(distinctSubseqII("aaa"));
    }

    // 不同的子序列 II

    // 思路：
    // 设计 int[] cnts = new int[26]
    // cnts[0]表示以'a'结尾的字符串个数
    // cnts[25]表示以'z'结尾的字符串个数
    // int all=1。表示一开始就有一个空字符串
    // int newAdd=0。表示新增字符串 (以str[i]位置作为结尾的新增字符串)
    // newAdd=all-cnts[str[i]] -> 新增=所有字符串的个数-表中该字符有的个数
    // cnts[str[i]]+=newAdd -> 表中的个数+=新增的个数
    // all+=newAdd -> 所有字符串+=新增的个数
    //
    //
    // 在过程中，在保留原有的子序列下，给每一个子序列末尾都添加了一个str[i]，但是考虑到有重复的问题，所以还要减去之前表里记录的
    // 所有 ->  newAdd=all-cnts[str[i]]
    // 如s="ab"
    // 一开始只有 {}
    // i=0，{},{a}
    // i=1，{},{a},{b},{ab}
    //
    //
    // 如 s="abab"
    // 一开始：{}，all=1
    // i=0:{} + {a}   ->  新增了1( all-cnts['a'] )，cnts['a']+=newAll=1，all+=newAdd=2
    // i=1:{}、{a} + {b}、{ab} -> 新增了2个( all-cnts['b'] )，cnts['b']+=newAdd=2，all+=newAdd=4
    // i=2:{}、{a}、{b}、{ab} + {a}、{aa}、{ba}、{aba} -> {a}重复，新增了3个( all-cnts['a'] )，cnts['a']+=newAdd=4，all+=newAdd=7
    // i=3:{}、{a}、{b}、{ab}、{aa}、{ba}、{aba} + {b}、{ab}、{bb}、{abb}、{aab}、{bab}、{abab} -> {b}、{ab}重复，新增了5个( all-cnts['b'] )，cnts['b']+=newAdd=7，all+=newAdd=12
    // all:{}、{a}、{b}、{ab}、{aa}、{ba}、{aba}、{bb}、{abb}、{aab}、{bab}、{abab}


    public static int mod = 1000000007;


    /**
     * 不同的子序列 II
     *
     * @param s
     * @return
     */
    public static int distinctSubseqII(String s) {
        
        int[] dp = new int[26];
        char[] str = s.toCharArray();

        int all = 1;
        for (int i = 0, newAdd; i < str.length; i++) {
            newAdd = (all - dp[str[i] - 'a'] + mod) % mod;
            dp[str[i] - 'a'] = (dp[str[i] - 'a'] + newAdd) % mod;
            all = (all + newAdd) % mod;
        }
        return (all - 1 + mod) % mod;
    }

}












