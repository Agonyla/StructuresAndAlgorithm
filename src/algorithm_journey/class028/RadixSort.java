package algorithm_journey.class028;

import java.util.Arrays;

/**
 * 排序数组  -  基数排序
 *
 * @author Agony
 * @create 2024/6/6 09:51
 * @describe: 给你一个整数数组 nums，请你将该数组升序排列。
 * @link: <a href="https://leetcode.cn/problems/sort-an-array/description/">排序数组</a>
 */
public class RadixSort {
    

    public static void main(String[] args) {
        // int[] arr = {5, 1, 1, 2, 0, 0};
        int[] arr = {-74, 48, -20, 2, 10, -84, -5, -9, 11, -24, -91, 2, -71, 64, 63, 80, 28, -30, -58, -11, -44, -87, -22, 54, -74, -10, -55, -28, -46, 29, 10, 50, -72, 34, 26, 25, 8, 51, 13, 30, 35, -8, 50, 65, -6, 16, -2, 21, -78, 35, -13, 14, 23, -3, 26, -90, 86, 25, -56, 91, -13, 92, -25, 37, 57, -20, -69, 98, 95, 45, 47, 29, 86, -28, 73, -44, -46, 65, -84, -96, -24, -12, 72, -68, 93, 57, 92, 52, -45, -2, 85, -63, 56, 55, 12, -85, 77, -39};


        Arrays.stream(sortArray(arr)).forEach(a -> System.out.print(a + " "));
    }

    // 进制
    public static int BASE = 10;
    // 记录进制的数组
    // 如 BASE=10 表示十进制
    // cnts[0]表示末位时0出现的次数
    public static int[] cnts = new int[BASE];

    public static int MAXN = 50001;
    public static int[] help = new int[MAXN];

    public static int[] sortArray(int[] arr) {
        if (arr == null || arr.length < 2) {
            return arr;
        }
        int n = arr.length;
        // 找到nums中的最小值，每个数-min
        int min = arr[0];
        for (int i = 1; i < n; i++) {
            min = Math.min(min, arr[i]);
        }
        // 把每个数转成非负数 并找到最大值->求的最大值一共有几位
        int max = 0;
        for (int i = 0; i < n; i++) {
            arr[i] -= min;
            max = Math.max(max, arr[i]);
        }
        // 排序数组
        radixSort(arr, n, getBits(max));
        // 把排序之后每个数转到之前的数
        for (int i = 0; i < n; i++) {
            arr[i] += min;
        }
        return arr;
    }


    /**
     * 基数排序
     * 要求数组没有负数 (负数排列各个位的大小就没有意义了)
     * 按位数遍历 -> bits.for
     * 1) 数组从右往左遍历，用cnts数组记录每个数末位出现的次数
     * 2) 处理成前缀和累加次数
     * cnts[0] -> 末位小于等于0的个数，cnts[1] -> 末位小于等于1的个数...
     * 3) help数组排序 按照各个位的大小顺序排序
     * 4) arr按照help数组次序排序
     *
     * @param arr  数组
     * @param n    数组长度
     * @param bits 数组最大数的位数
     */
    public static void radixSort(int[] arr, int n, int bits) {
        // offset 用于获取一个数各个位的数
        // res = number / offset % BASE
        // -> offset *= BASE
        for (int offset = 1; bits > 0; offset *= BASE, bits--) {

            // 记录完低位，记录高位需要清零
            Arrays.fill(cnts, 0);

            // 记录各个位(个位、十位、百位)出现的次数
            for (int i = 0; i < n; i++) {
                cnts[arr[i] / offset % BASE]++;
            }

            // 处理成前缀和累加次数
            for (int i = 1; i < BASE; i++) {
                cnts[i] = cnts[i] + cnts[i - 1];
            }

            // 把 arr 的各个数按照每个位的大小顺序放在 help 上
            for (int i = n - 1; i >= 0; i--) {
                // 如 arr = [3,1,2]
                // 经过前缀累加之后，cnts = [0,1,2,3,0,0,0,0,0,0,0]
                // arr[i]/offset%BASE 表示该数末位
                // 如 i=2，
                // cnts[2]=2,表示<=2的有两个
                // help[--2]=arr[2]
                // -> help=[1,2,3]
                help[--cnts[arr[i] / offset % BASE]] = arr[i];
            }
            for (int i = 0; i < n; i++) {
                arr[i] = help[i];
            }
        }
    }

    /**
     * 返回一个数在某个进制下有几位
     *
     * @param number 一个数
     * @return 返回在某个进制下一共有几位
     */
    public static int getBits(int number) {
        int ans = 0;
        while (number > 0) {
            number /= BASE;
            ans++;
        }
        return ans;
    }


}
