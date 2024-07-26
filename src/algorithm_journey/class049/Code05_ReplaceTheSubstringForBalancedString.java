package algorithm_journey.class049;

/**
 * 替换子串得到平衡字符串
 *
 * @author: Agony
 * @create: 2024/7/24 11:09
 * @describe: 有一个只含有 'Q', 'W', 'E', 'R' 四种字符，且长度为 n 的字符串。
 * 假如在该字符串中，这四个字符都恰好出现 n/4 次，那么它就是一个「平衡字符串」。
 * 给你一个这样的字符串 s，请通过「替换一个子串」的方式，使原字符串 s 变成一个「平衡字符串」。
 * 你可以用和「待替换子串」长度相同的 任何 其他字符串来完成替换。
 * 请返回待替换子串的最小可能长度。
 * 如果原字符串自身就是一个平衡字符串，则返回 0。
 * @link: <a href="https://leetcode.cn/problems/replace-the-substring-for-balanced-string/description/">替换子串得到平衡字符串</a>
 */
public class Code05_ReplaceTheSubstringForBalancedString {


    public static void main(String[] args) {

        String s = "QWER";
        System.out.println(balancedString(s));

        s = "QQQW";
        System.out.println(balancedString(s));
    }

    // 替换子串得到平衡字符串

    // 思路：

    // 先把字符串转换成对应的数组 int[] arr = new int[s.length]
    // 'Q'->0; 'W'->1; 'E'->2; 'R'->3
    // 再用 int[] cnts = new int[4]
    // 统计字符串中每一种字符出现的频次

    // 实现 boolean ok(int cnts, int length, int require)
    // return -> 能不能实现让整体字符串能达到要求
    // cnts -> 除去l，r范围内的字符，即在窗口之外，每一种字符的词频统计
    // length -> 窗口的长度
    // require -> 要求：每一种字符都达到 n/4

    // 总体流程：
    // 左窗口l从0位置开始，右窗口r从0位置
    // 如果除去窗口内的数组不能满足，并且r没有来到右边界 -> while(!ok(cnts,r-l,require) && r<n)
    // 右窗口向右移动，同时频次表除去刚进来的字符次数
    // 退出循环后
    // 判断是否是因为满足条件退出循环
    // 如果是的话，更新答案，
    // 如果不是，就不操作
    // 把左窗口的词频重新加1，l往右移动
    // 直到l来到边界位置
    //
    // 注意：右窗口时不回退的
    // 如在移动的过程中，2～9位置是满足要求的
    // l向右移动，3～1，3～2。。。3～8，这些位置必然是满足不了要求的
    // 下一就直接从3～9这个窗口验证是否能满足，
    // 如果不能，r继续向右滑动
    // 如果可以，更新答案，l向右滑动


    /**
     * 替换子串得到平衡字符串
     *
     * @param s
     * @return
     */
    public static int balancedString(String s) {
        int n = s.length();

        char[] str = s.toCharArray();

        // 字符转成数字
        int[] arr = new int[n];
        // 词频统计
        int[] cnts = new int[4];
        for (int i = 0; i < n; i++) {
            switch (str[i]) {
                case 'Q' -> {
                    arr[i] = 0;
                    cnts[0]++;
                }
                case 'W' -> {
                    arr[i] = 1;
                    cnts[1]++;
                }
                case 'E' -> {
                    arr[i] = 2;
                    cnts[2]++;
                }
                case 'R' -> {
                    arr[i] = 3;
                    cnts[3]++;
                }
            }
        }

        int require = n / 4;
        int ans = n;

        for (int r = 0, l = 0; l < n; l++) {


            // 窗口[l,r)
            while (!ok(cnts, r - l, require) && r < n) {
                cnts[arr[r++]]--;
            }
            if (ok(cnts, r - l, require)) {
                ans = Math.min(ans, r - l);
            }

            // 如果都没满足，左窗口往右移动，同时cnts词频++
            cnts[arr[l]]++;
        }


        return ans == n ? 0 : ans;
    }


    /**
     * 判断除去窗口内的字符是否满足要求
     *
     * @param cnts    除去窗口内的词频统计
     * @param length  窗口长度
     * @param require 满足要求的数量
     * @return 是否满足要求
     */
    public static boolean ok(int[] cnts, int length, int require) {


        for (int i = 0; i < cnts.length; i++) {

            // 如果窗口外有词频大于require的，直接返回false
            if (cnts[i] > require) {
                return false;
            }

            // 如果词频没达到要求，那么就让窗口长度减去插值
            // 意思是让这个插值变成缺少的字符
            length -= require - cnts[i];
        }

        return length == 0;
    }
}

