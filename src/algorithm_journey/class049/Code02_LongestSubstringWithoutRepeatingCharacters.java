package algorithm_journey.class049;

import java.util.Arrays;
import java.util.HashMap;

/**
 * 无重复字符的最长子串
 *
 * @author: Agony
 * @create: 2024/7/22 18:07
 * @describe: 给定一个字符串 s ，请你找出其中不含有重复字符的 最长 子串的长度。
 * <p>
 * 输入: s = "abcabcbb"
 * 输出: 3
 * 解释: 因为无重复字符的最长子串是 "abc"，所以其长度为 3。
 * @link: <a href="https://leetcode.cn/problems/longest-substring-without-repeating-characters/description/">无重复字符的最长子串</a>
 */
public class Code02_LongestSubstringWithoutRepeatingCharacters {


    public static void main(String[] args) {

        String s = "abcabcbb";
        System.out.println(lengthOfLongestSubstring(s));

    }
    // 无重复字符的最长子串

    // 思路：
    // 大体思路和上题一致
    // 左边界更新注意事项：
    // 每次遇到一个字母时！！！
    // 左边界 = max(左边界，上次遇到该字母的位置+1)
    // 如：s = "abdcdadfe"
    // 左边界 l=0
    // 右边界 r=4，遇到d了
    // 此时左边界直接来到 i=3
    // 这时右边界遇到 a 了
    // 但是之前 a 的位置+1 小于 现在左边界的位置，所以左边界不更新
    // 怎么实现？
    // -> int[] last = new int[256] ： 初始值为 -1 表示上次出现的位置为-1
    // 字符串可以装成 char 数组 每一个字符可以用 0～255 中的数字表示
    // last[cha] 就表示 cha 字符 上次出现的位置
    // 如：last['a'] = 3，表示字符 'a' 上次出现的位置为 3
    // 记得更新最新位置


    /**
     * 无重复字符的最长子串
     *
     * @param s
     * @return
     */
    public static int lengthOfLongestSubstring(String s) {

        char[] chas = s.toCharArray();

        // 记录字符上一次出现的位置
        int[] map = new int[256];
        Arrays.fill(map, -1);
        int ans = 0;
        for (int r = 0, l = 0; r < s.length(); r++) {

            // 右边界遇到字符。左边界更新
            // 如果该字符之前还没遇到过 -> 保持原位置
            // 如果该字符之前遇到过但是在l之前 -> 保持原位置
            // 如果该字符之前遇到过但是在l之后 -> 来到这个字符+1的位置
            l = Math.max(map[chas[r]] + 1, l);

            // ans 更新
            ans = Math.max(ans, r - l + 1);

            // 把该字符加入到map中
            char cha = chas[r];
            map[cha] = r;
        }
        return ans;
    }


    /**
     * 无重复字符的最长子串
     * map实现
     *
     * @param s
     * @return
     */
    public static int lengthOfLongestSubstringByMap(String s) {

        char[] chas = s.toCharArray();

        // 记录字符上一次出现的位置
        HashMap<Character, Integer> map = new HashMap<>();
        int ans = 0;
        for (int r = 0, l = 0; r < s.length(); r++) {

            // 右边界遇到字符。左边界更新
            // 如果该字符之前还没遇到过 -> 保持原位置
            // 如果该字符之前遇到过但是在l之前 -> 保持原位置
            // 如果该字符之前遇到过但是在l之后 -> 来到这个字符+1的位置
            l = Math.max(map.getOrDefault(chas[r], -1) + 1, l);

            // ans 更新
            ans = Math.max(ans, r - l + 1);

            // 把该字符加入到map中
            map.put(chas[r], r);
        }
        return ans;
    }
}
