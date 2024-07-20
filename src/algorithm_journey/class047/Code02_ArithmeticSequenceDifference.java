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
 * <p>
 * 输入：
 * 第一行： N：表示N个柱子(数组长度)   M：M次攻击(重复次次数)
 * 接下来 M 行
 * 输入 l, r, s, e -> 依次表示，左区间、右区间、首项、尾项、
 * <p>
 * 输出：
 * 异或和（异或和就是所有数字按位异或起来的值） and  最大值
 * @link: <a href="https://www.luogu.com.cn/problem/P4231">等差数列差分模版</a>
 */
public class Code02_ArithmeticSequenceDifference {


    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StreamTokenizer in = new StreamTokenizer(br);
        PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));

        in.nextToken();
        N = (int) in.nval;

        in.nextToken();
        int M = (int) in.nval;

        for (int i = 0; i < M; i++) {
            in.nextToken();
            int l = (int) in.nval;
            in.nextToken();
            int r = (int) in.nval;
            in.nextToken();
            int s = (int) in.nval;
            in.nextToken();
            int e = (int) in.nval;

            set(l, r, s, e, (e - s) / (r - l));
        }
        build();
        long max = 0;
        long xor = 0;

        // 柱子是从1开始编号的
        for (int i = 1; i <= N; i++) {
            xor ^= arr[i];
            max = Math.max(max, arr[i]);
        }
        out.println(xor + " " + max);


        out.flush();
        out.close();
        br.close();

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


    public static int MAX_LENGTH = 10000001;

    public static long[] arr = new long[MAX_LENGTH];

    public static int N;


    /**
     * 等差数组set操作
     *
     * @param l 左区间
     * @param r 右区间
     * @param s 首项
     * @param e 尾项
     * @param d 公差
     */
    public static void set(int l, int r, int s, int e, int d) {
        arr[l] += s;
        arr[l + 1] += d - s;
        arr[r + 1] -= d + e;
        arr[r + 2] += e;
    }


    /**
     * build - 前缀和累加操作
     */
    public static void build() {

        for (int i = 1; i <= N; i++) {
            arr[i] += arr[i - 1];
        }

        for (int i = 1; i <= N; i++) {
            arr[i] += arr[i - 1];
        }
    }


}
