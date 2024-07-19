package algorithm_journey.class046;

import java.util.Arrays;

/**
 * 每个元音包含偶数次的最长子字符串
 *
 * @author: Agony
 * @create: 2024/7/17 10:48
 * @describe: 给你一个字符串 s ，请你返回满足以下条件的最长子字符串的长度：每个元音字母，
 * 即 'a'，'e'，'i'，'o'，'u' ，在子字符串中都恰好出现了偶数次。
 * <p>
 * 输入：s = "eleetminicoworoep"
 * 输出：13
 * 解释：最长子字符串是 "leetminicowor" ，它包含 e，i，o 各 2 个，以及 0 个 a，u 。
 * @link: <a href="https://leetcode.cn/problems/find-the-longest-substring-containing-vowels-in-even-counts/description/">每个元音包含偶数次的最长子字符串</a>
 */
public class Code07_EvenCountsLongestSubarray {


    public static void main(String[] args) {
        String s = "eleetminicoworoep";
        System.out.println(findTheLongestSubstring(s));


        s = "bcbcbc";
        System.out.println(findTheLongestSubstring(s));

    }

    // 每个元音包含偶数次的最长子字符串

    // 思路
    // 用 int status 记录 元音字母的状态
    // status=00100 -> 表示 uoiea 中的 i 出现了奇数次
    // status=10011 -> 表示 uoiea 中的 u、e、a、出现了奇数次
    // 如果0～i位置的状态为 10010
    // 那么就去找status为10010，该状态最早出现的位置j，返回i-j，（利用奇偶性）
    //
    // 实现
    // 准备一个数组 int[] map = new int[32]来记录每个状态最早出现的位置
    // 00000 ～ 11111 一个32个数字
    // map初始化为-2，表示这32个状态之前一次都没出现过
    // 注意：00000 这个状态初始化为-1，表示全都是偶数这个状态最早出现位置为-1
    // 然后遍历字符串，通过 s.charAt(i)，判断该字符并构建 status 状态
    // 如果map中没有这个状态，就往map中加入该状态最招出现的位置 -> map[status]=i
    // 如果重复出现了这个状态，说明之前已经出现过了该状态，那么在之前出现的状态到i位置这段字符串内，aeiou 出现的次数是均是偶数次
    // 就直接返回 i-map[status]
    // 然后比较各个0到当前位置的最大值返回


    /**
     * 返回每个元音包含偶数次的最长子字符串
     *
     * @param s 字符串
     * @return 每个元音包含偶数次的最长子字符串
     */
    public static int findTheLongestSubstring(String s) {

        // 记录 uoiea 00000～11111 这32个状态的奇偶数量
        int[] map = new int[32];
        Arrays.fill(map, -2);
        map[0] = -1;

        int ans = 0;
        // 0~i uoiea 出现的状态
        int status = 0;

        for (int i = 0; i < s.length(); i++) {
            int path = path(s.charAt(i));
            if (path != -1) {
                status ^= 1 << path;
            }
            if (map[status] != -2) {
                ans = Math.max(ans, i - map[status]);
            } else {
                map[status] = i;
            }
        }
        return ans;
    }


    /**
     * 根据 uoiea 5、4、3、2、1
     *
     * @param cha
     * @return
     */
    public static int path(char cha) {
        return switch (cha) {
            case 'a' -> 0;
            case 'e' -> 1;
            case 'i' -> 2;
            case 'o' -> 3;
            case 'u' -> 4;
            default -> -1;
        };
    }


}







