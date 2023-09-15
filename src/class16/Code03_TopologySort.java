package class16;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

/**
 * @Author Agony
 * @Create 2023/9/15 10:03
 * @Version 1.0
 * 拓扑排序
 * 是一种有向无环图，根据先后顺序能够把工作做完
 * 先找到入度为0的节点，把它的影响全部消除，再找到下一个入度为0的节点，消除影响，往复执行
 */
public class Code03_TopologySort {
    public static List<Node> sortedTopology(Graph graph) {

        // 存放每个节点及其入度
        HashMap<Node, Integer> inMap = new HashMap<>();
        // 用以存放每个入度为0的节点，先进先出
        LinkedList<Node> queue = new LinkedList<>();
        // 遍历每一个节点
        for (Node value : graph.nodes.values()) {
            // 记录每个节点及其入度
            inMap.put(value, value.in);
            if (value.in == 0) {
                queue.add(value);
            }
        }
        ArrayList<Node> res = new ArrayList<>();
        while (!queue.isEmpty()) {
            Node cur = queue.pop();
            res.add(cur);
            for (Node next : cur.nexts) {
                inMap.put(next, inMap.get(next) - 1);
                if (inMap.get(next) == 0) {
                    queue.add(next);
                }
            }
        }
        return res;
    }
}
