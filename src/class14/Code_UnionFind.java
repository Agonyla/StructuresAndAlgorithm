package class14;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Stack;

/**
 * @Author Agony
 * @Create 2023/9/1 10:39
 * @Version 1.0
 */
public class Code_UnionFind {
    public static class Node<V> {
        V value;

        public Node(V v) {
            value = v;
        }
    }

    public static class UnionFind<V> {
        public HashMap<V, Node<V>> nodes;
        public HashMap<Node<V>, Node<V>> parents;
        public HashMap<Node<V>, Integer> sizeMap;

        public UnionFind(List<V> values) {
            nodes = new HashMap<>();
            parents = new HashMap<>();
            sizeMap = new HashMap<>();
            for (V cur : values) {
                Node<V> node = new Node<>(cur);
                nodes.put(cur, node);
                parents.put(node, node);
                sizeMap.put(node, 1);
            }
        }

        // 给你一个节点，请你往上到不能再往上，把代表返回
        public Node<V> findFather(Node<V> cur) {
            Stack<Node<V>> path = new Stack<>();
            while (cur != parents.get(cur)) {
                path.push(cur);
                cur = parents.get(cur);
            }
            while (!path.isEmpty()) {
                parents.put(path.pop(), cur);
            }
            return cur;
        }

        public boolean isSameSet(V a, V b) {
            return findFather(nodes.get(a)) == findFather(nodes.get(b));
        }

        public void union(V a, V b) {
            Node<V> aHead = findFather(nodes.get(a));
            Node<V> bHead = findFather(nodes.get(b));
            if (aHead != bHead) {
                int aSetSize = sizeMap.get(aHead);
                int bSetSize = sizeMap.get(bHead);
                Node<V> big = aSetSize >= bSetSize ? aHead : bHead;
                Node<V> small = big == aHead ? bHead : aHead;
                parents.put(small, big);
                sizeMap.put(big, aSetSize + bSetSize);
                sizeMap.remove(small);
            }
        }

        public int sets() {
            return sizeMap.size();
        }

    }

    public static void main(String[] args) {
        ArrayList<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        list.add(5);
        list.add(6);

        UnionFind<Integer> unionFind = new UnionFind<>(list);

        System.out.println(unionFind.isSameSet(1, 2));

        Node<Integer> i1 = unionFind.nodes.get(1);
        Node<Integer> i2 = unionFind.nodes.get(2);
        Node<Integer> i3 = unionFind.nodes.get(3);
        Node<Integer> i4 = unionFind.nodes.get(4);

        Node<Integer> father = unionFind.findFather(i1);
        System.out.println(father == i1);

        unionFind.union(1, 2);
        unionFind.union(1, 3);
        unionFind.union(3, 4);


        Node<Integer> father1 = unionFind.findFather(i1);
        Node<Integer> father2 = unionFind.findFather(i2);
        Node<Integer> father3 = unionFind.findFather(i3);

        System.out.println(father1 == father2);

        Node<Integer> p1 = unionFind.parents.get(i1);
        Node<Integer> p2 = unionFind.parents.get(i2);
        Node<Integer> p3 = unionFind.parents.get(i3);
        Node<Integer> p4 = unionFind.parents.get(i4);

        System.out.println(p1.value);
        System.out.println(p2.value);
        System.out.println(p3.value);
        System.out.println(p4.value);
    }
}
