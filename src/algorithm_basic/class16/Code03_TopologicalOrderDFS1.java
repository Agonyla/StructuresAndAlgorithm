package algorithm_basic.class16;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;

/**
 * @Author Agony
 * @Create 2023/9/14 21:39
 * @Version 1.0
 */
public class Code03_TopologicalOrderDFS1 {
    public static class DirectedGraphNode {
        int label;
        List<DirectedGraphNode> neighbors;

        DirectedGraphNode(int x) {
            label = x;
            neighbors = new ArrayList<DirectedGraphNode>();
        }
    }

    public static class Record {
        public DirectedGraphNode node;
        public int deep;

        public Record(DirectedGraphNode n, int o) {
            node = n;
            deep = o;
        }
    }

    public ArrayList<DirectedGraphNode> topSort(ArrayList<DirectedGraphNode> graph) {
        if (graph == null) {
            return null;
        }
        HashMap<DirectedGraphNode, Record> order = new HashMap<>();
        // 在order中放入所有节点的record
        for (DirectedGraphNode node : graph) {
            f(node, order);
        }
        ArrayList<Record> records = new ArrayList<>(order.values());
        // 按深度从高到低排序
        records.sort(new Comparator<Record>() {
            @Override
            public int compare(Record o1, Record o2) {
                return o2.deep - o1.deep;
            }
        });
        ArrayList<DirectedGraphNode> res = new ArrayList<>();
        for (Record record : records) {
            res.add(record.node);
        }
        return res;
    }

    public static Record f(DirectedGraphNode node, HashMap<DirectedGraphNode, Record> order) {
        if (order.containsKey(node)) {
            return order.get(node);
        }
        int deep = 0;
        for (DirectedGraphNode neighbor : node.neighbors) {
            deep = Math.max(deep, f(neighbor, order).deep);
        }
        Record res = new Record(node, deep + 1);
        order.put(node, res);
        return res;
    }
}
