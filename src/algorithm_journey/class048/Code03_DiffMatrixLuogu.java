package algorithm_journey.class048;

import java.io.*;
import java.util.Arrays;

/**
 * P3397 地毯
 *
 * @author: Agony
 * @create: 2024/7/20 15:39
 * @describe: 在 n×n 的格子上有 m 个地毯。
 * 给出这些地毯的信息，问每个点被多少个地毯覆盖。
 * <p>
 * 输入格式：
 * 第一行，两个正整数 n,m。意义如题所述。
 * 接下来 m 行，每行两个坐标 (x1,y1) 和 (x2,y2)，代表一块地毯，左上角是 (x1,y1)，右下角是 (x2,y2)。
 * @link: <a href="https://www.luogu.com.cn/problem/P3397">P3397 地毯</a>
 */
public class Code03_DiffMatrixLuogu {


    // 二维差分模版(洛谷)
    // 要求 左上角点(a,b)；右下角点(c,d) 这篇区域整体+V


    // 思路
    // 左上角点(a,b)；右下角点(c,d)
    // 1. set 做如下操作
    //
    //   ----------------
    //   |+V            | -V
    //   |              |
    //   |              |
    //   |              |
    //   |              |
    //   ----------------
    //    -V             +V
    //
    //  在(a,b) +V；(a,d+1) -V；(c+1,b) -V；(c+1,d+1) +V
    //
    //  (a,b)位置+V，会对右下角的整片区域产生影响
    //  (a,d+1)位置 -V，抵消对右边的影响
    //  (c+1,b)位置 -V，抵消对下边的影响
    //  (c+1,d+1)位置 +V，两次 -V，影响了两次，再加一次
    //
    // 2. build 加工
    // 按二维数组前缀和加工处理一次：
    // sum 从左往右，从上往下开始加工
    // sum[i][j] = 左边 + 上边 - 左上 + 自己
    // -> sum[i][j] += sum[i][j - 1] + sum[i - 1][j] - sum[i - 1][j - 1]

    // 注意：真实数据用一圈0包裹起来，可以减少很多边界讨论


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StreamTokenizer in = new StreamTokenizer(br);
        PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));

        in.nextToken();
        N = (int) in.nval;

        in.nextToken();
        M = (int) in.nval;

        for (int i = 0; i < M; i++) {

            in.nextToken();
            int a = (int) in.nval;
            in.nextToken();
            int b = (int) in.nval;
            in.nextToken();
            int c = (int) in.nval;
            in.nextToken();
            int d = (int) in.nval;

            set(a, b, c, d);
        }
        build();
        for (int i = 1; i <= N; i++) {
            out.print(arr[i][1]);
            for (int j = 2; j <= N; j++) {
                out.print(" " + arr[i][j]);
            }
            out.println();
        }

        clear();

        out.flush();
        out.close();
        br.close();
    }

    // 最大数组长度,题目给出1000，实际用一圈 0 包裹
    public static int MAX_LENGTH = 1002;

    // N * N的网格
    public static int N;

    // 地毯个数
    public static int M;

    // 二维数组
    public static int[][] arr = new int[MAX_LENGTH][MAX_LENGTH];


    /**
     * set 操作
     * 左上角 (a,b) 到 右下角 (c,d) 累加 1
     *
     * @param a
     * @param b
     * @param c
     * @param d
     */
    public static void set(int a, int b, int c, int d) {
        arr[a][b] += 1;
        arr[a][d + 1] -= 1;
        arr[c + 1][b] -= 1;
        arr[c + 1][d + 1] += 1;
    }


    /**
     * build 操作
     * 前缀和累加
     */
    public static void build() {
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                arr[i][j] += arr[i - 1][j] + arr[i][j - 1] - arr[i - 1][j - 1];
            }
        }
    }


    /**
     * 清除数组
     */
    public static void clear() {
        for (int i = 1; i <= N + 1; i++) {
            Arrays.fill(arr[i], 0);
        }
    }

}
