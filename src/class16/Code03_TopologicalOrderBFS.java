package class16;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author Agony
 * @Create 2023/9/14 20:14
 * @Version 1.0
 * <a href="https://www.lintcode.com/problem/127/">拓扑排序</a>
 * 是一种有向无环图，根据先后顺序能够把工作做完
 * 先找到入度为0的节点，把它的影响全部消除，再找到下一个入度为0的节点，消除影响，往复执行
 */
public class Code03_TopologicalOrderBFS {

    public static class DirectedGraphNode {
        int label;
        List<DirectedGraphNode> neighbors;

        DirectedGraphNode(int x) {
            label = x;
            neighbors = new ArrayList<DirectedGraphNode>();
        }
    }

    public ArrayList<DirectedGraphNode> topSort(ArrayList<DirectedGraphNode> graph) {

        return null;

    }

}
