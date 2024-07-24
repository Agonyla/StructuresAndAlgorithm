package algorithm_journey.class048;

import java.io.*;

/**
 * @author: Agony
 * @create: 2024/7/23 10:51
 * @describe: 新建一个差分数组实现
 * @link:
 */
public class DiffMatrixNowcoder2 {


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

        diff = new long[N + 2][M + 2];
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= M; j++) {
                in.nextToken();
                int num = (int) in.nval;
                arr[i][j] = num;
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


        out.flush();
        out.close();
        br.close();
    }


    // 最大长度
    public static int MAX_LENGTH = 1002;

    public static long[][] arr = new long[MAX_LENGTH][MAX_LENGTH];

    // 差分数组
    public static long[][] diff;

    // 行
    public static int N;

    // 列
    public static int M;

    // 操作次数
    public static int q;


    public static void set(int a, int b, int c, int d, int k) {
        diff[a][b] += k;
        diff[a][d + 1] -= k;
        diff[c + 1][b] -= k;
        diff[c + 1][d + 1] += k;
    }


    public static void build() {
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= M; j++) {
                diff[i][j] += diff[i - 1][j] + diff[i][j - 1] - diff[i - 1][j - 1];
                arr[i][j] += diff[i][j];
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
