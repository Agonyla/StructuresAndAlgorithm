package algorithm_journey.leetcode;

import java.util.Stack;

/**
 * @author: Agony
 * @create: 2025/4/6 16:27
 * @describe: 删除字符串中的所有相邻重复项
 * <a href="https://leetcode.cn/problems/remove-all-adjacent-duplicates-in-string/?envType=company&envId=huawei&favoriteSlug=huawei-three-months">删除字符串中的所有相邻重复项</a>
 */
public class LeetCode1047 {


    public static void main(String[] args) {

        String s = "abbaca";
        System.out.println(removeDuplicates(s));
    }


    public static String removeDuplicates(String s) {

        Stack<Character> stack = new Stack<>();

        boolean isSame = false;

        for (int i = 0; i < s.length(); i++) {

            char current = s.charAt(i);

            while (!stack.isEmpty() && current == stack.peek()) {
                stack.pop();
                isSame = true;
            }

            if (!isSame) {
                stack.push(current);
            }
            isSame = false;

        }


        char[] chars = new char[stack.size()];
        for (int i = chars.length - 1; i >= 0; i--) {
            chars[i] = stack.pop();
        }
        return new String(chars);
    }
}
