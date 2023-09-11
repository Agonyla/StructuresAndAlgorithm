package class16;

import java.util.HashMap;
import java.util.HashSet;

/**
 * @Author Agony
 * @Create 2023/9/11 20:32
 * @Version 1.0
 */
public class Graph {
    public HashMap<Integer, Node> nodes;
    public HashSet<Edge> edges;

    public Graph() {
        nodes = new HashMap<>();
        edges = new HashSet<>();
    }
}
