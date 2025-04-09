package algorithm_journey.leetcode;

import java.util.HashMap;
import java.util.List;

/**
 * @author: Agony
 * @create: 2025/4/9 11:32
 * @describe: 554. 砖墙
 * <a href="https://leetcode.cn/problems/brick-wall/description/?envType=company&envId=huawei&favoriteSlug=huawei-three-months">554. 砖墙</a>
 */
public class LeetCode554 {

    public static void main(String[] args) {

    }


    public static int leastBricks(List<List<Integer>> wall) {

        HashMap<Integer, Integer> edge = new HashMap<>();
        int max = 0;

        for (List<Integer> row : wall) {

            int position = 0;

            for (int i = 0; i < row.size() - 1; i++) {
                position += row.get(i);
                edge.put(position, edge.getOrDefault(position, 0) + 1);
                max = Math.max(max, edge.get(position));
            }

        }


        return wall.size() - max;
    }
}
