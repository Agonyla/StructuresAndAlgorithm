package algorithm_journey.class044;

import java.io.*;
import java.util.Arrays;

/**
 * 字典树的实现
 *
 * @author: Agony
 * @create: 2024/7/10 10:05
 * @describe: 字典树又称为前缀树或者Trie树，是处理字符串常用的数据结构。
 * 假设组成所有单词的字符仅是‘a’～‘z’，
 * 请实现字典树的结构，并包含以下四个主要的功能。
 * void insert(String word)：添加word，可重复添加；
 * void delete(String word)：删除word，如果word添加过多次，仅删除一次；
 * boolean search(String word)：查询word是否在字典树中出现过(完整的出现过，前缀式不算)；
 * int prefixNumber(String pre)：返回以字符串pre作为前缀的单词数量。
 * 现在给定一个m，表示有m次操作，每次操作都为以上四种操作之一。
 * 每次操作会给定一个整数op和一个字符串word，op代表一个操作码，
 * 如果op为1，则代表添加word，
 * op为2则代表删除word，
 * op为3则代表查询word是否在字典树中，
 * op为4代表返回以word为前缀的单词数量（数据保证不会删除不存在的word）。
 * @link: <a href="https://www.nowcoder.com/practice/7f8a8553ddbf4eaab749ec988726702b">字典树的实现</a>
 */
public class Code02_TrieTree {


    public static void main(String[] args) throws IOException {
        // Trie trie = new Trie();
        // trie.insert("apple");                                           // 插入 "apple"。
        // trie.insert("apple");                                           // 插入另一个 "apple"。
        // System.out.println(trie.search("apple"));                  // 有两个 "apple" 实例，所以返回 2。
        // System.out.println(trie.prefixNumber("app"));         // "app" 是 "apple" 的前缀，所以返回 2。
        // trie.delete("apple");                                            // 移除一个 "apple"。
        // System.out.println(trie.search("apple"));                  // 现在只有一个 "apple" 实例，所以返回 1。
        // System.out.println(trie.prefixNumber("app"));         // 返回 1
        // trie.delete("apple");                                            // 移除 "apple"。现在前缀树是空的。
        // System.out.println(trie.prefixNumber("app"));        // 返回 0
        //
        //
        // System.out.println("=============");
        // //["Trie","countWordsEqualTo","countWordsStartingWith","countWordsStartingWith","countWordsEqualTo","insert","erase","countWordsStartingWith"]
        // //[[],["keos"],["ke"],["keos"],["keos"],["keos"],["keos"],["keo"]]
        // trie = new Trie();
        // System.out.println(trie.search("keos"));
        // System.out.println(trie.prefixNumber("ke"));
        // System.out.println(trie.prefixNumber("keos"));
        // System.out.println(trie.search("keos"));
        // trie.insert("keos");
        // trie.delete("keos");
        // System.out.println(trie.prefixNumber("keo"));


        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));
        String line = null;
        while ((line = in.readLine()) != null) {
            build();
            m = Integer.parseInt(line);
            for (int i = 0; i < m; i++) {

                splits = in.readLine().split(" ");
                op = Integer.parseInt(splits[0]);
                // switch (op) {
                //     case 1:
                //         insert(splits[1]);
                //         break;
                //     case 2:
                //         delete(splits[1]);
                //         break;
                //     case 3:
                //         out.println(search(splits[1]) < 1 ? "NO" : "YES");
                //         break;
                //     case 4:
                //         out.println(prefixNumber(splits[1]));
                //         break;
                //
                // }
                switch (op) {
                    case 1 -> insert(splits[1]);
                    case 2 -> delete(splits[1]);
                    case 3 -> out.println(search(splits[1]) < 1 ? "NO" : "YES");
                    case 4 -> out.println(prefixNumber(splits[1]));
                }
            }
            clear();
        }
        out.flush();
        in.close();
        out.close();
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

    public static int m, op;

    public static String[] splits;

    public static int MAX_LENGTH = 150001;

    public static int[][] tree = new int[MAX_LENGTH][26];

    public static int[] pass = new int[MAX_LENGTH];

    public static int[] end = new int[MAX_LENGTH];

    public static int cnt;


    public static void build() {
        cnt = 1;
    }

    /**
     * 将字符串 word 插入前缀树中
     *
     * @param word
     */
    public static void insert(String word) {
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
    public static int search(String word) {
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
    public static int prefixNumber(String prefix) {

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
    public static void delete(String word) {
        if (search(word) < 1) {
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


    /**
     * 前缀树复原
     */
    public static void clear() {
        for (int i = 1; i <= cnt; i++) {
            Arrays.fill(tree[i], 0);
            pass[i] = 0;
            end[i] = 0;
        }

    }


}
