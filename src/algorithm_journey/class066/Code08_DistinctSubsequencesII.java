package algorithm_journey.class066;

/**
 * @author: Agony
 * @create: 2024/7/27 16:52
 * @describe:
 * @link:
 */
public class Code08_DistinctSubsequencesII {

    // todo

    // 不同的子序列 II

    // 思路：
    // 设计 int[] cnts = new int[26]
    // cnts[0]表示以'a'结尾的字符串个数
    // cnts[25]表示以'z'结尾的字符串个数
    // int all=1。表示一开始就有一个空字符串
    // int newAdd=0。表示新增字符串 (以str[i]位置最为结尾的新增字符串)
    // newAdd=all-cnts[str[i]] -> 新增=所有字符串的个数-表中该字符有的个数
    // cnts[str[i]]+=newAdd -> 表中的个数+=新增的个数
    // all+=newAdd -> 所有字符串+=新增的个数
    //
    // 如 s="abab"
    // 一开始：{}，all=1
    // i=0:{} + {a}   ->  新增了1( all-cnts['a'] )，cnts['a']+=newAll=1，all+=newAdd=2
    // i=1:{}、{a} + {b}、{ab} -> 新增了2个( all-cnts['b'] )，cnts['b']+=newAdd=2，all+=newAdd=4
    // i=2:{}、{a}、{b}、{ab} + {a}、{aa}、{ba}、{aba} -> {a}重复，新增了3个( all-cnts['a'] )，cnts['a']+=newAdd=4，all+=newAdd=7
    // i=3:{}、{a}、{b}、{ab}、{aa}、{ba}、{aba} + {b}、{ab}、{bb}、{abb}、{aab}、{bab}、{abab} -> {b}、{ab}重复，新增了5个( all-cnts['b'] )，cnts['b']+=newAdd=7，all+=newAdd=12
    // all:{}、{a}、{b}、{ab}、{aa}、{ba}、{aba}、{bb}、{abb}、{aab}、{bab}、{abab}
    
}
