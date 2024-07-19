package algorithm_journey.class047;

import java.io.*;

/**
 * 等差数列差分模版
 *
 * @author: Agony
 * @create: 2024/7/19 09:58
 * @describe: N个柱子排成一排，一开始每个柱子损伤度为0。
 * 接下来勇仪会进行 M 次攻击，每次攻击可以用4个参数l,r,s,e来描述：
 * 表示这次攻击作用范围为第 l 个到第 r 个之间所有的柱子(包含l,r)，对第一个柱子的伤害为s，对最后一个柱子的伤害为e。
 * 攻击产生的伤害值是一个等差数列。若l=1,r=5,s=2,e=10，则对第1~5个柱子分别产生2,4,6,8,10的伤害。
 * 鬼族们需要的是所有攻击完成之后每个柱子的损伤度。
 * @link: <a href="https://www.luogu.com.cn/problem/P4231">等差数列差分模版</a>
 */
public class Code02_ArithmeticSequenceDifference {


    // todo

    public static void main(String[] args) {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StreamTokenizer in = new StreamTokenizer(br);
        PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));

    }

    // 等差数列差分模版

    // 思路
    // 等差数列差分
    // void set(int l, int r, int s, int e, int d)
    // l -> 左区间
    // r -> 右区间
    // s -> 首项
    // e -> 尾项
    // d -> 公差
    // arr[l] += s;
    // arr[l + 1] += (d - s);
    // arr[r + 1] -= (d + e);
    // arr[r + 2] += e;
    // 相当于 l 位置加首项，然后 l+1 ，减去首项，把加首项抵消，再加公差
    // 在 r+1 位置减去尾项，再减去公差，把加上公差的抵消，再r+2位置加上尾项，把减去尾项抵消
    // 都执行后相当于全部抵消
    // 然后做两次前缀和


}
