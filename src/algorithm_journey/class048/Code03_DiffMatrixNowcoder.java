package algorithm_journey.class048;

import java.io.*;

/**
 * 【模板】二维差分
 *
 * @author: Agony
 * @create: 2024/7/21 16:22
 * @describe: 给你一个n行m列的矩阵，下标从1开始。
 * 接下来有q次操作，每次操作输入5个参数x1, y1, x2, y2, k
 * 表示把以(x1, y1)为左上角,(x2,y2)为右下角的子矩阵的每个元素都加上k，
 * 请输出操作后的矩阵。
 * <p>
 * 输入描述：
 * 第一行包含三个整数n,m,q.
 * 接下来n行，每行m个整数，代表矩阵的元素
 * 接下来q行，每行5个整数x1, y1, x2, y2, k，分别代表这次操作的参数
 * @link: <a href="https://www.nowcoder.com/practice/50e1a93989df42efb0b1dec386fb4ccc">【模板】二维差分</a>
 */
public class Code03_DiffMatrixNowcoder {


    // 二维差分模版(牛客)


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StreamTokenizer in = new StreamTokenizer(br);
        PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));

        in.nextToken();
        N = (int) in.nval;
        in.nextToken();
        M = (int) in.nval;
        in.nextToken();
        q = (int) in.nval;

        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= M; j++) {
                // arr[i][j] = num;
                // 直接接受原数组的数组好像有问题
                // 差分数组好像必须在初始值是0的基础上差分
                // 在单个区域差分
                in.nextToken();
                int num = (int) in.nval;
                set(i, j, i, j, num);
            }
        }


        for (int i = 0; i < q; i++) {
            in.nextToken();
            int a = (int) in.nval;
            in.nextToken();
            int b = (int) in.nval;
            in.nextToken();
            int c = (int) in.nval;
            in.nextToken();
            int d = (int) in.nval;
            in.nextToken();
            int k = (int) in.nval;

            set(a, b, c, d, k);
        }

        build();
        for (int i = 1; i <= N; i++) {
            out.print(arr[i][1]);
            for (int j = 2; j <= M; j++) {
                out.print(" " + arr[i][j]);
            }
            out.println();
        }

        // clear();

        out.flush();
        out.close();
        br.close();
    }


    // 最大长度
    public static int MAX_LENGTH = 1002;

    public static long[][] arr = new long[MAX_LENGTH][MAX_LENGTH];

    // 行
    public static int N;

    // 列
    public static int M;

    // 操作次数
    public static int q;


    public static void set(int a, int b, int c, int d, int k) {
        arr[a][b] += k;
        arr[a][d + 1] -= k;
        arr[c + 1][b] -= k;
        arr[c + 1][d + 1] += k;
    }


    public static void build() {
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= M; j++) {
                arr[i][j] += arr[i - 1][j] + arr[i][j - 1] - arr[i - 1][j - 1];
            }
        }
    }

    public static void clear() {
        for (int i = 1; i <= N + 1; i++) {
            for (int j = 1; j <= M + 1; j++) {
                arr[i][j] = 0;
            }
        }
    }


}
