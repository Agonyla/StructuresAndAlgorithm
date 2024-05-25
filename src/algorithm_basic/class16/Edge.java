package algorithm_basic.class16;

/**
 * @Author Agony
 * @Create 2023/9/11 20:26
 * @Version 1.0
 * 边信息
 */

/**
 * weight：边的权重
 * from:从哪个节点开始
 * to：指向哪个节点
 */
public class Edge {
    public int weight;
    public Node from;
    public Node to;

    public Edge(int weight, Node from, Node to) {
        this.weight = weight;
        this.from = from;
        this.to = to;
    }
}
