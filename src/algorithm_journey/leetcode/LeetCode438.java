package algorithm_journey.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: Agony
 * @create: 2025/4/4 21:18
 * @describe: 找到字符串中所有字母异位词
 * <a href="https://leetcode.cn/problems/find-all-anagrams-in-a-string/description/?envType=company&envId=huawei&favoriteSlug=huawei-three-months">找到字符串中所有字母异位词</a>
 */
public class LeetCode438 {


    public static void main(String[] args) {

        String s = "cbaebabacd";
        String p = "abc";

        for (Integer anagram : findAnagrams(s, p)) {
            System.out.print(anagram + " ");
        }
        System.out.println();


        s = "abab";
        p = "ab";
        for (Integer anagram : findAnagrams(s, p)) {
            System.out.print(anagram + " ");
        }
        System.out.println();

    }

    public static List<Integer> findAnagrams(String s, String p) {
        List<Integer> result = new ArrayList<>();

        // 处理边界情况
        if (s == null || s.isEmpty() || p == null || p.isEmpty() || s.length() < p.length()) {
            return result;
        }

        int[] charCount = new int[26]; // 用于记录小写英文字母出现次数的数组

        // 记录模式串p中每个字符的出现频率
        for (char c : p.toCharArray()) {
            charCount[c - 'a']++;
        }

        int left = 0;  // 滑动窗口的左边界
        int right = 0; // 滑动窗口的右边界
        int count = p.length(); // 需要匹配的字符数量

        // 滑动窗口技术
        while (right < s.length()) {
            // 如果右指针指向的字符在p中存在（该字符计数大于0），
            // 则减少需要匹配的字符数量
            if (charCount[s.charAt(right) - 'a'] > 0) {
                count--;
            }

            // 减少右指针字符的计数（即使变为负数，表示该字符在窗口中出现次数多于p中的出现次数）
            charCount[s.charAt(right) - 'a']--;
            right++; // 右指针向右移动

            // 如果所有字符都匹配上了（count = 0），找到一个异位词
            if (count == 0) {
                result.add(left);
            }

            // 当窗口大小等于p的长度时，需要移动左指针来维持窗口大小
            if (right - left == p.length()) {
                // 如果左指针指向的字符是p中的字符（charCount在减少后仍然大于等于0），
                // 则增加需要匹配的字符数量
                if (charCount[s.charAt(left) - 'a'] >= 0) {
                    count++;
                }

                // 增加左指针字符的计数（因为该字符将不再在窗口中）
                charCount[s.charAt(left) - 'a']++;
                left++; // 左指针向右移动
            }
        }

        return result;
    }


}
