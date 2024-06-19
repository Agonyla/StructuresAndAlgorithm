package algorithm_journey.class035;

import java.util.HashMap;
import java.util.Map;

/**
 * LRU 缓存
 *
 * @author: Agony
 * @create: 2024/6/14 10:37
 * @describe: 请你设计并实现一个满足  LRU (最近最少使用) 缓存 约束的数据结构。
 * 实现 LRUCache 类：
 * LRUCache(int capacity) 以 正整数 作为容量 capacity 初始化 LRU 缓存
 * int get(int key) 如果关键字 key 存在于缓存中，则返回关键字的值，否则返回 -1 。
 * void put(int key, int value) 如果关键字 key 已经存在，则变更其数据值 value ；
 * 如果不存在，则向缓存中插入该组 key-value 。如果插入操作导致关键字数量超过 capacity ，则应该 逐出 最久未使用的关键字。
 * 函数 get 和 put 必须以 O(1) 的平均时间复杂度运行。
 * 注意！！！每次 get 和 put 操作都表示该值已经使用过了
 * @link: <a href="https://leetcode.cn/problems/lru-cache/description/">LRU 缓存</a>
 */
public class Code02_LRU {


    public static void main(String[] args) {

        LRUCache lruCache = new LRUCache(1);
        lruCache.put(2, 1);
        lruCache.get(2);
        lruCache.put(3, 2);
        lruCache.get(2);
        lruCache.get(3);


    }

    // 实现LRU结构
    // 准备 hashmap 和 双向链表
    // hashmap key 存放键，value 存放链表节点
    // 在执行 get 或 put(更新操作)时候，把当前元素链接双向链表的尾节点
    // 执行 put(放新key)时，删除头节点，把新节点链接到双向链表的尾节点

    // notice 讲道理 单链表应该不行
    // 如 要更新节点位于链表的中间位置，单向链表应该很哪找到它的上一个节点

    static class LRUCache {

        public static class DoubleNode {
            int key;
            int val;
            DoubleNode last;
            DoubleNode next;

            public DoubleNode(int key, int val) {
                this.key = key;
                this.val = val;
            }
        }

        public static class NodeList {
            public DoubleNode head;
            public DoubleNode tail;

            public NodeList() {
                head = null;
                tail = null;
            }

            /**
             * 链表中加入节点
             *
             * @param node 新加入的节点
             */
            public void addNode(DoubleNode node) {
                if (node == null) {
                    return;
                }
                if (head == null) {
                    head = node;
                    tail = node;
                } else {
                    tail.next = node;
                    node.last = tail;
                    tail = node;
                }
            }

            /**
             * 把节点移动到尾部
             *
             * @param node 操作过的节点
             */
            public void moveNodeToTail(DoubleNode node) {
                if (tail == node) {
                    return;
                }
                if (head == node) {
                    head = node.next;
                    head.last = null;
                } else {
                    node.last.next = node.next;
                    node.next.last = node.last;
                }
                node.last = tail;
                node.next = null;
                tail.next = node;
                tail = node;
            }

            /**
             * 移除头节点
             *
             * @return 头节点
             */
            public DoubleNode removeHead() {
                if (head == null) {
                    return null;
                }
                DoubleNode ans = head;
                if (head == tail) {
                    head = null;
                    tail = null;

                } else {
                    head = ans.next;
                    ans.next = null;
                    head.last = null;
                }
                return ans;
            }
        }


        public Map<Integer, DoubleNode> map;
        public NodeList nodeList;
        public int size;


        public LRUCache(int capacity) {
            this.size = capacity;
            map = new HashMap<>();
            nodeList = new NodeList();
        }

        public int get(int key) {
            if (!map.containsKey(key)) {
                return -1;
            }
            DoubleNode node = map.get(key);
            nodeList.moveNodeToTail(node);
            return node.val;
        }

        public void put(int key, int value) {
            if (!map.containsKey(key)) {
                DoubleNode node = new DoubleNode(key, value);
                if (map.size() == size) {
                    DoubleNode removedNode = nodeList.removeHead();
                    map.remove(removedNode.key);
                }
                map.put(key, node);
                nodeList.addNode(node);
            } else {
                DoubleNode node = map.get(key);
                node.val = value;
                nodeList.moveNodeToTail(node);
            }
        }
    }

}
