package algorithm_journey.class049;

/**
 * @author: Agony
 * @create: 2024/7/24 11:09
 * @describe:
 * @link:
 */
public class Code05_ReplaceTheSubstringForBalancedString {

    // todo

    // 替换子串得到平衡字符串

    // 思路：

    // 先把字符串转换成对应的数组 int[] arr = new int[s.length]
    // 'Q'->0; 'W'->1; 'E'->2; 'R'->3
    // 再用 int[] cnts = new int[4]
    // 统计字符串中每一种字符出现的频次

    // 实现 boolean ok(int cnts, int length, int require)
    // return -> 能不能实现让整体字符串能达到要求
    // cnts -> 除去l，r范围内的字符，即在窗口之外，每一种字符的词频统计
    // length -> 窗口的长度
    // require -> 要求：每一种字符都达到 n/4

    // 总体流程：
    // 左窗口l从0位置开始，右窗口r从0位置
    // 如果除去窗口内的数组不能满足，并且r没有来到右边界 -> while(!ok(cnts,r-l,require) && r<n)
    // 右窗口向右移动，同时频次表除去刚进来的字符次数
    // 退出循环后
    // 判断是否是因为满足条件退出循环
    // 如果是的话，更新答案，
    // 如果不是，就不操作
    // 把左窗口的词频重新加1，l往右移动
    // 直到l来到边界位置
    //
    // 注意：右窗口时不回退的
    // 如在移动的过程中，2～9位置是满足要求的
    // l向右移动，3～1，3～2。。。3～8，这些位置必然是满足不了要求的
    // 下一就直接从3～9这个窗口验证是否能满足，
    // 如果不能，r继续向右滑动
    // 如果可以，更新答案，l向右滑动
}

