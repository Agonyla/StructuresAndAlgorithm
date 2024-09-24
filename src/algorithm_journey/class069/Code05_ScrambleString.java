package algorithm_journey.class069;

/**
 * 扰乱字符串
 *
 * @author: Agony
 * @create: 2024/9/11 11:50
 * @describe: 使用下面描述的算法可以扰乱字符串 s 得到字符串 t ：
 * 如果字符串的长度为 1 ，算法停止
 * 如果字符串的长度 > 1 ，执行下述步骤：
 * 在一个随机下标处将字符串分割成两个非空的子字符串。即，如果已知字符串 s ，则可以将其分成两个子字符串 x 和 y ，且满足 s = x + y 。
 * 随机 决定是要「交换两个子字符串」还是要「保持这两个子字符串的顺序不变」。即，在执行这一步骤之后，s 可能是 s = x + y 或者 s = y + x 。
 * 在 x 和 y 这两个子字符串上继续从步骤 1 开始递归执行此算法。
 * 给你两个 长度相等 的字符串 s1 和 s2，判断 s2 是否是 s1 的扰乱字符串。如果是，返回 true ；否则，返回 false 。
 * @link: <a href="https://leetcode.cn/problems/scramble-string/description/">扰乱字符串</a>
 */
public class Code05_ScrambleString {

    // todo

    // 扰乱字符串
    //
    // 暴力递归
    // 设计 boolean f(char[] s1, char[] s2, int l1, int r1, int l2, int r2)
    // -> 返回 s2 从 l1到r2 是不是s1从 l1到r1的扰乱串
    // 可能性分析：
    // s1在划分的时候，都是整段整段的，不会某一个字符穿插在另一个字符中间
    // 所以其扰乱串也是整段整段的
    // 如果s1.length=7，那么可分为两大情况
    // 一. 顺序对应 (不交错)
    // -> 1️⃣ f(0,0,0,0) && f(1,6,1,6)
    // -> 2️⃣ f(0,1,0,1) && f(2,6,2,6)
    // ...
    // 只要有一个成立就是true
    // 二. 首位对应 (交错)
    // -> 1️⃣ f(0,0,6,6) && f(1,6,0,5)
    // -> 2️⃣ f(0,1,5,6) && f(2,6,0,4)
    // ...
    // 只要有一个成立就是true
    //
    // 记忆化搜索 -> 改完之后先再用暴力递归写一下❗❗❗
    // 由于暴力递归涉及到4个可变参数 -> 将其改造一下变成3个可变参数
    // 设计 boolean int f(char[] s1, char[] s2, int l1, int l2, int length, int[][][] dp)
    // 为什么是int[][][] 而不是boolean呢
    // 要用整形表示3个状态
    // 0：表示没展开过
    // -1：展开过，且结果为false
    // 1：展开过，且结果为true
    // 然后挂缓存表
    // ...
    //
    // 动态规划
    // l1取值范围：0~n-1
    // l2取值范围：0~n-1
    // length取值范围：0~n
    // 位置依赖：
    // 依赖 f3(s1, s2, l1, l2, k, dp) && f3(s1, s2, l1 + k, l2 + k, len - k, dp)
    // 显然 k和len-k 都是小于 len -> len 作为层
    // 低层算出来之后可以直接推出高层
    // 填表顺序：
    // 总体方向是从下往上
    // 特殊位置分析：
    // length=1
    // dp[l1][l2][1] = s1[l1]==s2[l2]
    // 填表时要注意：
    // l1+len<=n
    // l2+len<=n


    public static boolean isScramble(String s1, String s2) {

        return false;
    }


}











