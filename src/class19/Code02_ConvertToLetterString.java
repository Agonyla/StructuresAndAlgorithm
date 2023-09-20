package class19;

/**
 * @Author Agony
 * @Create 2023/9/20 10:25
 * @Version 1.0
 * @describe: 规定1和A对应、2和B对应、3和C对应...26和Z对应
 * 那么一个数字字符串比如"111”就可以转化为:
 * "AAA"、"KA"和"AK"
 * 给定一个只有数字字符组成的字符串str，返回有多少种转化结果
 */
public class Code02_ConvertToLetterString {

    /**
     * @param str 字符串
     * @return 返回转换结果数量
     * @describe: 递归尝试
     */
    public static int number(String str) {
        // base case
        if (str == null || str.isEmpty()) {
            return 0;
        }
        return process(str.toCharArray(), 0);
    }

    /**
     * @param chars 需要转化的字符数组
     * @param index 当前所处位置
     * @return 转化结果数量
     */
    public static int process(char[] chars, int index) {
        // base case
        // index 到达chars最后面了，表示从头开始肯定有一种转化结果
        if (index == chars.length) {
            return 1;
        }
        if (chars[index] == '0') {
            return 0;
        }
        int p1 = process(chars, index + 1);
        int p2 = 0;
        if (index + 1 < chars.length && (chars[index] - '0') * 10 + (chars[index + 1] - '0') < 27) {
            p2 = process(chars, index + 2);
        }
        return p1 + p2;

    }

    /**
     * @param str 字符串数组
     * @return 返回转换结果数量
     * @describe: 动态规划
     */
    public static int dp(String str) {
        // base case
        if (str == null || str.isEmpty()) {
            return 0;
        }
        char[] chars = str.toCharArray();
        int N = chars.length;
        // index 范围：0~N
        int[] dp = new int[N + 1];
        dp[N] = 1;

        // 返回的是dp[0]，看递归时的依赖，index依赖index+1位置，所以要从右到左遍历
        for (int index = N - 1; index >= 0; index--) {

            if (chars[index] == '0') {
                dp[index] = 0;
                // 当递归转动态规划时，在递归中的if判断中的return放在动态规划中的for循环时，if判断中要加continue或者直接用if-else
            } else {
                int p1 = dp[index + 1];
                int p2 = 0;
                if (index + 1 < chars.length && (chars[index] - '0') * 10 + (chars[index + 1] - '0') < 27) {
                    p2 = dp[index + 2];
                }
                dp[index] = p1 + p2;
            }
        }
        return dp[0];
    }

    public static void main(String[] args) {
        System.out.println(number("7210231231232031203123"));
        System.out.println(dp("7210231231232031203123"));


    }
}
