package algorithm_journey.class035;

import java.util.HashMap;
import java.util.HashSet;

/**
 * 全 O(1) 的数据结构
 *
 * @author: Agony
 * @create: 2024/6/18 14:51
 * @describe: 请你设计一个用于存储字符串计数的数据结构，并能够返回计数最小和最大的字符串。
 * <p>
 * 实现 AllOne 类：
 * <p>
 * AllOne() 初始化数据结构的对象。
 * inc(String key) 字符串 key 的计数增加 1 。如果数据结构中尚不存在 key ，那么插入计数为 1 的 key 。
 * dec(String key) 字符串 key 的计数减少 1 。如果 key 的计数在减少后为 0 ，那么需要将这个 key 从数据结构中删除。测试用例保证：在减少计数前，key 存在于数据结构中。
 * getMaxKey() 返回任意一个计数最大的字符串。如果没有元素存在，返回一个空字符串 "" 。
 * getMinKey() 返回任意一个计数最小的字符串。如果没有元素存在，返回一个空字符串 "" 。
 * @link: <a href="https://leetcode.cn/problems/all-oone-data-structure/description/">全 O(1) 的数据结构</a>
 */
public class Code07_AllO1 {


    public static void main(String[] args) {
        //     输入
        // ["AllOne", "inc", "inc", "getMaxKey", "getMinKey", "inc", "getMaxKey", "getMinKey"]
        // [[], ["hello"], ["hello"], [], [], ["leet"], [], []]
        // 输出
        // [null, null, null, "hello", "hello", null, "hello", "leet"]

        AllOne ao = new AllOne();
        ao.inc("hello");
        ao.inc("hello");
        System.out.println(ao.getMaxKey());
        System.out.println(ao.getMinKey());
        ao.inc("leet");
        System.out.println(ao.getMaxKey());
        System.out.println(ao.getMinKey());

        ao = new AllOne();
        // ["AllOne","inc","inc","inc","inc","inc","dec","dec","getMaxKey","getMinKey"]
        //     [[],["a"],["b"],["b"],["b"],["b"],["b"],["b"],[],[]]
        ao.inc("a");
        ao.inc("b");
        ao.inc("b");
        ao.inc("b");
        ao.inc("b");
        ao.dec("b");
        ao.dec("b");

        System.out.println(ao.getMaxKey());
        System.out.println(ao.getMinKey());

    }


    // 全O(1)的数据结构
    // 双向链变和 hashmap 实现
    /*
        class Bucket {
			public HashSet<String> set;
			public int cnt;
			public Bucket last;
			public Bucket next;

			public Bucket(String s, int c) {
				set = new HashSet<>();
				set.add(s);
				cnt = c;
			}
		}
		hashmap<String, Bucket>
     */
    // 整条链变的头节点 head，存放 "", 出现次数为 0
    // 整条链变的尾节点 tail，存放 "", 出现次数为 Integer.MAX_VALUE
    // 加入操作
    // 看 hashmap 是否含有该元素
    // 1) 没有
    // 判断 head 的 next 节点的频次是否为 1，没有就新建，有的话就加入
    // 2) 含有
    // 判断 该节点后面有没有出现频次+1的桶，没有就新建，有就加入
    // 加入完成之后需要删掉之前出现频次桶中的该元素，
    // 相当于如果 "a"出现了8次，又添加了"a"，那我就把 8 次中的"a"删掉，加入到 9 次中的桶
    //
    // 删除操作 类似


    static class AllOne {

        /**
         * 桶结构
         */
        static class Bucket {

            // 记录该频次的字符串
            public HashSet<String> set;

            // 记录当前桶的出现频次
            public int cnt;

            public Bucket last;

            public Bucket next;

            public Bucket(String str, int cnt) {
                set = new HashSet<>();
                set.add(str);
                this.cnt = cnt;
            }
        }

        /**
         * 插入节点
         * 把next节点插入到cur节点后面
         *
         * @param cur  当前节点
         * @param next 被插入节点
         */
        public void insert(Bucket cur, Bucket next) {
            cur.next.last = next;
            next.next = cur.next;
            cur.next = next;
            next.last = cur;
        }

        /**
         * 移除节点
         * 把当前节点从链表中移除
         *
         * @param cur
         */
        public void remove(Bucket cur) {
            cur.last.next = cur.next;
            cur.next.last = cur.last;
        }

        public Bucket head;

        public Bucket tail;

        public HashMap<String, Bucket> map;


        public AllOne() {
            head = new Bucket("", 0);
            tail = new Bucket("", Integer.MAX_VALUE);
            head.next = tail;
            tail.last = head;
            map = new HashMap<>();
        }

        /**
         * 增加计数
         *
         * @param key
         */
        public void inc(String key) {
            // map 中没有key
            if (!map.containsKey(key)) {
                // head.next == tail -> 没有频次为1的桶不能这样写，因为有可能有频次为2的桶
                // 有频次为1的桶
                if (head.next.cnt == 1) {
                    head.next.set.add(key);
                    map.put(key, head.next);
                } else {
                    Bucket bucket = new Bucket(key, 1);
                    insert(head, bucket);
                    map.put(key, bucket);
                }

            } else {
                Bucket bucket = map.get(key);
                // 不能用 bucket.next == tail -> 可能没有下个频次的桶，但是有下下个频次的桶
                // 有下一个频次的桶
                if (bucket.cnt + 1 == bucket.next.cnt) {
                    map.put(key, bucket.next);
                    bucket.next.set.add(key);
                    bucket.set.remove(key);
                } else {
                    Bucket next = new Bucket(key, bucket.cnt + 1);
                    map.put(key, next);
                    insert(bucket, next);
                    bucket.set.remove(key);
                }
                if (bucket.set.isEmpty()) {
                    remove(bucket);
                }
            }
        }

        /**
         * 减少计数
         *
         * @param key
         */
        public void dec(String key) {
            if (!map.containsKey(key)) {
                return;
            }
            // 包含 key
            // key 在出现频次为1的桶
            Bucket bucket = map.get(key);
            if (bucket.cnt == 1) {
                map.remove(key);
            } else {
                // 有上一个频次的桶
                if (bucket.cnt == bucket.last.cnt + 1) {
                    map.put(key, bucket.last);
                    bucket.last.set.add(key);
                } else {
                    Bucket last = new Bucket(key, bucket.cnt - 1);
                    map.put(key, last);
                    insert(bucket.last, last);
                }
            }
            bucket.set.remove(key);
            if (bucket.set.isEmpty()) {
                remove(bucket);
            }
        }

        /**
         * 返回出现频次最大的字符串
         *
         * @return
         */
        public String getMaxKey() {
            return tail.last.set.iterator().next();
        }


        /**
         * 返回出现频次最少的字符串
         *
         * @return
         */
        public String getMinKey() {
            return head.next.set.iterator().next();
        }

    }
}
