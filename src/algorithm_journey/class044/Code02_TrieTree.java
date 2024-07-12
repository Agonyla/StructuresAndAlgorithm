package algorithm_journey.class044;

/**
 * 实现 Trie （前缀树） II
 *
 * @author: Agony
 * @create: 2024/7/10 10:05
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
public class Code02_TrieTree {


    // todo 其实是没问题的，但是leetcode运行好像有问题，不知道是不是没有重置的缘故，还是直接用牛客提交吧！！！


    public static void main(String[] args) {
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


        System.out.println("=============");
        //["Trie","countWordsEqualTo","countWordsStartingWith","countWordsStartingWith","countWordsEqualTo","insert","erase","countWordsStartingWith"]
        //[[],["keos"],["ke"],["keos"],["keos"],["keos"],["keos"],["keo"]]
        trie = new Trie();
        System.out.println(trie.countWordsEqualTo("keos"));
        System.out.println(trie.countWordsStartingWith("ke"));
        System.out.println(trie.countWordsStartingWith("keos"));
        System.out.println(trie.countWordsEqualTo("keos"));
        trie.insert("keos");
        trie.erase("keos");
        System.out.println(trie.countWordsStartingWith("keo"));
    }

    // 静态数组实现前缀树

    // 全局变量
    // int[][] tree -> 二维数组存放节点
    // int[] pass -> pass[i]表示经过节点i的单词数量
    // int[] end -> end[i]表示以节点i为结束的单词数量
    // int cnt -> 当前已使用的最大节点编号，初始化为 1


    // tree数组的结构解释
    //
    // 第一维：代表前缀树中的节点。每个节点用一个整数索引表示，这个索引在代码中通过cnt变量动态分配。
    // 第二维：代表26个英文小写字母（‘a’ 到 ‘z’）。数组的这一部分用于指向当前节点的子节点。
    //
    // 工作原理
    //
    // 每个节点可以视为一个数组，这个数组有26个可能的位置，每个位置对应一个英文字母。
    // 例如，tree[1][0]存储的是从根节点（假设为1）到子节点的链接，这个子节点通过字符’a’（‘a’ - ‘a’ = 0）到达。
    // 如果tree[1][0]的值为0，说明不存在通过字符’a’的子节点；
    // 如果它有一个非零值，比如5，那就意味着存在一个通过字符’a’到达的子节点，这个节点在前缀树中的索引为5。
    //
    // 举个例子
    //
    // 假设你要在前缀树中插入单词”cat”：
    // 	1.	从根节点开始（通常索引为1）。
    // 	2.	查找字符’c’对应的子节点，计算索引：‘c’ - ‘a’ = 2。
    // 	3.	如果tree[1][2]为0，说明还没有任何单词通过这个路径，那么你需要创建一个新的节点（假设为节点2），并设置tree[1][2] = 2。
    // 	4.	接着，移动到节点2，重复这个过程，直到单词的所有字符都被处理。


    /**
     * 静态数组实现
     */
    static class Trie {


        public static int MAX_LENGTH = 150001;

        public static int[][] tree = new int[MAX_LENGTH][26];

        public static int[] pass = new int[MAX_LENGTH];

        public static int[] end = new int[MAX_LENGTH];

        public static int cnt;


        public Trie() {
            cnt = 1;
        }

        /**
         * 将字符串 word 插入前缀树中
         *
         * @param word
         */
        public void insert(String word) {
            int cur = 1;
            pass[cur]++;
            for (int i = 0; i < word.length(); i++) {
                int path = word.charAt(i) - 'a';
                if (tree[cur][path] == 0) {
                    tree[cur][path] = ++cnt;
                }
                cur = tree[cur][path];
                pass[cur]++;
            }
            end[cur]++;
        }


        /**
         * 返回前缀树中字符串 word 的实例个数
         *
         * @param word
         * @return
         */
        public int countWordsEqualTo(String word) {
            int cur = 1;
            for (int i = 0; i < word.length(); i++) {
                int path = word.charAt(i) - 'a';
                if (tree[cur][path] == 0) {
                    return 0;
                }
                cur = tree[cur][path];
            }
            return end[cur];
        }


        /**
         * 返回前缀树中以 prefix 为前缀的字符串个数
         *
         * @param prefix
         * @return
         */
        public int countWordsStartingWith(String prefix) {

            int cur = 1;
            for (int i = 0; i < prefix.length(); i++) {
                int path = prefix.charAt(i) - 'a';
                if (tree[cur][path] == 0) {
                    return 0;
                }
                cur = tree[cur][path];
            }
            return pass[cur];

        }


        /**
         * 从前缀树中移除字符串 word
         *
         * @param word
         */
        public void erase(String word) {
            if (countWordsEqualTo(word) < 1) {
                return;
            }
            int cur = 1;
            for (int i = 0; i < word.length(); i++) {
                int path = word.charAt(i) - 'a';
                if (--pass[tree[cur][path]] == 0) {
                    tree[cur][path] = 0;
                    return;
                }
                cur = tree[cur][path];
            }
            end[cur]--;
        }
    }

}
