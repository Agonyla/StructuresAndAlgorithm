package algorithm_journey.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: Agony
 * @create: 2025/4/8 23:22
 * @describe: 1245. 树的直径
 * <a href="https://leetcode.cn/problems/tree-diameter/description/?envType=company&envId=huawei&favoriteSlug=huawei-three-months">1245. 树的直径</a>
 */
public class LeetCode1245 {


    public static void main(String[] args) {

    }

    private List<Integer>[] graph;
    private int farthestNode = 0;
    private int maxDistance = 0;

    public int treeDiameter(int[][] edges) {
        if (edges == null || edges.length == 0) {
            return 0;
        }

        // 构建图
        int n = edges.length + 1; // 边的数量为n-1，所以节点数为n
        graph = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            graph[i] = new ArrayList<>();
        }

        // 添加边（无向图）
        for (int[] edge : edges) {
            int u = edge[0];
            int v = edge[1];
            graph[u].add(v);
            graph[v].add(u);
        }

        // 第一次DFS，找到离任意节点（这里选0）最远的节点
        dfs(0, -1, 0);

        // 重置最大距离
        int temp = farthestNode;
        maxDistance = 0;

        // 第二次DFS，找到离第一次DFS找到的最远节点再最远的节点
        dfs(temp, -1, 0);

        return maxDistance;
    }

    private void dfs(int node, int parent, int distance) {
        // 更新最远距离和节点
        if (distance > maxDistance) {
            maxDistance = distance;
            farthestNode = node;
        }

        // 遍历当前节点的所有邻居
        for (int neighbor : graph[node]) {
            if (neighbor != parent) { // 避免回到父节点
                dfs(neighbor, node, distance + 1);
            }
        }
    }


}
