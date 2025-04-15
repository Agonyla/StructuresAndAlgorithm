package algorithm_journey.class068;

/**
 * 编辑距离
 *
 * @author: Agony
 * @create: 2024/9/5 15:03
 * @describe: 给你两个单词 word1 和 word2， 请返回将 word1 转换成 word2 所使用的最少操作数  。
 * <p>
 * 你可以对一个单词进行如下三种操作：
 * <p>
 * 插入一个字符
 * 删除一个字符
 * 替换一个字符
 * @link: <a href="https://leetcode.cn/problems/edit-distance/description/"> 编辑距离</a>
 */
public class Code02_EditDistance {


    // 编辑距离
    //
    // 暴力递归 (试一下)❕❕❕
    //
    // 动态规划
    // 设计 int[][] dp = new int[][]
    // dp[i][j] 表示 s1[0...i-1] 前i个组成的前缀串 彻底变成s2[0...j-1]个取前j个字符串 所需要的最小代价
    // 可能性分析：
    // 1️⃣. s1[i-1]参与
    // 1.1 s1[i-1]参与，变成s2[j-1]
    // 1.1.1 s1[i-1]==s2[j-1]
    // -> 不需要任何代价，直接就是dp[i-1][j-1] (s1[0...i-2] 变成 s2[0...i-2])
    // 1.1.2 s1[i-1]!=s2[j-1]
    // -> dp[i-1][j-1] + 替换代价
    // 1.2 s1[i-1]参与，通过让s1[0...i-1]变成s2[0...j-2]，然后再插入s2[j-1]
    // -> dp[i][j-1] + 插入代价
    // 2️⃣. s1[i-1]不参与
    // 让s1[0...i-2]变成s2[0...j-1]，然后删除s1[i-1]
    // -> dp[i-1][j] + 删除代价
    // 可能性分析优化
    // 从上述几种可能性中，显然1.1.1 可能是是最好的，
    // 所以当s1[i-1]==s2[j-1]时，就直接调用
    // s1[i-1]!=s2[j-1]时，再去考虑另外的情况
    // 直接用if-else分成两大类
    // 依赖分析：
    // 来到(i,j)依赖于 (i-1, j-1) (i, j-1) (i-1, j), 依赖左边、上边和左上的值
    // 填表顺序：
    // 从左往右，从上往下
    // 特殊位置分析：
    // 第0行，s1一个字符都没有 -> 每一列都是插入代价，每一列的插入代价 = s2的字符个数 * 插入代价
    // 第0列，s2一个字符都没有 -> 每一行都是删除代价，每一行的删除代价 = s1的字符个数 * 删除代价
    //
    // 动态规划 + 空间压缩
    // 一维代替二维
    // 类似于上一节的Code03_LongestCommonSubsequence
    // 涉及到依赖三个位置，在使用空间压缩的时候，可以考虑是不是需要额外引入一个（实际是两个）变量来实现


    public static void main(String[] args) {

        // zikwvkijajpkaicihcffiemzexmwjjlyrylxcuoewdmpivudhmgkuodjaurazdjnlgtpwz
        // wpnmubqfsnmapqpufmmsphqehjplwjkqspnnpywsvvjilxbcfsrygbelquaalenvkruyltiwqcpdrxgstywaja

        // 3
        String word1 = "horse";
        String word2 = "ros";
        System.out.println(minDistance1(word1, word2));
        System.out.println(minDistance2(word1, word2));
        System.out.println(minDistance3(word1, word2));
        System.out.println(minDistance4(word1, word2));
        System.out.println(minDistance5(word1, word2));


        // 5
        word1 = "intention";
        word2 = "execution";
        System.out.println(minDistance1(word1, word2));
        System.out.println(minDistance2(word1, word2));
        System.out.println(minDistance3(word1, word2));
        System.out.println(minDistance4(word1, word2));
        System.out.println(minDistance5(word1, word2));

        // 73
        word1 = "zikwvkijajpkaicihcffiemzexmwjjlyrylxcuoewdmpivudhmgkuodjaurazdjnlgtpwz";
        word2 = "wpnmubqfsnmapqpufmmsphqehjplwjkqspnnpywsvvjilxbcfsrygbelquaalenvkruyltiwqcpdrxgstywaja";
        // System.out.println(minDistance1(word1, word2));
        System.out.println(minDistance2(word1, word2));
        System.out.println(minDistance3(word1, word2));
        System.out.println(minDistance4(word1, word2));
        System.out.println(minDistance5(word1, word2));
    }

