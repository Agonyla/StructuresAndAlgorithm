package algorithm_basic.class16;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author Agony
 * @Create 2023/9/14 20:14
 * @Version 1.0
 * <a href="https://www.lintcode.com/problem/127/">拓扑排序</a>
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

    //TODO: 看看后面视频有没有讲别的方法实现拓扑排序
    public ArrayList<DirectedGraphNode> topSort(ArrayList<DirectedGraphNode> graph) {

        return null;

    }

}
