package algorithm_journey.leetcode;

import algorithm_journey.utils.MathUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: Agony
 * @create: 2025/4/8 10:26
 * @describe: 字典序排数
 * <a href="https://leetcode.cn/problems/lexicographical-numbers/?envType=company&envId=huawei&favoriteSlug=huawei-three-months">字典序排数</a>
 */
public class LeetCode386 {


    public static void main(String[] args) {

        List<Integer> list = lexicalOrder(13);
        MathUtils.printList(list);
    }


    public static List<Integer> lexicalOrder(int n) {

        ArrayList<Integer> list = new ArrayList<>();

        for (int i = 1; i <= 9; i++) {
            dfs(i, n, list);
        }

        return list;
    }


    public static void dfs(int current, int n, List<Integer> list) {


        if (current > n) {
            return;
        }

        list.add(current);


        for (int i = 0; i <= 9; i++) {
            if (current * 10 + i <= n) {
                dfs(current * 10 + i, n, list);
            } else {
                break;
            }
        }
    }

}
