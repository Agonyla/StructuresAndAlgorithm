package algorithm_journey.class067;

/**
 * 单词搜索
 *
 * @author: Agony
 * @create: 2024/8/1 22:26
 * @describe: 给定一个 m x n 二维字符网格 board 和一个字符串单词 word 。如果 word 存在于网格中，返回 true ；否则，返回 false 。
 * <p>
 * 单词必须按照字母顺序，通过相邻的单元格内的字母构成，其中“相邻”单元格是那些水平相邻或垂直相邻的单元格。同一个单元格内的字母不允许被重复使用。
 * @link: <a href='https://leetcode.cn/problems/word-search/description/'>单词搜索</a>
 */
public class Code02_WordSearch {


    public static void main(String[] args) {
        char[][] board = {{'A', 'B', 'C', 'E'}, {'S', 'F', 'C', 'S'}, {'A', 'D', 'E', 'E'}};
        String word = "ABCCED";

        System.out.println(exist(board, word));

        board = new char[][]{{'A', 'B', 'C', 'E'}, {'S', 'F', 'C', 'S'}, {'A', 'D', 'E', 'E'}};
        word = "SEE";

        System.out.println(exist(board, word));
    }

    // 单词搜索（无法改成动态规划）
    //
    // 暴力递归
    // 整体调用，网格的每一个位置开始尝试，从该位置出发调用f函数能不能拼凑出单词
    // 设计 boolean f(char[][] board, int i, int j, char[] word, int k)
    // f函数实现从(i,j)位置出发上下左右走能不能拼凑出word
    // board -> 题目提供的网格
    // i, j -> 从（i，j）位置出发
    // word -> 需要拼凑的字符串转成的字符数组
    // k -> 从数组的第k位置开始
    // 注意⚠️
    // 在过程中走过的位置需要人为地删除，表示该位置已经走过了，下次不能再走了
    // 然后调用递归
    // 递归结束之后需要把该位置复原
    //
    // 不能改成动态规划
    // 不仅仅是i, j, k这三个值来决定返回值
    // 在过程中 board 会发生变化，所以这个board的状况也会导致返回值发生变化
    // 所以不能改成动态规划


    /**
     * 单词搜索 - 暴力递归
     *
     * @param board
     * @param word
     * @return
     */
    public static boolean exist(char[][] board, String word) {

        char[] target = word.toCharArray();

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (f(board, i, j, target, 0)) {
                    return true;
                }
            }
        }

        return false;
    }


    /**
     * 暴力递归
     *
     * @param board 棋盘
     * @param i     当前来到第i行
     * @param j     当前来到第j列
     * @param word  目标字符数组
     * @param k     目标数组的第k个开始
     * @return 能否从目标数组第k个位置开始拼出剩下的单词
     */
    public static boolean f(char[][] board, int i, int j, char[] word, int k) {

        if (k == word.length) {
            return true;
        }

        // 越界 || 当前位置不能拼 (要先判断越界)
        if (i == board.length || i < 0 || j == board[0].length || j < 0 || board[i][j] != word[k] || board[i][j] == 0) {
            return false;
        }

        // 擦除当前位置，避免再次走
        char tmp = board[i][j];
        board[i][j] = 0;

        boolean up = f(board, i - 1, j, word, k + 1);
        boolean down = f(board, i + 1, j, word, k + 1);
        boolean right = f(board, i, j + 1, word, k + 1);
        boolean left = f(board, i, j - 1, word, k + 1);

        board[i][j] = tmp;

        return up || down || right || left;
    }
}














