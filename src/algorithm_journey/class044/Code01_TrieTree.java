package algorithm_journey.class044;

import java.util.HashMap;

/**
 * 实现 Trie （前缀树） II
 *
 * @author: Agony
 * @create: 2024/7/10 09:05
 * @describe: 前缀树（trie ，发音为 "try"）是一个树状的数据结构，用于高效地存储和检索一系列字符串的前缀。前缀树有许多应用，如自动补全和拼写检查。
 * <p>
 * 实现前缀树 Trie 类：
 * <p>
 * Trie() 初始化前缀树对象。
 * void insert(String word) 将字符串 word 插入前缀树中。
 * int countWordsEqualTo(String word) 返回前缀树中字符串 word 的实例个数。
 * int countWordsStartingWith(String prefix) 返回前缀树中以 prefix 为前缀的字符串个数。
 * void erase(String word) 从前缀树中移除字符串 word 。
 * @link: <a href="https://leetcode.cn/problems/implement-trie-ii-prefix-tree/description/">实现 Trie （前缀树） II</a>
 */
public class Code01_TrieTree {


    public static void main(String[] args) {

        System.out.println('A' - 'a');
        System.out.println('Z' - 'a');

        int a = 1;
        if (a-- == 0) {
            System.out.println("asadf");
        }

        String word = "abc";
        System.out.println(word.charAt(0) - 'a');
        System.out.println(word.charAt(1) - 'a');
        System.out.println(word.charAt(2) - 'a');


        System.out.println("------------");

        Trie trie = new Trie();
        trie.insert("apple");                                           // 插入 "apple"。
        trie.insert("apple");                                           // 插入另一个 "apple"。
        System.out.println(trie.countWordsEqualTo("apple"));                  // 有两个 "apple" 实例，所以返回 2。
        System.out.println(trie.countWordsStartingWith("app"));         // "app" 是 "apple" 的前缀，所以返回 2。
        trie.erase("apple");                                            // 移除一个 "apple"。
        System.out.println(trie.countWordsEqualTo("apple"));                  // 现在只有一个 "apple" 实例，所以返回 1。
        System.out.println(trie.countWordsStartingWith("app"));         // 返回 1
        trie.erase("apple");                                            // 移除 "apple"。现在前缀树是空的。
        System.out.println(trie.countWordsStartingWith("app"));        // 返回 0


    }


    // 用类描述实现前缀树

    // 每个样本 都从头节点开始 根据 前缀字符或者前缀数字 建出来的一棵大树，就是前缀树
    // 没有路就新建节点；已经有路了，就复用节点。
    // 根据"abc"每一个字符新建一条路。
    // o --a-- o --b-- o --c-- o
    // 注意！每个字符表示的是路，不是节点
    // 可以通过给节点添加信息
    // pass -> 表示经过的字符串个数
    // end -> 表示有几个字符串是在该节点处结束的


    /**
     * 路径由数组实现
     */
    static class Trie {

        static class TrieNode {

            int pass;
            int end;
            TrieNode[] nexts;

            public TrieNode() {
                this.pass = 0;
                this.end = 0;
                nexts = new TrieNode[26];
            }

            @Override
            public String toString() {
                return "pass: " + pass + " end: " + end;
            }
        }


        public TrieNode head;


        public Trie() {
            head = new TrieNode();
        }

        /**
         * 将字符串 word 插入前缀树中
         *
         * @param word 字符串
         */
        public void insert(String word) {

            TrieNode cur = head;
            cur.pass++;
            for (int i = 0; i < word.length(); i++) {
                int path = word.charAt(i) - 'a';
                if (cur.nexts[path] == null) {
                    cur.nexts[path] = new TrieNode();
                }
                cur = cur.nexts[path];
                cur.pass++;
            }
            cur.end++;
        }


        /**
         * 返回前缀树中字符串 word 的实例个数
         *
         * @param word 字符串
         * @return 字符串出现个数
         */
        public int countWordsEqualTo(String word) {
            TrieNode cur = head;
            for (int i = 0; i < word.length(); i++) {
                int path = word.charAt(i) - 'a';
                if (cur.nexts[path] == null) {
                    return 0;
                }
                cur = cur.nexts[path];
            }
            return cur.end;
        }


        /**
         * 返回前缀树中以 prefix 为前缀的字符串个数
         *
         * @param prefix 字符串前缀
         * @return 字符串前缀出现个数
         */
        public int countWordsStartingWith(String prefix) {

            TrieNode cur = head;
            for (int i = 0; i < prefix.length(); i++) {
                int path = prefix.charAt(i) - 'a';
                if (cur.nexts[path] == null) {
                    return 0;
                }
                cur = cur.nexts[path];
            }
            return cur.pass;
        }


        /**
         * 从前缀树中移除字符串 word
         *
         * @param word 字符串
         */
        public void erase(String word) {

            if (countWordsEqualTo(word) < 1) {
                return;
            }

            TrieNode cur = head;
            cur.pass--;

            for (int i = 0; i < word.length(); i++) {
                int path = word.charAt(i) - 'a';
                // pass在这里已经减过了
                if (--cur.nexts[path].pass == 0) {
                    cur.nexts[path] = null;
                    return;
                }
                cur = cur.nexts[path];
            }
            cur.end--;
        }
    }


    /**
     * 路由hashmap实现
     */
    static class Trie2 {


        static class TrieNode {
            int pass;
            int end;
            HashMap<Integer, TrieNode> map;

            public TrieNode() {
                pass = 0;
                end = 0;
                map = new HashMap<>();
            }
        }

        TrieNode head;

        public Trie2() {
            head = new TrieNode();
        }


        public void insert(String word) {
            TrieNode cur = head;
            head.pass++;
            for (int i = 0; i < word.length(); i++) {
                int path = word.charAt(i) - 'a';
                if (!cur.map.containsKey(path)) {
                    cur.map.put(path, new TrieNode());
                }
                cur = cur.map.get(path);
                cur.pass++;
            }
            cur.end++;
        }

        public int countWordsEqualTo(String word) {
            TrieNode cur = head;
            for (int i = 0; i < word.length(); i++) {
                int path = word.charAt(i) - 'a';
                if (!cur.map.containsKey(path)) {
                    return 0;
                }
                cur = cur.map.get(path);
            }
            return cur.end;
        }

        public int countWordsStartingWith(String prefix) {
            TrieNode cur = head;
            for (int i = 0; i < prefix.length(); i++) {
                int path = prefix.charAt(i) - 'a';
                if (!cur.map.containsKey(path)) {
                    return 0;
                }
                cur = cur.map.get(path);
            }
            return cur.pass;
        }

        public void erase(String word) {
            if (countWordsEqualTo(word) < 1) {
                return;
            }
            TrieNode cur = head;
            cur.pass--;
            for (int i = 0; i < word.length(); i++) {
                int path = word.charAt(i) - 'a';
                if (--cur.map.get(path).pass == 0) {
                    cur.map.remove(path);
                    return;
                }
                cur = cur.map.get(path);
            }
            cur.end--;
        }
    }
}
