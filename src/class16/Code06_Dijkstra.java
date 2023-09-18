package class16;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

/**
 * @Author Agony
 * @Create 2023/9/16 20:01
 * @Version 1.0
 * 最小生成树之Dijkstra算法(必须指定一个源点)
 * 生成一个源点到各个点的最小距离表
 * 一开始只有一条记录：源点到自己的最小距离为0，到其他点的距离为正无穷大
 * 从表中拿出没那出过的最小记录，通过从这个点发出的边，更新源点到各个边的距离，不断重复
 * 知道表中所有的点都拿过一次，最小距离表得到
 */
public class Code06_Dijkstra {

    // 普通
    public static HashMap<Node, Integer> dijkstra1(Node node) {
        // 记录从源头到各个节点的最短距离
        HashMap<Node, Integer> res = new HashMap<>();
        res.put(node, 0);
        // 记录哪些点已经从表中取出过
        HashSet<Node> setNode = new HashSet<>();

        Node minNode = getMinNode(res, setNode);
        while (minNode != null) {
            int minDistance = res.get(minNode);
            for (Edge edge : minNode.edges) {
                int distance = edge.weight;
                Node nodeTo = edge.to;
                if (!res.containsKey(nodeTo)) {
                    res.put(nodeTo, minDistance + distance);
                } else {
                    res.put(nodeTo, Math.min(res.get(nodeTo), minDistance + distance));
                }
            }
            setNode.add(minNode);
            minNode = getMinNode(res, setNode);
        }
        return res;
    }


    public static Node getMinNode(HashMap<Node, Integer> res, HashSet<Node> setNode) {
        Node minNode = null;
        int minDistance = Integer.MAX_VALUE;

        for (Map.Entry<Node, Integer> entry : res.entrySet()) {
            Node node = entry.getKey();
            Integer distance = entry.getValue();
            if (!setNode.contains(node) && distance < minDistance) {
                minNode = node;
                minDistance = distance;
            }
        }

        return minNode;
    }


    public static class NodeRecord {
        public Node node;
        public int distance;

        public NodeRecord(Node node, int distance) {
            this.node = node;
            this.distance = distance;
        }
    }

    public static class NodeHeap {
        private Node[] nodes; // 实际的堆结构
        // key 某一个node， value 上面堆中的位置
        private HashMap<Node, Integer> heapIndexMap;
        // key 某一个节点， value 从源节点出发到该节点的目前最小距离
        private HashMap<Node, Integer> distanceMap;
        private int size; // 堆上有多少个点

        public NodeHeap(int size) {
            nodes = new Node[size];
            heapIndexMap = new HashMap<>();
            distanceMap = new HashMap<>();
            size = 0;
        }

        public boolean isEmpty() {
            return size == 0;
        }

        // 有一个点叫node，现在发现了一个从源节点出发到达node的距离为distance
        // 判断要不要更新，如果需要的话，就更新
        public void addOrUpdateOrIgnore(Node node, int distance) {
            if (inHeap(node)) {
                distanceMap.put(node, Math.min(distanceMap.get(node), distance));
                insertHeapify(node, heapIndexMap.get(node));
            }
            if (!isEntered(node)) {
                nodes[size] = node;
                heapIndexMap.put(node, size);
                distanceMap.put(node, distance);
                insertHeapify(node, size++);
            }
        }

        public NodeRecord pop() {
            NodeRecord nodeRecord = new NodeRecord(nodes[0], distanceMap.get(nodes[0]));
            swap(0, size - 1);
            heapIndexMap.put(nodes[size - 1], -1);
            distanceMap.remove(nodes[size - 1]);
            // free C++同学还要把原本堆顶节点析构，对java同学不必
            nodes[size - 1] = null;
            heapify(0, --size);
            return nodeRecord;
        }

        private void insertHeapify(Node node, int index) {
            while (distanceMap.get(nodes[index]) < distanceMap.get(nodes[(index - 1) / 2])) {
                swap(index, (index - 1) / 2);
                index = (index - 1) / 2;
            }
        }

        private void heapify(int index, int size) {
            int left = index * 2 + 1;
            while (left < size) {
                int smallest = left + 1 < size && distanceMap.get(nodes[left + 1]) < distanceMap.get(nodes[left])
                        ? left + 1
                        : left;
                smallest = distanceMap.get(nodes[smallest]) < distanceMap.get(nodes[index]) ? smallest : index;
                if (smallest == index) {
                    break;
                }
                swap(smallest, index);
                index = smallest;
                left = index * 2 + 1;
            }
        }

        private boolean isEntered(Node node) {
            return heapIndexMap.containsKey(node);
        }

        private boolean inHeap(Node node) {
            return isEntered(node) && heapIndexMap.get(node) != -1;
        }

        private void swap(int index1, int index2) {
            heapIndexMap.put(nodes[index1], index2);
            heapIndexMap.put(nodes[index2], index1);
            Node tmp = nodes[index1];
            nodes[index1] = nodes[index2];
            nodes[index2] = tmp;
        }
    }

    // 改进后的dijkstra算法
    // 从head出发，所有head能到达的节点，生成到达每个节点的最小路径记录并返回
    public static HashMap<Node, Integer> dijkstra2(Node head, int size) {
        NodeHeap nodeHeap = new NodeHeap(size);
        nodeHeap.addOrUpdateOrIgnore(head, 0);
        HashMap<Node, Integer> result = new HashMap<>();
        while (!nodeHeap.isEmpty()) {
            NodeRecord record = nodeHeap.pop();
            Node cur = record.node;
            int distance = record.distance;
            for (Edge edge : cur.edges) {
                nodeHeap.addOrUpdateOrIgnore(edge.to, edge.weight + distance);
            }
            result.put(cur, distance);
        }
        return result;
    }
}
