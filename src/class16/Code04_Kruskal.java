package class16;

import java.util.*;

/**
 * @Author Agony
 * @Create 2023/9/15 19:18
 * @Version 1.0
 * 最小生成树算法之Kruskal
 * 用并查集实现Kruskal算法
 * Kruskal算法是一种贪心算法，也是取边法，
 * 小根堆 => 首先取出权值最小的边
 * 用并查集合并边的两个点
 * 再在剩下的边中依次取出权值最小却不会形成环的边
 */
public class Code04_Kruskal {
    // 并查集
    public static class UnionFind {
        private HashMap<Node, Node> fatherMap;
        private HashMap<Node, Integer> sizeMap;

        public UnionFind(Collection<Node> nodes) {
            fatherMap = new HashMap<Node, Node>();
            sizeMap = new HashMap<Node, Integer>();
            for (Node node : nodes) {
                fatherMap.put(node, node);
                sizeMap.put(node, 1);
            }
        }

        public boolean isSameSet(Node node1, Node node2) {
            return findFather(node1) == findFather(node2);
        }

        public Node findFather(Node node) {
            Stack<Node> stack = new Stack<>();
            while (fatherMap.get(node) != node) {
                stack.add(node);
                node = fatherMap.get(node);
            }
            while (!stack.isEmpty()) {
                fatherMap.put(stack.pop(), node);
            }
            return node;
        }

        public void union(Node node1, Node node2) {
            if (findFather(node1) == findFather(node2)) {
                return;
            }
            Node f1 = findFather(node1);
            Node f2 = findFather(node2);
            int s1 = sizeMap.get(f1);
            int s2 = sizeMap.get(f2);
            Node big = s1 > s2 ? f1 : f2;
            Node small = big == f1 ? f2 : f1;
            sizeMap.put(big, s1 + s2);
            sizeMap.remove(small);
        }
    }


    public static Set<Edge> kruskalMST(Graph graph) {
        // 记录添加的边
        HashSet<Edge> res = new HashSet<>();
        UnionFind unionFind = new UnionFind(graph.nodes.values());
        // 小根堆
        PriorityQueue<Edge> edges = new PriorityQueue<>((Edge o1, Edge o2) -> o1.weight - o2.weight);
        edges.addAll(graph.edges);
        while (!edges.isEmpty()) {
            Edge edge = edges.poll();
            Node nodeFrom = edge.from;
            Node nodeTo = edge.to;
            // 需要先判断是否是在同一个集合中
            // 不然在添加边的时候可能会重复添加
            if (!unionFind.isSameSet(nodeFrom, nodeTo)) {
                res.add(edge);
                unionFind.union(nodeTo, nodeFrom);
            }
        }
        return res;
    }

}