    /**
     * 编辑距离 - 暴力递归
     *
     * @param word1
     * @param word2
     * @return
     */
    public static int minDistance1(String word1, String word2) {


        return f1(word1.toCharArray(), word2.toCharArray(), word1.length(), word2.length(), 1, 1, 1);
    }


    /**
     * 暴力递归
     *
     * @param s1 字符数组s1
     * @param s2 字符数组s2
     * @param i  s1前i个字符
     * @param j  s2 前j个字符
     * @param a  插入一个字符的代价
     * @param b  删除一个字符的代价
     * @param c  替换一个字符的代价
     * @return 把s1转换成s2花费的最小代价
     */
    public static int f1(char[] s1, char[] s2, int i, int j, int a, int b, int c) {

        if (i == 0) {
            return j * a;
        }
        if (j == 0) {
            return i * b;
        }

        int p1 = Integer.MAX_VALUE;
        int p2 = Integer.MAX_VALUE;
        // 可能性1：如果最后一个字符一样，直接让调用
        if (s1[i - 1] == s2[j - 1]) {
            p1 = f1(s1, s2, i - 1, j - 1, a, b, c);
        } else {
            // 可能性2：最后一个字符不一样，s1最后一个字符替换成s2最后一个
            p2 = f1(s1, s2, i - 1, j - 1, a, b, c) + c;
        }

        // 可能性3: s1[0...i-1]去匹配s2[0...j-2],然后插入s2最后一个
        int p3 = f1(s1, s2, i, j - 1, a, b, c) + a;
        // 可能性4：让s1[0...i-2]去匹配s2[0...j-1],然后删掉s1最后一个
        int p4 = f1(s1, s2, i - 1, j, a, b, c) + b;
        return Math.min(Math.min(p1, p2), Math.min(p3, p4));
    }


    /**
     * 编辑距离 - 记忆化搜索
     *
     * @param word1
     * @param word2
     * @return
     */
    public static int minDistance2(String word1, String word2) {

        char[] s1 = word1.toCharArray();
        char[] s2 = word2.toCharArray();

        int len1 = word1.length();
        int len2 = word2.length();

        int[][] dp = new int[len1 + 1][len2 + 1];
        for (int i = 0; i <= len1; i++) {
            for (int j = 0; j <= len2; j++) {
                dp[i][j] = -1;
            }
        }

        return f2(s1, s2, len1, len2, 1, 1, 1, dp);
    }


    /**
     * 记忆化搜索
     *
     * @param s1
     * @param s2
     * @param i
     * @param j
     * @param a
     * @param b
     * @param c
     * @param dp
     * @return
     */
    public static int f2(char[] s1, char[] s2, int i, int j, int a, int b, int c, int[][] dp) {


        if (i == 0) {
            return j * a;
        }
        if (j == 0) {
            return i * b;
        }
        if (dp[i][j] != -1) {
            return dp[i][j];
        }

        int p1 = Integer.MAX_VALUE;
        int p2 = Integer.MAX_VALUE;
        // 可能性1：如果最后一个字符一样，直接让调用
        if (s1[i - 1] == s2[j - 1]) {
            p1 = f2(s1, s2, i - 1, j - 1, a, b, c, dp);
        } else {
            // 可能性2：最后一个字符不一样，s1最后一个字符替换成s2最后一个
            p2 = f2(s1, s2, i - 1, j - 1, a, b, c, dp) + c;
        }

        // 可能性3: s1[0...i-1]去匹配s2[0...j-2],然后插入s2最后一个
        int p3 = f2(s1, s2, i, j - 1, a, b, c, dp) + a;
        // 可能性4：让s1[0...i-2]去匹配s2[0...j-1],然后删掉s1最后一个
        int p4 = f2(s1, s2, i - 1, j, a, b, c, dp) + b;

        dp[i][j] = Math.min(Math.min(p1, p2), Math.min(p3, p4));
        return dp[i][j];
    }


