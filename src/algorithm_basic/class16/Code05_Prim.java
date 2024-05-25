package algorithm_basic.class16;

import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Set;

/**
 * @Author Agony
 * @Create 2023/9/16 19:45
 * @Version 1.0
 * 用堆实现Prim算法 => 小根堆
 * Prim算法又称为"加点法"
 * 先随便找一个点，解锁点的全部边 => 用小根堆添加
 * 弹出边，添加点，再解锁该点的所有边
 * 算法逐渐从某一个顶点s开始，逐渐将n个点纳入最小生成树中。
 */
public class Code05_Prim {
    public static Set<Edge> primMST(Graph graph) {
        PriorityQueue<Edge> edges = new PriorityQueue<>((Edge e1, Edge e2) -> e1.weight - e2.weight);
        // 用来存放点
        // 当弹出最小的边时，此时可能点已经添加过了
        HashSet<Node> setNode = new HashSet<>();
        HashSet<Edge> res = new HashSet<>();

        for (Node value : graph.nodes.values()) {
            setNode.add(value);
            // 解锁这个点的所有边
            edges.addAll(value.edges);
            while (!edges.isEmpty()) {
                // 弹出最小的边
                Edge edge = edges.poll();
                Node nodeTo = edge.to;
                // setNode 中没有这个边时，添加，并且所其所有边
                if (!setNode.contains(nodeTo)) {
                    setNode.add(nodeTo);
                    res.add(edge);
                    edges.addAll(nodeTo.edges);
                }
            }
        }
        return res;
    }

}
