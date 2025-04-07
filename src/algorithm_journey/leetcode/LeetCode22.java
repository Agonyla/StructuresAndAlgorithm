package algorithm_journey.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: Agony
 * @create: 2025/4/7 10:23
 * @describe: 括号生成
 * <a href="https://leetcode.cn/problems/generate-parentheses/description/?envType=company&envId=huawei&favoriteSlug=huawei-three-months">括号生成</a>
 */
public class LeetCode22 {


    public static void main(String[] args) {

        for (String string : generateParenthesis(3)) {
            System.out.println(string);
        }

    }

    public static List<String> generateParenthesis(int n) {

        ArrayList<String> list = new ArrayList<>();
        backtrack(n, 0, 0, new StringBuilder(), list);
        return list;
    }


    public static void backtrack(int n, int leftNum, int rightNum, StringBuilder sb, List<String> list) {

        if (sb.length() == n * 2) {
            list.add(sb.toString());
            return;
        }
        if (leftNum < n) {
            sb.append('(');
            backtrack(n, leftNum + 1, rightNum, sb, list);
            sb.deleteCharAt(sb.length() - 1);
        }
        if (rightNum < leftNum) {
            sb.append(')');
            backtrack(n, leftNum, rightNum + 1, sb, list);
            sb.deleteCharAt(sb.length() - 1);
        }


    }
}
