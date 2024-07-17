package algorithm_journey.class045;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 单词搜索 II
 *
 * @author: Agony
 * @create: 2024/7/12 10:47
 * @describe: 给定一个 m x n 二维字符网格 board 和一个单词（字符串）列表 words， 返回所有二维网格上的单词 。
 * <p>
 * 单词必须按照字母顺序，通过 相邻的单元格 内的字母构成，其中“相邻”单元格是那些水平相邻或垂直相邻的单元格。同一个单元格内的字母在一个单词中不允许被重复使用。
 * @link: <a href="https://leetcode.cn/problems/word-search-ii/description/">单词搜索 II</a>
 */
public class Code03_WordSearchII {


    public static void main(String[] args) {
        char[][] board = {{'o', 'a', 'a', 'n' }, {'e', 't', 'a', 'e' }, {'i', 'h', 'k', 'r' }, {'i', 'f', 'l', 'v' }};

        String[] words = {"oath", "pea", "eat", "rain"};

        List<String> list = findWords(board, words);

        list.forEach(System.out::println);


        board = new char[][]{{'a', 'b' }, {'c', 'd' }};
        words = new String[]{"abcd"};
        list = findWords(board, words);
        list.forEach(System.out::println);
    }

    // 在二维字符数组中搜索可能的单词


    // 前缀树实现
    // 1.可以减少无效分支的筛选，网格中某一节点要上下左右尝试，在前缀树中，该节点如果只有到一个节点的路（假设是上），那么另外三个方向的尝试就没有必要了
    // 2.节点的end信息,可以记录来到该节点的字符串，记录到达该节点的变量，收集答案方便
    // 3.节点的pass信息，记录遇到过的字符。在返回的时候，收集到了多少字符，就用pass减去收集到字符的数量，
    // 以便于下次再递归的时候，遇到pass==0了，就直接返回，剪枝

    // int[] pass
    // String[] end

    // 设计函数 int dfs(char[][] board, int i, int j, int t)
    // return -> 收集到的单词个数
    // board -> 棋盘
    // i -> 当前来到第i行
    // j -> 当前来到第j列
    // t -> 前缀树的编号

    /**
     * 在二维字符数组中搜索可能的单词
     *
     * @param board
     * @param words
     * @return
     */
    public static List<String> findWords(char[][] board, String[] words) {

        build(words);
        ans = new ArrayList<>();
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                dfs(board, i, j, 1);
            }
        }
        clear();
        return ans;
    }


    public static List<String> ans;


    /**
     * 递归收集答案
     *
     * @param board 棋盘
     * @param i     第i行
     * @param j     第j列
     * @param t     前缀树节点编号
     * @return 收集到单词的个数
     */
    public static int dfs(char[][] board, int i, int j, int t) {

        // 越界  或者  走了已经走过的位置就直接返回(把已经走过的位置置为0)
        if (i < 0 || i == board.length || j < 0 || j == board[0].length || board[i][j] == 0) {
            return 0;
        }

        // 记录当前棋盘位置的值，再擦除之后需要还原
        char tmp = board[i][j];
        // 找到下一个前缀树节点
        t = tree[t][tmp - 'a'];


        // 在前缀树中没有通往该节点的路
        if (pass[t] == 0) {
            return 0;
        }

        // 收集到的字符串个数
        int res = 0;
        // 该节点有字符串，收集
        if (end[t] != null) {
            res++;
            ans.add(end[t]);
            end[t] = null;
        }

        board[i][j] = 0;
        res += dfs(board, i + 1, j, t);
        res += dfs(board, i - 1, j, t);
        res += dfs(board, i, j + 1, t);
        res += dfs(board, i, j - 1, t);
        pass[t] -= res;
        // 还原现场
        board[i][j] = tmp;
        return res;

    }


    public static int MAX_LENGTH = 10001;

    public static int[][] tree = new int[MAX_LENGTH][26];

    public static int[] pass = new int[MAX_LENGTH];

    public static String[] end = new String[MAX_LENGTH];

    public static int cnt;


    /**
     * 初始化前缀树
     *
     * @param words
     */
    public static void build(String[] words) {
        cnt = 1;
        for (String word : words) {
            insert(word);
        }
    }


    /**
     * 插入字符串
     *
     * @param s
     */
    public static void insert(String s) {
        int cur = 1;
        pass[cur]++;

        for (int i = 0; i < s.length(); i++) {
            int path = s.charAt(i) - 'a';
            if (tree[cur][path] == 0) {
                tree[cur][path] = ++cnt;
            }
            cur = tree[cur][path];
            pass[cur]++;
        }
        end[cur] = s;
    }


    /**
     * 清除前缀树
     */
    public static void clear() {
        for (int i = 1; i <= cnt; i++) {
            Arrays.fill(tree[i], 0);
            pass[i] = 0;
            end[i] = null;
        }
    }


}








