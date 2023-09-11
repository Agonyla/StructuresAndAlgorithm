package class16;

import java.util.ArrayList;

/**
 * @Author Agony
 * @Create 2023/9/11 20:25
 * @Version 1.0
 * 图的节点信息
 * value:节点值
 * in:有几个节点进入这个节点
 * out:有几个节点出去
 * nexts：出去的节点
 * edges：每条出去的边
 */

public class Node {
    public int value;
    public int in;

    public int out;
    public ArrayList<Node> nexts;
    public ArrayList<Edge> edges;

    public Node(int value) {
        this.value = value;
        this.in = 0;
        this.out = 0;
        nexts = new ArrayList<>();
        edges = new ArrayList<>();
    }
}
