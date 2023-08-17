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

        // public void delete(String word) {
        //     if (word == null) {
        //         return;
        //     }
        //     char[] chars = word.toCharArray();
        //     Node1 cur = root;
        //     if (cur.next[chars[0] - 'a'] == null) {
        //         return;
        //     }
        //     cur.pass--;
        //     for (int i = 0; i < chars.length; i++) {
        //         int path = chars[i] - 'a';
        //         if (--cur.next[path].pass == 0) {
        //             cur.next[path] = null;
        //             return;
        //         }
        //         cur = cur.next[path];
        //     }
        //     cur.end--;
        // }

        public void delete(String word) {
            if (search(word) == 0) {
                return;
            }
            char[] chars = word.toCharArray();
            Node1 cur = root;
            cur.pass--;
            for (int i = 0; i < chars.length; i++) {
                int path = chars[i] - 'a';
                if (--cur.next[path].pass == 0) {
                    cur.next[path] = null;
                    return;
                }
                cur = cur.next[path];
            }
            cur.end--;
        }

        // word这个单词之前加入过几次
        public int search(String word) {
            if (word == null) {
                return 0;
            }
            char[] chars = word.toCharArray();
            Node1 cur = root;
            for (int i = 0; i < chars.length; i++) {
                int path = chars[i] - 'a';
                if (cur.next[path] == null) {
                    return 0;
                }
                cur = cur.next[path];
            }
            return cur.end;
        }

        // 所有加入的字符串中，有几个是以ab这个字符串作为前缀的
        public int prefixNumber(String word) {
            // 不能像delete一样直接用search确认
            // 当插入"abc"时，若前缀为"a"，search为 0 直接返回了，但此时实际结果是 1
            if (word == null) {
                return 0;
            }
            char[] chars = word.toCharArray();
            Node1 cur = root;
            for (int i = 0; i < chars.length; i++) {
                int path = chars[i] - 'a';
                if (cur.next[path] == null) {
                    return 0;
                }
                cur = cur.next[path];
            }
            return cur.pass;
        }
    }


    public static void main(String[] args) {

        String word = "abc";
        Trie1 trie1 = new Trie1();

        System.out.println("========第一次插入========");
        trie1.insert(word);
        System.out.println(trie1.root.pass);
        System.out.println(trie1.root.next[0].pass);
        System.out.println(trie1.root.next[0].end);
        System.out.println(trie1.root.next[0].next[1].pass);
        System.out.println(trie1.root.next[0].next[1].end);
        System.out.println(trie1.root.next[0].next[1].next[2].pass);
        System.out.println(trie1.root.next[0].next[1].next[2].end);


        System.out.println("========第二次插入========");
        trie1.insert("abd");
        System.out.println(trie1.root.pass);
        System.out.println(trie1.root.next[0].pass);
        System.out.println(trie1.root.next[0].end);
        System.out.println(trie1.root.next[0].next[1].pass);
        System.out.println(trie1.root.next[0].next[1].end);
        System.out.println(trie1.root.next[0].next[1].next[3].pass);
        System.out.println(trie1.root.next[0].next[1].next[3].end);

        System.out.println("================================");
        System.out.println(trie1.search("abc"));
        System.out.println(trie1.prefixNumber("a"));
        // System.out.println("========第一次删除========");
        // trie1.delete("abd");
        // System.out.println(trie1.root.pass);
        // System.out.println(trie1.root.next[0].pass);
        // System.out.println(trie1.root.next[0].end);
        // System.out.println(trie1.root.next[0].next[1].pass);
        // System.out.println(trie1.root.next[0].next[1].end);
        // System.out.println(trie1.root.next[0].next[1].next[3].pass);
        // System.out.println(trie1.root.next[0].next[1].next[3].end);

    }
}
