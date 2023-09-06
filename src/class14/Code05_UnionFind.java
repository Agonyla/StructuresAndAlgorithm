package class14;

import java.util.HashMap;
import java.util.List;
import java.util.Stack;

/**
 * @Author Agony
 * @Create 2023/9/6 21:02
 * @Version 1.0
 * 并查集实现
 */
public class Code05_UnionFind {

    public static class Node<V> {
        V value;

        public Node(V value) {
            this.value = value;
        }
    }

    /**
     * 并查集
     *
     * @param <V>
     */
    public static class UnionFind<V> {

        public HashMap<V, Node<V>> nodes;
        public HashMap<Node<V>, Node<V>> parents;
        public HashMap<Node<V>, Integer> sizeMap;

        // 初始化
        public UnionFind(List<V> values) {
            this.nodes = new HashMap<>();
            this.parents = new HashMap<>();
            this.sizeMap = new HashMap<>();

            for (V value : values) {
                Node<V> node = new Node<>(value);
                nodes.put(value, node);
                parents.put(node, node);
                sizeMap.put(node, 1);
            }
        }

        /**
         * 给你一个节点，找到其代表节点(最上面的那个节点) ==> 直接用while循环
         * 为了便于后序查找，将cur之后的节点的父节点全都设置成代表节点
         *
         * @param cur 查找结点
         * @return 代表节点
         */
        public Node<V> findFather(Node<V> cur) {

            Stack<Node<V>> stack = new Stack<>();

            while (cur != parents.get(cur)) {
                stack.push(cur);
                cur = parents.get(cur);
            }

            // 将cur之后的节点的父节点全都设置成代表节点
            while (!stack.isEmpty()) {
                parents.put(stack.pop(), cur);
            }
            return cur;
        }

        /**
         * 判断两个节点是否有相同的代表节点
         *
         * @param a
         * @param b
         * @return
         */
        public boolean isSameSet(V a, V b) {
            return findFather(nodes.get(a)) == findFather(nodes.get(b));
        }

        /**
         * 合并
         *
         * @param a
         * @param b
         */
        public void union(V a, V b) {
            if (isSameSet(a, b)) {
                return;
            }
            Node<V> nodeA = nodes.get(a);
            Node<V> nodeB = nodes.get(b);
            Node<V> fatherA = parents.get(nodeA);
            Node<V> fatherB = parents.get(nodeB);
            Integer sizeA = sizeMap.get(fatherA);
            Integer sizeB = sizeMap.get(fatherB);
            // if (sizeA >= sizeB) {
            //     parents.put(fatherB, fatherA);
            //     sizeMap.put(fatherA, sizeA + sizeB);
            //     sizeMap.remove(fatherB);
            // } else {
            //     parents.put(fatherB, fatherA);
            //     sizeMap.put(fatherA, sizeA + sizeB);
            //     sizeMap.remove(fatherA);
            // }

            // ==>

            Node<V> big = sizeA >= sizeB ? fatherA : fatherB;
            Node<V> small = big == fatherA ? fatherB : fatherA;
            parents.put(small, big);
            sizeMap.put(big, sizeA + sizeB);
            sizeMap.remove(small);
        }

        /**
         * @return 返回并查集的个数
         */
        public int sets() {
            return sizeMap.size();
        }


    }


}
