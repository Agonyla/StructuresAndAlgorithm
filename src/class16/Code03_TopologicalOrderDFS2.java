package class16;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;

/**
 * @Author Agony
 * @Create 2023/9/14 21:09
 * @Version 1.0
 * Code3同一个链接
 */
public class Code03_TopologicalOrderDFS2 {

    public static class DirectedGraphNode {
        int label;
        List<DirectedGraphNode> neighbors;

        DirectedGraphNode(int x) {
            label = x;
            neighbors = new ArrayList<DirectedGraphNode>();
        }
    }

    /**
     * node 记录节点
     * nodes 记录节点的点次
     */
    public static class Record {
        DirectedGraphNode node;
        long nodes;

        public Record(DirectedGraphNode node, long nodes) {
            this.node = node;
            this.nodes = nodes;
        }
    }

    public ArrayList<DirectedGraphNode> topSort(ArrayList<DirectedGraphNode> graph) {
        if (graph == null) {
            return null;
        }
        HashMap<DirectedGraphNode, Record> order = new HashMap<>();
        // 记录所有节点的点次
        for (DirectedGraphNode node : graph) {
            f(node, order);
        }
        ArrayList<Record> records = new ArrayList<>(order.values());
        // 把所有点次按从大到小的顺序排列
        records.sort(new Comparator<Record>() {
            @Override
            public int compare(Record o1, Record o2) {
                return Long.compare(o2.nodes, o1.nodes);
            }
        });

        ArrayList<DirectedGraphNode> res = new ArrayList<>();
        for (Record record : records) {
            res.add(record.node);
        }
        return res;
    }

    /**
     * @param node  当前节点
     * @param order 缓存表，记录节点以及节点的record
     * @return 当前节点的record
     */
    public static Record f(DirectedGraphNode node, HashMap<DirectedGraphNode, Record> order) {
        if (order.containsKey(node)) {
            return order.get(node);
        }
        long nodes = 0;
        for (DirectedGraphNode neighbor : node.neighbors) {
            nodes += f(neighbor, order).nodes;
        }
        Record res = new Record(node, nodes + 1);
        order.put(node, res);
        return res;
    }
}
