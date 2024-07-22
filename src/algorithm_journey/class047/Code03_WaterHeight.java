package algorithm_journey.class047;

import java.io.*;

/**
 * 水位高度
 *
 * @author: Agony
 * @create: 2024/7/19 10:25
 * @describe: 我们把山顶上的湖泊看作一条长度为 m 的直线，一开始水深都在水平线上，我们视作此时的水深为 '0'
 * 接下来，在一瞬间，小正方形的"朋友"们跳起并扎入水中，导致在入水点的水降低而远离入水点的水升高，注意两个 "朋友" 可能在同一地点入水。
 * 小正方形的每个朋友有一个体积数值 v，当体积为 v 的一个朋友跳入水中，我们设入水点为 i，将会导致 i-v+1 到 i 的水位依次降低 1,2,...,v
 * 同样地，第 i 到 i+v-1 的水位会依次降低 v,v-1,...1
 * 相对应地，i-v 的水位不变， i-v-1 到 i-2*v 水位依次增加 1,2,...,v ，i-2*v 到 i-3*v+1 水位依次增加 v,v-1,...1
 * 同样，i+v 水位不变，i+v+1 到 i+2*v 水位增加 1,2,...,v，
 * i-2*v 到 i-3*v+1  水位依次增加 v,v-1,...1
 * 现在小正方形想要穿过这个湖，他想要知道在这 n 个"朋友"跳入水中后湖上每个节点的水位，你能帮帮它吗？
 * 如下图所示
 * <p>
 * |      /\          /\
 * |     /  \        /  \
 * |    /    \  i   /    \
 * |          \    /
 * |           \  /
 * |            \/
 * @link: <a href="https://www.luogu.com.cn/problem/P5026">水位高度</a>
 */
public class Code03_WaterHeight {


    // 一群人落水后求每个位置的水位高度

    // 思路
    // 转化成四段等差数列 公差为1
    // 设计一个 OFFSET 偏移量
    // 如果落水点 x 离 0 位置比较近，那么其左边界 x-3v 很有可能越界
    // 右边界同理
    // 为了让整个过程减少 if 判断，通过添加 OFFSET 偏移量
    // 让返回数组置于大数组中间部分，使得过程计算更加流畅
    // 只需要在返回的时候加上这个 OFFSET 即可

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StreamTokenizer in = new StreamTokenizer(br);
        PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));


        in.nextToken();
        N = (int) in.nval;
        in.nextToken();
        M = (int) in.nval;

        for (int i = 0; i < N; i++) {
            // 体积
            in.nextToken();
            int v = (int) in.nval;

            // 落水点
            in.nextToken();
            int x = (int) in.nval;

            // 把 arr 修改成四段差分数组，公差为1
            fall(v, x);
        }
        // build 一下
        build();

        // 打印数组
        // 打印数组只从OFFSET+1开始打印
        // 如果 M = 10
        // 水位图就是从1～10，而不是0～9
        int start = OFFSET + 1;
        out.print(arr[start++]);
        for (int i = 2; i <= M; i++) {
            out.print(" " + arr[start++]);
        }
        out.println();


        out.flush();
        out.close();
        br.close();
    }


    // 题目中现实 m<=1000000,湖泊的最大宽度
    public static int MAX_LENGTH = 1000001;

    // 偏移量，如果落水点 x 离 0 位置比较近，那么其左边界 x-3v 很有可能越界
    // 因为题目中体积 v 最大是10000
    public static int OFFSET = 30001;

    // 加入偏移量，保证不会越界
    public static int[] arr = new int[OFFSET + MAX_LENGTH + OFFSET];

    // 朋友数量
    public static int N;

    // 湖泊宽度
    public static int M;


    /**
     * 根据体积和落水点，修改成四段等差差分数组，公差为1
     *
     * @param v 体积
     * @param x 落水点
     */
    public static void fall(int v, int x) {
        set(x - 3 * v + 1, x - 2 * v, 1, v, 1);
        set(x - 2 * v + 1, x, v - 1, -v, -1);
        set(x + 1, x + 2 * v, -v + 1, v, 1);
        set(x + 2 * v + 1, x + 3 * v - 1, v - 1, 1, -1);
    }


    /**
     * 等差数组set操作
     * 为了防止x - 3 * v + 1出现负数下标，进而有很多很烦的边界讨论
     * 所以任何位置，都加上一个较大的数字(OFFSET)
     * 这样一来，所有下标就都在0以上了，省去了大量边界讨论
     * 这就是为什么arr在初始化的时候要准备OFFSET + MAX_LENGTH + OFFSET这么多的空间
     *
     * @param l 左区间
     * @param r 右区间
     * @param s 首项
     * @param e 尾项
     * @param d 公差
     */
    public static void set(int l, int r, int s, int e, int d) {

        arr[l + OFFSET] += s;
        arr[l + 1 + OFFSET] += d - s;
        arr[r + 1 + OFFSET] -= d + e;
        arr[r + 2 + OFFSET] += e;

    }

    /**
     * build - 前缀和累加操作
     * 注意：build时，不能从 OFFSET+1 开始，因为 x-3*v+1，还是可能会小于OFFSET的
     */
    public static void build() {
        for (int i = 1; i <= M + OFFSET; i++) {
            arr[i] += arr[i - 1];
        }
        for (int i = 1; i <= M + OFFSET; i++) {
            arr[i] += arr[i - 1];
        }
    }


}
