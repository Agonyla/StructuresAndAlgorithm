package algorithm_journey.leetcode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * @author: Agony
 * @create: 2025/4/4 20:16
 * @describe: 课程表
 * @link: <a href="https://leetcode.cn/problems/course-schedule/description/?envType=company&envId=huawei&favoriteSlug=huawei-three-months">课程表</a>
 */
public class LeetCode207 {


    public static void main(String[] args) {


    }


    public boolean canFinish(int numCourses, int[][] prerequisites) {
        // 构建邻接表表示图
        List<List<Integer>> adjacency = new ArrayList<>();
        for (int i = 0; i < numCourses; i++) {
            adjacency.add(new ArrayList<>());
        }

        // 记录每个节点的入度
        int[] inDegree = new int[numCourses];

        // 填充邻接表和入度数组
        for (int[] prerequisite : prerequisites) {
            int course = prerequisite[0];
            int prereq = prerequisite[1];
            adjacency.get(prereq).add(course); // prereq -> course
            inDegree[course]++; // course的入度加1
        }

        // 使用队列进行拓扑排序
        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < numCourses; i++) {
            if (inDegree[i] == 0) {
                queue.offer(i);
            }
        }

        // 拓扑排序过程
        int count = 0;
        while (!queue.isEmpty()) {
            int current = queue.poll();
            count++;

            // 移除所有从current出发的边
            for (int next : adjacency.get(current)) {
                inDegree[next]--;
                if (inDegree[next] == 0) {
                    queue.offer(next);
                }
            }
        }

        // 如果所有课程都被访问，说明没有环
        return count == numCourses;
    }

}
