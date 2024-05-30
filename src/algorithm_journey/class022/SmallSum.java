package algorithm_journey.class022;


import java.io.*;

/**
 * 小和问题
 *
 * @author Agony
 * @create 2024/5/30 10:32
 * @describe: 例如，数组 s = [1, 3, 5, 2, 4, 6] ，
 * 在 s[0] 的左边小于或等于 s[0] 的数的和为 0 ；
 * 在 s[1] 的左边小于或等于 s[1] 的数的和为 1 ；
 * 在 s[2] 的左边小于或等于 s[2] 的数的和为 1+3=4 ；
 * 在 s[3] 的左边小于或等于 s[3] 的数的和为 1 ；
 * 在 s[4] 的左边小于或等于 s[4] 的数的和为 1+3+2=6 ；
 * 在 s[5] 的左边小于或等于 s[5] 的数的和为 1+3+5+2+4=15 。
 * 所以 s 的小和为 0+1+4+1+6+15=27
 * 给定一个数组 s ，实现函数返回 s 的小和
 * @link: <a href="https://www.nowcoder.com/practice/edfe05a1d45c4ea89101d936cac32469">小和问题</a>
 */
public class SmallSum {

    public static int MAXN = 100001;

    public static int[] arr = new int[MAXN];

    public static int[] help = new int[MAXN];

    public static int n;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StreamTokenizer in = new StreamTokenizer(br);
        PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));
        while (in.nextToken() != StreamTokenizer.TT_EOF) {
            n = (int) in.nval;
            for (int i = 0; i < n; i++) {
                in.nextToken();
                arr[i] = (int) in.nval;
            }
            out.println(smallSum(0, n - 1));
        }
        out.flush();
        out.close();

    }

    /**
     * 返回arr[l...r]范围上，小和的累加和，同时请把arr[l..r]变有序
     *
     * @param l 数组左边界下标
     * @param r 数组右边界下标
     * @return 小和
     */
    public static long smallSum(int l, int r) {
        if (l == r) {
            return 0;
        }
        int m = (l + r) / 2;
        return smallSum(l, m) + smallSum(m + 1, r) + mergeSort(l, m, r);
    }

    /**
     * 归并排序并返回排序前的小和
     * 归并排序前 左右两边的数组已经是有序了
     * 在计算好小和之后再进行归并
     *
     * @param l 数组左边界下标
     * @param m 数组中间值下标
     * @param r 数组右边界下标
     * @return 左右数组跨界小和 并使左右数组有序
     */
    public static long mergeSort(int l, int m, int r) {
        // 求小和
        long ans = 0;
        long sum = 0;
        for (int j = m + 1, i = l; j <= r; j++) {
            while (arr[i] <= arr[j] && i <= m) {
                sum += arr[i++];
            }
            ans += sum;
        }
        // 归并排序
        int i = l;
        int a = l;
        int b = m + 1;
        while (a <= m && b <= r) {
            help[i++] = arr[a] <= arr[b] ? arr[a++] : arr[b++];
        }
        while (a <= m) {
            help[i++] = arr[a++];
        }
        while (b <= r) {
            help[i++] = arr[b++];
        }
        // for (i = l; i <= r; i++) {
        //     arr[i] = help[i];
        // }
        while (l <= r) {
            arr[l] = help[l++];
        }
        return ans;
    }
}






















