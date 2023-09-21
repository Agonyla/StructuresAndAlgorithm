package class19;

import java.util.HashMap;

/**
 * @Author Agony
 * @Create 2023/9/21 19:29
 * @Version 1.0
 * @describe: 给定一个字符串str，给定一个字符串类型的数组arr，出现的字符都是小写英文
 * arr每一个字符串，代表一张贴纸，你可以把单个字符剪开使用，目的是拼出str来
 * 返回需要至少多少张贴纸可以完成这个任务(每张贴纸都有无穷多张)
 * 例子：str= "babac"，arr = {"ba","c","abcd"}
 * ba + ba + c  3  abcd + abcd 2  abcd+ba 2
 * 所以返回2
 * <a href="https://leetcode.cn/problems/stickers-to-spell-word/">贴纸拼词</a>
 */
public class Code03_StickersToSpellWord {
    public static int minStickers(String[] stickers, String target) {
        int res = process(stickers, target);
        return res == Integer.MAX_VALUE ? -1 : res;
    }

    public static int process(String[] stickers, String target) {

        // base case
        if (target.isEmpty()) {
            return 0;
        }
        int min = Integer.MAX_VALUE;
        for (String sticker : stickers) {
            String rest = minus(target, sticker);
            if (rest.length() != target.length()) {
                min = Math.min(min, process(stickers, rest));
            }
        }
        return min + (min == Integer.MAX_VALUE ? 0 : 1);
    }

    public static String minus(String target, String sticker) {
        char[] targetArr = target.toCharArray();
        char[] stickerArr = sticker.toCharArray();
        int[] arr = new int[26];
        for (char c : targetArr) {
            arr[c - 'a']++;
        }
        for (char c : stickerArr) {
            arr[c - 'a']--;
        }
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < 26; i++) {
            if (arr[i] > 0) {
                for (int j = 0; j < arr[i]; j++) {
                    stringBuilder.append((char) ('a' + i));
                }
            }
        }
        return stringBuilder.toString();
    }

    public static void main(String[] args) {
        char[] chars = {'a', 'b', 'c'};
        int[] chars1 = new int[26];
        for (char c : chars) {
            chars1[c - 'a']++;
        }
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < chars1.length; i++) {
            if (chars1[i] > 0) {
                stringBuilder.append(String.valueOf((char) ('a' + i)).repeat(Math.max(0, chars1[i])));
            }
        }
        System.out.println(stringBuilder.toString());

        String a = "abcdef";
        String b = "cde";
        String minus = minus(a, b);
        System.out.println(minus);


    }

    public static int minStickers3(String[] stickers, String target) {
        int N = stickers.length;
        int[][] counts = new int[N][26];
        for (int i = 0; i < N; i++) {
            char[] str = stickers[i].toCharArray();
            for (char cha : str) {
                counts[i][cha - 'a']++;
            }
        }
        HashMap<String, Integer> dp = new HashMap<>();
        dp.put("", 0);
        int ans = process3(counts, target, dp);
        return ans == Integer.MAX_VALUE ? -1 : ans;
    }

    public static int process3(int[][] stickers, String t, HashMap<String, Integer> dp) {
        if (dp.containsKey(t)) {
            return dp.get(t);
        }
        char[] target = t.toCharArray();
        int[] tcounts = new int[26];
        for (char cha : target) {
            tcounts[cha - 'a']++;
        }
        int N = stickers.length;
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < N; i++) {
            int[] sticker = stickers[i];
            if (sticker[target[0] - 'a'] > 0) {
                StringBuilder builder = new StringBuilder();
                for (int j = 0; j < 26; j++) {
                    if (tcounts[j] > 0) {
                        int nums = tcounts[j] - sticker[j];
                        for (int k = 0; k < nums; k++) {
                            builder.append((char) (j + 'a'));
                        }
                    }
                }
                String rest = builder.toString();
                min = Math.min(min, process3(stickers, rest, dp));
            }
        }
        int ans = min + (min == Integer.MAX_VALUE ? 0 : 1);
        dp.put(t, ans);
        return ans;
    }

}
