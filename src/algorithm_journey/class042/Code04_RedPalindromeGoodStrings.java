package algorithm_journey.class042;

/**
 * @author: Agony
 * @create: 2024/7/8 12:59
 * @describe:
 * @link:
 */
public class Code04_RedPalindromeGoodStrings {


    public static void main(String[] args) {
        System.out.println(goodString(1));
        System.out.println(goodString(2));
        System.out.println(goodString(3));


        for (int i = 0; i < 15; i++) {
            System.out.println(i + " : " + goodString(i));
        }

    }

    // 要求只有一个长度>=2的回文子串，求所有长度为n的red字符串中好串的数量

    // 可以用r、e、d三种字符拼接字符串，如果拼出来的字符串中
    // 有且仅有1个长度>=2的回文子串，那么这个字符串定义为"好串"

    // 返回长度为n的所有可能的字符串中，好串有多少个
    // 结果对1000000007取模， 1 <= n <= 10^9
    // 示例：
    // n = 1, 输出0
    // n = 2, 输出3
    // n = 3, 输出18
    // eee 就不是好串 因为其回文字串有两个


    // 思路
    // 先用暴力方法拼出所有字符串
    // 再遍历所有的字串去验证是否是好串


    /**
     * 返回 1～n中到好串数量
     *
     * @param n 字符串长度
     * @return 好串数量
     */
    public static int goodString(int n) {
        return f(new char[n], 0);
    }


    /**
     * 求好串数量
     * 暴力拼出所有字符串
     *
     * @param path 长度为n的字符数组
     * @param i    数组下标
     * @return 好串数量
     */
    public static int f(char[] path, int i) {

        if (i == path.length) {
            // todo 验证字符串是不是好串
            int cnt = 0;
            for (int j = 0; j < path.length; j++) {
                for (int k = j + 1; k < path.length; k++) {
                    if (isPalindrome(path, j, k)) {
                        cnt++;
                    }
                    if (cnt > 1) {
                        return 0;
                    }
                }
            }
            return cnt == 1 ? 1 : 0;
        }
        // 再每个位置尝试各种字符，然后去下一层遍历
        int ans = 0;
        path[i] = 'r';
        ans += f(path, i + 1);
        path[i] = 'e';
        ans += f(path, i + 1);
        path[i] = 'd';
        ans += f(path, i + 1);
        return ans;
    }


    /**
     * 判断字符串是否是回文
     *
     * @param path 字符数组
     * @param l    左下标
     * @param r    右下标
     * @return 是否是回文
     */
    public static boolean isPalindrome(char[] path, int l, int r) {

        // 可以直接写 l<r
        // 在奇数长度来到同一个位置必然相等
        // 可以少判断一次
        while (l < r) {
            if (path[l] != path[r]) {
                return false;
            }
            l++;
            r--;
        }
        return true;
    }


    /**
     * 规律：n>=4时
     * 数量 = (n+1) * 6
     *
     * @param n
     * @return
     */
    public static int goodStringBetter(int n) {
        if (n <= 1) {
            return 0;
        }
        if (n == 2) {
            return 3;
        }
        if (n == 3) {
            return 18;
        }
        return (n + 1) * 6 % 1000000007;

    }

}









