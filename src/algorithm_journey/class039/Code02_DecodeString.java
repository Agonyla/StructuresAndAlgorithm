package algorithm_journey.class039;

/**
 * 字符串解码
 *
 * @author: Agony
 * @create: 2024/7/3 09:45
 * @describe: 给定一个经过编码的字符串，返回它解码后的字符串。
 * <p>
 * 编码规则为: k[encoded_string]，表示其中方括号内部的 encoded_string 正好重复 k 次。注意 k 保证为正整数。
 * <p>
 * 你可以认为输入字符串总是有效的；输入字符串中没有额外的空格，且输入的方括号总是符合格式要求的。
 * <p>
 * 此外，你可以认为原始数据不包含数字，所有的数字只表示重复的次数 k ，例如不会出现像 3a 或 2[4] 的输入。
 * 输入：s = "2[abc]3[cd]ef"
 * 输出："abcabccdcdcdef
 * @link: <a href="https://leetcode.cn/problems/decode-string/description/">字符串解码</a>
 */
public class Code02_DecodeString {


    public static void main(String[] args) {
        String s = "3[a2[c]]";
        System.out.println(decodeString(s));

    }

    // 含有嵌套的字符串解码

    // 思路
    // 实现一个函数 String f(char[] str, int index)，全局变量 int where
    // return -> 解码后的字符串
    // str -> 字符数组
    // index -> 下标位置
    // 函数内部局部变量 StringBuilder path -> 路径，int count -> 出现的数字
    // 当在 j 位置遇到 '[' 时，调用 f(j+1)，把下一层的返回和 count 相乘(往 path 中添加 count 次数)，count清空。
    // 当在 k 位置遇到 ']' 时，将 where 更新成 k，并将改层的 path 返回。
    // 上层从 where + 1 处继续。


    /**
     * 含有嵌套的字符串解码
     *
     * @param s 字符串
     * @return 解码后的字符串
     */
    public static String decodeString(String s) {

        char[] str = s.toCharArray();
        where = 0;
        return f(str, 0);
    }


    public static int where;

    /**
     * 解码字符串
     *
     * @param str 字符串转成的字符数组
     * @param i   数组下标
     * @return 解码后的字符串
     */
    public static String f(char[] str, int i) {

        StringBuilder sb = new StringBuilder();
        // 出现的次数
        int count = 0;

        while (i < str.length && str[i] != ']') {

            // 遇到数字
            if (str[i] >= '0' && str[i] <= '9') {
                count = count * 10 + str[i++] - '0';
            } else if (str[i] >= 'a' && str[i] <= 'z') {
                // 遇到字母
                sb.append(str[i++]);
            } else {
                // 遇到 '['
                // 调用下一层，返回值往 sb 里添加, count 为重复的次数
                sb.append(addToSB(count, f(str, i + 1)));
                i = where + 1;
                count = 0;
            }
        }
        where = i;
        return sb.toString();
    }


    /**
     * 字符串重复添加
     *
     * @param count 重复次数
     * @param str   字符串
     * @return 处理后的字符串
     */
    public static String addToSB(int count, String str) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < count; i++) {
            sb.append(str);
        }
        return sb.toString();
    }
}






















