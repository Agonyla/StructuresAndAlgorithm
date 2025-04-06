package algorithm_journey.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: Agony
 * @create: 2025/4/6 16:41
 * @describe: 面试题 08.09. 括号
 * <a href="https://leetcode.cn/problems/bracket-lcci/description/?envType=company&envId=huawei&favoriteSlug=huawei-three-months">面试题 08.09. 括号</a>
 */
public class LeetCode0809 {


    public static void main(String[] args) {


        List<String> list = generateParenthesis(3);

        for (String s : list) {
            System.out.println(s);
        }


    }


    public static List<String> generateParenthesis(int n) {

        ArrayList<String> list = new ArrayList<>();

        backtrack(n, 0, 0, new StringBuilder(), list);
        return list;
    }


    /**
     * 递归回溯
     *
     * @param n        n对括号
     * @param leftNum  已使用左括号的数量
     * @param rightNum 已使用右括号的数量
     * @param sb
     * @param list
     */
    public static void backtrack(int n, int leftNum, int rightNum, StringBuilder sb, List<String> list) {

        if (sb.length() == n * 2) {
            list.add(sb.toString());
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
