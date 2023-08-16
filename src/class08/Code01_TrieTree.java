package class08;

/**
 * @Author Agony
 * @Create 2023/8/16 21:27
 * @Version 1.0
 * 前缀树
 */
public class Code01_TrieTree {

    public static class Node1 {
        int pass;
        int end;
        Node1[] next;

        public Node1() {
            pass = 0;
            end = 0;
            // 假设只有26个小写字母
            next = new Node1[26];
        }
    }

    public static class Trie1 {
        public Node1 root;

        public Trie1() {
            this.root = new Node1();
        }

        // 向树中插入字符串
        public void insert(String word) {
            if (word == null) {
                return;
            }
            char[] chars = word.toCharArray();
            Node1 cur = root;
            cur.pass++;
            for (int i = 0; i < chars.length; i++) {
                int path = chars[i] - 'a';
                if (cur.next[path] == null) {
                    cur.next[path] = new Node1();
                }
                cur = cur.next[path];
                cur.pass++;
            }
            cur.end++;
        }
    }

    public static void main(String[] args) {

        String word = "abc";
        Trie1 trie1 = new Trie1();
        trie1.insert(word);
        System.out.println(trie1.root.pass);
        System.out.println("================");
        System.out.println(trie1.root.next[0].pass);
        System.out.println(trie1.root.next[0].end);
        System.out.println(trie1.root.next[0].next[1].pass);
        System.out.println(trie1.root.next[0].next[1].end);
        System.out.println(trie1.root.next[0].next[1].next[2].pass);
        System.out.println(trie1.root.next[0].next[1].next[2].end);
    }
}
