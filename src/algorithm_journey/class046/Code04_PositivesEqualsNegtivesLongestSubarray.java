package algorithm_journey.class046;

import java.io.*;
import java.util.HashMap;

/**
 * @author: Agony
 * @create: 2024/7/17 09:32
 * @describe: 给定一个无序数组arr，其中元素可正、可负、可0。求arr所有子数组中正数与负数个数相等的最长子数组的长度。
 * [要求]
 * 时间复杂度为O(n)，空间复杂度为O(n)
 * 输入描述：
 * 第一行一个整数N，表示数组长度
 * 接下来一行有N个数表示数组中的数
 * 输出描述：
 * 输出一个整数表示答案
 * @link: <a href="https://www.nowcoder.com/practice/545544c060804eceaed0bb84fcd992fb">未排序数组中累加和为给定值的最长子数组系列问题补1</a>
 */
public class Code04_PositivesEqualsNegtivesLongestSubarray {


    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StreamTokenizer in = new StreamTokenizer(br);
        PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));

        in.nextToken();
        N = (int) in.nval;

        for (int i = 0; i < N; i++) {
            in.nextToken();
            arr[i] = (int) in.nval;
        }
        out.println(compute());
        out.flush();
        out.close();
        br.close();
    }

    // 返回无序数组中正数和负数个数相等的最长子数组长度

    // 思路
    // 和题目2类似
    // 把正数转化为1，0维持不变，负数转化为-1
    // 即求aim=0的最长子数组长度


    public static int MAX_LENGTH = 100001;

    public static int[] arr = new int[MAX_LENGTH];

    public static int N;

    // key:前缀和；value：最早出现的位置
    public static HashMap<Integer, Integer> map;


    /**
     * 求正数和负数个数相等的最长子数组长度
     *
     * @return
     */
    public static int compute() {

        map = new HashMap<>();
        map.put(0, -1);
        // 正数 -> 1; 负数 -> -1
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.compare(arr[i], 0);
        }

        int ans = 0;
        int sum = 0;
        for (int i = 0; i < N; i++) {
            sum += arr[i];
            if (map.containsKey(sum - 0)) {
                ans = Math.max(ans, i - map.get(sum - 0));
            }
            if (!map.containsKey(sum)) {
                map.put(sum, i);
            }
        }
        return ans;
    }


}













