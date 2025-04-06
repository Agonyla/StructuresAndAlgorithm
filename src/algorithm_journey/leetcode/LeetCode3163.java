package algorithm_journey.leetcode;

/**
 * @author: Agony
 * @create: 2025/4/6 17:28
 * @describe: 压缩字符串 III
 * <a href="https://leetcode.cn/problems/string-compression-iii/description/?envType=company&envId=huawei&favoriteSlug=huawei-three-months">压缩字符串 III</a>
 */
public class LeetCode3163 {

    public static void main(String[] args) {


        System.out.println(compressedString("abcde"));

        System.out.println(compressedString("aab"));

        System.out.println(compressedString("aaaaaaaaaaaaaabb"));

        System.out.println(compressedString("aaaaaaaaay"));
    }


    public static String compressedString(String word) {
        char[] words = word.toCharArray();

        StringBuilder sb = new StringBuilder();

        int left = 0;
        int count = 1;

        for (int right = 1; right < words.length; right++) {

            if (words[left] != words[right]) {
                sb.append(count).append(words[left]);
                left = right;
                count = 1;
            } else {
                count++;
                if (count == 9) {
                    sb.append(count).append(words[left]);
                    left = right + 1;
                    count = 0;
                }
            }
        }
        if (left < words.length) {
            sb.append(count).append(words[left]);
        }
        // sb.append(count).append(words[left]);

        return sb.toString();
    }
}
