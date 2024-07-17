package algorithm_journey.class045;

import java.util.Arrays;

/**
 * 接头密匙
 *
 * @author: Agony
 * @create: 2024/7/12 10:04
 * @describe: 牛牛和他的朋友们约定了一套接头密匙系统，用于确认彼此身份。密匙由一组数字序列表示，两个密匙被认为是一致的，如果满足以下条件：
 * <p>
 * 密匙 b 的长度不超过密匙 a 的长度。
 * 对于任意 0 <= i < length(b)，有 b[i+1] - b[i] == a[i+1] - a[i]。
 * 现在给定了m个密匙 b 的数组，以及n个密匙 a 的数组。请你返回一个长度为 m 的结果数组 ans，表示每个密匙b都有多少一致的密匙a。
 * <p>
 * {
 * {3, 4, 5, 6, 7, 8}, -> 1,1,1,1
 * {2, 4, 6, 8},       -> 2,2,2
 * {1, 3, 5, 7, 9}     -> 2,2,2,2
 * };
 * <p>
 * {
 * {1, 2, 3, 4, 5}, -> 1,1,1,1
 * {2, 4, 6, 8},    -> 2,2,2
 * {1, 4, 7, 10}    -> 3,3,3
 * };
 * @link: <a href="https://www.nowcoder.com/practice/c552d3b4dfda49ccb883a6371d9a6932">接头密匙</a>
 */
public class Code01_CountConsistentKeys {


    public static void main(String[] args) {

        int[] arr = {1, 23, 3, 4, 6};
        System.out.println(Arrays.toString(arr));
        System.out.println(toKeyString(arr));

        int[][] b = {
                {1, 2, 3, 4, 5},
                {2, 4, 6, 8},
                {1, 4, 7, 10}
        };
        int[][] a = {
                {3, 4, 5, 6, 7, 8},
                {2, 4, 6, 8},
                {1, 3, 5, 7, 9}
        };

        System.out.println(Arrays.toString(countConsistentKeys(b, a)));

    }

    // 接头密匙
    // 思路
    // 把a组数字序列特征转化字符串，再转成成前缀树
    // 如一组序列为 [3, 6, 50, 10] -> "3#44#-40#" -> 加到前缀树中
    // 再用b组转成字符串去前缀树中匹配

    /**
     * 街头密钥
     *
     * @param b int整型二维数组
     * @param a int整型二维数组
     * @return int整型一维数组
     */
    public static int[] countConsistentKeys(int[][] b, int[][] a) {
        // int[][] aa = {
        //         {1, 2, 3, 4, 5},
        //         {2, 4, 6, 8},
        //         {1, 4, 7, 10}
        // };
        // int[][] bb = {
        //         {3, 4, 5, 6, 7, 8},
        //         {2, 4, 6, 8},
        //         {1, 3, 5, 7, 9}
        // };
        // write code here

        build();
        for (int i = 0; i < a.length; i++) {
            insert(toKeyString(a[i]));
        }
        int[] ans = new int[b.length];
        for (int i = 0; i < b.length; i++) {
            ans[i] = preSearch(toKeyString(b[i]));
        }
        clear();
        return ans;
    }

    /**
     * 把数字传承密钥字符串
     * [1,2,3] -> 1#1#
     *
     * @param arr
     * @return
     */
    public static String toKeyString(int[] arr) {

        StringBuilder sb = new StringBuilder();
        for (int i = 1; i < arr.length; i++) {
            sb.append(arr[i] - arr[i - 1]).append("#");
        }
        return sb.toString();
    }


    public static int MAX_LENGTH = 2000001;

    // 0,1,2,3,4,5,6,7,8,9,-,# 一共12种
    public static int[][] tree = new int[MAX_LENGTH][12];

    public static int[] pass = new int[MAX_LENGTH];

    public static int cnt;

    /**
     * 前缀树初始化
     */
    public static void build() {
        cnt = 1;
    }

    public static int getPath(char cha) {
        if (cha == '#') {
            return 10;
        }
        if (cha == '-') {
            return 11;
        }
        return cha - '0';
    }

    /**
     * 插入
     *
     * @param word
     */
    public static void insert(String word) {
        int cur = 1;
        pass[cur]++;
        for (int i = 0; i < word.length(); i++) {
            int path = getPath(word.charAt(i));
            if (tree[cur][path] == 0) {
                tree[cur][path] = ++cnt;
            }
            cur = tree[cur][path];
            pass[cur]++;
        }
    }


    /**
     * 查询前缀次数
     *
     * @param word
     * @return
     */
    public static int preSearch(String word) {
        int cur = 1;
        for (int i = 0; i < word.length(); i++) {
            int path = getPath(word.charAt(i));
            if (tree[cur][path] == 0) {
                return 0;
            }
            cur = tree[cur][path];
        }
        return pass[cur];
    }


    /**
     * 清除tree
     */
    public static void clear() {
        for (int i = 1; i <= cnt; i++) {
            Arrays.fill(tree[i], 0);
            pass[i] = 0;
        }
    }


}

























