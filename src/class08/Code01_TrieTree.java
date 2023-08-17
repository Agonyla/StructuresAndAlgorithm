package class08;

import java.util.HashMap;

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


    // 用HashMap 来记录 节点的路径
    public static class Node2 {
        int pass;
        int end;

        HashMap<Integer, Node2> next;

        public Node2() {
            pass = 0;
            end = 0;
            next = new HashMap<>();
        }
    }

    public static class Trie2 {
        Node2 root;

        public Trie2() {
            root = new Node2();
        }

        public void insert(String word) {
            if (word == null) {
                return;
            }
            char[] chars = word.toCharArray();
            Node2 cur = root;
            cur.pass++;
            for (int i = 0; i < chars.length; i++) {
                int path = (int) chars[i];
                if (!cur.next.containsKey(path)) {
                    cur.next.put(path, new Node2());
                }
                cur = cur.next.get(path);
                cur.pass++;
            }
            cur.end++;
        }

        public void delete(String word) {
            if (search(word) == 0) {
                return;
            }
            char[] chars = word.toCharArray();
            Node2 cur = root;
            cur.pass--;
            for (int i = 0; i < chars.length; i++) {
                int path = chars[i];
                if (--cur.next.get(path).pass == 0) {
                    cur.next.remove(path);
                    return;
                }
                cur = cur.next.get(path);
            }
            cur.end--;
        }

        public int search(String word) {
            if (word == null) {
                return 0;
            }
            char[] chars = word.toCharArray();
            Node2 cur = root;
            for (int i = 0; i < chars.length; i++) {
                int path = chars[i];
                if (!cur.next.containsKey(path)) {
                    return 0;
                }
                cur = cur.next.get(path);
            }
            return cur.end;
        }

        public int prefixNumber(String word) {
            if (word == null) {
                return 0;
            }
            char[] chars = word.toCharArray();
            Node2 cur = root;
            for (int i = 0; i < chars.length; i++) {
                int path = chars[i];
                if (!cur.next.containsKey(path)) {
                    return 0;
                }
                cur = cur.next.get(path);
            }
            return cur.end;
        }
    }

    public static String generateRandomString(int strLen) {
        char[] chars = new char[(int) (Math.random() * strLen) + 1];
        for (int i = 0; i < chars.length; i++) {
            int value = (int) (Math.random() * 6);
            chars[i] = (char) (97 + value);
        }
        return String.valueOf(chars);
    }

    public static String[] generateRandomStringArr(int arrLen, int strLen) {
        String[] strings = new String[(int) (Math.random() * arrLen) + 1];
        for (int i = 0; i < strings.length; i++) {
            strings[i] = generateRandomString(strLen);
        }
        return strings;
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


        System.out.println("=================Trie2测试================");

        Trie2 trie2 = new Trie2();
        trie2.insert("abcd");

        System.out.println("=========================对数器=======================");

        int strLen = 5;
        int arrLen = 10;
        int testTimes = 10000;
        for (int i = 0; i < testTimes; i++) {
            String[] strings = generateRandomStringArr(arrLen, strLen);
            Trie1 trie11 = new Trie1();
            Trie2 trie22 = new Trie2();
            double random = Math.random();
            for (int j = 0; j < strings.length; j++) {
                if (random < 0.25) {
                    trie11.insert(strings[j]);
                    trie22.insert(strings[j]);
                } else if (random < 0.5) {
                    trie11.delete(strings[j]);
                    trie22.delete(strings[j]);
                } else if (random < 0.75) {
                    int res1 = trie11.search(strings[j]);
                    int res2 = trie22.search(strings[j]);
                    if (res1 != res2) {
                        System.out.println("Oops");
                    }
                } else {
                    int res1 = trie11.prefixNumber(strings[j]);
                    int res2 = trie22.prefixNumber(strings[j]);
                    if (res1 != res2) {
                        System.out.println("Oops");
                    }
                }
            }
        }
        System.out.println("test finish");

    }
}