    /**
     * 编辑距离 - 动态规划
     *
     * @param word1
     * @param word2
     * @return
     */
    public static int minDistance3(String word1, String word2) {

        char[] s1 = word1.toCharArray();
        char[] s2 = word2.toCharArray();
        int len1 = word1.length();
        int len2 = word2.length();

        int a = 1;
        int b = 1;
        int c = 1;

        int[][] dp = new int[len1 + 1][len2 + 1];

        for (int i = 0; i <= len1; i++) {
            dp[i][0] = i * b;
        }
        for (int j = 0; j <= len2; j++) {
            dp[0][j] = j * a;
        }

        for (int i = 1; i <= len1; i++) {
            for (int j = 1; j <= len2; j++) {

                int p1 = Integer.MAX_VALUE;
                int p2 = Integer.MAX_VALUE;
                if (s1[i - 1] == s2[j - 1]) {
                    p1 = dp[i - 1][j - 1];
                } else {
                    p2 = dp[i - 1][j - 1] + c;
                }
                int p3 = dp[i][j - 1] + a;
                int p4 = dp[i - 1][j] + b;
                dp[i][j] = Math.min(Math.min(p1, p2), Math.min(p3, p4));
            }
        }

        return dp[len1][len2];
    }


    /**
     * 编辑距离 - 动态规划 + 空间压缩
     *
     * @param word1
     * @param word2
     * @return
     */
    public static int minDistance4(String word1, String word2) {


        char[] s1 = word1.toCharArray();
        char[] s2 = word2.toCharArray();
        int len1 = word1.length();
        int len2 = word2.length();

        int a = 1;
        int b = 1;
        int c = 1;

        // int[][] dp = new int[len1 + 1][len2 + 1];
        int[] dp = new int[len2 + 1];

        for (int j = 0; j <= len2; j++) {
            dp[j] = j * a;
        }

        int leftUp = 0;
        int backup = 0;

        for (int i = 1; i <= len1; i++) {

            leftUp = dp[0];
            dp[0] = i * b;
            for (int j = 1; j <= len2; j++) {
                backup = dp[j];
                int p1 = Integer.MAX_VALUE;
                int p2 = Integer.MAX_VALUE;
                if (s1[i - 1] == s2[j - 1]) {
                    p1 = leftUp;
                } else {
                    p2 = leftUp + c;
                }
                int p3 = dp[j] + a;
                int p4 = dp[j - 1] + b;
                dp[j] = Math.min(Math.min(p1, p2), Math.min(p3, p4));
                leftUp = backup;
            }
        }
        return dp[len2];
    }


    /**
     * 小优化
     *
     * @param word1
     * @param word2
     * @return
     */
    public static int minDistance5(String word1, String word2) {


        char[] s1 = word1.toCharArray();
        char[] s2 = word2.toCharArray();
        int len1 = word1.length();
        int len2 = word2.length();

        int a = 1;
        int b = 1;
        int c = 1;

        int[] dp = new int[len2 + 1];

        for (int j = 0; j <= len2; j++) {
            dp[j] = j * a;
        }

        int leftUp = 0;
        int backup = 0;

        for (int i = 1; i <= len1; i++) {

            leftUp = dp[0];
            dp[0] = i * b;
            for (int j = 1; j <= len2; j++) {
                backup = dp[j];

                // 可分为两种大情况，最后一个字符是否一致，显然如果是一样的话代价肯定是最小的
                if (s1[i - 1] == s2[j - 1]) {
                    dp[j] = leftUp;
                } else {
                    dp[j] = Math.min(Math.min(dp[j] + a, dp[j - 1] + b), leftUp + c);
                }
                leftUp = backup;
            }
        }
        return dp[len2];
    }
}















